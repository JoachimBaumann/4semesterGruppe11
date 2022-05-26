package dk.sdu.mmmi.cbse.osgienemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.enemy.*;


import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.List;

public class EnemySystem implements IEntityProcessingService, EnemySPI{

    List<Entity> enemies;
    private static final String enemyAssetPath = "\\Zombie\\OSGIEnemy\\src\\main\\resources\\Assets";



    @Override
    public void process(GameData gameData, World world) {
        enemies = world.getEnemies();

        for (Entity enemy : enemies) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart enemyMovingPart = enemy.getPart(MovingPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);

            enemyMovingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            lifePart.process(gameData, enemy);
        }
    }

    @Override
    public Entity createEnemy(GameData gameData) {
        //random to spawning position, should be integers between x=(5, 3000) y=(5, 500)
        //random to maxSpeed (50, 150)'

        float deacceleration = 20;
        float acceleration = 50;
        float maxSpeed = getRandomNumber(50, 400);
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        //float radians = 3.1415f / 2;
        float direction = 6.28f;

        Entity enemy = getRandomEnemy();

        int xCoordinate = getRandomNumber(0, 4000);
        int yCoordinate = getRandomNumber(100, 500);
        enemy.add(new PositionPart(xCoordinate , yCoordinate , direction));

        enemy.directionTypeEntity = true;
        enemy.setType("enemy");


        enemy.setEntityAssetPath(enemyAssetPath);
        String stringPath = enemy.getName();

        // walk
        enemy.setFrameDuration(1f/6f);
        enemy.setLeftAssetPath("/EnemyAssets/Enemy"+stringPath+"/Left/Walk.txt");
        enemy.setRightAssetPath("/EnemyAssets/Enemy"+stringPath+"/Right/Walk.txt");

        return enemy;

    }

    private Entity getRandomEnemy() {
        int randomInt = getRandomNumber(0, 4);
        switch(randomInt) {
            case 0:
                Entity entity1 = new EnemyBat();
                entity1.setHeight(130);
                entity1.setWidth(50);
                entity1.add(new MovingPart(150));
                entity1.add(new LifePart(5));
                return entity1;
            case 1:
                Entity entity2 = new EnemyRaven();
                entity2.setHeight(300);
                entity2.setWidth(50);
                entity2.add(new MovingPart(500));
                entity2.add(new LifePart(50));
                return entity2;
            case 2:
                Entity entity3 = new EnemySnail();
                entity3.setHeight(186);
                entity3.setWidth(336);
                entity3.add(new MovingPart(50));
                entity3.add(new LifePart(75));
                return entity3;
            case 3:
                Entity entity4 = new EnemyZombie();
                entity4.setHeight(100);
                entity4.setWidth(115);
                entity4.add(new MovingPart(200));
                entity4.add(new LifePart(100));
                return entity4;
        }
        return null;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
