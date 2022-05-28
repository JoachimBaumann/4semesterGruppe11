package dk.sdu.mmmi.cbse.osgiplayer;


import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.weapon.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import dk.sdu.mmmi.cbse.common.player.Player;

public class PlayerShootingTest {



    @Test
    public void startTest() {

        GameData gameData = new GameData(); //creates a mock object
        World world = new World(); //creates another mock object


        PlayerPlugin instance = new PlayerPlugin(); //creates PlayerPlugin object
        instance.start(gameData, world);

        PlayerControlSystem playerControlSystem = new PlayerControlSystem();
        gameData.getKeys().setKey(GameKeys.SPACE, true);
        playerControlSystem.processTest(gameData, world);

        Assertions.assertEquals(2, world.getEntities().size());

        for (Entity entity: world.getEntities(Weapon.class)) {
            System.out.println(entity.getName());
            Assertions.assertEquals("Bullet", entity.getName());
            }
    }

}
