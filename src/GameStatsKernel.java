/**
 * Kernel interface for tracking game statistics. Defines the core operations
 * needed to manage scores.
 */
public interface GameStatsKernel {

    /**
     * Logs a score for a specific game.
     *
     * @param game
     *            the name of the game
     * @param score
     *            the score achieved
     */
    void logGame(String game, int score);

    /**
     * Returns the highest recorded score for a game.
     *
     * @param game
     *            the name of the game
     * @return highest score, or -1 if no scores are recorded
     */
    int getBestScore(String game);

    /**
     * Returns the total number of times a game has been played.
     *
     * @param game
     *            the name of the game
     * @return total play count
     */
    int getTotalGamesPlayed(String game);

    /**
     * Clears all game statistics.
     */
    void clear();
}
