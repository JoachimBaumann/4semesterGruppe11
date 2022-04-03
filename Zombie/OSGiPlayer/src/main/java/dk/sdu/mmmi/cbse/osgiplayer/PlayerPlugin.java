package dk.sdu.mmmi.cbse.osgiplayer;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private String playerID;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        Entity player = createPlayerShip(gameData);
        playerID = world.addEntity(player);

        System.out.println("added player object ");
        LifePart entityLife = player.getPart(LifePart.class);
        System.out.println(entityLife);

    }

    private Entity createPlayerShip(GameData gameData) {
        float maxSpeed = 600f;
        float x = gameData.getDisplayWidth();
        float y = gameData.getDisplayHeight();
        float radians = 6.1415f / 2;

        Entity playerShip = new Player();
        playerShip.add(new MovingPart(maxSpeed));
        playerShip.add(new PositionPart(500, 500, radians));
        playerShip.add(new LifePart(1));

        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(playerID);
    }

}
