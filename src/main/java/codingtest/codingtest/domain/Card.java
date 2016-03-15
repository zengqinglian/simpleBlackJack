package codingtest.domain;

/**
 * This is the domain class that represents a card in a card game.
 */
public class Card {
    private Suits suit;
    private Ranks rank;
    
    public Card(Suits suit, Ranks rank){
        this.suit = suit;
        this.rank = rank;
    }   
    
    public Suits getSuit() {
        return suit;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    public Ranks getRank() {
        return rank;
    }

    public void setValue(Ranks rank) {
        this.rank = rank;
    }
    
    @Override
    public String toString(){
        return suit.toString() + " " + rank.toString();
    }

    public static enum Suits {
        Hearts, Diamonds, Clubs , Spades
    }
    
    public static enum Ranks{
       Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Jack(10), Queen(10), King(10), Ace(11);
       private int rankValue;
       Ranks(int rankValue){
           this.rankValue = rankValue;
       }
       
       public int getRankValue(){
           return rankValue;
       }
    }
}
