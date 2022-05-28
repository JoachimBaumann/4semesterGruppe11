package dk.sdu.mmmi.cbse.osgiboss;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.osgiboss.AStar.GridCell;
import dk.sdu.mmmi.cbse.osgiboss.AStar.NavTmxMapLoader;
import dk.sdu.mmmi.cbse.osgiboss.AStar.NavigationTiledMapLayer;
import dk.sdu.mmmi.cbse.osgiboss.AStar.Pathfinder;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;

public class PathFinderTest {

    private static final String commonAssetPath = "\\Zombie\\Common\\src\\main\\resources\\Assets\\";
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


    @Before
    public void loadSettings() {
        map = loader.load(AssetLoader.getAssetPath(assetPath, "finalmap.tmx"));
        navLayer = (NavigationTiledMapLayer) map.getLayers().get("navigation");
        navLayer.setHeight(30);
        navLayer.setWidth(360);
        pathfinder = new Pathfinder(navLayer);
        System.out.println("Testing Path finding for coordinates (1500, 500)");
        path = pathfinder.findPath(navLayer.getCell(250, 100) , navLayer.getCell(1500, 500));
    }


    @Test
    public void startTest() {
        System.out.println("Starting pathfinding test");
        System.out.println(path);
        if (path == null) {
            System.out.println("Path is null");
            Assertions.fail();
        } else {
            for (GridCell node : path) {
                System.out.println(node.getX() + " " + node.getY());
            }
        }
    }
}
