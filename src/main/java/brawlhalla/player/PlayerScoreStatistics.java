package brawlhalla.player;

public class PlayerScoreStatistics {
    private int damageDealt = 0;
    private int DamageReceived = 0;
    private int hitsDealt = 0;
    private int hitsReceived = 0;

    public PlayerScoreStatistics() { };

    /**
     * Increments damage if damage is greater than 0
     * @param damage the damage dealt by a player
     */
    public void incrementDamageDealt(int damage) {
        damageDealt = getDamageDealt() + (Math.max(damage, 0));
    }

    /**
     * Increments damage if damage is greater than 0
     * @param damage the damage received by a player
     */
    public void incrementDamageReceived(int damage) {
        DamageReceived = getDamageReceived() + (Math.max(damage, 0));
    }

    /**
     * Increments damage if damage is greater than 0
     * @param hits the hits dealt by a player
     */
    public void incrementHitsDealt(int hits) {
        hitsDealt = getHitsDealt() + (Math.max(hits, 0));
    }

    /**
     * Increments damage if damage is greater than 0
     * @param hits the hits received by a player
     */
    public void incrementHitsReceived(int hits) {
        hitsReceived = getHitsReceived() + (Math.max(hits, 0));
    }

    public PlayerScoreStatistics getPlayerScoreStatistics() {
        return this;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public int getDamageReceived() {
        return DamageReceived;
    }

    public int getHitsDealt() {
        return hitsDealt;
    }

    public int getHitsReceived() {
        return hitsReceived;
    }
}
