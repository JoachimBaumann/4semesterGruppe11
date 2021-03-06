package dk.sdu.mmmi.cbse.osgienemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EnemyMovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private String enemyID;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
       // Entity enemy = createEnemy(gameData);
       // enemyID = world.addEntity(enemy);

       // System.out.println("added enemy object ");

    }

    @Override
    public void stop(GameData gameData, World world) {
        //Remove entities
      //  world.removeEntity(enemyID);

    }

    private Entity createEnemy(GameData gameData){
        float maxSpeed = 120;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float radians = 3.1415f / 2;

        Entity enemy = new Enemy();

        enemy.add(new EnemyMovingPart(maxSpeed));
        enemy.add(new PositionPart(4 , 200 , radians));
        enemy.setHeight(84);
        enemy.setWidth(115);
        enemy.setRadius(20);

        return enemy;

    }
}
