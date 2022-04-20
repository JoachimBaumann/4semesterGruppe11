/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * @author Alexander
 */


public class MovingPart {

    public MovingPart() {
    }

    public boolean canJump = true;
    /*
    private float maxSpeed = 60, gravity = 60 * 1.8f, increment;
    private Vector2 velocity = new Vector2();
    private boolean left, right, up, space;
    private int count;
    private boolean canJump = true;

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

     */


    public boolean isCellBlocked(float x, float y, TiledMapTileLayer collisionLayer) {
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

    public boolean collidesRight(float x, float y, TiledMapTileLayer collisionLayer, Entity entity) {
        boolean collides = false;

        for (float step = 0; step < entity.getSprite().getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellBlocked(x + entity.getSprite().getWidth(), y + step, collisionLayer))
                break;
        }

        return collides;
    }

    public boolean collidesLeft(float x, float y, TiledMapTileLayer collisionLayer, Entity entity) {
        boolean collides = false;

        for (float step = 0; step < entity.getSprite().getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellBlocked(x, y + step, collisionLayer)) {
                break;
            }
        }

        return collides;
    }

    public boolean collidesTop(float x, float y, TiledMapTileLayer collisionLayer, Entity entity) {
        boolean collides = false;

        for (float step = 0; step < entity.getSprite().getWidth(); step += entity.getSprite().getHeight() / 2) {
            if (collides = isCellBlocked(x + step, y + entity.getSprite().getHeight(), collisionLayer)) {
                break;
            }
        }

        return collides;

    }

    public boolean collidesBottom(float x, float y, TiledMapTileLayer collisionLayer, Entity entity) {
        boolean collides = false;

        for (float step = 0; step < entity.getSprite().getWidth(); step += entity.getSprite().getHeight() / 2) {
            if (collides = isCellBlocked(x + step, y, collisionLayer)) {
                canJump = true;
                break;
            }
        }

        return collides;

    }

}

