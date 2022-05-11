package dk.sdu.mmmi.cbse.common.data;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AssetLoader {

    private static final String assetPath = "\\Zombie\\OSGiCommon\\src\\main\\resources\\Assets\\";

    public static String getAssetPath(String fileName) {

        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + assetPath + fileName;
    }
}

