package dk.sdu.mmmi.osgienemyspawningsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EnemySpawningSystem implements IEntityProcessingService {

    private int currentLevel = 1;
    private Map<Integer, Integer> waves = waveMap();
    private List<Entity> enemies = new ArrayList<>();
    public EnemySPI enemyService;


    @Override
    public void process(GameData gameData, World world) {
        enemies = world.getEnemies();

        if (enemies.size() == 0 && currentLevel < 10) {
            spawnEnemies(gameData, world);
            updateLevel(gameData);
        }
        if (currentLevel == 10) {
            spawnBoss(gameData, world);
            updateLevel(gameData);
        }
        if (currentLevel == 11) {
            gameData.setGameWon(true);
        }
    }

    public void spawnEnemies(GameData gameData, World world) {
        int enemyAmount = waves.get(currentLevel);
        for (int i = 0; i < enemyAmount; i++) {
            System.out.println("enemyservice" + enemyService);

            if (enemyService != null) {
                Entity enemy = enemyService.createEnemy(gameData);
                System.out.println(enemy);
                System.out.println(gameData);
                world.addEntity(enemy);
            }
        }
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

    public int getCurrentLevel() {
        return currentLevel;
    }

    private static Map<Integer, Integer> waveMap() {
        Map<Integer,Integer> myMap = new HashMap<>();
        myMap.put(1, 1);
        myMap.put(2, 2);
        myMap.put(3, 3);
        myMap.put(4, 4);
        myMap.put(5, 5);
        myMap.put(6, 6);
        myMap.put(7, 8);
        myMap.put(8, 9);
        myMap.put(9, 10);
        myMap.put(10, 1);
        myMap.put(11, 0);
        return myMap;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setEnemyService(EnemySPI enemySPI) {
        this.enemyService = enemySPI;
    }

    public void removeEnemyService(EnemySPI weaponSPI) {
        this.enemyService = null;
    }
}
