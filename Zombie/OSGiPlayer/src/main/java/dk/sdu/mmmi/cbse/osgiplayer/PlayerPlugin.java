package dk.sdu.mmmi.cbse.osgiplayer;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.player.Player;



public class PlayerPlugin extends Player implements IGamePluginService {

    private String playerID;

    private static final String playerAssetPath = "\\Zombie\\OSGIPlayer\\src\\main\\resources\\Assets\\";
    private static final String commonPlayerAssetPath = "\\Zombie\\OSGICommonPlayer\\src\\main\\resources\\Assets\\";



    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        Entity player = createPlayerShip(gameData);
        player.setType("player");
        playerID = world.addEntity(player);
        gameData.setPlayerID(playerID);

        System.out.println("added player object");
        LifePart entityLife = player.getPart(LifePart.class);
        System.out.println(entityLife);

    }

    private Entity createPlayerShip(GameData gameData) {
        float maxSpeed = 600f;
        float x = gameData.getDisplayWidth();
        float y = gameData.getDisplayHeight();
        float direction = 6.28f;

        Entity playerShip = new Player();
        playerShip.add(new PlayerMovingPart(maxSpeed));
        playerShip.add(new PositionPart(1000, 500, direction));
        playerShip.add(new LifePart(100));
        playerShip.add(new DirectionPart());
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
