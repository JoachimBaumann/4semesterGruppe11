package dk.sdu.mmmi.cbse.osgienemytracking;

import com.badlogic.gdx.math.Vector2;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EnemyMovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.player.Player;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;




public class EnemyTrackingSystem implements IPostEntityProcessingService {

    private float maxSpeed = 60, gravity = 60 * 1.8f, increment;
    private Vector2 velocity = new Vector2();
    private boolean canJump = true;


    @Override
    public void process(GameData gameData, World world) {
        //get current player position
        Entity player = world.getEntities(Player.class).get(0); //This works because there is only 1 player object.
        PositionPart playerPositionPart = player.getPart(PositionPart.class);


        //get all enemy positions
        for (Entity entity : world.getEntities(Enemy.class)) {

            //Get enemy data positions
            PositionPart enemyPositionPart = entity.getPart(PositionPart.class);
            EnemyMovingPart enemyMovingPart = entity.getPart(EnemyMovingPart.class);


            if (playerPositionPart.getX() > enemyPositionPart.getX()){
                enemyMovingPart.setRight(true);
                enemyMovingPart.setSpace(false);
                enemyMovingPart.setLeft(false);
            } else {
                enemyMovingPart.setLeft(true);
                enemyMovingPart.setSpace(false);
                enemyMovingPart.setRight(false);
            }

            //Check if player is above and if enemy is stuck in ground position
            if (playerPositionPart.getY() > enemyPositionPart.getY() && enemyPositionPart.isStuck()) enemyMovingPart.setSpace(true);
        }
    }
}
