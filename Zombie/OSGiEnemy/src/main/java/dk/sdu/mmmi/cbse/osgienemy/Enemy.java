package dk.sdu.mmmi.cbse.osgienemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity{

    @Override
    public void create() {
        this.setSprite(new Sprite(new Texture("C:\\Users\\kinky\\IdeaProjects\\4semesterGruppe11\\Zombie\\OSGiCommon\\src\\main\\java\\dk\\sdu\\mmmi\\cbse\\common\\Assets\\png.jpg"),60,120));

    }
}
