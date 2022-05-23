package dk.sdu.mmmi.cbse.osgiboss;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EnemyMovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class BossPlugin implements IGamePluginService {

    private String enemyID;


    public BossPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {
        Entity boss = createBoss(gameData);
        System.out.println("added boss Component");
        boss.setType("enemy");
        world.addEntity(boss);

    }

    private Entity createBoss(GameData gameData) {

        float speed;
        float x;
        Entity boss = new Boss();
        boss.add(new PositionPart(2400, 150f, 0));
        boss.add(new LifePart(100));
        boss.add(new EnemyMovingPart(50, 0));
        boss.setHeight(315f);
        boss.setWidth(315f);



        return boss;
    }

    @Override
    public void stop(GameData gameData, World world) {


    }
}
