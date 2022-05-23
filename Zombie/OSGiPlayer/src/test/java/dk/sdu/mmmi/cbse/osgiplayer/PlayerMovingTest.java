package dk.sdu.mmmi.cbse.osgiplayer;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PlayerMovingPart;
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
    //Mocks are used to isolate components during tests

    @Test
    public void startTest(){
        System.out.println("start player moving test");


        GameData gameData = new GameData(); //creates a mock object
        World world = new World(); //creates another mock object


        PlayerPlugin instance = new PlayerPlugin(); //creates PlayerPlugin object
        instance.start(gameData, world);

        List<Entity> playerList = world.getEntities(Player.class);
        Entity player = playerList.get(0);

        PositionPart positionPart = player.getPart(PositionPart.class);
        PlayerMovingPart movingPart = player.getPart(PlayerMovingPart.class);

        Assertions.assertEquals(1000f, positionPart.getX());

        gameData.getKeys().setKey(GameKeys.RIGHT, true);
        movingPart.setRight(true);
        movingPart.testProcess(gameData, player);

        //produces nullpointer?? todo: get the part
        positionPart = player.getPart(PositionPart.class);
        Assertions.assertNotEquals(1000f, positionPart.getX());
        System.out.println("Expected position: > 1000f. Actual position" + positionPart.getX());

    }

    @Test
    public void stopTest(){

    }



}
