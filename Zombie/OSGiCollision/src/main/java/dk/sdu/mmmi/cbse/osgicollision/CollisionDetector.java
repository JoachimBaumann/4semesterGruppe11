package dk.sdu.mmmi.cbse.osgicollision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.weapon.Weapon;

public class CollisionDetector implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity : world.getEntities()) {
            for (Entity collisionDetection : world.getEntities()) {
                // get life parts on all entities
                LifePart entityLife = entity.getPart(LifePart.class);
                // if the two entities are identical, skip the iteration
                if (entity.getID().equals(collisionDetection.getID())) {
                    continue;
                }

                // remove entities with zero in expiration
                if (entityLife.isDead()) {
                    System.out.println("Bullet removed maybe?");
                    world.removeEntity(entity);
                }



                // CollisionDetection
                if (this.Collides(entity, collisionDetection)) {
                    // if entity has been hit, and should have its life reduced

                    if (entityLife.getLife() > 0) {
                        entityLife.setLife(entityLife.getLife() - 1);
                        entityLife.setIsHit(true);
                        // if entity is out of life - remove
                        if (entityLife.getLife() == 0 && entity.getName() != "Bullet") {
                            world.removeEntity(entity);
                        }
                    }
                }
            }
        }
    }

    public Boolean Collides(Entity entity, Entity entity2) {
        PositionPart entMov = entity.getPart(PositionPart.class);
        PositionPart entMov2 = entity2.getPart(PositionPart.class);

        if (entMov.getX() < entMov2.getX() + entity2.getWidth()
                && entMov.getX() + entity.getWidth() > entMov2.getX() && entMov.getY() < entMov2.getY() + entity2.getHeight()
                && entity.getHeight() + entMov.getY() > entMov2.getY()) {

            return true;
        }
        return false;
    }


}
