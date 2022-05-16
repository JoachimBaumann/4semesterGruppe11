package dk.sdu.mmmi.cbse.screen;

import com.badlogic.gdx.Game;
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
import dk.sdu.mmmi.cbse.common.data.AssetLoader;


public class EndScreen implements Screen {

    final AlienVsZombie game;
    private SpriteBatch batch;

    int score, highscore;

    Texture gameOverBanner;
    BitmapFont scoreFont;

    private static final String screenAssetPath = "\\Zombie\\screen\\src\\main\\resources\\Assets\\";
    private static final String assetPath = AssetLoader.whichOS(screenAssetPath);


    public EndScreen (AlienVsZombie game, int score) {
        this.game = game;
        this.score = score;

        // RED IMG RN
        gameOverBanner = new Texture(AssetLoader.getAssetPath(assetPath, "red.png"));
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));

        /* HIGH SCORE LOGIC
        //Get highscore from save files
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
        game.batch.begin();


        game.batch.draw(gameOverBanner, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //AlienVsZombie.WIDTH / 2 - BANNER_WIDTH / 2, SpaceGame.HEIGHT - BANNER_HEIGHT - 15, BANNER_WIDTH, BANNER_HEIGHT);

        // DRAW SCORE on screen
        scoreFont.draw(game.batch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        scoreFont.draw(game.batch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


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
