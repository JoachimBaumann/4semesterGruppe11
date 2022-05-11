package dk.sdu.mmmi.osgienemyspawningsystem;


import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EnemyMovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.osgienemy.EnemyBat;
import dk.sdu.mmmi.cbse.osgienemy.EnemyRaven;
import dk.sdu.mmmi.cbse.osgienemy.EnemySnail;


import java.util.*;


public class EnemySpawningSystem implements IEntityProcessingService {

    Map<Integer, Integer> waves = createWaveMap();

    private int currentLevel = 1;
    List<Entity> enemies = new ArrayList<>();


    @Override
    public void process(GameData gameData, World world) {
        enemies = world.getEntities(EnemyBat.class);
        enemies.addAll(world.getEntities(EnemyRaven.class));
        enemies.addAll(world.getEntities(EnemySnail.class));
        enemies.addAll(world.getEntities(Enemy.class));

        if (enemies.size() == 0 && currentLevel < 10) {
            createEnemy(gameData, world);

            //spawnEnemies(currentLevel, gameData, world);
            //updateLevel(gameData);
        if (currentLevel == 10) {
            spawnBoss(gameData, world);
            updateLevel(gameData); }
        if (currentLevel >= 10) endGame(gameData, world);
        }
    }

    private void endGame(GameData gameData, World world) {
        System.out.println("Game finished, you reached level " + String.valueOf(currentLevel)
                + ", killing " + String.valueOf(waves.get(currentLevel)) + " enemies."); }


    private void spawnEnemies(int currentLevel, GameData gameData, World world) {
        int enemyAmount = waves.get(currentLevel);
        for (int i = 0; i < enemyAmount; i++) {
            createEnemy(gameData, world);
        }

    }

    private void spawnBoss(GameData gameData, World world) {
        //todo?
    }


    private void updateLevel(GameData gameData) {
        this.currentLevel++;
        gameData.setCurrentLevel(this.currentLevel);
        System.out.println("Current level: " + String.valueOf(currentLevel));
    }

    private static Map<Integer, Integer> createWaveMap() {
        Map<Integer,Integer> myMap = new HashMap<>();
        myMap.put(1, 1);
        myMap.put(2, 2);
        myMap.put(3, 4);
        myMap.put(4, 8);
        myMap.put(5, 12);
        myMap.put(6, 18);
        myMap.put(7, 22);
        myMap.put(8, 26);
        myMap.put(9, 30);
        myMap.put(10, 1);
        return myMap;
    }


    private void createEnemy(GameData gameData, World world){
        //random to spawning position, should be integers between x=(5, 3000) y=(5, 500)
        //random to maxSpeed (50, 150)'

        float deacceleration = 20;
        float acceleration = 50;
        float maxSpeed = getRandomNumber(50, 450);
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float radians = 3.1415f / 2;

        //Entity enemy = new Enemy();
        Entity enemy = getRandomEnemy();

        enemy.add(new EnemyMovingPart(maxSpeed));
        int xCoordinate = getRandomNumber(0, 500);
        int yCoordinate = getRandomNumber(50, 400);
        enemy.add(new PositionPart(xCoordinate , yCoordinate , radians));
        enemy.add(new LifePart(1));
        enemy.setWidth(115);
        enemy.setRadius(20);

        //EnemyMovingPart part = enemy.getPart(EnemyMovingPart.class);

        world.addEntity(enemy);
    }


    private Entity getRandomEnemy() {
        int randomInt = getRandomNumber(0, 4);
        switch(randomInt) {
            case 0:
                Entity entity1 = new EnemyBat();
                entity1.setHeight(50);
                entity1.setWidth(50);
                //entity1.add(new EnemyMovingPart(75));
                return entity1;
            case 1:
                Entity entity2 = new EnemyRaven();
                entity2.setHeight(50);
                entity2.setWidth(50);
                //entity2.add(new EnemyMovingPart(500,0));
                return entity2;
            case 2:
                Entity entity3 = new EnemySnail();
                entity3.setHeight(186);
                entity3.setWidth(336);
                //entity3.add(new EnemyMovingPart(50));
                return entity3;
            case 3:
                Entity entity4 = new Enemy();
                entity4.setHeight(100);
                entity4.setWidth(115);
                //entity4.add(new EnemyMovingPart(200));
                return entity4;
        }
        return null;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
