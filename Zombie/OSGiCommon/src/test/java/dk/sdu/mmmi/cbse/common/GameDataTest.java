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

        GameData gameData = mock(GameData.class); //creates mock object


        System.out.println("Assert Game is won or lost");
        gameData.setGameWon(true);
        Assertions.assertTrue(gameData.isGameWon());
        Assertions.assertFalse(gameData.isGameLost());

        System.out.println("Assert displaySize is correctly set");
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        Assertions.assertEquals(Gdx.graphics.getWidth(), gameData.getDisplayWidth());
        Assertions.assertEquals(Gdx.graphics.getHeight(), gameData.getDisplayHeight());


        System.out.println("Assert gamekeys can be set and accessed.");
        gameData.getKeys().setKey(GameKeys.LEFT, true);
        Assertions.assertTrue(gameData.getKeys().isDown(GameKeys.LEFT));

    }

    @Test
    public void stopTest(){

    }
}
