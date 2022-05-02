package dk.sdu.mmmi.cbse.osgiplayer;

import com.badlogic.gdx.Game;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerPluginTest {
    //Mocks are used to isolate components during tests

    @Test
    public void startTest(){
        System.out.println("start - player");

        GameData gameData = mock(GameData.class); //creates a mock object
        World world = mock(World.class); //creates another mock object
        when(world.addEntity(any(Entity.class))).thenReturn("1"); //when addEntity() is called then return "1"

        PlayerPlugin instance = new PlayerPlugin(); //creates PlayerPlugin object
        instance.start(gameData, world); //uses the start method from the PlayerPlugin class

        verify(world).addEntity(any(Entity.class));
        //verifies if any Entity class has been added to the world
        //behavioral testing - verifies that a certain behavior happened once

    }

    @Test
    public void stopTest(){
        /**
         * In doubt on whether this method works correctly or not, but I am 99% sure that it does.
         */
        System.out.println("stop - player");

        GameData gameData = mock(GameData.class);
        World world = mock(World.class);

        Entity player = new Entity(); //creates Entity object 'player'
        String playerID = world.addEntity(player); //adds player to the world through the playerID variable

        when(world.getEntity(playerID)).thenReturn(player); //when getEntity() is called the player object is returned

        PlayerPlugin instance = new PlayerPlugin(); //creates PlayerPlugin object
        instance.stop(gameData, world); //calls the stop method from the PlayerPlugin class

        verify(world).removeEntity(playerID); //verifies if playerID (player) has been removed

    }
}
