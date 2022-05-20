package dk.sdu.mmmi.cbse.osgiscoresystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.FileWrite;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ScorePostProcessing implements IPostEntityProcessingService {

    private int currentLevel = 1;
    //Map<Integer, Integer> waves = waveMap();
    //List<Entity> enemies = new ArrayList<>();

    // SET because we don't want duplicates
    HashSet deadList = new HashSet<>();
    HashSet aliveList = new HashSet<>();
    //List deadList = new ArrayList<>();

    @Override
    public void process(GameData gameData, World world) {

        /*
        Tilføj alle enemies til en alive-liste, hvor der ikke kan komme duplicates.
        hver process, tjek om de enemie, der er i world også er i alive-listen
        hvis der findes en enemy i alive-listen, som er død, add den enemy til dead-listen
         */

        for (Entity entity : world.getEnemies()){
            aliveList.add(entity.getId());
            LifePart lifePart = entity.getPart(LifePart.class);
            if (lifePart.isDead()){
                deadList.add(entity.getId());
            }
        }

        String kills = String.valueOf(deadList.size());
        System.out.println("Game finished, you reached level " + currentLevel
                + ", killing " +  kills + " enemies.");
        try {
            String playerID = gameData.getPlayerID();
            FileWrite fileWriter = new FileWrite();
            fileWriter.writeToScoresFile(playerID, kills);
            gameData.setPlayerScore(playerID + ", " + kills);
            gameData.setEndGame(true);
        } catch (NullPointerException e) {
            System.out.println("An error occured ending game: " + e.toString());
        }
    }

}
