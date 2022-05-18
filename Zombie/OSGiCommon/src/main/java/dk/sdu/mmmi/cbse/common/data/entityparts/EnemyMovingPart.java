package dk.sdu.mmmi.cbse.common.data.entityparts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class EnemyMovingPart extends MovingPart implements EntityPart {

    public EnemyMovingPart(float maxSpeed) {
        super();
        this.maxSpeed = maxSpeed;
    }
    public EnemyMovingPart(float maxSpeed, float gravity){
        super();
        this.maxSpeed = maxSpeed;
        this.gravity = gravity;
    }

    private float maxSpeed;
    private float gravity = 60 * 1.8f, increment;
    private Vector2 velocity = new Vector2();
    private boolean left, right, space;
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


    public void setSpace(boolean space) {
        this.space = space;
    }

    private static final String enemyAssetPath = "\\Zombie\\OSGIEnemy\\src\\main\\resources\\Assets\\";
    private static final String assetPath = AssetLoader.whichOS(enemyAssetPath);


    @Override
    public void process(GameData gameData, Entity entity) {

        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float delta = gameData.getDelta();
        float newX, newY;
        TiledMapTileLayer collisonLayer = (TiledMapTileLayer) gameData.getWorldMap().getMap().getLayers().get(0);
        float jumpHeight = 50;

        // apply gravity
        velocity.y -= gravity * delta;

        // clamp velocity
        if (velocity.y > maxSpeed)
            velocity.y = maxSpeed;
        else if (velocity.y < -maxSpeed)
            velocity.y = -maxSpeed;
        ;



        if (left) {
            velocity.x -= maxSpeed * delta;
            if(entity.getName() == "snail"){
                entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/EnemyAssets/EnemySnail/SnailLeft/EnemySnailWalk.txt")));
                entity.setAnimation(new Animation(1f/6f, entity.getTextureAtlas().getRegions()));
            }
            if(entity.getName() == "raven"){
                entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/EnemyAssets/EnemyRaven/RavenLeft/EnemyRavenWalk.txt")));
                entity.setAnimation(new Animation(1f/6f, entity.getTextureAtlas().getRegions()));
            }
            if (entity.getName() == "zombie") {
                entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/EnemyAssets/EnemyRight/flippedenemywalk.txt")));
                entity.setAnimation(new Animation(1f/6f,entity.getTextureAtlas().getRegions()));
            }
            if (collidesLeft(x + velocity.x, y, collisonLayer, entity)) {
                velocity.x = 0;
            }
        }
        if (right) {
            velocity.x += maxSpeed * delta;
            if (entity.getName() == "zombie") {
                entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/EnemyAssets/EnemyRight/flippedenemywalk.txt")));
                entity.setAnimation(new Animation(1 / 15f, entity.getTextureAtlas().getRegions()));
            }
            if(entity.getName() == "snail"){
                entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/EnemyAssets/EnemySnail/SnailRight/SnailRight.txt")));
                entity.setAnimation(new Animation(1f/6f, entity.getTextureAtlas().getRegions()));
            }
            if(entity.getName() == "raven"){
                entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/EnemyAssets/EnemyRaven/RavenRight/ravenRight.txt")));
                entity.setAnimation(new Animation(1f/6f, entity.getTextureAtlas().getRegions()));
            }
            if (collidesRight(x + velocity.x, y, collisonLayer, entity)) {
                velocity.x = 0;
            }
        }


        if (space) {
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
        }else{
            if (entity.getName() == "zombie") {
                entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(assetPath,"/EnemyAssets/EnemyRight/flippedenemywalk.txt")));
                entity.setAnimation(new Animation(1f / 6f, entity.getTextureAtlas().getRegions()));
            }
        }


        newX = x + velocity.x;
        newY = y + velocity.y;

        velocity.x = 0;

        positionPart.setX(newX);
        positionPart.setY(newY);


        //System.out.println(this.left + " " + this.right);
    }


}
