package dk.sdu.mmmi.cbse.common;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;


import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



public class LifePartTest {

    int startLife = 10;

    @Test
    public void startTest() {


        GameData gameData = mock(GameData.class); //creates a mock object
        Entity entity = mock(Entity.class);


        LifePart lifePart = new LifePart(10);

        lifePart.processTest();
        System.out.println(lifePart.getLife());
        Assertions.assertEquals(startLife, lifePart.getLife());
        /*
        lifePart = new LifePart(10);
        lifePart.setIsHit(true);
        lifePart.processTest();
        System.out.println(lifePart.getLife());
        //hvorfor fuck vil du ikke virke din skiderik
        Assertions.assertEquals(startLife-1, lifePart.getLife());

         */



    }

}
