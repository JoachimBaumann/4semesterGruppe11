package dk.sdu.mmmi.cbse.common.data.entityparts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class PlayerMovingPart extends MovingPart implements EntityPart {

    public PlayerMovingPart(float maxSpeed) {
        super();
        this.maxSpeed = maxSpeed;
    }

    private float maxSpeed = 60, gravity = 60 * 1.8f, increment;
    private Vector2 velocity = new Vector2();
    private boolean left, right, up, space;
    private int count;

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    private String blockedKey = "blocked";


    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }


    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setSpace(boolean space) {
        this.space = space;
    }


    @Override
    public void process(GameData gameData, Entity entity) {

        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float delta = gameData.getDelta();
        float newX, newY;
        TiledMapTileLayer collisonLayer = (TiledMapTileLayer) gameData.getWorldMap().getMap().getLayers().get(0);
        float jumpHeight = 90;

        // apply gravity
        velocity.y -= gravity * delta;

        // clamp velocity
        if (velocity.y > maxSpeed)
            velocity.y = maxSpeed;
        else if (velocity.y < -maxSpeed)
            velocity.y = -maxSpeed;


        if (left) {
            entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath("/PlayerAssets/PlayerLeft/flippedPlayerWalk.txt")));
            entity.setAnimation(new Animation(1f/6f,entity.getTextureAtlas().getRegions()));
            velocity.x -= maxSpeed * delta;
            if (collidesLeft(x + velocity.x, y, collisonLayer, entity)) {
                velocity.x = 0;
            }
        }
        if (right) {
            entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath("/PlayerAssets/PlayerRight/playerwalk.txt")));
            entity.setAnimation(new Animation(1f/6f,entity.getTextureAtlas().getRegions()));
            velocity.x += maxSpeed * delta;
            if (collidesRight(x + velocity.x, y, collisonLayer, entity)) {
                velocity.x = 0;
            }
        }


        if (up) {
            entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath("/PlayerAssets/PlayerRight/playerjump.txt")));
            entity.setAnimation(new Animation(1f/3f,entity.getTextureAtlas().getRegions()));
            if (super.getCantJump()) {
                velocity.y += jumpHeight / 1.8f;
                super.setCanJump(false);
            }
        }
        if (collidesTop(x, y + velocity.y, collisonLayer, entity)) {
            velocity.y = 0;
        }
        if (collidesBottom(x, y + velocity.y, collisonLayer, entity)) {
            velocity.y = 0;
        }


        newX = x + velocity.x;
        newY = y + velocity.y;

        velocity.x = 0;

        positionPart.setX(newX);
        positionPart.setY(newY);

    }
}
