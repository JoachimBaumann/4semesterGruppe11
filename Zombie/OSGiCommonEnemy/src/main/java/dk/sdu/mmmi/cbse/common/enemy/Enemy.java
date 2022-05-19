package dk.sdu.mmmi.cbse.common.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity{

    private String type = "enemy";

    @Override
    public void create() {
    }

    @Override
    public void dispose(){
        super.dispose();
    }


    public String getType() {
        return this.type;
    }
}
