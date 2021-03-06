/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

import java.util.ArrayList;



public class PositionPart implements EntityPart {

    private float x;
    private float y;
    private float radians;
    private ArrayList<Float> prevX = new ArrayList<>();


    public PositionPart(float x, float y, float radians) {
        this.x = x;
        this.y = y;
        this.radians = radians;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadians() {
        return radians;
    }

    private void addPrev(float x) {
        if (prevX.size() > 100) prevX.clear();
        prevX.add(x);
        //System.out.println(x);
    }

    public void setX(float newX) {
        addPrev(x);
        this.x = newX;
    }
    
    public void setY(float newY) {

        this.y = newY;
    }

    public boolean isStuck() {
        for (Float f : prevX) {
            if (!f.equals(prevX.get(0))) {
                return false;
            }
        }
        return true;
    }




    public void setPosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        try{
            entity.getTextureAtlas().createSprite("moving").setPosition(this.getX(),this.getY());
            //entity.getSprite().setPosition(this.getX(), this.getY());
        }catch(NullPointerException e){

        }
    }
    
    
    
    
}
