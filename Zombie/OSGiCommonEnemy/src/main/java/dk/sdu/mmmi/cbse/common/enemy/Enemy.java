package dk.sdu.mmmi.cbse.common.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity{

    @Override
    public void create() {
        this.setTextureAtlas(new TextureAtlas("/Users/faezeh/Desktop/4semesterGruppe11/Zombie/OSGiCommon/src/main/resources/Assets/EnemyAssets/enemywalking.txt"));
        this.setAnimation(new Animation(1/15f,getTextureAtlas().getRegions()));

    }

    @Override
    public void dispose(){
        super.dispose();
    }
}
