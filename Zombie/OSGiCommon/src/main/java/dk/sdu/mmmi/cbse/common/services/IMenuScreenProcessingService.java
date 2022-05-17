package dk.sdu.mmmi.cbse.common.services;

import com.badlogic.gdx.Screen;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IMenuScreenProcessingService {
    Screen activate(GameData gameData, World world);
    void deActivate();
}
