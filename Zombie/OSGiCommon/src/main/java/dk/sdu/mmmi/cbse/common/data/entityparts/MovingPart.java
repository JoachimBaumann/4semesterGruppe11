
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * @author Alexander
 */
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
        TiledMapTileLayer collisonLayer = (TiledMapTileLayer) gameData.getWorldMap().getMap().getLayers().get("CollisionLayer");

        float jumpHeight = 50;
        Array<Sprite> entitySprites = entity.getTextureAtlas().createSprites();

        // apply gravity
        velocity.y -= gravity * delta;

        // clamp velocity
        if (velocity.y > maxSpeed)
            velocity.y = maxSpeed;
        else if (velocity.y < -maxSpeed)
            velocity.y = -maxSpeed;


        if (left) {

            velocity.x -= maxSpeed * delta;
            if (collidesLeft(x + velocity.x, y, collisonLayer, entity)) {
                velocity.x = 0;
            }
        }
        if (right) {

            velocity.x += maxSpeed * delta;
            if (collidesRight(x + velocity.x, y, collisonLayer, entity)) {
                velocity.x = 0;
            }
        }


        if (up) {

            if (canJump) {

                velocity.y += jumpHeight / 1.8f;
                canJump = false;
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

}