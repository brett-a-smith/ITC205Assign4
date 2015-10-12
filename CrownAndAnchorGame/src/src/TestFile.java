/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.src;

import java.util.List;

/**
 *
 * @author Brett.Smith
 */
public class TestFile {
    public static void main(String[] args)
    {
        runWinPayoutTest();
    }
    
    public static void runWinPayoutTest()
    {
        // set seed to produce consistent results
        DiceValue.RANDOM.setSeed(1);
        
        // initialise variables
        int bet = 5;
        int winnings = 0;
        
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();

        Player player = new Player("Fred", 100);
        Game game = new Game(d1, d2, d3);
        List<DiceValue> cdv = game.getDiceValues();
        DiceValue pick = DiceValue.CROWN;
        
        // output player selection
        System.out.printf("%s bet %d on %s\n",
                            player.getName(), bet, pick);
        
        // round of game played
        winnings = game.playRound(player, pick, bet);
        cdv = game.getDiceValues();
        
        // output game result after round
        System.out.printf("Rolled %s, %s, %s\n",
                            cdv.get(0), cdv.get(1), cdv.get(2));
        
        if (winnings > 0) 
        {
            System.out.printf("%s won %d, balance now %d\n\n",
                        player.getName(), winnings, player.getBalance());
        }
        else 
        {
            System.out.printf("%s lost, balance now %d\n\n",
                        player.getName(), player.getBalance());
        }
        
    }
}
