package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.enemy.*;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.data.*;




import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class EnemyControlSystem implements IEntityProcessingService, EnemySPI{

    List<Entity> enemies;


    @Override
    public void process(GameData gameData, World world) {
        enemies = world.getEnemies();

        //System.out.println(enemies.size());
        for (Entity enemy : enemies) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            EnemyMovingPart enemyMovingPart = enemy.getPart(EnemyMovingPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);

            enemyMovingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            lifePart.process(gameData, enemy);


            //lifePart.process(gameData, enemy);

           // System.out.println("x = " + positionPart.getX() + " y = " + positionPart.getY());

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
        float radians = 3.1415f / 2;

        //Entity enemy = new Enemy();
        Entity enemy = getRandomEnemy();

        //enemy.add(new EnemyMovingPart(maxSpeed));
        int xCoordinate = getRandomNumber(0, 4000);
        int yCoordinate = getRandomNumber(100, 500);
        enemy.add(new PositionPart(xCoordinate , yCoordinate , radians));
        enemy.add(new LifePart(1));
        enemy.setWidth(115);
        enemy.setRadius(20);
        enemy.add(new LifePart(1));

        //EnemyMovingPart part = enemy.getPart(EnemyMovingPart.class);

        return enemy;

        //world.addEntity(enemy);
    }

    private Entity getRandomEnemy() {
        int randomInt = getRandomNumber(0, 4);
        switch(randomInt) {
            case 0:
                Entity entity1 = new EnemyBat();
                entity1.setHeight(130);
                entity1.setWidth(50);
                entity1.add(new EnemyMovingPart(75));
                entity1.add(new LifePart(5));
                entity1.setType("enemy");
                return entity1;
            case 1:
                Entity entity2 = new EnemyRaven();
                entity2.setHeight(300);
                entity2.setWidth(50);
                entity2.add(new EnemyMovingPart(300));
                entity2.add(new LifePart(10));
                entity2.setType("enemy");
                return entity2;
            case 2:
                Entity entity3 = new EnemySnail();
                entity3.setHeight(186);
                entity3.setWidth(336);
                entity3.add(new EnemyMovingPart(50));
                entity3.add(new LifePart(15));
                entity3.setType("enemy");
                return entity3;
            case 3:
                Entity entity4 = new EnemyZombie();
                entity4.setHeight(100);
                entity4.setWidth(115);
                entity4.add(new EnemyMovingPart(200));
                entity4.add(new LifePart(20));
                entity4.setType("enemy");
                return entity4;
        }
        return null;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
