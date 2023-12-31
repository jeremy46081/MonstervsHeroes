package mvh.world;

import mvh.enums.Direction;
import mvh.enums.WeaponType;

/**
 * A Monster is an Entity with a set ARMOR STRENGTH and a user provided WEAPON TYPE
 * @author Jonathan Hudson
 * @version 1.0a
 */
public final class Monster extends Entity {

    /**
     * The set armor strength of a Monster
     */
    private static final int MONSTER_ARMOR_STRENGTH = 2;

    /**
     * The user provided weapon type
     */
    private final WeaponType weaponType;

    /**
     * A Monster has regular health and symbol as well as a weapon type
     *
     * @param health     Health of Monster
     * @param symbol     Symbol for map to show Monster
     * @param weaponType The weapon type of the Monster
     */
    public Monster(int health, char symbol, WeaponType weaponType) {
        super(symbol, health);
        this.weaponType = weaponType;
    }

    /**
     * Gets Monster's weapon type
     * @return The Monster's weapon type
     */
    public WeaponType getWeaponType(){
        return this.weaponType;
    }

    /**
     * The weapon strength of monster is from their weapon type
     * @return The weapon strength of monster is from their weapon type
     */
    @Override
    public int weaponStrength() {
        return weaponType.getWeaponStrength();
    }

    /**
     * The armor strength of monster is from the stored constant
     * @return The armor strength of monster is from the stored constant
     */
    @Override
    public int armorStrength() {
        return MONSTER_ARMOR_STRENGTH;
    }

    @Override
    /**
     * this choosemove function gets a 5x5 grid which shows what is surrounding the
     * monster. The function then checks if there is an alive hero in this grid
     * which the monster uses to attack or move in the direction of the hero.
     */
    public Direction chooseMove(World local) {
        int e = 0;
        for (int i = 1; i > -2; i--) {
            for (int j = 1; j > -2; j--) {
                Entity a = local.getEntity(i, j);
                if (a.isAlive()) {
                    if (a instanceof Hero) {
                        for (Direction k : Direction.getDirections(i, j)) {
                            Entity c = local.getEntity(i, j, k);
                            if (c.canMoveOnTopOf()) {
                                return k;
                            }
                        }
                    } else {
                        return Direction.SOUTHEAST;
                    }

                }
            }

        }
        if (e == 2) {

        } else if (e ==3) {
            e = 4;
        }
        boolean m = false;
        if (e == 4) {
            m = false;
            return Direction.getRandomDirection();
        }
        boolean o = true;
        if (m == true) {
            return Direction.STAY;
        }
    return null;}

    @Override
    /**
     * In this function the monster gets a 3x3 view of the world of what is
     * around them and using this they can choose where they want to attack.
     * It loops through the local view and checks whether there is an alive hero
     * in this grid
     */
    public Direction attackWhere(World local) {
        for (int i = 1; i > -2 ; i --) {
            for (int j = 1; j > -2 ; j --) {
                Entity a = local.getEntity(i, j);
                if (a.isAlive()) {
                    if (a instanceof Hero) {
                        return Direction.getDirection(i, j);
                    }
                } else {
                        return Direction.NORTHWEST;

                }
            }
        }return null;
    }


    /**
     * Can only be moved on top of if dad
     * @return isDead()
     */
    @Override
    public boolean canMoveOnTopOf() {
        return isDead();
    }

    /**
     * Can only be attacked if alive
     * @return isAlive()
     */
    @Override
    public boolean canBeAttacked() {
        return isAlive();
    }

    @Override
    public String toString() {
        return super.toString() + "\t" + weaponType;
    }
}
