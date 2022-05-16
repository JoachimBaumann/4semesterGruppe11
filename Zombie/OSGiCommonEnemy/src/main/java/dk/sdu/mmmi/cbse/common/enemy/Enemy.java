package dk.sdu.mmmi.cbse.common.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {

    private String name = "enemy";

    @Override
    public void create() {
        this.setTextureAtlas(new TextureAtlas(AssetLoader.getCommonEnemyAssetPath("/EnemyLeft/enemywalking.txt")));
        this.setAnimation(new Animation(1/15f, getTextureAtlas().getRegions()));
    }

    @Override
    public void dispose(){
        super.dispose();
    }


    public String getName() {
        return this.name;
    }
}
