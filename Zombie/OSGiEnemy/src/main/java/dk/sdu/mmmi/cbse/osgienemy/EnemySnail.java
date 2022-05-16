package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;

public class EnemySnail extends Enemy {

    @Override
    public void create() {
        this.setTextureAtlas(new TextureAtlas(AssetLoader.getEnemyAssetPath("/EnemyAssets/EnemySnail/SnailLeft/EnemySnailWalk.txt")));
        this.setAnimation(new Animation(1/8f, getTextureAtlas().getRegions()));
    }

    @Override
    public void dispose(){
        super.dispose();
    }


}
