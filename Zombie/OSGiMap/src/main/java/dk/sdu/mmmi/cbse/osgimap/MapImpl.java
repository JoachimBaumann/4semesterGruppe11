package dk.sdu.mmmi.cbse.osgimap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.sdu.mmmi.cbse.common.data.WorldMap;

public class MapImpl extends WorldMap {

    public MapImpl() {
    }

    @Override
    public void create() {
        TiledMap map = new TiledMap();

        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("C:\\Users\\kinky\\IdeaProjects\\4semesterGruppe11\\Zombie\\Assets\\Map\\map.tmx");
        this.setMap(map);

        this.setRenderer(new OrthogonalTiledMapRenderer(map));
        this.setSr(new ShapeRenderer());
        Gdx.gl.glLineWidth(3);
    }
}
