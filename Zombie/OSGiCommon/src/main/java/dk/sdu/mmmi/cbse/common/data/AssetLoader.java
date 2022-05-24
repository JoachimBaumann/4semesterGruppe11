package dk.sdu.mmmi.cbse.common.data;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AssetLoader {
    public static String whichOS(String path){
        if (Objects.equals(System.getProperty("os.name"), "Mac OS X")){
            System.out.println("True");
            return  path.replace("\\", "/");
        } else {
            System.out.println("false");
            return path;
        }
    }

    public static String getAssetPath(String assetPath, String fileName) {
        //String correctAssetPath = whichOS(assetPath);
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        System.out.println(workingPath + assetPath);
        String path = whichOS(workingPath + assetPath + fileName);
        System.out.println("work: " + workingPath);
        System.out.println("asset: " + assetPath);
        System.out.println("file: " + fileName);
        return path;
        //return workingPath + assetPath + fileName;
    }

}

