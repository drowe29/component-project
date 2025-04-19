package components.gamestats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import components.gamestats.GameStats1L;

public class GameStats1LTest {

    private GameStats1L gs;

    @Before
    public void setUp() {
        this.gs = new GameStats1L();
    }

    // ---------- logGame / getTotalGamesPlayed ----------

    @Test
    public void testLogGameFirstScore() {
        this.gs.logGame("Overwatch", 250);
        assertEquals(1, this.gs.getTotalGamesPlayed("Overwatch"));
        assertEquals(250, this.gs.getBestScore("Overwatch"));
    }

    @Test
    public void testLogGameMultipleScores() {
        this.gs.logGame("Overwatch", 200);
        this.gs.logGame("Overwatch", 400);
        this.gs.logGame("Overwatch", 300);
        assertEquals(3, this.gs.getTotalGamesPlayed("Overwatch"));
        assertEquals(400, this.gs.getBestScore("Overwatch"));
    }

    // ---------- clear ----------

    @Test
    public void testClear() {
        this.gs.logGame("Fortnite", 120);
        this.gs.clear();
        assertEquals(0, this.gs.getTotalGamesPlayed("Fortnite"));
        assertEquals(-1, this.gs.getBestScore("Fortnite"));
        assertTrue(this.gs.getGames().isEmpty());
    }

    // ---------- transferFrom ----------

    @Test
    public void testTransferFrom() {
        GameStats1L src = new GameStats1L();
        src.logGame("Soccer", 2);
        this.gs.transferFrom(src);

        // destination now has data
        assertEquals(1, this.gs.getTotalGamesPlayed("Soccer"));
        // source is empty
        assertEquals(0, src.getTotalGamesPlayed("Soccer"));
    }
}
