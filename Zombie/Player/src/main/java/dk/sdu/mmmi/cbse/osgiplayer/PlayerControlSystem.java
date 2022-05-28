package dk.sdu.mmmi.cbse.osgiplayer;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.player.Player;
import dk.sdu.mmmi.cbse.common.weapon.Weapon;
import dk.sdu.mmmi.cbse.common.weapon.WeaponSPI;


public class PlayerControlSystem implements IEntityProcessingService {

    private WeaponSPI weaponService;
    float shootTime = 0;
    private static final String playerAssetPath = "\\Zombie\\Player\\src\\main\\resources\\Assets";
    private static final String commonPlayerAssetPath = "\\Zombie\\CommonPlayer\\src\\main\\resources\\Assets";



    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);

            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));
            movingPart.setSpace(gameData.getKeys().isDown(GameKeys.SPACE));


            shootTime += gameData.getDelta();

            if (gameData.getKeys().isReleased(GameKeys.allKeys())){
                if (positionPart.getDirection() == positionPart.getRight()){
                    player.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(commonPlayerAssetPath, "/playeridle.txt")));
                    player.setAnimation(new Animation(1f/6f, player.getTextureAtlas().getRegions()));
                }
                if (positionPart.getDirection() == positionPart.getLeft()){
                    player.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(playerAssetPath, "/playerLeft/flippedPlayerIdle.txt")));
                    player.setAnimation(new Animation(1f/6f, player.getTextureAtlas().getRegions()));
                }
            }
            if (gameData.getKeys().isDown(GameKeys.SPACE) && weaponService != null)
            {
                if (positionPart.getDirection() == positionPart.getRight()) {
                        player.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(playerAssetPath, "/PlayerRight/playershootright.txt")));
                        player.setAnimation(new Animation(1f / 30f, player.getTextureAtlas().getRegions()));
                    } else if (positionPart.getDirection() == positionPart.getLeft()) {
                        player.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(playerAssetPath, "/PlayerLeft/FlippedShootPlayer.txt")));
                        player.setAnimation(new Animation(1f / 30f, player.getTextureAtlas().getRegions()));
                    }
                if (shootTime > 0.2f){
                    shootTime = 0;
                    Entity bullet = weaponService.createWeapon(player, gameData);
                    world.addEntity(bullet);
                }
            }
            movingPart.process(gameData, player);
            positionPart.process(gameData, player);

        }
    }


    /*
    Mock of process
     */
    public void processTest(GameData gameData, World world) {
        Entity player = world.getEntities(Player.class).get(0);

        if (gameData.getKeys().isDown(GameKeys.SPACE)) {
            Entity bullet = new Weapon();
            world.addEntity(bullet);
        }
    }

    public void setWeaponService(WeaponSPI weaponSPI) {
        this.weaponService = weaponSPI;
    }

    public void removeWeaponService(WeaponSPI weaponSPI) {
        this.weaponService = null;
    }


}
