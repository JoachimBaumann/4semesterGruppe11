package dk.sdu.mmmi.cbse.osgiplayer;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
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
        float deacceleration = 50f;
        float acceleration = 200f;
        float maxAccelaretion = 1920f;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth();
        float y = gameData.getDisplayHeight();
        float radians = 6.1415f / 2;

        Entity playerShip = new Player();
        playerShip.setRadius(8);
        playerShip.add(new MovingPart(acceleration, deacceleration, maxAccelaretion));
        playerShip.add(new PositionPart(900, y, radians));
        playerShip.add(new LifePart(1));
        playerShip.add(new SpritePart(400, 200, "C:\\Users\\Phill\\IdeaProjects\\4semesterGruppe11\\Zombie\\OSGiCommon\\src\\main\\java\\dk\\sdu\\mmmi\\cbse\\common\\Assets\\player.png"));

        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(playerID);
    }

}
