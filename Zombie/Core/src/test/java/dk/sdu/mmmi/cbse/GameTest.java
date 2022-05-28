package dk.sdu.mmmi.cbse;


import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.WorldMap;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameTest {

    @Test
    public void startTest(){
        Game game = mock(Game.class);
        game.init();


        System.out.println("MEMORY TEST - GAME");
        System.out.println("Current size of core Game object");
        System.out.println(ObjectSizeCalculator.getObjectSize(game));

    }

    @Test
    public void stopTest(){

    }

}
