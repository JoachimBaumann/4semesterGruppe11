package dk.sdu.mmmi.osgienemyspawningsystem;


import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EnemyMovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.player.Player;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.osgienemy.EnemyBat;
import dk.sdu.mmmi.cbse.osgienemy.EnemyRaven;
import dk.sdu.mmmi.cbse.osgienemy.EnemySnail;
import dk.sdu.mmmi.cbse.osgienemy.EnemyZombie;


import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class EnemySpawningSystem implements IEntityProcessingService {
    private int currentLevel = 1;
    Map<Integer, Integer> waves = createWaveMap();
    List<Entity> enemies = new ArrayList<>();
    private static final String commonAssetPath = "\\Zombie\\OSGICommon\\src\\main\\resources\\Assets\\";
    private static final String assetPath = AssetLoader.whichOS(commonAssetPath);


    @Override
    public void process(GameData gameData, World world) {
        enemies = world.getEntities(EnemyBat.class);
        enemies.addAll(world.getEntities(EnemyRaven.class));
        enemies.addAll(world.getEntities(EnemySnail.class));
        enemies.addAll(world.getEntities(EnemyZombie.class));


        if (enemies.size() == 0 && currentLevel < 10) {
            spawnEnemies(gameData, world);
            updateLevel(gameData);
        }
        if (currentLevel == 10) {
            spawnBoss(gameData, world);
            updateLevel(gameData);
        }
        if (currentLevel == 11) {
            endGame(gameData, world);
            updateLevel(gameData);
        }
        if (currentLevel == 12) {
            currentHighscore();
        }
    }

    private void endGame(GameData gameData, World world) {
        String kills = String.valueOf(getKills());
        System.out.println("Game finished, you reached level " + String.valueOf(currentLevel)
                + ", killing " +  kills + " enemies.");
        try {
            String playerID = world.getEntities(Player.class).get(0).getID();
            writeToFile(playerID, kills);
        } catch (NullPointerException e) {
            System.out.println("An error occured ending game: " + e.toString());
        }
    }

    private int getKills() {
        int totalKills = 0;
        for (int i = 1; i < currentLevel; i++) {
            totalKills += waves.get(i);
        }
        return totalKills;
    }


    public void writeToFile(String username, String score) {
        try {
            String path = AssetLoader.getAssetPath(assetPath,"\\scores\\scores.txt");
            FileWriter myWriter = new FileWriter(path, true);
            myWriter.write(username + "," + score + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occured writing to file: " + e.toString());
        }
    }


    private void spawnEnemies(GameData gameData, World world) {
        int enemyAmount = waves.get(currentLevel);
        for (int i = 0; i < enemyAmount; i++) {
            createEnemy(gameData, world);
        }

    }

    private void currentHighscore() {

    }

    private void spawnBoss(GameData gameData, World world) {
        //todo?
        System.out.println("boss spawned");
    }


    private void updateLevel(GameData gameData) {
        currentLevel++;
        gameData.setCurrentLevel(currentLevel);
        System.out.println("Current level: " + String.valueOf(currentLevel));
    }

    private static Map<Integer, Integer> createWaveMap() {
        Map<Integer,Integer> myMap = new HashMap<>();
        myMap.put(1, 1);
        myMap.put(2, 2);
        myMap.put(3, 10);
        myMap.put(4, 4);
        myMap.put(5, 5);
        myMap.put(6, 6);
        myMap.put(7, 8);
        myMap.put(8, 10);
        myMap.put(9, 14);
        myMap.put(10, 1);
        myMap.put(11, 0);
        return myMap;
    }


    private void createEnemy(GameData gameData, World world){
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

        //EnemyMovingPart part = enemy.getPart(EnemyMovingPart.class);

        world.addEntity(enemy);
    }


    private Entity getRandomEnemy() {
        int randomInt = getRandomNumber(0, 4);
        switch(randomInt) {
            case 0:
                Entity entity1 = new EnemyBat();
                entity1.setHeight(70);
                entity1.setWidth(50);
                entity1.add(new EnemyMovingPart(75));
                entity1.setType("enemy");
                return entity1;
            case 1:
                Entity entity2 = new EnemyRaven();
                entity2.setHeight(50);
                entity2.setWidth(50);
                entity2.add(new EnemyMovingPart(300));
                entity2.setType("enemy");
                return entity2;
            case 2:
                Entity entity3 = new EnemySnail();
                entity3.setHeight(186);
                entity3.setWidth(336);
                entity3.add(new EnemyMovingPart(50));
                entity3.setType("enemy");
                return entity3;
            case 3:
                Entity entity4 = new EnemyZombie();
                entity4.setHeight(100);
                entity4.setWidth(115);
                entity4.add(new EnemyMovingPart(200));
                entity4.setType("enemy");
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
