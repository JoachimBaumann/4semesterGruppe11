package dk.sdu.mmmi.cbse.osgiboss;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.List;

public class BossControlSystem implements IEntityProcessingService {
    private static final String commonAssetPath = "\\Zombie\\OSGiCommon\\src\\main\\resources\\Assets\\";
    private String assetPath = AssetLoader.whichOS(commonAssetPath);
    Boolean first = true;
    TiledMap map;
    TmxMapLoader loader = new NavTmxMapLoader();
    boolean second = true;


    @Override
    public void process(GameData gameData, World world) {

        if (first) {
            first = false;
            map = loader.load(AssetLoader.getAssetPath(assetPath, "widemap.tmx"));
            NavigationTiledMapLayer navLayer = (NavigationTiledMapLayer) map.getLayers().get("navigation");

            if (navLayer == null) {
                System.out.println("crap");
            } else {
                if (second) {
                    second = false;
                    navLayer.setWidth(2000);
                    navLayer.setHeight(30);

                    GridCell node = navLayer.getCell(4, 3);
                    List<GridCell> list = navLayer.getNeighbors(node);

                    for (GridCell cell1 : list) {
                        System.out.println(cell1.getX() + " " + cell1.getY() + " " + cell1.isWalkable());
                    }
                }


            }
        }


    }
}
