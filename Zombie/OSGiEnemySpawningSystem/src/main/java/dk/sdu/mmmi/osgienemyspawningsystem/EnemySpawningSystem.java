package dk.sdu.mmmi.osgienemyspawningsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.BossSPI;
import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EnemySpawningSystem implements IEntityProcessingService {

    private int currentLevel = 1;
    private Map<Integer, Integer> waves = waveMap();
    private List<Entity> enemies = new ArrayList<>();
    private List<Entity> bosses = new ArrayList<>();
    public EnemySPI enemyService;
    public BossSPI bossService;
    public boolean firstBossSpawned = false;


    @Override
    public void process(GameData gameData, World world) {
        enemies = world.getEnemies();


        if (enemies.size() == 0 && currentLevel < 10) {
            spawnEnemies(gameData, world);
            if (enemies.size() == 0){
                updateLevel(gameData);
            }
        }
        if (currentLevel == 10 && !firstBossSpawned) {
            spawnBoss(gameData, world);
            bosses = world.getBoss();
            firstBossSpawned = true;
            if (bosses.size() == 0){
                updateLevel(gameData);
            }
        }
        if (currentLevel == 11) {
            gameData.setGameWon(true);
        }
    }

    public void spawnEnemies(GameData gameData, World world) {
        int enemyAmount = waves.get(currentLevel);
        for (int i = 0; i < enemyAmount; i++) {
            if (enemyService != null) {
                Entity enemy = enemyService.createEnemy(gameData);
                world.addEntity(enemy);
            }
        }
    }

    private void spawnBoss(GameData gameData, World world) {
        int bossAmount = waves.get(currentLevel);
        for (int i = 0; i < bossAmount; i++) {
            if (bossService != null) {
                Entity boss = bossService.createBoss(gameData);
                world.addEntity(boss);
            }
        }
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
        Map<Integer, Integer> myMap = new HashMap<>();
        myMap.put(1, 1);
        myMap.put(2, 2);
        myMap.put(3, 3);
        myMap.put(4, 4);
        myMap.put(5, 5);
        myMap.put(6, 6);
        myMap.put(7, 8);
        myMap.put(8, 9);
        myMap.put(9, 0);
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

    public void setBossService(BossSPI bossSPI) {
        this.bossService = bossSPI;
    }

    public void removeBossService(BossSPI bossSPI) {
        this.bossService = null;
    }
}
