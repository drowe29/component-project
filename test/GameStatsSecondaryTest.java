package components.gamestats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import components.gamestats.GameStats;
import components.gamestats.GameStats1L;

public class GameStatsSecondaryTest {

    private GameStats gs; // polymorphic handle
    private GameStats1L impl; // to cross‑check state

    @Before
    public void setUp() {
        this.impl = new GameStats1L();
        this.gs = this.impl; // treat as enhanced interface
    }

    // ---------- getAverageScore ----------

    @Test
    public void testAverageSingleScore() {
        this.gs.addScore("Chess", 100);
        assertEquals(100.0, this.gs.getAverageScore("Chess"), 0.001);
    }

    @Test
    public void testAverageManyScores() {
        this.gs.addScore("Chess", 80);
        this.gs.addScore("Chess", 100);
        this.gs.addScore("Chess", 120);
        assertEquals(100.0, this.gs.getAverageScore("Chess"), 0.001);
    }

    // ---------- getAllScores ----------

    @Test
    public void testGetAllScoresSize() {
        this.gs.addScore("Halo", 50);
        this.gs.addScore("Halo", 70);
        assertEquals(2, this.gs.getAllScores("Halo").size());
    }

    // ---------- equals / toString (spot check) ----------

    @Test
    public void testEqualsSameStats() {
        GameStats other = new GameStats1L();
        other.addScore("Halo", 42);
        this.gs.addScore("Halo", 42);
        assertTrue(this.gs.equals(other));
    }

    @Test
    public void testToStringNotEmpty() {
        this.gs.addScore("Halo", 99);
        String s = this.gs.toString();
        assertTrue(s.contains("Halo"));
        assertTrue(s.contains("99"));
    }
}
