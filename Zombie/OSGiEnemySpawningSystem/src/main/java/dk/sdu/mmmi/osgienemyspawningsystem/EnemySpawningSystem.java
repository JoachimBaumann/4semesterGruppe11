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


import java.util.*;


public class EnemySpawningSystem implements IEntityProcessingService {

    Map<Integer, Integer> waves = createWaveMap();

    private int currentLevel = 1;
    List<Entity> enemies = new ArrayList<>();


    @Override
    public void process(GameData gameData, World world) throws InterruptedException {
        enemies = world.getEntities(Enemy.class);
        if (enemies.size() == 0 && currentLevel < 10) {
            wait(5000);
            spawnEnemies(currentLevel, gameData, world);
            updateLevel(gameData);
        if (currentLevel == 10) {
            spawnBoss(gameData, world);
            updateLevel(gameData); }
        if (currentLevel >= 10) endGame(gameData, world);
        }
    }

    private void endGame(GameData gameData, World world) {

    }


    private void spawnEnemies(int currentLevel, GameData gameData, World world) {
        int enemyAmount = waves.get(currentLevel);
        for (int i = 0; i < enemyAmount; i++) {
            createEnemy(gameData, world);
        }

    }

    private void spawnBoss(GameData gameData, World world) {

    }


    private void updateLevel(GameData gameData) {
        this.currentLevel++;
        gameData.setCurrentLevel(this.currentLevel);
        System.out.println("Current level: " + String.valueOf(currentLevel));
    }

    private static Map<Integer, Integer> createWaveMap() {
        Map<Integer,Integer> myMap = new HashMap<Integer,Integer>();
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

        Entity enemy = new Enemy();
        //Entity enemy = getRandomEnemy();

        enemy.add(new EnemyMovingPart(maxSpeed));
        int xCoordinate = getRandomNumber(0, 2000);
        int yCoordinate = getRandomNumber(10, 1500);
        enemy.add(new PositionPart(xCoordinate , yCoordinate , radians));
        enemy.add(new LifePart(5));
        enemy.setHeight(84);
        enemy.setWidth(115);
        enemy.setRadius(20);
        world.addEntity(enemy);
    }




    //todo: Implement random enemy selector
    private Entity getRandomEnemy() {
        int randomInt = getRandomNumber(0, 3);

        switch(randomInt) {
            case 0:
                Entity entity = new Enemy();
                return entity;
            case 1:
                Entity entity2 = new Enemy();
                return entity2;
            case 2:
                Entity entity3 = new Enemy();
                return entity3;
            case 3:
                Entity entity4 = new Enemy();
                return entity4;
        }
        return null;
    }


    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
