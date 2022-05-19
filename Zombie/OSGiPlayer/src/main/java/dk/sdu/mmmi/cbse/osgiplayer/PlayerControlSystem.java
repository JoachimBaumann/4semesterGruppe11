package dk.sdu.mmmi.cbse.osgiplayer;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.player.Player;
import dk.sdu.mmmi.cbse.common.weapon.WeaponSPI;


public class PlayerControlSystem implements IEntityProcessingService {

    private WeaponSPI weaponService;
    float shootTime = 0;
    //String playerShootRight = "/PlayerRight/playershootright.txt";
    private static final String playerAssetPath = "\\Zombie\\OSGIPlayer\\src\\main\\resources\\Assets\\";



    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            PlayerMovingPart movingPart = player.getPart(PlayerMovingPart.class);
            LifePart lifePart = player.getPart(LifePart.class);

            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));
            movingPart.setSpace(gameData.getKeys().isDown(GameKeys.SPACE));
            shootTime += gameData.getDelta();

            if (gameData.getKeys().isDown(GameKeys.SPACE) && weaponService != null){
                    String assetPath = AssetLoader.whichOS(playerAssetPath);
                    player.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/PlayerRight/playershootright.txt")));
                    player.setAnimation(new Animation(1f / 30f, player.getTextureAtlas().getRegions()));
                if(shootTime > 0.2f){
                    shootTime = 0;
                    Entity bullet = weaponService.createWeapon(player, gameData);
                    world.addEntity(bullet);
                }
            }

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);
            //lifePart.process(gameData, player);

        }
    }

    public void setWeaponService(WeaponSPI weaponSPI) {
        this.weaponService = weaponSPI;
    }

    public void removeWeaponService(WeaponSPI weaponSPI) {
        this.weaponService = null;
    }


}
