package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;

public class EnemyBat extends Enemy {

    private static final String enemyAssetPath = "\\Zombie\\OSGiEnemy\\src\\main\\resources\\Assets\\";
    private static final String coreAssetPath = "\\Zombie\\OSGICore\\src\\main\\resources\\Assets\\";


    @Override
    public void create() {
        String assetPath = AssetLoader.whichOS(enemyAssetPath);
        this.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath, "/EnemyAssets/EnemyBat/EnemyBatWalk.txt")));
        this.setAnimation(new Animation(1 / 8f, getTextureAtlas().getRegions()));

        Texture hpbar;
        Sprite healthbar;

        String corePath = AssetLoader.whichOS(coreAssetPath);
        hpbar = new Texture(AssetLoader.getAssetPath(corePath,"/UI/Health.png"));
        healthbar = new Sprite(hpbar, 50, 50, 1045, 64);
        healthbar.setPosition(Gdx.graphics.getWidth() * 0.07f, Gdx.graphics.getHeight() * 0.11f);
        healthbar.setSize(Gdx.graphics.getWidth() * 0.6f, Gdx.graphics.getHeight() * 0.07f);

       /* gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        shapeRenderer = new ShapeRenderer();

       Gdx.input.setInputProcessor(new GameInputProcessor(gameData));*/
    }

    @Override
    public void dispose(){
        super.dispose();
    }

    @Override
    public String getName() {
        return "bat";
    }
}
