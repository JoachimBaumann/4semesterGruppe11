package dk.sdu.mmmi.cbse.common.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity{

    @Override
    public void create() {
        this.setSprite(new Sprite(new Texture(AssetLoader.getAssetPath("player.png")),60,120));

    }
}
