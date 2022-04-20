package dk.sdu.mmmi.cbse.osgienemy;

import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private String enemyID;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        Entity enemy = createEnemy(gameData);
        enemyID = world.addEntity(enemy);

        System.out.println("added enemy object ");
        LifePart entityLife = enemy.getPart(LifePart.class);
        System.out.println(entityLife);
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    private Entity createEnemy(GameData gameData){
        float deacceleration = 20;
        float acceleration = 100;
        float maxSpeed = 200;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float radians = 6.1415f / 2;

        Entity enemy = new Enemy();
        enemy.add(new EnemyMovingPart(maxSpeed));
        enemy.add(new PositionPart(10 , 500 , radians));
        enemy.add(new LifePart(1));
        //enemy.add(new SpritePart(1, 1, AssetLoader.getAssetPath("enemy1.png")));

        return  enemy;
    }
}
