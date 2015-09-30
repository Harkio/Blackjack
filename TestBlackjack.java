import java.util.Scanner;


public class TestBlackjack {

	public static void main(String[] args) {
		Blackjack jack = new Blackjack();
		jack.setupGame();
		
		jack.printHand(jack.dealerHand, false);
		System.out.println(jack.calculateScore(jack.dealerHand, false));
		System.out.println();
		jack.printHand(jack.playerHand, true);
		System.out.println(jack.calculateScore(jack.playerHand, true));
		System.out.println();
		
//		if((jack.calculateScore(jack.playerHand) == 21) && !(jack.calculateScore(jack.dealerHand) == 21)){
//			System.out.println("Blackjack!");
//			System.exit(0);
//		}else if(!(jack.calculateScore(jack.playerHand) == 21) && (jack.calculateScore(jack.dealerHand) == 21)){
//			System.out.println("Dealer gets Blackjack!");
//			System.exit(0);
//		}else if((jack.calculateScore(jack.playerHand) == 21) && (jack.calculateScore(jack.dealerHand) == 21)){
//			System.out.println("Push!");
//			System.exit(0);
//		}
		
//		while(getInput() == 'h' && jack.calculateScore(jack.playerHand) <= 21){
//			jack.hit();
//		}
		
//		jack.finishDealersPlay();
//		
//		System.out.println("Final hands : ");
//		jack.printHand(jack.dealerHand, false);
//		System.out.println();
//		jack.printHand(jack.playerHand, true);
//		System.out.println();
		
//		int winner = jack.calculateWinnings();
//		if(winner == 1){
//			System.out.println("You win!");
//		}else if(winner == 0){
//			System.out.println("Push!");
//		}else{
//			System.out.println("Dealer wins!");
//		}
		
	}
	
	public static char getInput(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to hit (h) or stand (s)?");
		String input = scan.next();
		char c = input.charAt(0);
		while(c != 'h' && c != 's'){
			input = scan.next();
			c = input.charAt(0);
		}
		
		return c;
	}

}
