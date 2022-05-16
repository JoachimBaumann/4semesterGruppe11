package dk.sdu.mmmi.cbse.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class AlienVsZombie extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    Boolean outOfHealth = true;

    public void create() {
        /* TODO start game as normal create
        *
        *
          */
        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font

        // TODO CONDITION on changing the screen - maybe place in gameScreen instead
        if (outOfHealth){
            this.setScreen(new EndScreen(this));
        }

    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}