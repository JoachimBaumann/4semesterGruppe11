package dk.sdu.mmmi.cbse;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.core.managers.GameInputProcessor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private final GameData gameData = new GameData();
    private static World world = new World();
    private static final List<IEntityProcessingService> entityProcessorList = new CopyOnWriteArrayList<>();
    private static final List<IGamePluginService> gamePluginList = new CopyOnWriteArrayList<>();
    private static List<IPostEntityProcessingService> postEntityProcessorList = new CopyOnWriteArrayList<>();


    private SpriteBatch batch;
    private float elapsedTime = 0;
    private Texture img;


    public Game() {
        init();
    }

    public void init() {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "ZombieGame";
        cfg.width = 1920;
        cfg.height = 1080;
        cfg.useGL30 = false;
        cfg.resizable = false;

        new LwjglApplication(this, cfg);
    }

    @Override
    public void create() {



        //spirit loading
        batch = new SpriteBatch();
        img = new Texture(AssetLoader.getAssetPath("/BackgroundFlutter/_Background.png"));
        batch.begin();
        batch.draw(img,0,0);
        batch.end();

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        shapeRenderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

    }

    @Override
    public void render() {
        // clear screen to black
        Gdx.gl.glClearColor(0.128f, 0.128f, 0.128f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        gameData.getKeys().update();


        WorldMap worldMap = world.getWorldMap();


        /**
         * Try/catch has been implemented so, if map is not loaded, it will be created and loaded in, else its updated.
         */

        try {
            TiledMapTileLayer layer0 = (TiledMapTileLayer) worldMap.getMap().getLayers().get(0);

            Vector3 center = new Vector3(layer0.getWidth() * layer0.getTileWidth() / 2, layer0.getHeight() * layer0.getTileHeight() / 2, 0);
            cam.position.set(center);

            cam.update();

            worldMap.getRenderer().setView(cam);

            worldMap.getRenderer().render();
        } catch (NullPointerException e) {
            worldMap.create();
            gameData.setWorldMap(worldMap);
        }


        draw();
        update();

    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : entityProcessorList) {
            entityProcessorService.process(gameData, world);
        }
        // Post Update
        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessorList) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    //remove when sprite is implemented
    private void draw() {
        batch.begin();
        elapsedTime += Gdx.graphics.getDeltaTime();
        for (Entity entity : world.getEntities()) {
            try {
                PositionPart positionPart = entity.getPart(PositionPart.class);
                batch.draw(entity.getAnimation().getKeyFrame(elapsedTime,true),positionPart.getX()-12f,positionPart.getY()-12f);
                entity.getSprite().draw(batch);
            } catch (NullPointerException e) {
                entity.create();
            }
        }
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        world.getWorldMap().dispose();
    }

    public void addEntityProcessingService(IEntityProcessingService eps) {
        this.entityProcessorList.add(eps);
    }

    public void removeEntityProcessingService(IEntityProcessingService eps) {
        this.entityProcessorList.remove(eps);
    }

    public void addPostEntityProcessingService(IPostEntityProcessingService eps) {
        postEntityProcessorList.add(eps);
    }

    public void removePostEntityProcessingService(IPostEntityProcessingService eps) {
        postEntityProcessorList.remove(eps);
    }

    public void addGamePluginService(IGamePluginService plugin) {
        this.gamePluginList.add(plugin);
        plugin.start(gameData, world);

    }

    public void removeGamePluginService(IGamePluginService plugin) {
        this.gamePluginList.remove(plugin);
        plugin.stop(gameData, world);
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

}
