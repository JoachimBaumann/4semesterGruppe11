package dk.sdu.mmmi.cbse.common.data;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity extends ApplicationAdapter implements Serializable {

    public String entityAssetPath;
    public String leftAssetPath;
    public float frameDuration;
    public String rightAssetPath;
    public float jumpFrameDuration;
    public String rightJumpAssetPath;
    public String leftJumpAssetPath;

    public String getEntityAssetPath() {return entityAssetPath;}
    public void setEntityAssetPath(String entityAssetPath) {this.entityAssetPath = entityAssetPath;}
    public String getLeftAssetPath() {return leftAssetPath;}
    public void setLeftAssetPath(String leftAssetPath) {this.leftAssetPath = leftAssetPath;}
    public float getFrameDuration() {return frameDuration;}
    public void setFrameDuration(float frameDuration) {this.frameDuration = frameDuration;}
    public String getRightAssetPath() {return rightAssetPath;}
    public void setRightAssetPath(String rightAssetPath) {this.rightAssetPath = rightAssetPath;}
    public String getRightJumpAssetPath() {return rightJumpAssetPath;}
    public String getLeftJumpAssetPath() {return leftJumpAssetPath;}
    public float getJumpFrameDuration() {return jumpFrameDuration;}
    public void setJumpFrameDuration(float jumpFrameDuration) {this.jumpFrameDuration = jumpFrameDuration;}
    public void setRightJumpAssetPath(String rightJumpAssetPath) {
        this.rightJumpAssetPath = rightJumpAssetPath;
    }
    public void setLeftJumpAssetPath(String leftJumpAssetPath) {
        this.leftJumpAssetPath = leftJumpAssetPath;
    }


    private UUID id = UUID.randomUUID();;


    //sprite stuff
    public boolean directionTypeEntity = false;
    public boolean jumpingTypeEntity = false;
    private Sprite sprite;
    private String type;
    private float animationYOffset = - 12;
    private float animationXOffset = - 12;

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
        //id = UUID.randomUUID();
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
    

    public String getId() {
        return id.toString();
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


    public float getAnimationYOffset() {
        return animationYOffset;
    }

    public void setAnimationYOffset(float animationYOffset) {
        this.animationYOffset = animationYOffset;
    }

    public float getAnimationXOffset() {
        return animationXOffset;
    }

    public void setAnimationXOffset(float animationXOffset) {
        this.animationXOffset = animationXOffset;
    }
}
