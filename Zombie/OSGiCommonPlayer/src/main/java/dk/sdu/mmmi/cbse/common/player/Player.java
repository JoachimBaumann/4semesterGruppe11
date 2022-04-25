package dk.sdu.mmmi.cbse.common.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Player extends Entity {


    @Override
    public void create() {
        //this.setSprite(new Sprite(new Texture(AssetLoader.getAssetPath("player.png")),128,256));
        this.setTextureAtlas(new TextureAtlas("C:/Users/Phill/IdeaProjects/4semesterGruppe11/Zombie/OSGiCommon/src/main/resources/Assets/playerwalk.atlas"));
        this.setAnimation(new Animation(1f/15f,getTextureAtlas().getRegions()));


    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
