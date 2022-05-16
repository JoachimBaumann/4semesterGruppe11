package dk.sdu.mmmi.cbse.common.data;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity extends ApplicationAdapter implements Serializable {
    private final UUID ID = UUID.randomUUID();


    //sprite stuff
    private Sprite sprite;
    private String type;

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    //TextureAtlas for animations
    private TextureAtlas textureAtlas;

    private float width, height;
    private Animation animation;
    private float radius;
    private String name;

    private Map<Class, EntityPart> parts;
    
    public Entity() {
        parts = new ConcurrentHashMap<>();
    }
    
    public void add(EntityPart part) {
        parts.put(part.getClass(), part);
    }

    
    public void remove(Class partClass) {
        parts.remove(partClass);
    }
    
    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }

    public void flip(TextureAtlas textureatlas, String regionNames){
        this.textureAtlas = textureatlas;
        //this.textureAtlas = new TextureAtlas(AssetLoader.getCommonPlayerAssetPath("playeridle.txt"));
        TextureRegion[] flippedAnimation = new TextureRegion[3];
        for(int i = 0; i<3; i++){
            flippedAnimation[i] = new TextureRegion(textureAtlas.findRegion(regionNames +i));
            flippedAnimation[i].flip(true,false);
        }
        this.setAnimation(new Animation(1f/6f,flippedAnimation));
    }


    public float getRadius() {
        return this.radius;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setSprite(Sprite sprite) {
        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
        this.sprite = sprite;
    }
    public Sprite getSprite(){
        return this.sprite;
    }
    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    

    public String getID() {
        return ID.toString();
    }



    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }


    public String getType(){
        return type;
    }

    public String getName() {
        return null;
    }



}
