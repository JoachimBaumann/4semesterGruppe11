package dk.sdu.mmmi.cbse.common.data.entityparts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class PlayerMovingPart extends MovingPart implements EntityPart {

    //String playerRight = "/PlayerAssets/PlayerRight/playerwalk.txt";

    public PlayerMovingPart(float maxSpeed) {
        super();
        this.maxSpeed = maxSpeed;
    }

    private float maxSpeed = 50, gravity = 50 * 1.8f, increment;
    private Vector2 velocity = new Vector2();

    public boolean isLeft() {
        return left;
    }
    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

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

    private static final String coreAssetPath = "\\Zombie\\OSGICommon\\src\\main\\resources\\Assets\\";
    String assetPath = AssetLoader.whichOS(coreAssetPath);


    @Override
    public void process(GameData gameData, Entity entity) {

        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float delta = gameData.getDelta();
        float direction = positionPart.getDirection();
        float newX, newY;
        TiledMapTileLayer collisonLayer = (TiledMapTileLayer) gameData.getWorldMap().getMap().getLayers().get(0);
        float jumpHeight = 70;


        // apply gravity
        velocity.y -= gravity * delta;

        // clamp velocity
        if (velocity.y > maxSpeed)
            velocity.y = maxSpeed;
        else if (velocity.y < -maxSpeed)
            velocity.y = -maxSpeed;


        if (left) {
            direction = positionPart.getLeft();
            entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/PlayerAssets/PlayerLeft/flippedPlayerWalk.txt")));
            entity.setAnimation(new Animation(1f / 6f, entity.getTextureAtlas().getRegions()));
            velocity.x -= maxSpeed * delta;
            if (collidesLeft(x + velocity.x, y, collisonLayer, entity)) {
                velocity.x = 0;
            }
        }
        if (right) {
            direction = positionPart.getRight();
            entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath, "/PlayerAssets/PlayerRight/playerwalk.txt")));
            entity.setAnimation(new Animation(1f / 6f, entity.getTextureAtlas().getRegions()));
            velocity.x += maxSpeed * delta;
            if (collidesRight(x + velocity.x, y, collisonLayer, entity)) {
                velocity.x = 0;
            }
        }


        if (up) {
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
        } else {
            entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/PlayerAssets/PlayerRight/playerjump.txt")));
            entity.setAnimation(new Animation(1f / 3f, entity.getTextureAtlas().getRegions()));
        }


        newX = x + velocity.x;
        newY = y + velocity.y;

        velocity.x = 0;

        positionPart.setX(newX);
        positionPart.setY(newY);

        positionPart.setDirection(direction);

    }

    //only for testing purposes to avoid nullpointer,
    // as there is no map initialized to check for collision

    public void testProcess(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float delta = 10f;
        float direction = positionPart.getDirection();
        float newX, newY;
        float jumpHeight = 70;


        // apply gravity
        velocity.y -= gravity * delta;

        // clamp velocity
        if (velocity.y > maxSpeed)
            velocity.y = maxSpeed;
        else if (velocity.y < -maxSpeed)
            velocity.y = -maxSpeed;


        if (left) {
            direction = positionPart.getLeft();

            velocity.x -= maxSpeed * delta;

        }
        if (right) {
            direction = positionPart.getRight();

            velocity.x += maxSpeed * delta;

        }


        if (up) {
            if (super.getCantJump()) {
                velocity.y += jumpHeight / 1.8f;
                super.setCanJump(false);
            }
        }


        newX = x + velocity.x;
        newY = y + velocity.y;

        velocity.x = 0;

        positionPart.setX(newX);
        positionPart.setY(newY);

        positionPart.setDirection(direction);
    }
}
