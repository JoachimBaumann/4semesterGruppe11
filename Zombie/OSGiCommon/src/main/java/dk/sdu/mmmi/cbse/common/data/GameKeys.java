package dk.sdu.mmmi.cbse.common.data;


import java.util.ArrayList;
import java.util.Collections;

public class GameKeys {

    private static boolean[] keys;
    private static boolean[] pkeys;

    private static final int NUM_KEYS = 8;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int ENTER = 4;
    public static final int ESCAPE = 5;
    public static final int SPACE = 6;
    public static final int SHIFT = 7;


    public static ArrayList allKeys(){
        ArrayList<Integer> any = new ArrayList<Integer>();
        Collections.addAll(any,1, 2, 3, 4, 5, 6, 7, 8 );
        return any;

    }


    public GameKeys() {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];

    }

    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
    }

    public void setKey(int k, boolean b) {
        keys[k] = b;
    }

    public boolean isDown(int k) {
        return keys[k];
    }

    public boolean isPressed(int k) {
        return keys[k] && !pkeys[k];
    }

    public boolean isReleased(int k){
        return !isPressed(k);
    }

    public boolean isReleased(ArrayList keys){
        for (int i = 0; i < keys.size(); i++) {
            if (this.isReleased(i)){
                return false;
            }
        }
        return true;
    }

}
