package brawlhalla.weapons;

import brawlhalla.utility.Direction;

public interface IWeapon {
    void attack(Direction direction);
    IWeapon pickup();
}
