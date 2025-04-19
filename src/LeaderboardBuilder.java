import java.util.Comparator;

import components.gamestats.GameStats;
import components.gamestats.GameStats1L;
import components.set.Set;

/**
 * Builds a simple “top score” leaderboard across all games.
 */
public final class LeaderboardBuilder {

    /**
     * Prevents instantiation.
     */
    private LeaderboardBuilder() {
    }

    /**
     * Demonstrates the use of the {@link GameStats} component by:
     * <ol>
     * <li>Logging sample scores for several games.</li>
     * <li>Retrieving the set of recorded games.</li>
     * <li>Sorting games in descending order by their best score.</li>
     * <li>Printing each game name alongside its best score.</li>
     * </ol>
     *
     * @param args
     *            command-line arguments (not used)
     */
    public static void main(String[] args) {
        GameStats gs = new GameStats1L();
        gs.addScore("Rocket League", 3);
        gs.addScore("Rocket League", 5);
        gs.addScore("Valorant", 19);
        gs.addScore("Valorant", 13);
        gs.addScore("Tetris", 100000);

        Set<String> games = gs.getGames();
        java.util.List<String> list = new java.util.ArrayList<>();
        for (String g : games) {
            list.add(g);
        }
        list.sort(Comparator.comparingInt(a -> -gs.getBestScore(a)));
        for (String g : list) {
            System.out.printf("%-12s  %6d%n", g, gs.getBestScore(g));
        }
    }
}
