package dk.sdu.mmmi.cbse.osgienemytracking;

import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.player.Player;



public class EnemyTrackingSystem implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        //get current player position
        Entity player = world.getEntities(Player.class).get(0); //This works because there is only 1 player object.
        PositionPart playerPositionPart = player.getPart(PositionPart.class);

        //get all enemy positions
        for (Entity entity : world.getEntities()) {
            continue;

        }
    }
}
