package dk.sdu.mmmi.cbse.osgiscoresystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.FileWrite;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.player.Player;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.HashSet;


public class ScoreControlSystem implements IEntityProcessingService {

    HashSet aliveList = new HashSet<>(); // HashSet because we don't want duplicates
    private Boolean isWritten = false;


    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEnemies()) {
            aliveList.add(entity);
            gameData.setPlayerScore(String.valueOf(aliveList.size()));
        }
        if (gameData.isGameWon() || gameData.isGameLost()) {
            String kills = String.valueOf(aliveList.size() - world.getEnemies().size());

            if (!isWritten) {
                try {
                    for (Entity entity : world.getEntities(Player.class)) {
                        String playerID = entity.getId();
                        FileWrite fileWriter = new FileWrite();
                        fileWriter.writeToScoresFile(playerID, kills);
                        gameData.setPlayerScore(playerID + ", " + kills);
                        isWritten = true;
                    }
                } catch (NullPointerException e) {
                    System.out.println("An error occured ending game: " + e.toString());
                }
            }
        }
    }
}
