package dk.sdu.mmmi.cbse.common.weapon;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;


public interface WeaponSPI {
    Entity createWeapon(Entity e, GameData gameData);
}
