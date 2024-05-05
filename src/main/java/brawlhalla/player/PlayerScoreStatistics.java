package brawlhalla.player;

/**
 * This will track the score from a player, seperated in different metrics.
 * Example: Damage dealt/received
 */
public class PlayerScoreStatistics {
    private int DamageReceived = 0;
    private int hitsReceived = 0;

    public PlayerScoreStatistics() { }

    /**
     * Increments damage if damage is greater than 0
     * @param damage the damage received by a player
     */
    public void incrementDamageReceived(int damage) {
        DamageReceived = getDamageReceived() + (Math.max(damage, 0));
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

    public int getDamageReceived() {
        return DamageReceived;
    }

    public int getHitsReceived() {
        return hitsReceived;
    }
}
