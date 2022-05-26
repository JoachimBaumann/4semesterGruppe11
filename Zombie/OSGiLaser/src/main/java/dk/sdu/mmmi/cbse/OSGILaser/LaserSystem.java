package dk.sdu.mmmi.cbse.OSGILaser;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
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

public class LaserSystem implements IEntityProcessingService, WeaponSPI {

    private float CD;
    private boolean canShoot = true;
    private boolean isFirst = true;
    private static final String laserAssetPath = "\\Zombie\\OSGILaser\\src\\main\\resources\\Assets";
    //String assetPath = AssetLoader.whichOS(laserAssetPath);



    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Weapon.class)) {

            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);
            LifePart lifepart = bullet.getPart(LifePart.class);


            if (lifepart.isHit() && isFirst){
                movingPart.setVelocity(new Vector2(0,0));
                movingPart.setMaxSpeed(0f);
                isFirst = false;
                timerPart.setExpiration(0.25f);

            }
            if (timerPart.getExpiration() <= 0.25f){
                if (positionPart.getDirection() == positionPart.getRight()) {
                    bullet.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(laserAssetPath,"/ShootingAssets/ShootingRight/shootexplosion.txt")));
                    bullet.setAnimation(new Animation(1f / 4f, bullet.getTextureAtlas().getRegions()));
                } else if (positionPart.getDirection() == positionPart.getLeft()) {
                    bullet.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(laserAssetPath,"/ShootingAssets/ShootingLeft/shootexplosionLeft.txt")));
                    bullet.setAnimation(new Animation(1f / 4f, bullet.getTextureAtlas().getRegions()));
                }

                movingPart.setVelocity(new Vector2(0,0));
                movingPart.setMaxSpeed(0f);
            }

            if (timerPart.getExpiration() <= 0){
                world.removeEntity(bullet);
                isFirst = true;

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
        float direction = shooterPos.getDirection();
        float dt = gameData.getDelta();
        float speed = 700;


        Entity bullet = new Weapon();

        if (direction == shooterPos.getLeft()){
            bullet.add(new PositionPart(x + -140, y + 70, direction));
            bullet.add(new MovingPart(speed,0f));
            MovingPart movingpart = bullet.getPart(MovingPart.class);
            movingpart.setLeft(true);
            movingpart.setRight(false);

        } else if (direction == shooterPos.getRight()) {
            bullet.add(new PositionPart(x + 140, y + 70, direction));
            bullet.add(new MovingPart(speed,0f));
            MovingPart movingpart = bullet.getPart(MovingPart.class);
            movingpart.setRight(true);
            movingpart.setLeft(false);
        }

        //bullet.setRadius(2);
        if (shooterPos.getDirection() == shooterPos.getRight()) {
            bullet.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(laserAssetPath, "/ShootingAssets/ShootingRight/shotright.txt")));
            bullet.setAnimation(new Animation(1f / 6f, bullet.getTextureAtlas().getRegions()));
        } else if (shooterPos.getDirection() == shooterPos.getLeft()) {
            bullet.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(laserAssetPath, "/ShootingAssets/ShootingLeft/shotLeft.txt")));
            bullet.setAnimation(new Animation(1f / 6f, bullet.getTextureAtlas().getRegions()));
        }


        bullet.add(new LifePart(2));
        bullet.add(new TimerPart(2));
        bullet.setType("bullet");

        return bullet;
    }

}