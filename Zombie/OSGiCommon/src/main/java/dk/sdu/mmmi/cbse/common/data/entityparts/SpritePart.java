package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;

public class SpritePart implements EntityPart {
    private float height;
    private float width;
    private String spritePath;
    private int layer;
    private float alpha;

    //Constructors
    public SpritePart(float height,  float width, String spritePath){
        this.height = height;
        this.width = width;
        this.spritePath = spritePath;
        layer = 1;
        alpha = 0;
    }
    public SpritePart(float height,  float width, String spritePath, int layer){
        this.height = height;
        this.width = width;
        this.spritePath = spritePath;
        this.layer = layer;
        alpha = 0;
    }
    public SpritePart(float height,  float width, String spritePath, int layer, float alpha){
        this.height = height;
        this.width = width;
        this.spritePath = spritePath;
        this.layer = layer;
        this.alpha = alpha;
    }

    @Override
    public void process(GameData gameData, Entity entity) {

    }

    //Getters and Setters
    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }
}
