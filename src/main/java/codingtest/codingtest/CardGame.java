package codingtest;

import java.util.ArrayList;
import java.util.List;

import codingtest.domain.Deck;
import codingtest.domain.Player;

/**
 * Class that handles the playing of a card game from a simple command line interface,
 * and echoes back a step-by-step description of the game to the console.
 */
public class CardGame {
    private Deck deck;
    private List<Player> playerList;
    
    public CardGame(Deck deck){
        this.deck = deck;
    }
    
    public CardGame(Deck deck, List<Player> playerList){
        this.deck = deck;
        this.playerList = playerList;
    }
    
    public void gameStart(){
        
        for(Player player : playerList){
            Thread thread = new Thread(player);
            thread.start();    
        }   
        boolean allFinished = false;
        while(!allFinished){           
            for(Player player : playerList){
                if(!player.isFinished()){
                    allFinished = false;
                    break;
                }else{
                    allFinished = true;
                    continue;
                }
            }      
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        checkWinner(playerList);
        
        System.out.println("End of Game!");
    }
    
    private void checkWinner(List<Player> playerList){
        List<Player> playerUnder21 = new ArrayList<Player>();
        for(Player player : playerList){
            if(player.getTotal()<=21){
                playerUnder21.add(player);
            }
        }
        if(playerUnder21.size()==0){
            System.out.println("All burst, no winner!");
        }else{
            int maxPoint = 0;
            for(Player player : playerUnder21){
                if(maxPoint==0){
                    maxPoint = player.getTotal();
                }else if(maxPoint<player.getTotal()){
                    maxPoint = player.getTotal();
                }
            }
            for(Player player : playerUnder21){
                if(player.getTotal()==maxPoint){
                    System.out.println("Player:" + player.getPlayerName() + " wins.");
                }
            }
        }
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    
    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    } 

    /**
     * Main. Plays a card game from a command line interface.
     * @param args the arguments to the game
     */
    public static void main(String[] args) {
        Deck deck = new Deck();
        List<Player> players = new ArrayList<Player>(3);
        Player player1 = new Player("Eric", deck.popCard(),deck.popCard());
        player1.setDeck(deck);
        Player player2 = new Player("John", deck.popCard(),deck.popCard());
        player2.setDeck(deck);
        Player player3 = new Player("Charlie", deck.popCard(),deck.popCard());
        player3.setDeck(deck);
        player1.setNextPlayer(player2);
        player2.setNextPlayer(player3);
        player3.setNextPlayer(player1);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        
        deck.setCurrentPlayer(player1);
        
        CardGame cardGame = new CardGame(new Deck(),players);
        System.out.println("Start a blackjack game!");
        cardGame.gameStart();
    }
}
