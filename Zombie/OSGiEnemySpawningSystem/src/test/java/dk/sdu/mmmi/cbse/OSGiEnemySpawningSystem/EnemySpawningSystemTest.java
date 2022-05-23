package dk.sdu.mmmi.cbse.OSGiEnemySpawningSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.osgienemyspawningsystem.EnemySpawningSystem;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.junit.jupiter.api.Test;

import static java.lang.System.in;
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


       // System.out.println("World memory size before adding enemies: ");
       // System.out.println(ObjectSizeCalculator.getObjectSize(world));


        EnemySpawningSystem instance = new EnemySpawningSystem();

        System.out.println("Memory size of Spawning system: ");
        System.out.println(ObjectSizeCalculator.getObjectSize(instance));



    }

    @Test
    public void stopTest(){

    }


}
