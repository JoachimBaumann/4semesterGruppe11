package dk.sdu.mmmi.cbse.osgimap;

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

public class MapPluginTest {
    //Mocks are used to isolate components during tests

    @Test
    public void startTest(){
        System.out.println("start");

        GameData gameData = new GameData(); //creates a mock object
        World world = new World(); //creates another mock object


        //assert world is not set
        Assertions.assertNull(world.getWorldMap());


        MapPlugin mapPlugin = new MapPlugin();
        mapPlugin.start(gameData, world);

        //assert world is set
        Assertions.assertNotNull(world.getWorldMap());


        /*
        Given an object, returns the total allocated size, in bytes, of the object
         * and all other objects reachable from it.  Attempts to to detect the current JVM memory layout,
        * but may fail with {@link UnsupportedOperationException};
         */

        MapImpl map = mock(MapImpl.class);
        map.create();
        System.out.println(" ");
        System.out.println("MEMORY TEST - MAP");
        System.out.println("Current MapPlugin object size");
        System.out.println(ObjectSizeCalculator.getObjectSize(mapPlugin));
        System.out.println("Current MapImpl object size");
        System.out.println(ObjectSizeCalculator.getObjectSize(map));

    }

    @Test
    public void testDynamics() {

    }

    @Test
    public void stopTest(){

    }
}
