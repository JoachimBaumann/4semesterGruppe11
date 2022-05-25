package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;


public class EnemyBat extends Enemy {



    private static final String enemyAssetPath = "\\Zombie\\OSGiEnemy\\src\\main\\resources\\Assets";
    private float animationYOffset = 60f;

    @Override
    public void create() {

        this.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(enemyAssetPath, "/EnemyAssets/EnemyBat/Left/Walk.txt")));
        this.setAnimation(new Animation(1 / 8f, getTextureAtlas().getRegions()));

    }

    @Override
    public void dispose(){
        super.dispose();
    }



    @Override
    public float getAnimationYOffset() {
        return animationYOffset;
    }

    @Override
    public String getName(){
        return "Bat";
    }


}
