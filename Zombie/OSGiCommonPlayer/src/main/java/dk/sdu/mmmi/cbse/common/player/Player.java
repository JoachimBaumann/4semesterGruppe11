package dk.sdu.mmmi.cbse.common.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Player extends Entity {
    private static Player instance;
    static public Player getInstance()
    {
        if (instance == null)
            instance = new Player();
        return instance;
    }

    private static final String playerAssetPath = "\\Zombie\\OSGICommonPlayer\\src\\main\\resources\\Assets\\";

    @Override
    public void create() {
        String assetPath = AssetLoader.whichOS(playerAssetPath);

        this.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath, "playeridle.txt")));
        this.setAnimation(new Animation(1f/6f, getTextureAtlas().getRegions()));



    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public String getName() {
        return "player";
    }

    /*@Override
    public void create() {}

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public String getName() {return null;}

     */
}
