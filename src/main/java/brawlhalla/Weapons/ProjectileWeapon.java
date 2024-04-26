package brawlhalla.Weapons;

import com.github.hanyaeger.api.entities.Direction;

public abstract class ProjectileWeapon implements IWeapon{

    @Override
    public abstract void attack(Direction direction) ;
}
