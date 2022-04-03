package dk.sdu.mmmi.cbse.common.data;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity extends ApplicationAdapter implements Serializable {
    private final UUID ID = UUID.randomUUID();


    //sprite stuff
    private Sprite sprite;
    private float width, height;

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




}
