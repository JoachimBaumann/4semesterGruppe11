package dk.sdu.mmmi.cbse.common.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {

    @Override
    public void create() {
        //this.setSprite(new Sprite(new Texture("C:\\Users\\Emil\\Desktop\\UNI\\SemesterProject4\\4semesterGruppe11\\Zombie\\OSGiCommon\\src\\main\\resources\\Assets\\gaben.png"),122,124));

        this.setSprite(new Sprite(new Texture(AssetLoader.getAssetPath("gaben.png")),122,124));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
