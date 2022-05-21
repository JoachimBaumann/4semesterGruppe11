package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;

public class EnemyZombie extends Enemy {

    private static final String enemyAssetPath = "\\Zombie\\OSGIEnemy\\src\\main\\resources\\Assets\\";
    String assetPath = AssetLoader.whichOS(enemyAssetPath);


    @Override
    public void create() {
        this.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/EnemyAssets/EnemyZombie/Left/Walk.txt")));
        this.setAnimation(new Animation(1/15f, getTextureAtlas().getRegions()));
    }


    @Override
    public void dispose(){
        super.dispose();
    }

    @Override
    public String getName() {
        return "Zombie";
    }

}
