package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;

public class EnemySnail extends Enemy {
    private static final String enemyAssetPath = "\\Zombie\\OSGIEnemy\\src\\main\\resources\\Assets\\";



    @Override
    public void create() {
        String assetPath = AssetLoader.whichOS(enemyAssetPath);
        this.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/EnemyAssets/EnemySnail/SnailLeft/EnemySnailWalk.txt")));
        this.setAnimation(new Animation(1/8f, getTextureAtlas().getRegions()));
    }

    @Override
    public void dispose(){
        super.dispose();
    }

    @Override
    public String getName() {
        return "snail";
    }
}
