package dk.sdu.mmmi.cbse.screen;

import com.badlogic.gdx.Screen;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.player.Player;

public class GameScreen implements Screen {
    final AlienVsZombie game;

    private final GameData gameData = new GameData();
    private static World world = new World();


    public GameScreen(AlienVsZombie game){
        this.game = game;

        // load images + sound
        // create spriteBatch
        // etc.
    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //If health is depleted, go to game over screen
        for (Entity player : world.getEntities(Player.class)) {
            LifePart playerLife = player.getPart(LifePart.class);
            if (playerLife.getLife() <= 0) {
                this.dispose();
                game.setScreen(new EndScreen(game, score));
                return;
            }

        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
