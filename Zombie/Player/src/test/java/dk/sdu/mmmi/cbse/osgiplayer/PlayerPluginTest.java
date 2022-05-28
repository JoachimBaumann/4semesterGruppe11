package dk.sdu.mmmi.cbse.osgiplayer;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerPluginTest {
    //Mocks are used to isolate components during tests

    @Test
    public void startTest(){
        System.out.println("start");

        GameData gameData = mock(GameData.class); //creates a mock object
        World world = mock(World.class); //creates another mock object
        when(world.addEntity(any(Entity.class))).thenReturn("1"); //when addEntity() is called then return "1"

        PlayerPlugin instance = new PlayerPlugin(); //creates PlayerPlugin object
        instance.start(gameData, world);

        verify(world).addEntity(any(Entity.class)); //verifies that the method is called with the right parameters
        //behavioral testing - verifies that a certain behavior happened once


    }

    @Test
    public void stopTest(){

    }
}
