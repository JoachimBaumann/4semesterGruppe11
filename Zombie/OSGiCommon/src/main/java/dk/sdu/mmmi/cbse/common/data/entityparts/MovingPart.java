/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.Vector;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 *
 * @author Alexander
 */
public class MovingPart implements EntityPart {

    private float dx, dy;
    private float maxSpeed, rotationSpeed;
    private boolean left, right, up, space;
    private int count;



    private float acceleration;
    private float deacceleration;
    private static final float jumpAcceleration = 190F;
    private static final float gravity = 150F;
    private float maxAcceleration;


    public MovingPart(float acceleration, float deacceleration, float maxAcceleration) {
      this.acceleration = acceleration;
      this.deacceleration = deacceleration;
      this.maxAcceleration = maxAcceleration;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }
    
    public void setDeceleration(float deceleration) {
        this.deacceleration = deacceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    
    public void setSpeed(float speed) {
        this.acceleration = speed;
        this.maxSpeed = speed;
    }

    public void setRotationSpeed(float rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
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
        float radians = positionPart.getRadians();
        float delta = gameData.getDelta();
        float oldX = positionPart.getX();
        float oldY = positionPart.getY();


        // Apply gravity
        Vector velocity = new Vector(x, y);
        velocity.setY(velocity.getY() - gravity * delta);
        //positionPart.setY(y - gravity * delta);


        if (right) {
            if (velocity.getX() < maxAcceleration) {
                velocity.setX(Math.min(maxAcceleration, velocity.getX() + acceleration * delta));
            }
        } //else if (velocity.getX() > 0) {
          //  velocity.setX(Math.max(0, velocity.getX() - deacceleration * delta));
          //  System.out.println("VelocityELSE = " + velocity.getX());
        //}
        if (left) {
            if (velocity.getX() > -maxAcceleration) {
                velocity.setX(Math.max(-maxAcceleration, velocity.getX() - acceleration * delta));
            }
        } else if (velocity.getX() < 0) {
            velocity.setX(Math.min(0, velocity.getX() + deacceleration * delta));
        }


        // Jump
        if (space) {
            velocity.setY(jumpAcceleration);
        }


        //floor collision
        if (velocity.getY() <= 180) {
            velocity.setY(180);
        } else if (velocity.getX() <= 0) {
            velocity.setX(0);
         }

        positionPart.setX(velocity.getX());
        positionPart.setY(velocity.getY());


        /*
        // turning
        if (left) {
            //radians += rotationSpeed * dt;
            System.out.println("LEFT");
            dx -=  acceleration * dt;
        }

        if (right) {
            //radians -= rotationSpeed * dt;
            System.out.println("RIGHT");
            dx +=  acceleration * dt;
        }

        // accelerating            
        if (up) {
            //dx += cos(radians) * acceleration * dt;
            //dy += 800f * dt;
        }

        // deccelerating
        float vec = (float) sqrt(dx * dx + dy * dy);
        if (vec > 0) {
            dx -= (dx / vec) * deceleration * dt;
            dy -= (dy / vec) * deceleration * dt;
        }
        if (vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position
        x += dx * dt;
        if (x > gameData.getDisplayWidth()) {
            x = 0;
        }
        else if (x < 0) {
            x = gameData.getDisplayWidth();
        }

        y += dy * dt;
        if (y > gameData.getDisplayHeight()) {
            y = 0;
        }
        else if (y < 0) {
            y = gameData.getDisplayHeight();
        }

        positionPart.setX(x);
        positionPart.setY(y);

        positionPart.setRadians(radians);
            */
    }

}
