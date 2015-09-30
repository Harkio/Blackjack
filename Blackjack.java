
public class Blackjack {

	static int[] dealerHand = new int[11];
	static int numDealerCards = 0;
	
	static int[] playerHand = new int[11];
	static int numPlayerCards = 0;
	
	static DeckOfCards cards = new DeckOfCards();
	
	public static void main(String[] args) {
		setupGame();
		printHand(playerHand, true);
		System.out.println();
		printHand(dealerHand, false);
	}
	
	/* Set up the game: perform a shuffle, "deal" 2 cards to both the
	dealer & player */
	public static void setupGame(){
		cards.shuffle();
		playerHand[numPlayerCards++] = cards.drawCard();
		playerHand[numPlayerCards++] = cards.drawCard();
		
		dealerHand[numDealerCards++] = cards.drawCard();
		dealerHand[numDealerCards++] = cards.drawCard();
	}
	
	/* Deal another card to the player */
	public static void hit(){
		playerHand[numPlayerCards++] = cards.drawCard();
	}
	
	/* Print the hand supplied as an int array to the console, e.g.:
	* 7 of Spades, Ace of Hearts, 3 of Diamonds
	* King of Clubs, 10 of Hearts
	* */
	public static void printHand(int[] hand, boolean isPlayer){
		if(isPlayer){
			for(int i = 0; i < numPlayerCards; i++){
				System.out.printf("%-1s of %-5s (%d)\n", cards.getFaceRank(hand[i]), cards.getSuit(hand[i]), cards.getRank(hand[i]));
			}
		}else{
			for(int i = 0; i < numDealerCards; i++){
				System.out.printf("%-1s of %-5s (%d)\n", cards.getFaceRank(hand[i]), cards.getSuit(hand[i]), cards.getRank(hand[i]));
			}
		}
	}
	
	/* Expects an int array representing a blackjack hand as a parameter.
	* Returns the score from the card representation */
	public static int calculateScore(int[] hand, boolean isPlayer){
		int sum = 0;
		boolean hasAce = false;
		int numCards = (isPlayer ? numPlayerCards : numDealerCards);
		
		for(int i = 0; i < numCards; i++){
			int value = cards.getRank(hand[i]);
			if(value == 1){
				hasAce = true;
				sum += 11;
			}else{
				sum += value;
			}
		}
		
		if(sum <= 21){
			return sum;
		}else if(hasAce && sum > 21){
			for(int i = 0; i < numCards; i++){
				int value = cards.getRank(hand[i]);
				if(value == 1){
					sum -= 10; // minimize the Ace
				}
				
				if(sum <= 21){
					break;
				}
			}
			
			return sum;
		}else{
			return sum;
		}
		
//		if(sum <= 21 || !hasAce){ // under or equal to 21 or over 21 with no aces
//			return sum;
//		}else{
//			for(int i = 0; i < hand.length; i++){
//				int value = cards.getRank(hand[i]);
//				if(value == 1){
//					sum -= 10; // minimize the Ace
//				}
//				
//				if(sum <= 21){
//					break;
//				}
//			}
//			
//			return sum;
//		}	
//		
	}
	
	/* Automates the dealer's play after the player stands. Dealer
	draws a new card until he has 17 or greater */
	public static void finishDealersPlay(){
		while(calculateScore(dealerHand, false) < 17){
			dealerHand[numDealerCards++] = cards.drawCard();
		}
	}
	
	/* Returns 1 if the player has won, -1 if the player
	* has lost and 0 if it's a draw/push (both the player
	* and the dealer have the same score)
	*
	*
	* A player has won if:
	* 1. His/her hand is <= 21 and his/her hand has a
	* higher score than the dealer
	* 2. His/her hand is <= 21 and the dealers goes bust
	* (i.e., the dealer's score is >21)
	* 3. The player gets Blackjack (21) with two cards:
	* any 10 card and an Ace (except if the dealer also
	* has a blackjack)
	*
	* Otherwise the dealer has won.
	* */
	public int calculateWinnings(){
		
		int playerScore = calculateScore(playerHand, true);
		int dealerScore = calculateScore(dealerHand, false);
		
		if(playerScore <= 21 && playerScore > dealerScore){
			return 1;
		}else if(playerScore <= 21 && dealerScore > 21){
			return 1;
		}else if((numPlayerCards == 2 && playerScore == 21) && !(numDealerCards == 2 && dealerScore == 21)){
			return 1;
		}else if(playerScore == dealerScore){
			return 0;
		}else{
			return -1;
		}
		
	}
}
