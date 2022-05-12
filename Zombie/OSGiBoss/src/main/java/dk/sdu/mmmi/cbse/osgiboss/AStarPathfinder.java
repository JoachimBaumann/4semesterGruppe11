
package dk.sdu.mmmi.cbse.osgiboss;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.player.Player;

;


/**
 * @author Anton
 */


public class AStarPathfinder {

    private AStarEngine aStar = new AStarEngine();

    public PositionPart findNextPosition(Entity me, GameData gameData, World world) {
        PositionPart nextPos = null;

        Entity target = getTarget(me, world);
        if (target != null) {
            PositionPart targetPos = target.getPart(PositionPart.class);
            if (this.aStar != null) {
                nextPos = this.aStar.search(world, me, targetPos);
            }
        }

        return nextPos;
    }

    private Entity getTarget(Entity me, World world) {
        // find all entities

            Entity player = world.getEntities(Player.class).get(0);
            if (player != me && player != null) {
                return player;}
            else
        return null;
    }
}

