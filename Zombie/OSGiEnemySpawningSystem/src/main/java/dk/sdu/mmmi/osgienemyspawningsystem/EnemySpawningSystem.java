package dk.sdu.mmmi.osgienemyspawningsystem;



import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EnemyMovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.player.Player;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class EnemySpawningSystem implements IEntityProcessingService {

    HashMap<Integer, Integer> waves = new HashMap<Integer, Integer>();
    private int currentLevel = 0;
    List<Entity> enemies = new ArrayList<>();

    /**Enemy spawning service

      @param gameData, world
     if enemies in world == 0:
        level = +1
     */


    @Override
    public void process(GameData gameData, World world) {
        enemies = world.getEntities(Enemy.class);
        for (Entity entity : enemies) {
            if (enemies.size() == 0 && currentLevel != 20) {
                //spawn enemies
            } else {

            }
        }
    }

    private void spawnEnemies(int currentLevel) {
        return;
    }

    private Entity createEnemy(GameData gameData){
        //Spawn random enemy from random int
        float deacceleration = 20;
        float acceleration = 50;
        float maxSpeed = 120;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float radians = 3.1415f / 2;

        Entity enemy = new Enemy();
        enemy.add(new EnemyMovingPart(maxSpeed));
        enemy.add(new PositionPart(4 , 200 , radians));
        enemy.add(new LifePart(5));
        enemy.setHeight(84);
        enemy.setWidth(115);
        enemy.setRadius(20);
        return  enemy;
    }

}
