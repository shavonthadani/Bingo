package bingo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;
import java.util.Scanner;

import Objects.BingoCardObject;
import Objects.Card;
import Objects.Deck;
import Objects.Player;
import Objects.title;

public class BingoGame {
	public static Player human;
	public static int wins;
	public static int losses;
	private static Card card;
	public static String title = "  B I N G O";
	public static BingoCardObject cpusBoard;
	public static BingoCardObject humansBoard;
	public static BingoCardObject humanView;
	public static title bingoTitle = new title(title);
	public static Deck deck;
	private static int r = 0;
	private static String[][] cpuBoardHumanView = new String[4][4];
	private static int x = 0;
	/**
	 * This method randomly picks the pattern to complete
	 * Example:
	 * if random # is 2 then it will do a frame pattern.
	 * @return returns the random number to decide which other methods needs to be
	 *         executed
	 */
	private static int pickPattern() {
		System.out.println("Welcome to " + title.toString());
		x = (int) (Math.random() * ((3 - 1) + 1)) + 1;
		if (x == 1) {
			System.out.println("pattern is complete the whole board");
		}
		if (x == 2) {
			System.out.println("pattern is complete the frame");
		}
		if (x == 3) {
			System.out.println("pattern is complete a diagonal");
		}
		return x;
	}
	/**
	 * This method fills a bingo card object with random numbers in a 4x4 array from
	 * 1 to 32 without repeating
	 * @return returns humans bingocard
	 */
	private static BingoCardObject makeHumanBoard() {
		int k = 0;
		Random rgen = new Random();
		int size = 32 - 1 + 1;
		String[] array = new String[size];
		for (int i = 0; i < size; i++) {
			array[i] = Integer.toString((1 + i));
		}
		for (int i = 0; i < array.length; i++) {
			int randomPosition = rgen.nextInt(array.length);
			String temp = array[i];
			array[i] = array[randomPosition];
			array[randomPosition] = temp;
		}
		String[][] humanBoard = new String[4][4];
		for (int i = 0; i < humanBoard.length; i++) {
			for (int j = 0; j < humanBoard[i].length; j++) {
			humanBoard[i][j] = array[k];
				k++;
			}
		}
		humansBoard = new BingoCardObject(bingoTitle.title, humanBoard, false);
		return humansBoard;
	}
	/**
	 * This method fills a bingo card object with * in a 4x4 array for the human to
	 * see the computers card
	 * @return returns humanview card
	 */
	private static BingoCardObject makeHumanView() {
		for (int i = 0; i < cpuBoardHumanView.length; i++) {
			for (int j = 0; j < cpuBoardHumanView[i].length; j++) {
				cpuBoardHumanView[i][j] = "*";
			}
		}
		humanView = new BingoCardObject(bingoTitle.title, cpuBoardHumanView, false);
		return humanView;
	}
	/**
	 * This method fills a bingo card object with random numbers in a 4x4 array from
	 * 1 to 32 without repeating
	 * @return returns cpu's bingocard
	 */
	private static BingoCardObject makeCpuBoard() {
		int k = 0;
		Random rgen = new Random(); // Random number generator
		int size = 32 - 1 + 1;
		String[] array = new String[size];
		for (int i = 0; i < size; i++) {
			array[i] = Integer.toString(1 + i);
		}
		for (int i = 0; i < array.length; i++) {
			int randomPosition = rgen.nextInt(array.length);
			String temp = array[i];
			array[i] = array[randomPosition];
			array[randomPosition] = temp;
		}
		String[][] cpuBoard = new String[4][4];
		for (int i = 0; i < cpuBoard.length; i++) {
			for (int j = 0; j < cpuBoard[i].length; j++) {
				cpuBoard[i][j] = array[k];
				k++;
			}
		}
		cpusBoard = new BingoCardObject(bingoTitle.title, cpuBoard, false);
		return cpusBoard;
	}
	/**
	 * This method shufffles a 32 length array filled from numbers to 32
	 */
	private static void shuffle() {
		Random rgen = new Random(); // Random number generator
		int size = 32 - 1 + 1;
		String[] array = new String[size];
		for (int i = 0; i < size; i++) {
			array[i] = Integer.toString((1 + i));
		}
		for (int i = 0; i < array.length; i++) {
			int randomPosition = rgen.nextInt(array.length);
			String temp = array[i];
			array[i] = array[randomPosition];
			array[randomPosition] = temp;
		}
		deck = new Deck(array);
		card = new Card((deck.getCards()[r]));
	}
	/**
	 * This method selects the specific number in the deck object after its been
	 * shuffled. Increments by 1 each time to get the next element in the array
	 * @return returns card being flipped
	 */
	private static Card card() {
		card.setCardFlipped(deck.getCards()[r]);
		r++;
		System.out.println("The card selected is: " + card.toString());
		return card;
	}
	/**
	 * This method checks to see if card is equal to any elements in array and if
	 * so, replace it with an x 
	 */
	private static void cpuMatch() {
		for (int i = 0; i < cpusBoard.getBoard().length; i++) {
			for (int j = 0; j < cpusBoard.getBoard()[i].length; j++) {
				if (cpusBoard.getBoard()[i][j].equals(card.toString())) {
					cpusBoard.getBoard()[i][j] = "x ";
					humanView.getBoard()[i][j] = "x";
				}
			}
		}
		System.out.println("Computers Board: " + "\n" + humanView.toString());
	}
	/**
	 * This method asks the user to check to see if the card is equal to any
	 * elements in array and if so, replace it with an x 
	 */
	private static void humanMatch() {
		int match = 0;
		System.out.println("Your board: " + "\n" + humansBoard.toString());
		int rowNum = 0;
		int colomnNum = 0;
		human = new Player(wins, losses, rowNum, colomnNum, false);
		Scanner input = new Scanner(System.in);
		System.out.println("do you have a match? y/n");
		if (input.nextLine().toUpperCase().startsWith("Y")) {
			System.out.println("Please Enter Row #:");
			human.setRowNum(input.nextInt());
			System.out.println("Please Enter Column #:");
			human.setColomnNum(input.nextInt());
			if (humansBoard.getBoard()[human.getRowNum() - 1][human.getColomnNum() - 1].equals(card.toString())) {
				humansBoard.getBoard()[human.getRowNum() - 1][human.getColomnNum() - 1] = "x ";
			} else {
				System.out.println("row # and colomn # incorrect");
				for (int i = 0; i < humansBoard.getBoard().length; i++) {
					for (int j = 0; j < humansBoard.getBoard()[i].length; j++) {
						if (humansBoard.getBoard()[i][j].equals(card.toString())) {
							System.out.println(
									"You did have a match. You just entered it incorrectly. Don't worry, I did it for you");
							humansBoard.getBoard()[i][j] = "x ";
							match = 1;
						}
					}
				}
				if (match == 0) {
					System.out.println("You had no match");
				}
			}
		} else {
			for (int i = 0; i < humansBoard.getBoard().length; i++) {
				for (int j = 0; j < humansBoard.getBoard()[i].length; j++) {
					if (humansBoard.getBoard()[i][j].equals(card.toString())) {
						System.out.println(
								"You did have a match. You must have missed it. Don't worry, I fiiled it out for you.");
						humansBoard.getBoard()[i][j] = "x ";
					}
				}
			}
		}
		System.out.println("Your board: " + "\n" + humansBoard.toString());
	}
	/**
	 * This method checks pattern methods and see who won. Will see who won, if tie
	 * win or loss method also adds to win loss record and executes endgame method 
	 */
	private static void bingo() {
		if (x == 1) {
			if (squareHuman() == true && squareCpu() == true) {
				System.out.println("There was a tie." + "\n" + human.toString());
				endgame();
			} else if (squareHuman() == true && squareCpu() == false) {
				wins++;
				human.setWins(wins);
				System.out.println("You won!!!" + "\n" + human.toString());
				endgame();
			} else if (squareHuman() == false && squareCpu() == true) {
				losses++;
				human.setLosses(losses);
				System.out.println("You lost!!!" + "\n" + human.toString());
				endgame();
			}
		}
		else if (x == 2) {
			if (frameHuman() == true && frameCpu() == true) {
				System.out.println("There was a tie." + "\n" + human.toString());
				endgame();
			} else if (frameHuman() == true && frameCpu() == false) {
				wins++;
				human.setWins(wins);
				System.out.println("You won!!!" + "\n" + human.toString());
				endgame();
			}
			else if (frameHuman() == false && frameCpu() == true) {
				losses++;
				human.setLosses(losses);
				System.out.println("You lost!!!" + "\n" + human.toString());
				endgame();
			}
		} else if (x == 3) {
			if (diagonalHuman() == true && diagonalCpu() == true) {
				System.out.println("There was a tie." + "\n" + human.toString());
				endgame();
			} else if (diagonalHuman() == true && diagonalCpu() == false) {
				wins++;
				human.setWins(wins);
				System.out.println("You won!!!" + "\n" + human.toString());
				endgame();
			}
			else if (diagonalHuman() == false && diagonalCpu() == true) {
				losses++;
				human.setLosses(losses);
				System.out.println("You lost!!!" + "\n" + human.toString());
				endgame();
			}
		}
	}
	/**
	 * pattern method for human. Will check every array element to make sure its an
	 * x 
	 * @return returns boolean depending if bingo is correct
	 */
	private static boolean squareHuman() {
		humansBoard.setPattern(true);
		for (int i = 0; i < humansBoard.getBoard().length; i++) {
			for (int j = 0; j < humansBoard.getBoard()[i].length; j++) {
				if (humansBoard.getBoard()[i][j].equals("x ") == false) {
					humansBoard.setPattern(false);
				}
			}
		}
		return humansBoard.isPattern();
	}
	/**
	 * pattern method for cpu. Will check every array element to make sure its an x 
	 * @return returns boolean depending if bingo is correct
	 */
	private static boolean squareCpu() {
		cpusBoard.setPattern(true);
		for (int i = 0; i < cpusBoard.getBoard().length; i++) {
			for (int j = 0; j < cpusBoard.getBoard()[i].length; j++) {
				if (cpusBoard.getBoard()[i][j].equals("x ") == false) {
					cpusBoard.setPattern(false);
				}
			}
		}
		return cpusBoard.isPattern();
	}
	/**
	 * pattern method for human. Will check every array element on the perimeter to
	 * make sure its an x 
	 * @return returns boolean depending if bingo is correct
	 */
	private static boolean frameHuman() {
		humansBoard.setPattern(true);
		for (int i = 0; i < humansBoard.getBoard().length; i++) {
			for (int j = 0; j < humansBoard.getBoard()[i].length; j++) {
				if (humansBoard.getBoard()[0][j].equals("x ") == false
						|| humansBoard.getBoard()[3][j].equals("x ") == false
						|| humansBoard.getBoard()[i][0].equals("x ") == false
						|| humansBoard.getBoard()[i][3].equals("x ") == false) {
					humansBoard.setPattern(false);
				}
			}
		}
		return humansBoard.isPattern();
	}
	/**
	 * pattern method for cpu. Will check every array element on the perimeter to
	 * make sure its an x
	 * 
	 * @return returns boolean depending if bingo is correct
	 * 
	 */
	private static boolean frameCpu() {
		cpusBoard.setPattern(true);
		for (int i = 0; i < cpusBoard.getBoard().length; i++) {
			for (int j = 0; j < cpusBoard.getBoard()[i].length; j++) {
				if (cpusBoard.getBoard()[0][j].equals("x ") == false || cpusBoard.getBoard()[3][j].equals("x ") == false
						|| cpusBoard.getBoard()[i][0].equals("x ") == false
						|| cpusBoard.getBoard()[i][3].equals("x ") == false) {
					cpusBoard.setPattern(false);
				}
			}
		}
		return cpusBoard.isPattern();
	}
	/**
	 * pattern method for human. Will check every diagonal array element to make
	 * sure its an x 
	 * @return returns boolean depending if bingo is correct
	 */
	private static boolean diagonalHuman() {
		humansBoard.setPattern(false);
		for (int i = 0; i < humansBoard.getBoard().length; i++) {
			for (int j = 0; j < humansBoard.getBoard()[i].length; j++) {
				if (humansBoard.getBoard()[0][0].equals("x ") == true && humansBoard.getBoard()[1][1].equals("x ")
						&& true && humansBoard.getBoard()[2][2].equals("x ") == true
						&& humansBoard.getBoard()[3][3].equals("x ") == true) {
					humansBoard.setPattern(true);
				} else if (humansBoard.getBoard()[3][0].equals("x ") == true
						&& humansBoard.getBoard()[2][1].equals("x ") == true
						&& humansBoard.getBoard()[1][2].equals("x ") == true
						&& humansBoard.getBoard()[0][3].equals("x ") == true) {
					humansBoard.setPattern(true);
				}
			}
		}
		return humansBoard.isPattern();
	}
	/**
	 * pattern method for cpu. Will check every diagonal array element to make sure
	 * its an x 
	 * @return returns boolean depending if bingo is correct
	 */
	private static boolean diagonalCpu() {
		cpusBoard.setPattern(false);
		for (int i = 0; i < cpusBoard.getBoard().length; i++) {
			for (int j = 0; j < cpusBoard.getBoard()[i].length; j++) {
				if (cpusBoard.getBoard()[0][0].equals("x ") == true && cpusBoard.getBoard()[1][1].equals("x ") == true
						&& cpusBoard.getBoard()[2][2].equals("x ") == true
						&& cpusBoard.getBoard()[3][3].equals("x ") == true) {
					cpusBoard.setPattern(true);
				} else if (cpusBoard.getBoard()[3][0].equals("x ") == true
						&& cpusBoard.getBoard()[2][1].equals("x ") == true
						&& cpusBoard.getBoard()[1][2].equals("x ") == true
						&& cpusBoard.getBoard()[0][3].equals("x ") == true) {
					cpusBoard.setPattern(true);
				}
			}
		}
		return cpusBoard.isPattern();
	}
	/**
	 * asks the user if they want to play again, if so loop will repeat 
	 */
	private static void endgame() {
		Scanner input = new Scanner(System.in);
		System.out.println("do you want to play again? Y/N");
		if (input.nextLine().toUpperCase().startsWith("Y")) {
			r = 0;
			main();
		} else {
			saveResults();
			System.out.println("ok Bye Bye!");
			System.exit(0);
		}
	}
	/**
	 * create text file of users record 
	 */
	public static void saveResults() {

		System.out.println("Do you want to save your results? y/n");
		Scanner answer = new Scanner(System.in);
		if (answer.nextLine().toUpperCase().startsWith("Y")) {

			File file = new File("results.txt");

			try (Writer writer = new BufferedWriter(new FileWriter(file))) {
				String contents = "Results" + "\n" + human.toString();
				writer.write(contents);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main() {
		pickPattern();
		makeHumanBoard();
		makeCpuBoard();
		makeHumanView();
		shuffle();
		while (true) {
			card();
			cpuMatch();
			humanMatch();
			bingo();
		}
	}

	public static void main(String[] args) {

		main();

	}

}
