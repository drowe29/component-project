import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * An abstract class for secondary methods of the GameStats component.
 *
 * This class implements the secondary (enhanced) methods of the GameStats
 * interface using only the kernel methods. It also provides implementations of
 * key Object methods. Concrete classes must implement the kernel methods.
 */
public abstract class GameStatsSecondary implements GameStats {
    /**
     * Computes the average score for a game using only the kernel methods. It
     * iterates over the scores obtained via {@link #getScoreAt(String, int)}.
     *
     * @param game
     *            the name of the game
     * @return the average score, or -1 if no scores exist
     */
    @Override
    public double getAverageScore(String game) {
        int count = this.getTotalGamesPlayed(game);
        if (count == 0) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += this.getScoreAt(game, i);
        }
        return (double) sum / count;
    }

    /**
     * Retrieves all recorded scores for a specific game using only the kernel
     * methods.
     *
     * @param game
     *            the name of the game
     * @return a list of scores, or an empty list if no scores exist
     */
    @Override
    public List<Integer> getAllScores(String game) {
        List<Integer> scores = new ArrayList<>();
        int count = this.getTotalGamesPlayed(game);
        for (int i = 0; i < count; i++) {
            scores.add(this.getScoreAt(game, i));
        }
        return scores;
    }

    /**
     * Generates a string representation of the game statistics. This method
     * uses only the kernel methods to retrieve the necessary data.
     *
     * @return a string representing all game statistics
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GameStats:\n");
        for (String game : this.getGames()) {
            sb.append("Game: ").append(game).append(", ");
            sb.append("Total Plays: ").append(this.getTotalGamesPlayed(game))
                    .append(", ");
            sb.append("Best Score: ").append(this.getBestScore(game))
                    .append(", ");
            sb.append("Average Score: ").append(this.getAverageScore(game))
                    .append("\n");
        }
        return sb.toString();
    }

    /**
     * Determines equality between this GameStats object and another object. The
     * comparison is performed using kernel methods only.
     *
     * @param obj
     *            the other object to compare
     * @return true if the objects represent the same game statistics; false
     *         otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GameStats)) {
            return false;
        }
        GameStats other = (GameStats) obj;

        // Compare the sets of game names
        Set<String> games1 = this.getGames();
        Set<String> games2 = other.getGames();
        if (!games1.equals(games2)) {
            return false;
        }
        // Compare statistics for each game
        for (String game : games1) {
            if (this.getTotalGamesPlayed(game) != other
                    .getTotalGamesPlayed(game)) {
                return false;
            }
            if (this.getBestScore(game) != other.getBestScore(game)) {
                return false;
            }
            if (Double.compare(this.getAverageScore(game),
                    other.getAverageScore(game)) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Computes the hash code for this GameStats object using only the kernel
     * methods.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        for (String game : this.getGames()) {
            hash = 31 * hash + game.hashCode();
            hash = 31 * hash + this.getTotalGamesPlayed(game);
            hash = 31 * hash + this.getBestScore(game);
            hash = 31 * hash
                    + Double.valueOf(this.getAverageScore(game)).hashCode();
        }
        return hash;
    }

    // Kernel methods: Concrete classes must implement these methods.

    /**
     * Logs a score for a specific game.
     *
     * @param game
     *            the name of the game
     * @param score
     *            the score achieved
     */
    @Override
    public abstract void logGame(String game, int score);

    /**
     * Returns the highest recorded score for a game.
     *
     * @param game
     *            the name of the game
     * @return the highest score, or -1 if no scores are recorded
     */
    @Override
    public abstract int getBestScore(String game);

    /**
     * Returns the total number of times a game has been played.
     *
     * @param game
     *            the name of the game
     * @return the total number of recorded scores
     */
    @Override
    public abstract int getTotalGamesPlayed(String game);

    /**
     * Clears all game statistics.
     */
    @Override
    public abstract void clear();

    // Additional abstract methods assumed to be part of the kernel interface.
    // These methods provide indirect access to the underlying data.

    /**
     * Returns the score at a specific index for a given game.
     *
     * @param game
     *            the name of the game
     * @param index
     *            the index of the score to retrieve
     * @return the score at the specified index
     */
    protected abstract int getScoreAt(String game, int index);

    /**
     * Returns the set of game names that have been logged.
     *
     * @return a set of game names
     */
    @Override
    public abstract Set<String> getGames();
}
