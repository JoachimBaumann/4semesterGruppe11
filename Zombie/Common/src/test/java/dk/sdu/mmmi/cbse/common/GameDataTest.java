package dk.sdu.mmmi.cbse.common;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GameDataTest {

    @Test
    public void startTest(){
        System.out.println("start - enemy");

        GameData gameData = new GameData(); //creates mock object


        System.out.println("Assert Game is won or lost");
        gameData.setGameWon(true);

        Assertions.assertTrue(gameData.isGameWon());
        Assertions.assertFalse(gameData.isGameLost());

        System.out.println("Assert displaySize is correctly set");
        gameData.setDisplayWidth(1920);
        gameData.setDisplayHeight(1080);

        Assertions.assertNotEquals(0, gameData.getDisplayHeight());
        Assertions.assertNotEquals(0, gameData.getDisplayWidth());


        System.out.println("Assert gamekeys can be set and accessed.");
        gameData.getKeys().setKey(GameKeys.LEFT, true);
        Assertions.assertTrue(gameData.getKeys().isDown(GameKeys.LEFT));

    }

    @Test
    public void stopTest(){

    }
}
