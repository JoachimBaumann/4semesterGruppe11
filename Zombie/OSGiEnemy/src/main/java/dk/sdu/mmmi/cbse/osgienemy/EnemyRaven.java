package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;

public class EnemyRaven extends Enemy {

    @Override
    public void create() {
        this.setTextureAtlas(new TextureAtlas(AssetLoader.getEnemyAssetPath("/EnemyAssets/EnemyRaven/RavenLeft/EnemyRavenWalk.txt")));
        this.setAnimation(new Animation(1/6f, getTextureAtlas().getRegions()));
    }

    @Override
    public void dispose(){
        super.dispose();
    }


}
