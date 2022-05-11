package dk.sdu.mmmi.cbse.osgiscoresystem;

import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;


public class ScoreSystem implements IGamePluginService {


    //Different implementations of Maps offers different time complexities - https://www.geeksforgeeks.org/differences-treemap-hashmap-linkedhashmap-java/
    //TreeMap insert, delete, search offers O(log(n)) time
    //HashMap and LinkedHashMap offers 0(n)
    //Choose a structure to hold scores
    TreeMap<Integer, Integer> treeMapScores = new TreeMap<Integer, Integer>(new Comparator<Integer>()
    {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    HashMap<Integer, Integer> hashMapScores = new HashMap<>();



    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
