package codingtest.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import codingtest.domain.Card.Ranks;
import codingtest.domain.Card.Suits;

/**
 * This is the class that represents a deck of cards in a card game.
 */
public class Deck {
    private Stack<Card> cardStack = new Stack<Card>();
    private List<Card> cardList = new ArrayList<Card>(52);
    private Player currentPlayer;
    
    public Deck(){
        for(Suits suit: Card.Suits.values()){
            for(Ranks rank : Card.Ranks.values()){
                cardList.add(new Card(suit,rank));
            }
        }
        this.shuffleCard();
    }

    public Stack<Card> getCardStack() {
        return cardStack;
    }
    
    public Card popCard(){
        if(cardStack.isEmpty()){
            //re shuffle the card.
            shuffleCard();
        }
        return cardStack.pop();
    }  

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private void shuffleCard(){
        while(!cardList.isEmpty()){
            int size = cardList.size();
            int index = getRandomIndex(size);
            cardStack.push(cardList.get(index));
            cardList.remove(index);
        }
    }
    
    private int getRandomIndex(int range){
        if(range == 1){
            return 0;
        }else{
            return (int)(Math.random() * range); 
        }
    }
}
