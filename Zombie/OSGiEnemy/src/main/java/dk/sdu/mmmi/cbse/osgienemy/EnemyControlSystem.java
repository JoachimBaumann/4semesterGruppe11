package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            //MovingPart movingPart = enemy.getPart(MovingPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);
            SpritePart spritePart = enemy.getPart(SpritePart.class);

           // movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            //movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            //movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));
            //movingPart.setSpace(gameData.getKeys().isDown(GameKeys.SPACE));

            //movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            lifePart.process(gameData, enemy);
            spritePart.process(gameData, enemy);

            updateShape(enemy);

        }
    }
    private void updateShape(Entity entity) {

        PositionPart positionPart = entity.getPart(PositionPart.class);
        SpritePart spritePart = entity.getPart(SpritePart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        Texture img = new Texture(spritePart.getSpritePath());
        Sprite sprite = new Sprite(img);
        sprite.setPosition(Gdx.graphics.getWidth()/2-sprite.getRegionHeight()/2,Gdx.graphics.getWidth()/2-Gdx.graphics.getHeight()/2);
    }




}
