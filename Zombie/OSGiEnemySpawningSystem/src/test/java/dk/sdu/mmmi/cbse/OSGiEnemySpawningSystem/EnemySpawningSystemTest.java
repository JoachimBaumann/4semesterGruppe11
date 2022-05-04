package dk.sdu.mmmi.cbse.OSGiEnemySpawningSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



public class EnemySpawningSystemTest {

    @Test
    public void startTest(){
        System.out.println("EnemySpawningSystemTest");

        GameData gameData = mock(GameData.class); //creates a mock object
        World world = mock(World.class); //creates another mock object

        EnemySpawningSystemTest instance = new EnemySpawningSystemTest();



    }

    @Test
    public void stopTest(){

    }


}
