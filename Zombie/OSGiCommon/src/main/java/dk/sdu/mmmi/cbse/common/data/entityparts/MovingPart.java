
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;


public class MovingPart implements EntityPart {

    public MovingPart(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public MovingPart(float maxSpeed, float gravity) {
        this.maxSpeed = maxSpeed;
        this.gravity = gravity;
    }

    private float maxSpeed = 60, gravity = 60 * 1.8f, increment;
    private Vector2 velocity = new Vector2();
    private boolean left, right, up, space;
    private int count;
    private boolean canJump = true;

    public MovingPart() {

    }

    public boolean getCantJump() {
        return canJump;
    }

    public void setCanJump(Boolean bol) {
        this.canJump = bol;
    }

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

        float jumpHeight = 75;

        //NEW
        float direction = positionPart.getDirection();



        // apply gravity
        velocity.y -= gravity * delta;

        // clamp velocity
        if (velocity.y > maxSpeed)
            velocity.y = maxSpeed;
        else if (velocity.y < -maxSpeed)
            velocity.y = -maxSpeed;

        String stringPath = entity.getName();

        if (left) {
            direction = positionPart.getLeft();
            velocity.x -= maxSpeed * delta;
            if (stringPath != null && entity.directionTypeEntity){
                entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(entity.getEntityAssetPath(), entity.getLeftAssetPath())));
                entity.setAnimation(new Animation(entity.getFrameDuration(), entity.getTextureAtlas().getRegions()));
            }
            if (collidesLeft(x + velocity.x, y, collisonLayer, entity)) {
                velocity.x = 0;
            }
        }
        if (right) {
            direction = positionPart.getRight();
            velocity.x += maxSpeed * delta;
            if (stringPath != null && entity.directionTypeEntity){
                entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(entity.getEntityAssetPath(), entity.getRightAssetPath())));
                entity.setAnimation(new Animation(entity.getFrameDuration(), entity.getTextureAtlas().getRegions()));
            }
            if (collidesRight(x + velocity.x, y, collisonLayer, entity)) {
                velocity.x = 0;
            }
        }
        if (up) {
            if (getCantJump()) {
                velocity.y += jumpHeight / 1.8f;
                setCanJump(false);
            }
        }
        if (collidesTop(x, y + velocity.y, collisonLayer, entity)) {
            velocity.y = 0;
        }
        if (collidesBottom(x, y + velocity.y, collisonLayer, entity)) {
            velocity.y = 0;
        } else if (direction == positionPart.getRight() && entity.jumpingTypeEntity){
            entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(entity.getEntityAssetPath(), entity.getRightJumpAssetPath())));
            entity.setAnimation(new Animation(entity.getJumpFrameDuration(), entity.getTextureAtlas().getRegions()));
        } else if (direction == positionPart.getLeft() && entity.jumpingTypeEntity){
            entity.setTextureAtlas(new TextureAtlas(AssetLoader.getAssetPath(entity.getEntityAssetPath(),entity.getLeftJumpAssetPath())));
            entity.setAnimation(new Animation(entity.getJumpFrameDuration(), entity.getTextureAtlas().getRegions()));
        }


        newX = x + velocity.x;
        newY = y + velocity.y;

        velocity.x = 0;

        positionPart.setX(newX);
        positionPart.setY(newY);

        if (entity.directionTypeEntity){
            positionPart.setDirection(direction);

        }

    }

    private boolean isCellBlocked(float x, float y, TiledMapTileLayer collisionLayer) {
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

    public boolean collidesRight(float x, float y, TiledMapTileLayer collisionLayer, Entity entity) {
        boolean collides = false;

        for (float step = 0; step < entity.getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellBlocked(x + entity.getHeight(), y + step, collisionLayer))
                break;
        }

        return collides;
    }

    public boolean collidesLeft(float x, float y, TiledMapTileLayer collisionLayer, Entity entity) {
        boolean collides = false;

        for (float step = 0; step < entity.getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellBlocked(x, y + step, collisionLayer)) {
                break;
            }
        }

        return collides;
    }

    public boolean collidesTop(float x, float y, TiledMapTileLayer collisionLayer, Entity entity) {
        boolean collides = false;

        for (float step = 0; step < entity.getHeight(); step += entity.getHeight() / 2) {
            if (collides = isCellBlocked(x + step, y + entity.getHeight(), collisionLayer)) {
                break;
            }
        }

        return collides;

    }

    public boolean collidesBottom(float x, float y, TiledMapTileLayer collisionLayer, Entity entity) {
        boolean collides = false;

        for (float step = 0; step < entity.getWidth(); step += entity.getHeight() / 2) {
            if (collides = isCellBlocked(x + step, y, collisionLayer)) {
                canJump = true;
                break;
            }
        }

        return collides;

    }
    /*
    Method used in testing, as there is no map dependency
     */
    public void testProcess(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float delta = 2f;
        float newX, newY;
        //TiledMapTileLayer collisonLayer = (TiledMapTileLayer) gameData.getWorldMap().getMap().getLayers().get(0);

        float jumpHeight = 75;

        //NEW
        //float direction = positionPart.getDirection();
        gravity = 0f;


        // apply gravity
        velocity.y -= gravity * delta;

        // clamp velocity
        if (velocity.y > maxSpeed)
            velocity.y = maxSpeed;
        else if (velocity.y < -maxSpeed)
            velocity.y = -maxSpeed;

        //String stringPath = entity.getName();

        if (left) {
            //direction = positionPart.getLeft();
            velocity.x -= maxSpeed * delta;

        }
        if (right) {

            velocity.x += maxSpeed * delta;

        }
        if (up) {
            if (getCantJump()) {
                velocity.y += jumpHeight / 1.8f;
                setCanJump(false);
            }
        }

        newX = x + velocity.x;
        newY = y + velocity.y;

        velocity.x = 0;

        positionPart.setX(newX);
        positionPart.setY(newY);

    }

}