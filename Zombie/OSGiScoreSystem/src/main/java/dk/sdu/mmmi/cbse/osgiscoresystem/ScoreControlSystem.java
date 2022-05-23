package dk.sdu.mmmi.cbse.osgiscoresystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.FileWrite;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.HashSet;


public class ScoreControlSystem implements IEntityProcessingService {

    HashSet aliveList = new HashSet<>(); // HashSet because we don't want duplicates

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEnemies()){
            aliveList.add(entity);
        }
        if (gameData.isEndGame()){
            String kills = String.valueOf(aliveList.size() - world.getEnemies().size());
            System.out.println("Game finished, you reached level " + gameData.getCurrentLevel()
                + ", killing " +  kills + " enemies.");
        try {
            String playerID = gameData.getPlayerID();
            FileWrite fileWriter = new FileWrite();
            fileWriter.writeToScoresFile(playerID, kills);
            gameData.setPlayerScore(playerID + ", " + kills);
            gameData.setEndGame(true);
        } catch (NullPointerException e) {
            System.out.println("An error occured ending game: " + e.toString());
        }}
    }

}
