package dk.sdu.mmmi.cbse.osgienemytracking;

import com.badlogic.gdx.math.Vector2;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.player.Player;
import java.util.List;


public class EnemyTrackingSystem implements IPostEntityProcessingService {

    private float maxSpeed = 60, gravity = 60 * 1.8f, increment;
    private Vector2 velocity = new Vector2();
    private boolean canJump = true;
    private List<Entity> enemies;



    @Override
    public void process(GameData gameData, World world) {
        //get current player position
        List<Entity> player = world.getEntities(Player.class);
        if(!player.isEmpty()) {
            PositionPart playerPositionPart = player.get(0).getPart(PositionPart.class);
            enemies = world.getEnemies();

            //get all enemy positions
            for (Entity entity : enemies) {

                //Get enemy data positions
                PositionPart enemyPositionPart = entity.getPart(PositionPart.class);
                MovingPart enemyMovingPart = entity.getPart(MovingPart.class);


                if (enemyPositionPart.getX() < playerPositionPart.getX()) {
                    enemyPositionPart.setDirectionRight();
                    enemyMovingPart.setRight(true);
                    enemyMovingPart.setSpace(false);
                    enemyMovingPart.setLeft(false);
                } else {
                    enemyPositionPart.setDirectionLeft();
                    enemyMovingPart.setLeft(true);
                    enemyMovingPart.setSpace(false);
                    enemyMovingPart.setRight(false);

                }

                //Check if player is above and if enemy is stuck in ground position
                /*if (playerPositionPart.getY() > enemyPositionPart.getY() && enemyPositionPart.isStuck())
                    enemyMovingPart.setSpace(true);

                 */
            }
        }
    }
}
