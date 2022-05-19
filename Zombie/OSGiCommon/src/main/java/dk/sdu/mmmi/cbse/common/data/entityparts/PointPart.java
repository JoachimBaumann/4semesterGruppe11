package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

import java.util.HashSet;

public class PointPart implements EntityPart{

    public HashSet getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(HashSet enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    HashSet enemiesKilled = new HashSet<>();

    public PointPart(HashSet enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }


    @Override
    public void process(GameData gameData, Entity entity) {
        gameData.

        enemiesKilled


    }
}
