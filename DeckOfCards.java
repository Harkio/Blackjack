
public class DeckOfCards {

	 // We declare and create a 52 element array to contain the 52 cards in the pack
	static int[] deck = new int[52];
	
	// Initialize cards
	// have to have for loop inside a method, only data fields up here 
	static String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
	static String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	
	/* We declare, create and initialise an array of 13 elements to contain the card rank and values. 
	 * The index of the array is the rank and the contents of the array are the rank values. */
	static int [] value = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	
	// if (alreadyPicked[cardNumber]) then the card has already been picked
	static boolean[] alreadyPicked = new boolean[52];

	// put numbers 0 to 51 into 'deck' array
	// these numbers represent cards
	public DeckOfCards(){
		for(int i = 0; i < 52; i++){
			deck[i] = i;
		}
	}
	
	/* Returns an integer representing a card in the deck. 
	 * Between shuffles, the same card will never be returned twice */   
	public static int drawCard() {
//		int index = (int)(Math.random()*52); 
		int index = 0;
		int cardNumber = deck[index]; // a number between 0 and 51 representing the card
		
		while(alreadyPicked[cardNumber]){ 
//			index = (int)(Math.random()*52);
			cardNumber = deck[++index];
		}
		
		alreadyPicked[cardNumber] = true;
		return cardNumber;
	}
	
	public static int getRank(int card){
		return value[card % value.length];
	}
	
	public static String getSuit(int card){
		return suits[card % suits.length];
	}
	
	public static String getFaceRank(int card){
		return ranks[card % ranks.length];
	}
	
	/* Shuffle the "deck" of ints */   
	public static void shuffle() {
		alreadyPicked = new boolean[52]; // reset picked cards - it's as if no cards have been drawn
		
		for (int i = 0; i < deck.length; i++) {
			// Generate an index randomly
			int index = (int)(Math.random() * deck.length);
			int temp = deck[i];
			deck[i] = deck[index]; 
			deck[index] = temp;
		}
	}
	
	public static void main(String[] args){
//		shuffle();
		DeckOfCards d = new DeckOfCards();
		for(int i = 0; i < 52; i++){
			System.out.printf("%-10s %-5s %d\n", d.getSuit(deck[i]),  d.getFaceRank(deck[i]), d.getRank(deck[i]));
//			System.out.println(getFaceRank(i));
		}
		
	}
}