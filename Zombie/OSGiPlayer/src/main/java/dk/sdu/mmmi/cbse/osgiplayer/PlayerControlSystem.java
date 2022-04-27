package dk.sdu.mmmi.cbse.osgiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.player.Player;
import dk.sdu.mmmi.cbse.common.weapon.Weapon;
import dk.sdu.mmmi.cbse.common.weapon.WeaponSPI;


public class PlayerControlSystem implements IEntityProcessingService {

    private WeaponSPI weaponService;


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


            if (gameData.getKeys().isDown(GameKeys.SPACE) && weaponService != null) {
                System.out.println("Skud!");
                Entity bullet = weaponService.createWeapon(player, gameData);
                world.addEntity(bullet);
            }


            movingPart.process(gameData, player);
            positionPart.process(gameData, player);
            lifePart.process(gameData, player);

            System.out.println(positionPart.getX() + " - " + positionPart.getY());

        }
    }

    //TODO: Dependency injection via Declarative Services
    public void setWeaponService(WeaponSPI weaponSPI) {
        this.weaponService = weaponSPI;
    }

    public void removeWeaponService(WeaponSPI weaponSPI) {
        this.weaponService = null;
    }


}
