package dk.sdu.mmmi.cbse.osgiplayer;

import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.player.Player;



public class PlayerPlugin implements IGamePluginService {

    private String playerID;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        Entity player = createPlayerShip(gameData);
        gameData.setPlayerID(playerID);
        playerID = world.addEntity(player);


        System.out.println("added player object");
        LifePart entityLife = player.getPart(LifePart.class);
        System.out.println(entityLife);

    }

    private Entity createPlayerShip(GameData gameData) {
        float maxSpeed = 600f;
        float x = gameData.getDisplayWidth();
        float y = gameData.getDisplayHeight();
        float radians = 6.1415f / 2;

        Entity playerShip = new Player();
        playerShip.add(new PlayerMovingPart(maxSpeed));
        playerShip.add(new PositionPart(1000, 500, radians));
        playerShip.add(new LifePart(100));
        playerShip.setHeight(84);
        playerShip.setWidth(115);
        playerShip.setRadius(20);
        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(playerID);
        System.out.println("removed player");
    }

}
