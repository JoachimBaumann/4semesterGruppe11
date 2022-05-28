package dk.sdu.mmmi.cbse.osgiplayer;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.player.Player;



public class PlayerPlugin extends Player implements IGamePluginService {

    private static final String playerAssetPath = "\\Zombie\\Player\\src\\main\\resources\\Assets";

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        Entity player = createPlayer(gameData);
        world.addEntity(player);

    }

    private Entity createPlayer(GameData gameData) {
        float maxSpeed = 600f;
        float x = gameData.getDisplayWidth();
        float y = gameData.getDisplayHeight();
        float direction = 6.28f;

        Entity playerShip = new Player();
        playerShip.add(new MovingPart(maxSpeed));
        playerShip.add(new PositionPart(1000, 500, direction));
        playerShip.add(new LifePart(1000));
        playerShip.setHeight(84);
        playerShip.setWidth(115);
        playerShip.setRadius(20);
        playerShip.setType("player");

        playerShip.setEntityAssetPath(playerAssetPath);

        // walk
        playerShip.setFrameDuration(1f/6f);
        playerShip.setLeftAssetPath("/PlayerLeft/flippedPlayerWalk.txt");
        playerShip.setRightAssetPath("/PlayerRight/playerwalk.txt");

        // jump
        playerShip.setJumpFrameDuration(1f / 3f);
        playerShip.setLeftJumpAssetPath("/PlayerLeft/flippedPlayerJump.txt");
        playerShip.setRightJumpAssetPath("/PlayerRight/playerjump.txt");


        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Player.class)){
            String playerID = entity.getId();
            world.removeEntity(playerID);
        }
        // Remove entities
        //world.removeEntity(playerID);
        //System.out.println("removed player");
    }

}
