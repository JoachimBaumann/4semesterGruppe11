package dk.sdu.mmmi.cbse.common.data;

import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {

    public void writeToScoresFile(String username, String score) {
        String commonAssetPath = "\\Zombie\\OSGICommon\\src\\main\\resources\\Assets\\";
        String assetPath = AssetLoader.whichOS(commonAssetPath);
        try {
            String path = AssetLoader.getAssetPath(assetPath,"\\scores\\scores.txt");
            FileWriter myWriter = new FileWriter(path, true);
            myWriter.write(username + "," + score + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occured writing to file: " + e.toString());
        }
    }
}