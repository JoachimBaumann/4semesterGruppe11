package dk.sdu.mmmi.cbse.osgiscoresystem;

import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ScorePlugin implements IGamePluginService {

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
        loadScoresHash();
        System.out.println(getHighScore());
        gameData.setCurrentHighScore(getHighScore());
    }

    @Override
    public void stop(GameData gameData, World world) {

    }


    /**
     * Loads comma separated text file and adds entries to Map structure
     */
    public TreeMap<String, Integer> loadScoresTree() {
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

    public HashMap<String, Integer> loadScoresHash() {
        try {
            File scores = new File(path);
            Scanner myReader = new Scanner(scores);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dict = data.split(",");
                String key = dict[0];
                int value = Integer.parseInt(dict[1]);
                hashInsert(key, value);
            }
            myReader.close();
            return hashMapScores; //necessary?
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred reading from file: " + e.toString());
            return null;
        }
    }

    static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1; // Special fix to preserve items with equal values
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }




    public void treeInsert(String key, int value) {
        if (treeMapScores.containsKey(key)) {
            if (value > treeMapScores.get(key)) {
                treeMapScores.put(key, value);
            }
        }
        treeMapScores.put(key, value);
    }

    public void hashInsert(String key, int value) {
        if (hashMapScores.containsKey(key)) {
            if (value > hashMapScores.get(key)) {
                hashMapScores.put(key, value);
            }
        }
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
        return getSortedHashMap().last().toString();
    }

    public SortedSet getSortedHashMap() {
        if (hashMapScores.isEmpty()) {
            loadScoresHash();
        }
        return entriesSortedByValues(hashMapScores);
    }

    public SortedSet getSortedTreeMap() {
        if (treeMapScores.isEmpty()) {
            loadScoresTree();
        }
        return entriesSortedByValues(treeMapScores);
    }




}
