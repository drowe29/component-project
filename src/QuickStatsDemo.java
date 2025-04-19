import components.gamestats.GameStats;
import components.gamestats.GameStats1L;

/**
 * QuickStatsDemo is a simple demonstration of the {@link GameStats} component.
 * <p>
 * This utility class logs a few sample scores for different games and then
 * prints out:
 * <ul>
 * <li>The set of games recorded</li>
 * <li>The best score for a particular game</li>
 * <li>The average score for another game</li>
 * <li>A full text dump of all recorded statistics</li>
 * </ul>
 * </p>
 * <p>
 * Intended to show off the most basic operations of the GameStats API.
 * </p>
 */
public final class QuickStatsDemo {

    /**
     * Private constructor to prevent instantiation.
     */
    private QuickStatsDemo() {
    }

    /**
     * Entry point for the QuickStatsDemo.
     * <p>
     * Creates a {@link GameStats1L} instance, logs sample scores, and prints
     * summary information to standard output.
     * </p>
     *
     * @param args
     *            command-line arguments (ignored)
     */
    public static void main(String[] args) {
        GameStats stats = new GameStats1L();
        stats.addScore("Overwatch", 275);
        stats.addScore("Overwatch", 310);
        stats.addScore("Fortnite", 140);

        System.out.println("Games recorded: " + stats.getGames());
        System.out.println(
                "Best Overwatch score: " + stats.getBestScore("Overwatch"));
        System.out.println(
                "Average Fortnite score: " + stats.getAverageScore("Fortnite"));
        System.out.println("\nFull dump:\n" + stats);
    }
}
