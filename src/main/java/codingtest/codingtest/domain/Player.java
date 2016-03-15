package codingtest.domain;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private String playerName;
    private List<Card> cards = new ArrayList<Card>();
    private int total=0;
    private Deck deck;
    private Player nextPlayer;
    private boolean finished;
    
    public Player(String playerName, Card card1, Card card2){
        System.out.println("Player:" + playerName + " joined the game!");
        this.playerName = playerName;
        System.out.println("Player:" + playerName + " got initial two cards!");
        cards.add(card1);
        cards.add(card2);
        finished = false;
    }
    
    public void hitMe(Card card){
        cards.add(card);
        System.out.println("Player " + playerName + " got card: " + card.toString());
        total += card.getRank().getRankValue();
    }

    public int getTotal() {
        return total;
    }
    
    public void setDeck(Deck deck){
        this.deck = deck;
    }
      

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public String getPlayerName() {
        return playerName;
    }
    
    public boolean isFinished(){
        return finished;
    }

    @Override
    public String toString(){
        return "Player:" + playerName;
    }
    
    @Override
    public boolean equals(Object o){
        if(o==null || !(o instanceof Player)){
            return false;
        }
        Player other = (Player)o;
        if(other.playerName.equals(this.playerName)){
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while(total<17){
            synchronized(deck){
                while(!deck.getCurrentPlayer().equals(this)){
                    if(!deck.getCurrentPlayer().isFinished()){
                        try{
                            System.out.println("Player:" + playerName + " Waiting for next player: " + deck.getCurrentPlayer().getPlayerName());
                            deck.wait();
                        }catch(InterruptedException ie){
                            ie.printStackTrace();
                        }
                    }else{
                        deck.setCurrentPlayer(deck.getCurrentPlayer().getNextPlayer());
                    }
                }
                System.out.println("Player:" + playerName + " turn to play.");
                Card card = deck.popCard();
                hitMe(card); 
                deck.setCurrentPlayer(nextPlayer);             
                deck.notifyAll();
            }
        }
        if(total>21){
            System.out.println("Player:" + playerName + " gone burst.");
            deck.setCurrentPlayer(nextPlayer);
        }else{
            System.out.println("Player:" + playerName + " has points:" + total);
            deck.setCurrentPlayer(nextPlayer);
        }
        finished = true;
        
    }
}
