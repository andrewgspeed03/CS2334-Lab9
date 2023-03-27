/*
 * @author Andrew Chen-Goodspeed
 * CS 2334 - Fall
 */
import java.util.*;
public class BlackjackHand {
	
	private static  final Map<Rank, Integer> CARD_VALUES = intialize();
	private static final int MAX_VALUE = 21;
	private List<Card> cards = new ArrayList<>();
	private int value = 0;
	private int numAcesAs11 = 0;
	
	private static Map<Rank, Integer> intialize(){
		Map<Rank, Integer> CARD_VALUES = new HashMap<Rank, Integer>();
		Rank[] ranks = Rank.values();
		
		for(Rank r : ranks) 
			if(r == Rank.JACK || r == Rank.QUEEN || r == Rank.KING)
				CARD_VALUES.put(r , 10);
			else if( r == Rank.ACE)
				CARD_VALUES.put(r, 11);
			else
				CARD_VALUES.put(r, Integer.parseInt(r.toString()));
		
		return CARD_VALUES;
	}
	
	public BlackjackHand(Card c1, Card c2) {
		cards.add(c1);
		cards.add(c2);
	}
	
	public void addCard(Card card) {
		if(card == null)
			throw new NullPointerException();
		if(getValue() < MAX_VALUE) {
			cards.add(card);
			getValue();
		}
	}
	
	public int size() {
		return cards.size();
	}
	
	
	public static Map<Rank, Integer> getCardValues(){
		Map<Rank, Integer> copy = new HashMap<>(CARD_VALUES);
		return copy;
	
	}
	
	public List<Card> getCards(){
		List<Card> copy = new ArrayList<>(cards);
		return copy;
	}
	public int getValue() {
		value = 0;
		numAcesAs11 = 0;
		
		for(Card c: cards)
			for(Rank r : CARD_VALUES.keySet())
				if(c.getRank() == Rank.ACE && c.getRank() == r){
					value += 11;
					numAcesAs11++;
				}
				else if(c.getRank() == r)
					value += CARD_VALUES.get(r);

		while(value > MAX_VALUE && numAcesAs11 != 0) {
			value -= 10;
			numAcesAs11--;
		}

		return value;
		
	}
	public String toString() {
		return cards.toString();
	}

}
