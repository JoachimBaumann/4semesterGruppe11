package dk.sdu.mmmi.cbse.osgiscoresystem;

import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ScoreSystem implements IGamePluginService {

    private static final String commonAssetPath = "\\Zombie\\OSGICommon\\src\\main\\resources\\Assets\\";
    private static final String assetPath = AssetLoader.whichOS(commonAssetPath);

    private String path = AssetLoader.getAssetPath(assetPath,"\\scores\\scores.txt");

    //Different implementations of Maps offers different time complexities - https://www.geeksforgeeks.org/differences-treemap-hashmap-linkedhashmap-java/
    //TreeMap insert, delete, search offers O(log(n)) time
    //A red-black tree is a self-balancing binary search tree where each node has an extra bit, and that bit is often interpreted as the colour (red or black).
    // These colours are used to ensure that the tree remains balanced during insertions and deletions. Although the balance of the tree is not perfect,
    // it is good enough to reduce the searching time and maintain it around O(log n) time, where n is the total number of elements in the tree.
    //HashMap and LinkedHashMap offers O(n)
    //Choose a structure to hold scores

    private TreeMap<String, Integer> treeMapScores = new TreeMap<>();

    private HashMap<String, Integer> hashMapScores = new HashMap<>();

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        loadScores();

    }


    /**
     * Loads comma separated text file and adds entries to Map structure
     */
    public TreeMap<String, Integer> loadScores() {
        try {
            File scores = new File(path);
            Scanner myReader = new Scanner(scores);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dict = data.split(",");
                String key = dict[0];
                int value = Integer.parseInt(dict[1]);
                treeInsert(key, value);
                System.out.println("User: " + key + ", Kills: " + dict[1] + "");
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


    //According to the implementation in the Open JDK, it (lastKey()) is O(log N):
    //
    //public K lastKey() {
    //    return key(getLastEntry());
    //}
    //final Entry<K,V> getLastEntry() {
    //    Entry<K,V> p = root;
    //    if (p != null)
    //        while (p.right != null)
    //            p = p.right;
    //    return p;
    //}

    public String getHighScore() {
        return treeMapScores.lastKey();
    }


}
