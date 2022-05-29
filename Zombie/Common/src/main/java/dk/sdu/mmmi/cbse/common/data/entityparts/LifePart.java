/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;


public class LifePart implements EntityPart {
    private boolean dead = false;
    private boolean isHit = false;
    private int life;
    private int starterLife;


    public LifePart(){}

    public LifePart(int life) {
        this.life = life;
        starterLife = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }
    
    public boolean isDead() {
        return dead;
    }

    
    
    @Override
    public void process(GameData gameData, Entity entity) {
        if (isHit) {
            isHit = false;
        }
        if (life <= 0) {
            dead = true;
        }

    }

    public int getStarterLife() {
        return starterLife;
    }

    public void setStarterLife(int starterLife) {
        this.starterLife = starterLife;
    }
}