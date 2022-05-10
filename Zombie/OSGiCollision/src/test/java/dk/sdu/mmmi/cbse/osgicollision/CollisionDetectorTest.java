package dk.sdu.mmmi.cbse.osgicollision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CollisionDetectorTest {


    @Test
    public void startTest(){
        //instantiate objects
        Entity enemy = new Enemy();
        Entity player = new Player();
        enemy.setWidth(50);
        enemy.setHeight(50);
        player.setHeight(50);
        player.setWidth(50);
        enemy.add(new PositionPart(100, 1, 40));
        player.add(new PositionPart(99, 0, 40));

        //Instance of new detector
        CollisionDetector detector = new CollisionDetector();

        //assertTrue, check if method returns True
        assertTrue(detector.Collides(enemy, player));

        Entity enemy2 = new Enemy();
        Entity player2 = new Player();
        enemy2.add(new PositionPart(500, 0, 20));
        player2.add(new PositionPart(0, 0, 20));

        //assertFalse, check if method returns False.
        assertFalse(detector.Collides(enemy2, player2));
    }

    @Test
    public void stopTest(){

    }
}


