package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;

public class EnemyRaven extends Enemy {
    private static final String coreAssetPath = "\\Zombie\\OSGICore\\src\\main\\resources\\Assets\\";
    private static final String enemyAssetPath = "\\Zombie\\OSGIEnemy\\src\\main\\resources\\Assets\\";



    @Override
    public void create() {
        String assetPath = AssetLoader.whichOS(enemyAssetPath);
        this.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/EnemyAssets/EnemyRaven/EnemyRavenWalk.txt")));
        this.setAnimation(new Animation(1/6f, getTextureAtlas().getRegions()));

        Texture hpbar;
        Sprite healthbar;

        String corePath = AssetLoader.whichOS(coreAssetPath);
        hpbar = new Texture(AssetLoader.getAssetPath(corePath, "/UI/Health.png"));
        healthbar = new Sprite(hpbar, 50, 50, 1045, 64);
        healthbar.setPosition(Gdx.graphics.getWidth() * 0.07f, Gdx.graphics.getHeight() * 0.11f);
        healthbar.setSize(Gdx.graphics.getWidth() * 0.6f, Gdx.graphics.getHeight() * 0.07f);

    }

    @Override
    public void dispose(){
        super.dispose();
    }

    @Override
    public String getName() {
        return null;
    }
}
