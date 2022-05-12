package dk.sdu.mmmi.cbse.osgiboss;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EnemyMovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.List;

public class EnemyControlSystem implements IEntityProcessingService {
    private AStarPathfinder aStarPathfinder = new AStarPathfinder();

    List<Entity> enemies;


    @Override
    public void process(GameData gameData, World world) {
        enemies = world.getEntities(Enemy.class);
        enemies.addAll(world.getEntities(Enemy.class));

        //System.out.println(enemies.size());
        for (Entity enemy : enemies) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            EnemyMovingPart enemyMovingPart = enemy.getPart(EnemyMovingPart.class);
            //LifePart lifePart = enemy.getPart(LifePart.class);

            if (aStarPathfinder != null) {

                // find next position
                PositionPart nextPos = aStarPathfinder.findNextPosition(enemy, gameData, world);
                // move to next position

                System.out.println("x: " + nextPos.getX() + " Y: " + nextPos.getY());
                enemyMovingPart.setRight(positionPart.getX() < nextPos.getX());
                enemyMovingPart.setLeft(positionPart.getX() > nextPos.getX());



                aStarPathfinder.findNextPosition(enemy, gameData, world);


                enemyMovingPart.process(gameData, enemy);
                positionPart.process(gameData, enemy);

                //lifePart.process(gameData, enemy);

                // System.out.println("x = " + positionPart.getX() + " y = " + positionPart.getY());

            }
        }
    }
}
