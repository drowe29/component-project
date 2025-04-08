import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;

/**
 * Kernel implementation #1 for {@code GameStats}.
 * <p>
 * Representation: {@code Map<String, Sequence<Integer>> stats} where each key
 * is a game name and the associated {@code Sequence<Integer>} stores the scores
 * for that game in the order they were logged.
 * </p>
 *
 * <p>
 * <strong>Convention (Representation Invariant)</strong>
 * <ul>
 * <li>{@code stats} is not {@code null}</li>
 * <li>No key in {@code stats} is {@code null} or an empty {@code String}</li>
 * <li>For every key {@code g} in {@code stats}, {@code stats.value(g)} is not
 * {@code null}</li>
 * <li>Every {@code Integer} value {@code s} in every stored sequence satisfies
 * {@code s >= 0}</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Correspondence (Abstraction Function)</strong><br>
 * {@code this} represents an abstract game‑statistics object {@code GS} such
 * that:
 * <ul>
 * <li>{@code dom(GS) = stats.keySet()}</li>
 * <li>For each game {@code g} in {@code dom(GS)}, {@code GS(g)} is the sequence
 * ⟨{@code stats.value(g).entry(0)}, …, {@code stats.value(g).entry(n‑1)}⟩ where
 * {@code n = stats.value(g).length()}</li>
 * </ul>
 * </p>
 */
public class GameStats1L extends GameStatsSecondary {

    //Representation

    /**
     * Map from game names to their logged scores.
     * <p>
     * The key is a non‑empty {@code String} naming the game. The value is a
     * {@code Sequence<Integer>} that stores every score logged for that game in
     * the exact order they were added. All scores are non‑negative.
     * </p>
     */
    private Map<String, Sequence<Integer>> stats;

    //Constructors

    /**
     * Default constructor: initializes an empty game‑statistics object.
     */
    public GameStats1L() {
        this.createNewRep();
    }

    /**
     * Replaces the current representation with a fresh, empty one.
     */
    private void createNewRep() {
        this.stats = new Map1L<>();
    }

    //Kernel Methods

    @Override
    public void logGame(String game, int score) {
        assert game != null : "Violation of: game is not null";
        assert score >= 0 : "Violation of: score >= 0";

        Sequence<Integer> seq;
        if (this.stats.hasKey(game)) {
            seq = this.stats.value(game);
        } else {
            seq = new Sequence1L<>();
            this.stats.add(game, seq);
        }
        seq.add(seq.length(), score);
    }

    @Override
    public int getBestScore(String game) {
        assert game != null : "Violation of: game is not null";

        if (!this.stats.hasKey(game) || this.stats.value(game).length() == 0) {
            return -1;
        }
        Sequence<Integer> seq = this.stats.value(game);
        int best = seq.entry(0);
        for (int i = 1; i < seq.length(); i++) {
            int s = seq.entry(i);
            if (s > best) {
                best = s;
            }
        }
        return best;
    }

    @Override
    public int getTotalGamesPlayed(String game) {
        assert game != null : "Violation of: game is not null";
        if (this.stats.hasKey(game)) {
            return this.stats.value(game).length();
        } else {
            return 0;
        }
    }

    //Standard methods

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public GameStats1L newInstance() {
        return new GameStats1L();
    }

    @Override
    public void transferFrom(GameStats source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";

        GameStats1L src = (GameStats1L) source;
        this.stats = src.stats;
        src.createNewRep();
    }

    //Methods required by GameStatsSecondary

    @Override
    protected int getScoreAt(String game, int index) {
        assert game != null : "Violation of: game is not null";
        assert this.stats.hasKey(game) : "Violation of: game exists";
        Sequence<Integer> seq = this.stats.value(game);
        assert 0 <= index
                && index < seq.length() : "Violation of: index in bounds";
        return seq.entry(index);
    }

    @Override
    public Set<String> getGames() {
        Set<String> games = new Set1L<>();
        Sequence<Pair<String, Sequence<Integer>>> temp = new Sequence1L<>();

        while (this.stats.size() > 0) {
            Pair<String, Sequence<Integer>> p = this.stats.removeAny();
            games.add(p.key());
            temp.add(temp.length(), p);
        }

        // Restore representation
        for (int i = 0; i < temp.length(); i++) {
            Pair<String, Sequence<Integer>> p = temp.entry(i);
            this.stats.add(p.key(), p.value());
        }
        temp.clear();
        return games;
    }
}
