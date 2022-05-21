package dk.sdu.mmmi.cbse;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.WorldMap;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.player.Player;
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
    private boolean freeze = false;


    private SpriteBatch batch;
    private SpriteBatch endgameBatch;
    BitmapFont font;
    private float elapsedTime = 0;
    private Texture deadEndgame;
    private Texture youDiedText;
    private Texture hpbar;
    private Texture ufo;
    private Texture victoryText;

    private Sprite healthbar;
    private Sprite ufoSprite;
    private Sprite youDiedTextSprite;
    private Sprite victoyTextSprite;
    private static final String coreAssetPath = "\\Zombie\\OSGICommon\\src\\main\\resources\\Assets\\";
    private static final String assetPath = AssetLoader.whichOS(coreAssetPath);

    public Game() {
        init();
    }

    public void init() {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "ZombieGame";
        cfg.width = 1280;
        cfg.height = 720;
        cfg.useGL30 = false;
        cfg.resizable = false;

        new LwjglApplication(this, cfg);
    }

    @Override
    public void create() {


        //sprite loading
        batch = new SpriteBatch();
        endgameBatch = new SpriteBatch();

        //Health-bar sprite
        hpbar = new Texture(AssetLoader.getAssetPath(assetPath, "/UI/Health.png"));
        healthbar = new Sprite(hpbar,50,50,1045,64);
        healthbar.setPosition(Gdx.graphics.getWidth()*0.05f,Gdx.graphics.getHeight()*0.9f);
        healthbar.setSize(Gdx.graphics.getWidth()*0.4f, Gdx.graphics.getHeight()*0.05f);

        ufo = new Texture(AssetLoader.getAssetPath(assetPath,"UI/UFO.png"));
        ufoSprite = new Sprite(ufo,50,50, 800,600);
        ufoSprite.setPosition(11000,100);
        ufoSprite.setSize(Gdx.graphics.getWidth()*0.3f, Gdx.graphics.getHeight()*0.4f);


        youDiedText = new Texture(AssetLoader.getAssetPath(assetPath,"UI/youDiedText.png"));
        youDiedTextSprite = new Sprite(youDiedText, 0, 0, 957,240);
        youDiedTextSprite.setPosition(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f);


        victoryText = new Texture(AssetLoader.getAssetPath(assetPath, "/UI/VictoryText.png"));
        victoyTextSprite = new Sprite(victoryText, 0, 0, 746, 214);
        victoyTextSprite.setPosition(Gdx.graphics.getWidth()*1/4f, Gdx.graphics.getHeight()*1.5f/3f);
        victoyTextSprite.setSize(Gdx.graphics.getWidth()*0.5f, Gdx.graphics.getHeight()*0.5f);




        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();


        shapeRenderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

        ui();

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
            TiledMapTileLayer layer0 = (TiledMapTileLayer) worldMap.getMap().getLayers().get(1);


            List<Entity> player = world.getEntities(Player.class);
            if(!player.isEmpty()) {
                PositionPart playerPositionPart = player.get(0).getPart(PositionPart.class);
                Vector3 position = cam.position;
                position.x = playerPositionPart.getX();
            }

            cam.update();
            worldMap.getRenderer().setView(cam);

            worldMap.getRenderer().render();
        } catch (NullPointerException e) {
            worldMap.create();
            gameData.setWorldMap(worldMap);
        }


        //Pause function
        if (gameData.getKeys().isDown(GameKeys.ESCAPE)){
            if (!freeze){
                freeze = true;
            }else if (freeze){
                freeze = false;
            }
        }
        draw();

        //Pauses update to pause the game
        if (!freeze) {
            update();
        }

        ui();

        List<Entity> player = world.getEntities(Player.class);
        if(!player.isEmpty()) {
            LifePart playerLifePart = player.get(0).getPart(LifePart.class);
            healthbar.setSize(Gdx.graphics.getWidth()*0.4f*playerLifePart.getLife()/ playerLifePart.getStarterLife(), Gdx.graphics.getHeight()*0.05f);
        } else {
            gameData.setGameLost(true);
        }
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

        if (gameData.isGameLost()) {

            String highScore = gameData.getCurrentHighScore();
            String playerScore = gameData.getPlayerScore();
            //todo: display scores here
            endgameBatch.begin();
            youDiedTextSprite.setPosition(Gdx.graphics.getWidth()*0.15f,Gdx.graphics.getHeight()*0.5f);
            youDiedTextSprite.draw(endgameBatch);
            //deadEndgameSprite.draw(endgameBatch);
            endgameBatch.end();
            //setFreeze(true);
        }

        if (gameData.isGameWon()){

            List<Entity> player = world.getEntities(Player.class);
            PlayerMovingPart playerMovingPart = player.get(0).getPart(PlayerMovingPart.class);
            playerMovingPart.setMaxSpeed(0f);
            font = new BitmapFont();
            font.setScale(2f);
            String highScore = gameData.getCurrentHighScore();
            String playerScore = gameData.getPlayerScore();
            CharSequence newHighScore = "High Score:";
            CharSequence charHighScore = playerScore;
            endgameBatch.begin();
            victoyTextSprite.draw(endgameBatch);
            font.draw(endgameBatch, "high score: " + charHighScore, Gdx.graphics.getWidth()/4.25f, Gdx.graphics.getHeight()/2f);

            endgameBatch.end();
            //setFreeze(true);

        }
    }

    //remove when sprite is implemented
    private void draw() {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        healthbar.draw(batch);
        ufoSprite.draw(batch);
        elapsedTime += Gdx.graphics.getDeltaTime();
        for (Entity entity : world.getEntities()) {
            try {
                PositionPart positionPart = entity.getPart(PositionPart.class);
                batch.draw(entity.getAnimation().getKeyFrame(elapsedTime, true), positionPart.getX() + entity.getAnimationXOffset(), positionPart.getY() + entity.getAnimationYOffset());
                entity.getSprite().draw(batch);
            } catch (NullPointerException e) {
                entity.create();
            }
        }
        batch.end();
    }

    private void ui(){
        hpbar = new Texture(AssetLoader.getAssetPath(assetPath,"/UI/Health.png"));
        healthbar = new Sprite(hpbar,50,50,1045,64);
        try {
            Entity player = world.getEntities(Player.class).get(0);
            PositionPart playerPositionPart = player.getPart(PositionPart.class);
            healthbar.setPosition(playerPositionPart.getX() - Gdx.graphics.getWidth()*0.45f,Gdx.graphics.getHeight()*0.9f);
        } catch (IndexOutOfBoundsException e) {
            healthbar.setPosition(Gdx.graphics.getWidth()*0.05f,Gdx.graphics.getHeight()*0.9f);
            healthbar.setSize(0f,0f);
        }
        healthbar.setSize(Gdx.graphics.getWidth()*0.4f, Gdx.graphics.getHeight()*0.05f);
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

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }
}
