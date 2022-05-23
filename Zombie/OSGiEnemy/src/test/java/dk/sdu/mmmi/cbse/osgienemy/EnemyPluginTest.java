package dk.sdu.mmmi.cbse.OSGiEnemy;

import com.badlogic.gdx.Game;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.osgienemy.EnemyPlugin;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EnemyPluginTest {

    @Test
    public void startTest(){
        System.out.println("start - enemy");

        GameData gameData = mock(GameData.class); //creates mock object
        World world = mock(World.class); //creates another mock object

        when(world.addEntity(any(Entity.class))).thenReturn("1"); //when addEntity() is called then return "1"

        EnemyPlugin instance = new EnemyPlugin(); //creates EnemyPlugin object
        instance.start(gameData, world); //uses the start method from the EnemyPlugin class

        verify(world).addEntity(any(Entity.class)); //verifies if any Entity class has been added to the world
    }

    @Test
    public void stopTest(){
        System.out.println("stop - enemy");

        GameData gameData = mock(GameData.class);
        World world = mock(World.class);

        Entity enemy = new Entity(); //creates Entity object 'enemy'
        String enemyID = world.addEntity(enemy); //adds enemy to the world through the enemyID variable

        when(world.getEntity(enemyID)).thenReturn(enemy); //when getEntity() is called the enemy object is returned

        EnemyPlugin instance = new EnemyPlugin(); //creates EnemyPlugin object
        instance.stop(gameData, world); //calls the stop method from the EnemyPlugin class

        verify(world).removeEntity(enemyID); //verifies if the enemyID (enemy) has been removed

    }

}
