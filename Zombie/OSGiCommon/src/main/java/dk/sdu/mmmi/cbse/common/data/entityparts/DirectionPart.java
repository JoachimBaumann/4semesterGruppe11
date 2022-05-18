package dk.sdu.mmmi.cbse.common.data.entityparts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class DirectionPart implements EntityPart{
    private Boolean left;
    private static final String coreAssetPath = "\\Zombie\\OSGICommon\\src\\main\\resources\\Assets\\";
    String assetPath = AssetLoader.whichOS(coreAssetPath);



    public Boolean getLeft() {
        return left;
    }

    public void setLeft(Boolean left) {
        this.left = left;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        PlayerMovingPart player = entity.getPart(PlayerMovingPart.class);
        if (left){
            /*entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/PlayerAssets/PlayerLeft/flippedPlayerWalk.txt")));
            entity.setAnimation(new Animation(1f / 6f, entity.getTextureAtlas().getRegions()));*/
            //Flip animation
        }else if(!left){
            /*entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath, "/PlayerAssets/PlayerRight/playerwalk.txt")));
            entity.setAnimation(new Animation(1f / 6f, entity.getTextureAtlas().getRegions()));*/
            //Undo Flip
        }
        if(player.isUp()){
            /*entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/PlayerAssets/PlayerRight/playerjump.txt")));
            entity.setAnimation(new Animation(1f / 3f, entity.getTextureAtlas().getRegions()));*/
        }
    }
}
