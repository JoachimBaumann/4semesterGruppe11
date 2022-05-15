package dk.sdu.mmmi.cbse.common.data;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AssetLoader {

    private static final String assetPath = "\\Zombie\\OSGiCommon\\src\\main\\resources\\Assets\\";
    private static final String commonEnemyassetPath = "\\Zombie\\OSGiCommonEnemy\\src\\main\\resources\\Assets\\";
    private static final String commonPlayerassetPath = "\\Zombie\\OSGICommonPlayer\\src\\main\\resources\\Assets\\";
    private static final String coreassetPath = "\\Zombie\\OSGICore\\src\\main\\resources\\Assets\\";
    private static final String enemyassetPath = "\\Zombie\\OSGIEnemy\\src\\main\\resources\\Assets\\";
    private static final String laserSystemassetPath = "\\Zombie\\OSGILaser\\src\\main\\resources\\Assets\\";
    private static final String playerassetPath = "\\Zombie\\OSGIPlayer\\src\\main\\resources\\Assets\\";
    private static final String enemySpawningassetPath = "\\Zombie\\OSGIEnemyTracking\\src\\main\\resources\\Assets\\";


    public static String getAssetPath(String fileName) {

        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + assetPath + fileName;
    }
    public static String getCommonEnemyAssetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + commonEnemyassetPath + fileName;
    }
    public static String getCommonPlayerAssetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + commonPlayerassetPath + fileName;
    }
    public static String getCoreAssetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + coreassetPath + fileName;
    }
    public static String getEnemyAssetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + enemyassetPath + fileName;
    }
    public static String getLaserSystemAssetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + laserSystemassetPath + fileName;
    }
    public static String getPlayerAssetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + playerassetPath + fileName;
    }
    public static String getEnemySpawningassetPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String workingPath = currentRelativePath.toAbsolutePath().getParent().getParent().toString();
        return workingPath + enemySpawningassetPath + fileName;
    }
}

