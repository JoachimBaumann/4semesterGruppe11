package dk.sdu.mmmi.cbse.common.weapon;

import dk.sdu.mmmi.cbse.common.data.Entity;


public class Weapon extends Entity {
    @Override
    public String getName() {
        return "Bullet";
    }

    @Override
    public  String getType(){
        return "Weapon";
    }
}
