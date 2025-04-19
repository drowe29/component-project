package components.gamestats;

import components.set.Set;
import components.standard.Standard;

/**
 * Kernel interface for tracking game statistics. Defines the core operations
 * needed to manage scores.
 */
public interface GameStatsKernel extends Standard<GameStats> {

     /**
      * Logs a score for a specific game.
      *
      * @param game
      *             the name of the game
      * @param score
      *             the score achieved
      * @requires game != null and score >= 0
      * @ensures the score is added to the recorded scores for the specified
      *          game
      */
     void logGame(String game, int score);

     /**
      * Returns the highest recorded score for a game.
      *
      * @param game
      *             the name of the game
      * @return the highest score recorded for the game, or -1 if no scores are
      *         recorded
      * @requires game != null
      * @ensures getBestScore = highest score for the game, or -1 if no scores
      *          exist
      */
     int getBestScore(String game);

     /**
      * Returns the total number of times a game has been played.
      *
      * @param game
      *             the name of the game
      * @return the total number of times scores have been logged for the game
      * @requires game != null
      * @ensures getTotalGamesPlayed = number of scores logged for the specified
      *          game
      */
     int getTotalGamesPlayed(String game);

     /**
      * Clears all game statistics.
      *
      * @ensures all recorded game statistics are removed
      */
     @Override
     void clear();

     /**
      * Returns the set of game names that have been logged.
      *
      * @return a set of game names
      * @ensures getGames = {all game names for which at least one score has
      *          been logged}
      */
     Set<String> getGames();
}
