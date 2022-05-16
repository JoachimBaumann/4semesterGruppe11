package dk.sdu.mmmi.cbse.osgimap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.WorldMap;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class MapPlugin implements IGamePluginService {
    private TiledMap map;
    private ShapeRenderer sr;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private static final String coreAssetPath = "\\Zombie\\OSGICommon\\src\\main\\resources\\Assets\\";



    @Override
    public void start(GameData gameData, World world) {
        WorldMap worldMap = new MapImpl();
        world.setWorldMap(worldMap);
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
    public void createMap() {
        map = new TiledMap();

        TmxMapLoader loader = new TmxMapLoader();
        String assetPath = AssetLoader.whichOS(coreAssetPath);
        map = loader.load(AssetLoader.getAssetPath(assetPath,"widemap.tmx"));

        renderer = new OrthogonalTiledMapRenderer(map);
        sr = new ShapeRenderer();
        Gdx.gl.glLineWidth(3);

        camera = new OrthographicCamera();

    }
}
