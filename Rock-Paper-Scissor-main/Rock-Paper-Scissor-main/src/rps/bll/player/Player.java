package rps.bll.player;

//Project imports

import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;

//Java imports
import java.util.ArrayList;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;

    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }


    /**
     * Decides the next move for the bot...
     *
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();
        //Implement better AI here...
        int rockProbability = 0;
        int paperProbability = 0;
        int scissorsProbability = 0;

        ArrayList<Move> humanMoves = new ArrayList<>();
        for (Result r : results) {
            if (r.getWinnerPlayer().getPlayerType() == PlayerType.Human)
                humanMoves.add(r.getWinnerMove());
            else if (r.getLoserPlayer().getPlayerType() == PlayerType.Human)
                humanMoves.add(r.getLoserMove());
        }

        for (Move m : humanMoves) {
            if (m == Move.Rock)
                rockProbability++;
            else if (m == Move.Paper)
                paperProbability++;
            else
                scissorsProbability++;
        }


        if (humanMoves.size() > 2) {
            if (humanMoves.get(humanMoves.size() - 1) == humanMoves.get(humanMoves.size() - 2))
                return beat(humanMoves.get(humanMoves.size() - 1));
            else {
                if (humanMoves.get(humanMoves.size() - 1) == Move.Rock)
                    return Move.Rock;
                else if (humanMoves.get(humanMoves.size() - 1) == Move.Paper)
                    return Move.Paper;
                else return Move.Scissor;
            }
        }

        return biggestMove(rockProbability, paperProbability, scissorsProbability);
    }

    private Move beat(Move move) {
        if (move == Move.Rock)
            return Move.Paper;
        else if (move == Move.Paper)
            return Move.Scissor;

        return Move.Rock;
    }

    private Move biggestMove(int rockProbability, int paperProbability, int scissorsProbability) {
        if (rockProbability > paperProbability && rockProbability > scissorsProbability)
            return Move.Rock;
        else if (paperProbability > scissorsProbability && paperProbability > rockProbability)
            return Move.Paper;
        else return Move.Scissor;
    }
}
