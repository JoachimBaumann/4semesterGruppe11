package dk.sdu.mmmi.cbse.common.data;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AssetLoader {
    public static String whichOS(String path){
        if (Objects.equals(System.getProperty("os.name"), "Mac OS X")){
            String newPath = path.replace("\\", "/");
            return  newPath;
        } else {
            return path;
        }
    }

    public static String getAssetPath(String assetPath, String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        String path = whichOS(workingPath + assetPath + fileName);
        return path;
    }

}

