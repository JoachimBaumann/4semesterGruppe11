package dk.sdu.mmmi.cbse.osgiplayer;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import dk.sdu.mmmi.cbse.common.player.Player;

import java.util.List;

public class PlayerMovingTest {

 /*   @Test
    public void startTest(){
        System.out.println("started player moving test");

        *//*
        Uncomment to try mock objects, hint, not working.
        GameData gameData = mock(GameData.class); //creates a mock object
        World world = mock(World.class); //creates another mock object
        PlayerPlugin instance = mock(PlayerPlugin.class);
         *//*

        GameData gameData = new GameData(); //creates a mock object
        World world = new World(); //creates another mock object


        PlayerPlugin instance = new PlayerPlugin(); //creates PlayerPlugin object
        instance.start(gameData, world);

        List<Entity> playerList = world.getEntities(Player.class);
        Entity player = playerList.get(0);

        PositionPart positionPart = player.getPart(PositionPart.class);
        MovingPart movingPart = player.getPart(MovingPart.class);


        //test current position.x is 1000f
        Assertions.assertEquals(1000f, positionPart.getX());




        //move player to right
        movingPart.setRight(true);
        movingPart.testProcess(gameData, player);
        movingPart.setRight(false);

        positionPart = player.getPart(PositionPart.class);
        Assertions.assertNotEquals(1000f, positionPart.getX());
        System.out.println("Expected position: > 1000f. Actual position: " + positionPart.getX());

        //reset x to start value
        positionPart.setX(1000f);


        //move player to left
        movingPart.setLeft(true);
        movingPart.testProcess(gameData, player);
        movingPart.setLeft(false);


        positionPart = player.getPart(PositionPart.class);
        Assertions.assertNotEquals(1000f, positionPart.getX());
        System.out.println("Expected position: < 1000f. Actual position: " + positionPart.getX());


        //make player jump
        movingPart.setUp(true);
        movingPart.testProcess(gameData, player);
        movingPart.setUp(false);

        positionPart = player.getPart(PositionPart.class);
        Assertions.assertNotEquals(0, positionPart.getY());
        System.out.println("Expected position > 0. Actual position: " + positionPart.getY());

    }
*/
    @Test
    public void stopTest(){

    }



}
