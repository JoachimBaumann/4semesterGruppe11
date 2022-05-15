package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;

public class EnemyBat extends Enemy {

    EnemyBat enemyBat;

    private static final String enemyAssetPath = "\\Zombie\\OSGiEnemy\\src\\main\\resources\\Assets\\";
    private static final String coreAssetPath = "\\Zombie\\OSGICore\\src\\main\\resources\\Assets\\";


    @Override
    public void create() {
        String assetPath = AssetLoader.whichOS(enemyAssetPath);
        this.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath, "/EnemyAssets/EnemyBat/EnemyBatWalk.txt")));
        this.setAnimation(new Animation(1 / 8f, getTextureAtlas().getRegions()));



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
