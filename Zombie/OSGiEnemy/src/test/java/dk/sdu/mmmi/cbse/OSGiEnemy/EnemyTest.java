package dk.sdu.mmmi.cbse.OSGiEnemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class EnemyTest {

    @Test
    public void startTest(){
        System.out.println("EnemySpawningSystemTest");

        GameData gameData = mock(GameData.class); //creates a mock object
        World world = mock(World.class); //creates another mock object

        List<Entity> enemyList = loadEnemies();

        System.out.println("Current object size of List of enemies: ");

        System.out.println(ObjectSizeCalculator.getObjectSize(enemyList));
    }

    @Test
    public void stopTest(){

    }


    public List<Entity> loadEnemies() {
        Entity enemy1 = new Enemy();
        enemy1.add(new PositionPart(0 , 0 , 1));
        enemy1.add(new LifePart(1));
        enemy1.setWidth(115);
        enemy1.setRadius(20);

        Entity enemy2 = new Enemy();
        enemy2.add(new PositionPart(0 , 0 , 1));
        enemy2.add(new LifePart(1));
        enemy2.setWidth(115);
        enemy2.setRadius(20);

        Entity enemy3 = new Enemy();
        enemy3.add(new PositionPart(0 , 0 , 1));
        enemy3.add(new LifePart(1));
        enemy3.setWidth(115);
        enemy3.setRadius(20);

        List<Entity> enemyList = new ArrayList<>();
        enemyList.add(enemy1);
        enemyList.add(enemy2);
        enemyList.add(enemy3);

        return enemyList;

    }

}
