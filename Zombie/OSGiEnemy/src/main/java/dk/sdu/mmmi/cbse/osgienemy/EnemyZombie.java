package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;

public class EnemyZombie extends Enemy {

    private String name = "enemy";

    private static final String coreAssetPath = "\\Zombie\\OSGICommon\\src\\main\\resources\\Assets\\";

    @Override
    public void create() {
        String assetPath = AssetLoader.whichOS(coreAssetPath);
        this.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/EnemyAssets/EnemyLeft/enemywalking.txt")));
        this.setAnimation(new Animation(1/15f, getTextureAtlas().getRegions()));

    }

    @Override
    public void dispose(){
        super.dispose();
    }

    @Override
    public String getName() {
        return this.name;
    }

}
