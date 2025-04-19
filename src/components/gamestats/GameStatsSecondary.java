package components.gamestats;

import java.util.ArrayList;
import java.util.List;

import components.set.Set;

/**
 * An abstract class for secondary methods of the GameStats component.
 *
 * <p>
 * This class implements all of the enhanced methods of the {@link GameStats}
 * interface using *only* the kernel methods. It also provides
 * {@link #toString()}, {@link #equals(Object)}, and {@link #hashCode()}.
 * </p>
 *
 * <p>
 * Concrete subclasses must implement the kernel‐level methods:
 * <ul>
 * <li>{@link #logGame(String,int)}</li>
 * <li>{@link #getBestScore(String)}</li>
 * <li>{@link #getTotalGamesPlayed(String)}</li>
 * <li>{@link #clear()}</li>
 * <li>{@link #getScoreAt(String,int)}</li>
 * <li>{@link #getGames()}</li>
 * </ul>
 * </p>
 */
public abstract class GameStatsSecondary implements GameStats {

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GameStats:\n");
        for (String game : this.getGames()) {
            sb.append("Game: ").append(game).append(", Total Plays: ")
                    .append(this.getTotalGamesPlayed(game))
                    .append(", Best Score: ").append(this.getBestScore(game))
                    .append(", Average Score: ")
                    .append(this.getAverageScore(game)).append("\n");
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc}
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
        Set<String> games1 = this.getGames();
        Set<String> games2 = other.getGames();
        if (!games1.equals(games2)) {
            return false;
        }
        for (String g : games1) {
            if (this.getTotalGamesPlayed(g) != other.getTotalGamesPlayed(g)
                    || this.getBestScore(g) != other.getBestScore(g)
                    || Double.compare(this.getAverageScore(g),
                            other.getAverageScore(g)) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 7;
        for (String g : this.getGames()) {
            hash = 31 * hash + g.hashCode();
            hash = 31 * hash + this.getTotalGamesPlayed(g);
            hash = 31 * hash + this.getBestScore(g);
            hash = 31 * hash
                    + Double.valueOf(this.getAverageScore(g)).hashCode();
        }
        return hash;
    }

    // ----------------------------------------------------------------------
    // Abstract kernel‐level methods (to be implemented by GameStats1L)
    // ----------------------------------------------------------------------

    @Override
    public abstract void logGame(String game, int score);

    @Override
    public abstract int getBestScore(String game);

    @Override
    public abstract int getTotalGamesPlayed(String game);

    @Override
    public abstract void clear();

    /**
     * Helper that returns the score at a given index for a game.
     *
     * @param game
     *            the name of the game
     * @param index
     *            index into that game's score list
     * @return the recorded score
     */
    protected abstract int getScoreAt(String game, int index);

    /**
     * {@inheritDoc}
     * 
     * @return the set of all game names logged
     */
    @Override
    public abstract Set<String> getGames();
}
