package dk.sdu.mmmi.cbse.common.data;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AssetLoader {

    private static final String macPath = "/Zombie/OSGiCommon/src/main/resources/Assets/";
    private static final String windowsPath = "\\Zombie\\OSGiCommon\\src\\main\\resources\\Assets\\";

    public static String getAssetPath(String fileName) {
        String assetPath;

        if (Objects.equals(System.getProperty("os.name"), "Mac OS X")){
            assetPath = macPath;
        } else {
            assetPath = windowsPath;
        }

        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + assetPath + fileName;
    }
}



