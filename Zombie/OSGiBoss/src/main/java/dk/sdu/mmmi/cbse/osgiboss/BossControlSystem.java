package dk.sdu.mmmi.cbse.osgiboss;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EnemyMovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.player.Player;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.osgiboss.AStar.GridCell;
import dk.sdu.mmmi.cbse.osgiboss.AStar.NavTmxMapLoader;
import dk.sdu.mmmi.cbse.osgiboss.AStar.NavigationTiledMapLayer;
import dk.sdu.mmmi.cbse.osgiboss.AStar.Pathfinder;

import java.util.List;

public class BossControlSystem implements IEntityProcessingService {

    private static final String commonAssetPath = "\\Zombie\\OSGiCommon\\src\\main\\resources\\Assets\\";
    private String assetPath = AssetLoader.whichOS(commonAssetPath);

    // Navigation stuff
    private Boolean firstRun = true;
    private TiledMap map;
    private TmxMapLoader loader = new NavTmxMapLoader();
    private NavigationTiledMapLayer navLayer;
    private Pathfinder pathfinder;
    private boolean isRunning;
    private int LEAST_DISTANCE = 16;
    private int pixelToTile = 32;
    List<GridCell> path;

    @Override
    public void process(GameData gameData, World world) {


        if (firstRun) {
            firstRun = false;
            map = loader.load(AssetLoader.getAssetPath(assetPath, "finalmap.tmx"));
            navLayer = (NavigationTiledMapLayer) map.getLayers().get("navigation");
            navLayer.setHeight(30);
            navLayer.setWidth(360);
            pathfinder = new Pathfinder(navLayer);
        }

        Entity player = world.getEntities(Player.class).get(0);
        PositionPart playerPos = player.getPart(PositionPart.class);

        for (Entity ent : world.getEntities(Boss.class)) {
            PositionPart positionPart = ent.getPart(PositionPart.class);
            LifePart lifePart = ent.getPart(LifePart.class);
            EnemyMovingPart movingPart = ent.getPart(EnemyMovingPart.class);


            if(path == null) {
                path = pathfinder.findPath(navLayer.getCell((int) positionPart.getX() / pixelToTile, (int) positionPart.getY() / pixelToTile), navLayer.getCell((int) playerPos.getX()/pixelToTile, (int)playerPos.getY()/pixelToTile));
            }
            GridCell node = path.get(0);
            double distance = Math.sqrt(Math.pow(positionPart.getX() - node.getX() * pixelToTile, 2) + Math.pow(positionPart.getY() - node.getY() * pixelToTile, 2));

            if (distance <= LEAST_DISTANCE) {
                path.get(0);
                path.remove(0);
                if (path.isEmpty()){
                    path = null;
                }
                System.out.println("Path removed: ");
            }
            System.out.println("Enemy Position X: " + positionPart.getX()/pixelToTile);
            System.out.println("Node Position X: " + node.getX());
            System.out.println("Node Position Y: " + node.getY());
            System.out.println("Enemy Position Y: " + node.getY());

            if (positionPart.getX()/pixelToTile < node.getX()) {
                movingPart.setRight(true);
                movingPart.setLeft(false);
                System.out.println("Right");
            }
            if (positionPart.getX()/pixelToTile > node.getX()) {
                movingPart.setLeft(true);
                movingPart.setRight(false);
                System.out.println("Left");
            }
            if (positionPart.getY()/pixelToTile < node.getY()) {
                movingPart.setUp(true);
                movingPart.setDown(false);
                System.out.println("UP");
            }
            if (positionPart.getY()/pixelToTile > node.getY()) {
                movingPart.setDown(true);
                movingPart.setUp(false);
                System.out.println("Down");
            }

/*            if (path.isEmpty()) {
                movingPart.setLeft(false);
                movingPart.setRight(false);
                movingPart.setUp(false);
                movingPart.setDown(false);
                System.out.println("Empty");
            }*/

            positionPart.process(gameData, ent);
            movingPart.process(gameData, ent);
            lifePart.process(gameData, ent);

        }


    }
}









