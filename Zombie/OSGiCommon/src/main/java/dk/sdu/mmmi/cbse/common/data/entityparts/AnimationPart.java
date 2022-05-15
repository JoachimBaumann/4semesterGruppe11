package dk.sdu.mmmi.cbse.common.data.entityparts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class AnimationPart implements EntityPart{

    TextureAtlas idleAtlas;
    TextureAtlas walkAtlas;
    TextureAtlas shootAtlas;
    TextureAtlas jumpAtlas;


    @Override
    public void process(GameData gameData, Entity entity) {

    }





    public AnimationPart(TextureAtlas idleAtlas) {
        this.idleAtlas = idleAtlas;
    }

    public AnimationPart(TextureAtlas idleAtlas, TextureAtlas walkAtlas) {
        this.idleAtlas = idleAtlas;
        this.walkAtlas = walkAtlas;

    }

    public AnimationPart(TextureAtlas idleAtlas, TextureAtlas walkAtlas, TextureAtlas shootAtlas) {
        this.idleAtlas = idleAtlas;
        this.walkAtlas = walkAtlas;
        this.shootAtlas = shootAtlas;
    }

    public AnimationPart(TextureAtlas idleAtlas, TextureAtlas walkAtlas, TextureAtlas shootAtlas, TextureAtlas jumpAtlas) {
        this.idleAtlas = idleAtlas;
        this.walkAtlas = walkAtlas;
        this.shootAtlas = shootAtlas;
        this.jumpAtlas = jumpAtlas;
    }

    public TextureAtlas getIdleAtlas() {
        return idleAtlas;
    }

    public void setIdleAtlas(TextureAtlas idleAtlas) {
        this.idleAtlas = idleAtlas;
    }

    public TextureAtlas getWalkAtlas() {
        return walkAtlas;
    }

    public void setWalkAtlas(TextureAtlas walkAtlas) {
        this.walkAtlas = walkAtlas;
    }

    public TextureAtlas getShootAtlas() {
        return shootAtlas;
    }

    public void setShootAtlas(TextureAtlas shootAtlas) {
        this.shootAtlas = shootAtlas;
    }

    public TextureAtlas getJumpAtlas() {
        return jumpAtlas;
    }

    public void setJumpAtlas(TextureAtlas jumpAtlas) {
        this.jumpAtlas = jumpAtlas;
    }



}
