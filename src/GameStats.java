import java.util.List;

/**
 * Enhanced interface for tracking game statistics. Extends the kernel to
 * provide additional functionality.
 */
public interface GameStats extends GameStatsKernel {

    /**
     * Computes the average score for a game.
     *
     * @param game
     *            the name of the game
     * @return average score, or -1 if no scores exist
     */
    double getAverageScore(String game);

    /**
     * Retrieves all recorded scores for a specific game.
     *
     * @param game
     *            the name of the game
     * @return a list of scores, or an empty list if no scores exist
     */
    List<Integer> getAllScores(String game);
}
