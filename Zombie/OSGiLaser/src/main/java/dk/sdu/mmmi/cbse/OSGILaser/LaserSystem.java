package dk.sdu.mmmi.cbse.OSGILaser;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.weapon.Weapon;
import dk.sdu.mmmi.cbse.common.weapon.WeaponSPI;

import dk.sdu.mmmi.cbse.common.*;

public class LaserSystem implements IEntityProcessingService, WeaponSPI {

    private float CD;
    private boolean canShoot = true;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Weapon.class)) {

            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);
            movingPart.setRight(true);
            if (timerPart.getExpiration() < 0) {
                world.removeEntity(bullet);
            }

            timerPart.process(gameData, bullet);
            movingPart.process(gameData,bullet);

            positionPart.process(gameData, bullet);


        }
    }

    @Override
    public Entity createWeapon(Entity shooter, GameData gameData) {
        PositionPart shooterPos = shooter.getPart(PositionPart.class);
        MovingPart shooterMovingPart = shooter.getPart(MovingPart.class);



        float x = shooterPos.getX();
        float y = shooterPos.getY();
        float radians = shooterPos.getRadians();
        float dt = gameData.getDelta();
        float speed = 350;

        Entity bullet = new Weapon();
        //bullet.setRadius(2);
        bullet.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath("/ShootingAssets/ShootingRight/shotright.txt")));
        bullet.setAnimation(new Animation(1f / 6f, bullet.getTextureAtlas().getRegions()));

        bullet.add(new PositionPart(x + 180, y + 70, radians));
        bullet.add(new LifePart(1));
        bullet.add(new MovingPart(300,0f));
        bullet.add(new TimerPart(5));


        return bullet;
    }

}