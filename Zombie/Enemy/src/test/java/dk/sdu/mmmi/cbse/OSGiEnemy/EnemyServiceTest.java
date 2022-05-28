package dk.sdu.mmmi.cbse.OSGiEnemy;

import com.badlogic.gdx.Game;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
//import dk.sdu.mmmi.cbse.osgienemy.EnemyPlugin;
import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.osgienemy.EnemySystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EnemyServiceTest {

    List<String> nameList = new ArrayList<>();


    @Test
    public void startTest(){
        nameList.add("Raven");
        nameList.add("Zombie");
        nameList.add("Snail");
        nameList.add("Bat");

        System.out.println("start - enemy");

        GameData gameData = mock(GameData.class); //creates mock object
        World world = mock(World.class); //creates another mock object

        when(world.addEntity(any(Entity.class))).thenReturn("1"); //when addEntity() is called then return "1"

        EnemySPI spi = new EnemySystem();
        Entity entity = spi.createEnemy(gameData);
        world.addEntity(entity);

        verify(world).addEntity(any(Entity.class)); //verifies if any Entity class has been added to the world


        System.out.println("Test random enemies");
        List<Entity> entityList = new ArrayList<>();
        for (int i=0; i < 50; i++) {
            Entity enemy = spi.createEnemy(gameData);
            entityList.add(enemy);
        }
        for (Entity e: entityList) {
            System.out.println("Name:" + e.getName());
            Assertions.assertTrue(nameList.contains(e.getName()));
        }
    }
}
