package dk.sdu.mmmi.cbse.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.ScreenUtils;

//NEW
import com.badlogic.gdx.graphics.GL20;
import dk.sdu.mmmi.cbse.Game;
import dk.sdu.mmmi.cbse.common.data.AssetLoader;


public class EndScreen implements Screen {

    final Game game;
    private SpriteBatch batch;

    int score, highscore;

    Texture gameOverBanner;
    BitmapFont scoreFont;

    private static final String screenAssetPath = "\\Zombie\\screen\\src\\main\\resources\\Assets\\";
    private static final String assetPath = AssetLoader.whichOS(screenAssetPath);


    public EndScreen (Game game) {
        this.game = game;
        //this.score = score;

        // RED IMG RN
        gameOverBanner = new Texture(AssetLoader.getAssetPath(assetPath, "Red.png"));
        scoreFont = new BitmapFont();

        /* TODO HIGH SCORE LOGIC
        // this is from another game
        // Get highscore from save files
        Preferences prefs = Gdx.app.getPreferences("alienVszombie");
        this.highscore = prefs.getInteger("highscore", 0);

        //check if score beats highscore
        if (score>highscore) {
            prefs.putInteger("highscore",score);
            prefs.flush(); //save to the file
        }
         */
    }


    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO DRAW SCORE on screen
        game.batch.begin();
        game.batch.draw(gameOverBanner, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        scoreFont.draw(game.batch, "SCORE", Gdx.graphics.getWidth()* .25f,Gdx.graphics.getHeight()* .75f);
        game.batch.end();
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
