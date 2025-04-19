import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple GameStatsTracker to log and analyze video game scores.
 */
public class GameStatsTracker {

    /**
     * A map storing game names as keys and lists of scores as values.
     */
    private final Map<String, List<Integer>> gameStats;

    /**
     * Constructs a new GameStatsTracker with an empty game statistics map.
     */
    public GameStatsTracker() {
        this.gameStats = new HashMap<>();
    }

    /**
     * Logs a score for a specific game.
     *
     * @param game
     *            the name of the game being logged
     * @param score
     *            the score being input
     */
    public void logGame(String game, int score) {
        this.gameStats.putIfAbsent(game, new ArrayList<>());
        this.gameStats.get(game).add(score);
    }

    /**
     * Returns the highest recorded score for a game.
     *
     * @param game
     *            the name of the game
     * @return highest score, or -1 if no scores are recorded
     */

    public int getBestScore(String game) {
        if (!this.gameStats.containsKey(game)
                || this.gameStats.get(game).isEmpty()) {
            return -1;
        }
        return Collections.max(this.gameStats.get(game));
    }

    /**
     * Returns the total number of times a game has been played.
     *
     * @param game
     *            the name of the game
     * @return total play count
     */
    public int getTotalGamesPlayed(String game) {
        return this.gameStats.getOrDefault(game, Collections.emptyList())
                .size();
    }

    /**
     * Computes the average score for a game.
     *
     * @param game
     *            the name of the game
     * @return the average score, or -1 if no scores exist
     */
    public double getAverageScore(String game) {
        List<Integer> scores = this.gameStats.get(game);
        if (scores == null || scores.isEmpty()) {
            return -1;
        }
        //just to compile, need to figure out math
        return 0;
    }

    /**
     * Main method to demonstrate.
     *
     * @param args
     *            command-line arguments (not used)
     */
    public static void main(String[] args) {
        GameStatsTracker tracker = new GameStatsTracker();

        tracker.logGame("Marvel Rivals", 200);
        tracker.logGame("Marvel Rivals", 300);
        tracker.logGame("Marvel Rivals", 250);
        tracker.logGame("Fortnite", 100);
        tracker.logGame("Fortnite", 150);

        System.out.println("Best Marvel Rivals Score: "
                + tracker.getBestScore("Marvel Rivals")); //300
        System.out.println("Total  Marvel Rivals Games: "
                + tracker.getTotalGamesPlayed("Marvel Rivals")); //3
        System.out.println("Average Marvel Rivals Score: "
                + tracker.getAverageScore("Marvel Rivals")); //250.0

        System.out.println(
                "Best Fortnite Score: " + tracker.getBestScore("Fortnite")); //150
        System.out.println("Total  Fortnite Games: "
                + tracker.getTotalGamesPlayed("Fortnite")); //2
        System.out.println("Average Fortnite Score: "
                + tracker.getAverageScore("Fortnite")); //125.0

    }

}
