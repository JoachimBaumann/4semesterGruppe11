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

        for (Entity ent : world.getEntities(Boss.class)) {
            PositionPart positionPart = ent.getPart(PositionPart.class);
            LifePart lifePart = ent.getPart(LifePart.class);
            EnemyMovingPart movingPart = ent.getPart(EnemyMovingPart.class);
            movingPart.process(gameData, ent);
            positionPart.process(gameData, ent);

            if(path == null) {
                path = pathfinder.findPath(navLayer.getCell((int) positionPart.getX() / pixelToTile, (int) positionPart.getY() / pixelToTile), navLayer.getCell(50, 15));
            }
            GridCell node = path.get(0);
            double distance = Math.sqrt(Math.pow(positionPart.getX() - node.getY() * pixelToTile, 2) + Math.pow(positionPart.getY() - node.getY() * pixelToTile, 2));

            if (distance < LEAST_DISTANCE) {
                path.remove(0);
            }

            if (positionPart.getX() > node.getX()) {
                movingPart.setRight(true);
                movingPart.setLeft(false);
            }
            if (positionPart.getX() < node.getX()) {
                movingPart.setLeft(true);
                movingPart.setRight(false);
            }

            if (path.isEmpty()) {
                movingPart.setLeft(false);
                movingPart.setRight(false);
                movingPart.setUp(false);
                movingPart.setDown(false);
            }

            movingPart.process(gameData, ent);
            lifePart.process(gameData, ent);

        }


    }
}









