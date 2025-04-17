import java.util.List;

/**
 * Enhanced interface for tracking game statistics. Extends the kernel to
 * provide additional functionality.
 */
public interface GameStats extends GameStatsKernel {

     /**
      * Adds a score for a specific game.
      *
      * @param game
      *             the name of the game
      * @param score
      *             the score to add
      * @requires game != null and score >= 0
      * @ensures the score is added to the list of scores for the given game
      */
     void addScore(String game, int score);

     /**
      * Computes the average score for a game.
      *
      * @param game
      *             the name of the game
      * @return the average score, or -1 if no scores exist
      * @requires game != null
      * @ensures getAverageScore=(sum of all scores for the game)/(total number
      *          of scores), or -1 if no scores exist
      */
     double getAverageScore(String game);

     /**
      * Retrieves all recorded scores for a specific game.
      *
      * @param game
      *             the name of the game
      * @return a list of scores, or an empty list if no scores exist
      * @requires game != null
      * @ensures getAllScores = [list containing all scores recorded for the
      *          game]
      */
     List<Integer> getAllScores(String game);

     /**
      * Retrieves the set of all games with recorded scores.
      *
      * @return a set of game names
      * @ensures getGames = {all game names that have at least one score
      *          recorded}
      */
}
