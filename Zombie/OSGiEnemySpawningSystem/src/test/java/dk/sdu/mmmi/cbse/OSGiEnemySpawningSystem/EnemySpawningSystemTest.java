package dk.sdu.mmmi.cbse.OSGiEnemySpawningSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.osgienemyspawningsystem.EnemySpawningSystem;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;




public class EnemySpawningSystemTest {

    @Test
    public void startTest(){
        System.out.println("EnemySpawningSystemTest");

        GameData gameData = mock(GameData.class); //creates a mock object
        World world = mock(World.class); //creates another mock object

        EnemySpawningSystem instance = new EnemySpawningSystem();
        instance.spawnEnemies(gameData, world);

        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(EnemySpawningSystem.class).toPrintable());
    }

    @Test
    public void stopTest(){

    }


}
