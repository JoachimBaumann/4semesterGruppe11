package dk.sdu.mmmi.cbse.common.data;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AssetLoader {
    public static String whichOS(String path){
        if (Objects.equals(System.getProperty("os.name"), "Mac OS X")){
            return  path.replaceAll("\\\\", "/");
        } else {
            return path;
        }
    }

    public static String getAssetPath(String assetPath, String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + assetPath + fileName;
    }


    //private static final String windowsPath = "\\Zombie\\OSGiCommon\\src\\main\\resources\\Assets\\";
    // private static final String macPath = "/Zombie/OSGiCommon/src/main/resources/Assets/";

    // private static final String windowsEnemyAssetPath = "\\Zombie\\OSGiCommonEnemy\\src\\main\\resources\\Assets\\";
    // private static final String macEnemyassetPath = "/Zombie/OSGiCommonEnemy/src/main/resources/Assets/";
    ///private static final String commonPlayerassetPath = "\\Zombie\\OSGICommonPlayer\\src\\main\\resources\\Assets\\";
    //private static final String coreassetPath = "\\Zombie\\OSGICore\\src\\main\\resources\\Assets\\";
    //private static final String enemyassetPath = "\\Zombie\\OSGIEnemy\\src\\main\\resources\\Assets\\";
    //private static final String laserSystemassetPath = "\\Zombie\\OSGILaser\\src\\main\\resources\\Assets\\";
    //private static final String playerassetPath = "\\Zombie\\OSGIPlayer\\src\\main\\resources\\Assets\\";

    /*
    get asset path(path_to_ressources_dir_now, filename)

     */


    /*
    public static String getCommonEnemyAssetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + windowsEnemyAssetPath + fileName;
    }*/

   /* public static String getCommonPlayerAssetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + commonPlayerassetPath + fileName;
    }*/
/*
    public static String getCoreAssetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + coreassetPath + fileName;
    }

 */
    /*
    public static String getEnemyAssetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + enemyassetPath + fileName;
    }

     */
    /*public static String getLaserSystemAssetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + laserSystemassetPath + fileName;
    }

     */
    /*public static String getPlayerAssetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + playerassetPath + fileName;
    }

     */
}

