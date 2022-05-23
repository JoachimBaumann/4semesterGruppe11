package dk.sdu.mmmi.cbse.osgiboss;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;

public class Boss extends Enemy {

    private static final String bossAssetpath = "\\Zombie\\OSGIBoss\\src\\main\\resources\\Assets\\";
    String assetPath = AssetLoader.whichOS(bossAssetpath);

    @Override
    public void create() {
        this.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath, "boss.txt")));
        this.setAnimation(new Animation(1f/6f, getTextureAtlas().getRegions()));

    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
