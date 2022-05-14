package dk.sdu.mmmi.cbse.osgiscoresystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Map;
import java.util.TreeMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ScoreSystemTest {

    @Test
    public void startTest(){
        ScoreSystem system = new ScoreSystem();

        TreeMap<String, Integer> treeMapScores = system.loadScores();

        //iterate over map
        Assertions.assertNotNull(treeMapScores);
        System.out.println("Iterating over Map");
        for (Map.Entry<String, Integer>
                entry : treeMapScores.entrySet())
            System.out.println(
                    "[" + entry.getKey()
                            + ", " + entry.getValue() + "]");

    }

    @Test
    public void stopTest(){

    }
}
