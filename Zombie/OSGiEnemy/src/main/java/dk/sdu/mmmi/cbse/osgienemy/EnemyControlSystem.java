package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.enemy.*;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.data.*;




import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class EnemyControlSystem implements IEntityProcessingService {

    List<Entity> enemies;

    private static final String coreAssetPath = "\\Zombie\\OSGICore\\src\\main\\resources\\Assets\\";




    @Override
    public void process(GameData gameData, World world) {
        enemies = world.getEntities(EnemyBat.class);
        enemies.addAll(world.getEntities(EnemyRaven.class));
        enemies.addAll(world.getEntities(EnemySnail.class));
        enemies.addAll(world.getEntities(Enemy.class));

        //System.out.println(enemies.size());
        for (Entity enemy : enemies) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            EnemyMovingPart enemyMovingPart = enemy.getPart(EnemyMovingPart.class);

            float x = positionPart.getX();
            float y = positionPart.getY();
            Texture hpbar;
            Sprite healthbar;

            String corePath = AssetLoader.whichOS(coreAssetPath);
            hpbar = new Texture(AssetLoader.getAssetPath(corePath,"/UI/Health.png"));
            healthbar = new Sprite(hpbar, 50, 50, 1045, 64);

            System.out.println(healthbar + "   "+ hpbar);

            //PositionPart positionPart = enemyBat.getPart(PositionPart.class);

            healthbar.setPosition(x,y);
            //healthbar.setPosition(Gdx.graphics.getWidth() * 0.005f, Gdx.graphics.getHeight() * 0.09f);
            healthbar.setSize(Gdx.graphics.getWidth() * 0.04f, Gdx.graphics.getHeight() * 0.05f);

            //process
            enemyMovingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            //lifePart.process(gameData, enemy);

           // System.out.println("x = " + positionPart.getX() + " y = " + positionPart.getY());

        }
    }





}
