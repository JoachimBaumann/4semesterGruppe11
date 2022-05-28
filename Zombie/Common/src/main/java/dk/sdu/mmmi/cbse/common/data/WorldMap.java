package dk.sdu.mmmi.cbse.common.data;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class WorldMap extends ApplicationAdapter {

    private ShapeRenderer sr;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;


    public ShapeRenderer getSr() {
        return sr;
    }

    public String getMapBounds() {
        return map.getTileSets().toString();
    }

    // sets the renderer
    public void setSr(ShapeRenderer sr) {
        this.sr = sr;
    }

    // returns the map
    public TiledMap getMap() {
        return map;
    }

    // sets the tiled map
    public void setMap(TiledMap map) {
        this.map = map;
    }

    // Returns the renderer
    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    //Renderer for map
    public void setRenderer(OrthogonalTiledMapRenderer renderer) {
        this.renderer = renderer;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    // GameCamera
    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void create() {
        // map loading logic goes here, in the extended class
    }

}


