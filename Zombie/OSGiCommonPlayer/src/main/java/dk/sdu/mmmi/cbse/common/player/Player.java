package dk.sdu.mmmi.cbse.common.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Player extends Entity {



    @Override
    public void create() {
        this.setSprite(new Sprite(new Texture(AssetLoader.getAssetPath("player.png")),180,180));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
