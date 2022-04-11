package dk.sdu.mmmi.cbse.common.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Player extends Entity {



    @Override
    public void create() {
        this.setSprite(new Sprite(new Texture("C:\\Users\\kinky\\IdeaProjects\\4semesterGruppe11\\Zombie\\OSGiCommon\\src\\main\\java\\dk\\sdu\\mmmi\\cbse\\common\\Assets\\png.jpg"),32,64));


    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
