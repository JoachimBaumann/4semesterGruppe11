package dk.sdu.mmmi.cbse.osgienemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.FileWrite;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnemyPlugin implements IGamePluginService {

    private String enemyID;
    List<Entity> enemies = new ArrayList<>();
    private int currentLevel = 1;


    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        Map<Integer, Integer> waves = waveMap();

        int enemyAmount = waves.get(currentLevel);
        for (int i = 0; i < enemyAmount; i++) {
            Entity e = createEnemy(gameData);
            world.addEntity(e);

        }

        // Add entities to the world
        //Entity enemy = createEnemy(gameData);
        //enemy = world.addEntity(enemy);

        //System.out.println("added enemy object ");
        //LifePart entityLife = enemy.getPart(LifePart.class);
    }



    private Entity createEnemy(GameData gameData){
        //random to spawning position, should be integers between x=(5, 3000) y=(5, 500)
        //random to maxSpeed (50, 150)'

        float deacceleration = 20;
        float acceleration = 50;
        float maxSpeed = getRandomNumber(50, 400);
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float direction = 3.1415f / 2;

        //Entity enemy = new Enemy();
        Entity enemy = getRandomEnemy();

        //enemy.add(new EnemyMovingPart(maxSpeed));
        int xCoordinate = getRandomNumber(0, 4000);
        int yCoordinate = getRandomNumber(100, 500);
        enemy.add(new PositionPart(xCoordinate , yCoordinate , direction));
        enemy.add(new LifePart(1));
        enemy.setWidth(115);
        enemy.setRadius(20);

        //EnemyMovingPart part = enemy.getPart(EnemyMovingPart.class);

        return enemy;
        /*float deacceleration = 20;
        float acceleration = 50;
        float maxSpeed = 120;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float direction = 3.1415f / 2;

        Entity enemy = new Enemy();
        enemy.add(new EnemyMovingPart(maxSpeed));
        enemy.add(new PositionPart(4 , 200 , direction));
        enemy.add(new LifePart(5));
        enemy.setHeight(84);
        enemy.setWidth(115);
        enemy.setRadius(20);
        return  enemy;

         */
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
                entity2.setHeight(300);
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

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static Map<Integer, Integer> waveMap() {
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





    @Override
    public void stop(GameData gameData, World world) {
        String kills = String.valueOf(getKills());
        System.out.println("Game finished, you reached level " + String.valueOf(currentLevel)
                + ", killing " +  kills + " enemies.");
        try {
            String playerID = world.getEntities(Player.class).get(0).getID();
            FileWrite fileWriter = new FileWrite();
            fileWriter.writeToScoresFile(playerID, kills);
        } catch (NullPointerException e) {
            System.out.println("An error occured ending game: " + e.toString());
        }

    }
}
