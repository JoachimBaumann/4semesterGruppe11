package dk.sdu.mmmi.cbse.osgiscoresystem;

import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class ScoreSystem implements IGamePluginService {


    private String path = AssetLoader.getAssetPath("\\scores\\scores.txt");

    //Different implementations of Maps offers different time complexities - https://www.geeksforgeeks.org/differences-treemap-hashmap-linkedhashmap-java/
    //TreeMap insert, delete, search offers O(log(n)) time
    //HashMap and LinkedHashMap offers 0(n)
    //Choose a structure to hold scores

    private TreeMap<String, Integer> treeMapScores = new TreeMap<>();

    private HashMap<String, Integer> hashMapScores = new HashMap<>();

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {

    }


    /**
     * Loads comma separated text file and adds entries to Map structure
     */
    public TreeMap<String, Integer> loadScores() {
        String lines;
        try {
            File scores = new File(path);
            Scanner myReader = new Scanner(scores);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dict = data.split(",");
                String key = dict[0];
                int value = Integer.parseInt(dict[1]);
                treeInsert(key, value);
            }
            myReader.close();
            return treeMapScores;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred reading from file: " + e.toString());
            return null;
        }
    }


    private void treeInsert(String key, int value) {
        treeMapScores.put(key, value);
    }

    private void hashInsert(String key, int value) {
        hashMapScores.put(key, value);
    }
}
