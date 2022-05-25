package dk.sdu.mmmi.cbse.osgiboss;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
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
    private TmxMapLoader loader = new NavTmxMapLoader();
    private NavigationTiledMapLayer navLayer;
    private Pathfinder pathfinder;
    private float maxSpeed = 100;
    boolean second = false;


    private final int LEAST_DISTANCE = 10;
    private final int pixelToTile = 32;
    List<GridCell> path;

    @Override
    public void process(GameData gameData, World world) {


        if (firstRun) {
            firstRun = false;
            TiledMap map = loader.load(AssetLoader.getAssetPath(assetPath, "finalmap.tmx"));
            navLayer = (NavigationTiledMapLayer) map.getLayers().get("navigation");
            navLayer.setHeight(30);
            navLayer.setWidth(360);
            pathfinder = new Pathfinder(navLayer);
        }
        Entity player = world.getEntities(Player.class).get(0);
        PositionPart playerPos = player.getPart(PositionPart.class);

        List<Entity> bossList = world.getEntities(Boss.class);


        if (!bossList.isEmpty()) {

            Entity boss = bossList.get(0);
            PositionPart positionPart = boss.getPart(PositionPart.class);
            LifePart lifePart = boss.getPart(LifePart.class);

            if (path == null) {
                System.out.println(" new path");
                path = pathfinder.findPath(navLayer.getCell((int) positionPart.getX() / pixelToTile, (int) positionPart.getY() / pixelToTile), navLayer.getCell((int) Math.round(playerPos.getX() / pixelToTile), (int) Math.round(playerPos.getY() / pixelToTile)));
                for (GridCell node : path) {
                    System.out.println(node.getX() + " " + node.getY() + " Gscore: " + node.getG() + " Hscore: " + node.getH());

                }
            }


            GridCell node = path.get(0);


            double distance = Math.sqrt(Math.pow(positionPart.getX() - node.getX() * pixelToTile, 2) + Math.pow(positionPart.getY() - node.getY() * pixelToTile, 2));
            if (distance <= LEAST_DISTANCE) {
                path.remove(0);
                if (path.isEmpty()) {
                    path = null;
                }
                System.out.println("Path removed: ");
            }

            if (positionPart.getX() < node.getX() * pixelToTile) {
                positionPart.setX(positionPart.getX() + maxSpeed * gameData.getDelta());
            }
            if (positionPart.getX() > node.getX() * pixelToTile) {
                positionPart.setX(positionPart.getX() - maxSpeed * gameData.getDelta());
            }
            if (positionPart.getY() < node.getY() * pixelToTile) {
                positionPart.setY(positionPart.getY() + maxSpeed * gameData.getDelta());
            }
            if (positionPart.getY() > node.getY() * pixelToTile) {
                positionPart.setY(positionPart.getY() - maxSpeed * gameData.getDelta());
            }

            positionPart.process(gameData, boss);
            lifePart.process(gameData, boss);

        }

    }
}










