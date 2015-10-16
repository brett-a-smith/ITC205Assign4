/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.List;

/**
 *
 * @author Brett.Smith
 */
public class TestFile {
    public static void main(String[] args)
    {
    }
    
    public static void runWinPayoutTest(DiceValue pick)
    {
        // initialise variables
        int bet = 5;
        int winnings = 0;
        
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();

        Player player = new Player("Fred", 100);
        Game game = new Game(d1, d2, d3);
        
        List<DiceValue> cdv = game.getDiceValues();

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
    
    public static void runAutomatedWinPayoutTest()
            
            // Note: Since bug fix 3 was implemented, this function fails to
            // operate because of line 77, the cdv variable inside of game didnt
            // change, now it does, which is normal. This function shoudl not be
            // used.
    {
        // initialise variables
        int bet = 5;
        boolean winOne = true, winTwo = true, winThree = true;
        
        while(winOne || winTwo || winThree)
        {
            // Loop begin
            Player player = new Player("Fred", 100);
            Dice d1 = new Dice();
            Dice d2 = new Dice();
            Dice d3 = new Dice();
            Game game = new Game(d1, d2, d3);
            List<DiceValue> cdv = game.getDiceValues();
            DiceValue pick = cdv.get(0);

            if(cdv.get(0).equals(pick) || cdv.get(1).equals(pick) || cdv.get(2).equals(pick))
                // If there is a win of any type
            {
                if (winOne)
                {
                    winOne = false;
                    displayWinnings(game, cdv, player, pick, bet);
                }
                else if (winTwo)
                {
                    if(cdv.get(0).equals(pick) && cdv.get(1).equals(pick) && !cdv.get(2).equals(pick) || cdv.get(1).equals(pick) && cdv.get(2).equals(pick) && !cdv.get(0).equals(pick) || cdv.get(0).equals(pick) && cdv.get(2).equals(pick) && !cdv.get(1).equals(pick))
                    {
                        winTwo = false;
                        displayWinnings(game, cdv, player, pick, bet);
                    }
                }
                else if (winThree)
                {
                    if (winThree && cdv.get(0).equals(pick) && cdv.get(1).equals(pick) && cdv.get(2).equals(pick))
                    {
                        winThree = false;
                        displayWinnings(game, cdv, player, pick, bet);
                    }
                }
            }
        }
    }
    
    private static void displayWinnings(Game game, List<DiceValue> cdv, Player player, DiceValue pick, int bet)
    {
        int winnings = 0;
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
    
    public static void runWinBettingLimitTest()
    {
        int bet = 5;
        int winnings = 0;
        DiceValue pick = DiceValue.CLUB;
        
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();

        Player player = new Player("Fred", 100);
        Game game = new Game(d1, d2, d3);
        
        List<DiceValue> cdv = game.getDiceValues();

        while (player.balanceExceedsLimitBy(bet))
        {
            pick = DiceValue.getRandom();
            
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
    
    public static void runOddsCheck()
    {
        int bet = 5;
        int winnings = 0;
        int runnings = 0;
        int numGamesWon = 0;
        int numGamesLost = 0;
        
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();

        Player player = new Player("Fred", 100);
        Game game = new Game(d1, d2, d3);
        
        List<DiceValue> cdv = game.getDiceValues();
        DiceValue pick = game.getDiceValues().get(0); // Bug is the dice dont roll....

        while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200 && runnings < 1000)
        {
            // output player selection
            System.out.printf("%s bet %d on %s\n",
                                player.getName(), bet, pick);

            // round of game played
            winnings = game.playRound(player, pick, bet);
            cdv = game.getDiceValues();

            // output game result after round
            System.out.printf("Rolled %s, %s, %s\n",
                                cdv.get(0), cdv.get(1), cdv.get(2));
            
            if(cdv.get(0).equals(pick) || cdv.get(1).equals(pick) || cdv.get(2).equals(pick))
            {
                numGamesWon++;
            }
            else
            {
                numGamesLost++;
            }

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
            System.out.println("Game run: " + runnings);
            runnings++; // Used in the event that we keep winning nothing.
        }
        
        System.out.println("Won games: " + numGamesWon + " Lost games: " + numGamesLost + " Ratio: " + (float) numGamesWon/(numGamesWon + numGamesLost));
        
    }
}
