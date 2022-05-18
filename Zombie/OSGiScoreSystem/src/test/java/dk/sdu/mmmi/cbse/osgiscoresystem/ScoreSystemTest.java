package dk.sdu.mmmi.cbse.osgiscoresystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
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

        //test time of insertion
        long start = System.currentTimeMillis();
        TreeMap<String, Integer> treeMapScores = system.loadScoresTree();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Elapsed time to insert into treeMap: " + timeElapsed);

        Assertions.assertNotNull(treeMapScores);
        System.out.println("Iterating over TreeMap");

        for (Map.Entry<String, Integer>
                entry : treeMapScores.entrySet())
            System.out.println(
                    "[" + entry.getKey()
                            + ", " + entry.getValue() + "]");



        start = System.currentTimeMillis();

        HashMap<String, Integer> hashMapScores = system.loadScoresHash();
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("Elapsed time to insert into hashMap: " + timeElapsed);

        Assertions.assertNotNull(hashMapScores);
        System.out.println("Iterating over HashMap");
        for (Map.Entry<String, Integer>
                entry : hashMapScores.entrySet())
            System.out.println(
                    "[" + entry.getKey()
                            + ", " + entry.getValue() + "]");


        System.out.println("Sorted maps ");
        System.out.println("TreeMap");
        System.out.println(system.entriesSortedByValues(treeMapScores));
        System.out.println(" ");

        System.out.println("HashMap");
        System.out.println(system.entriesSortedByValues(hashMapScores));

        System.out.println(" ");
        System.out.println("Current Highscore: ");
        System.out.println(system.getHighScore());
    }

    @Test
    public void stopTest(){

    }
}
