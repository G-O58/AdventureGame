/**
 * Author: Greg Borrow
 * Class: CSD299
 * Assignment 1 part 2
 * 4/18/2020
 * 
 * Original Author: RyiSnow
 */

import java.util.Random;
import java.util.Scanner;

public class AdventureGame {
	private static enum Page {
		town, winGame, crossRoad, river, forest, goblin, fight, attack, winFight, loseGame
	}

	private static Scanner myScanner = new Scanner(System.in);
	private static Scanner enterScanner = new Scanner(System.in);
	public static Random myRandom = new Random();

	private static int playerHP;
	private static String playerName;
	private static String playerWeapon;
	private static int choice;
	private static int monsterHP;
	private static boolean silverRing = false;
		
	public static void main(String[] args) {
		Page nextPage = Page.town;
		Page currentPage;
		boolean isPlaying = true;
		initializePlayer(); 
		do {
			currentPage = nextPage;
			switch (currentPage){
				case town:
					nextPage = approachTown();
					break;
				case winGame:
					winGame();
					isPlaying = false;
					break;
				case loseGame:
					loseGame();
					isPlaying = false;
					break;
				case crossRoad:
					nextPage = approachCrossRoad();
					break;
				case river:
					nextPage = approachRiver();
					break;
				case forest:
					nextPage = approachForest();
					break;
				case goblin:
					nextPage = approachGoblin();
					break;
				case fight:
					nextPage = fight();
					break;
				case attack:
					nextPage = attack();
					break;
				case winFight:
					nextPage = winFight();
					break;
				default:
					nextPage = Page.town;
					break;
			}
		} while (isPlaying);
	}
	
	private static void initializePlayer() {
		playerHP = 10;
		monsterHP = 15;
		playerWeapon = "Knife";

		System.out.println("Your HP: "+ playerHP);
		System.out.println("Your Weapon: "+ playerWeapon);
		System.out.println("Please enter your name:");
		playerName = myScanner.nextLine();
		System.out.println("Hello " + playerName + ", let's start the game!");	
	}	
	
	private static Page approachTown() {
		//TODO remove all text brackets
		{ //text
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You are at the gate of the town.");
		System.out.println("A guard is standing in front of you.");
		System.out.println("");
		System.out.println("What do you want to do?");
		System.out.println("");
		System.out.println("1: Talk to the guard");
		System.out.println("2: Attack the guard");
		System.out.println("3: Leave");
		System.out.println("\n------------------------------------------------------------------\n");
		}
		choice = myScanner.nextInt();

		Page chosenPage;
		switch (choice) {
			case 1:
				if (silverRing) { chosenPage = Page.winGame;} 
				else {
					System.out.println("Guard: Hello there, stranger. So your name is " + playerName + "? \nSorry but we cannot let stranger enter our town.");
					enterScanner.nextLine();
					chosenPage = Page.town;
				}
				break;
			case 2:
				playerHP = playerHP - 1;
				System.out.println("Guard: Hey don't be stupid.\n\nThe guard hit you so hard and you gave up.\n(You receive 1 damage)\n");
				System.out.println("Your HP: " + playerHP);
				enterScanner.nextLine();
				chosenPage = Page.town;
				break;
			case 3:
				chosenPage = Page.crossRoad;
				break;
			default:
				chosenPage = Page.town;
				break;
		}
		return chosenPage;
	}
	
	private static Page approachCrossRoad() {
		{ //text
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You are at a crossroad. If you go south, you will go back to the town.\n\n");
		System.out.println("1: Go north");
		System.out.println("2: Go east");
		System.out.println("3: Go south");
		System.out.println("4: Go west");
		System.out.println("\n------------------------------------------------------------------\n");
		}
		choice = myScanner.nextInt();
		Page chosenPage;
		switch (choice) {
			case 1:
				chosenPage = Page.river;
				break;
			case 2:
				chosenPage = Page.forest;
				break;
			case 3:
				chosenPage = Page.town;
				break;
			default:
				chosenPage = Page.goblin;
				break;
		}
		return chosenPage;
	}
	
	private static Page approachRiver() {
		playerHP = playerHP + 1;
		{ //text
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("There is a river. You drink the water and rest at the riverside.");
		System.out.println("Your HP is recovered.");
		System.out.println("Your HP: " + playerHP);
		System.out.println("\n\n1: Go back to the crossroad");
		System.out.println("\n------------------------------------------------------------------\n");
		}
		choice = myScanner.nextInt();
		
		if (choice == 1) {
			return Page.crossRoad;
		}
		else {
			return Page.river;
		}
	}
	
	private static Page approachForest() {
		playerWeapon = "Long Sword";
		{ //text
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You walked into a forest and found a Long Sword!");
		System.out.println("Your Weapon: "+ playerWeapon);
		System.out.println("\n\n1: Go back to the crossroad");
		System.out.println("\n------------------------------------------------------------------\n");
		}
		choice = myScanner.nextInt();
		
		if (choice == 1) {
			return Page.crossRoad;
		}
		else {
			return Page.forest;
		}
	}
	
	private static Page approachGoblin() {
		{//text
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You encounter a goblin!\n");
		System.out.println("1: Fight");
		System.out.println("2: Run");
		System.out.println("\n------------------------------------------------------------------\n");
		}
		choice = myScanner.nextInt();
		Page chosenPage;
		switch (choice) {
			case 1:
				chosenPage = Page.fight;
				break;
			case 2:
				chosenPage = Page.crossRoad;
				break;
			default:
				chosenPage = Page.goblin;
				break;
		}
		return chosenPage;
	}
	
	private static Page fight() {
		{ //text
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Your HP: "+ playerHP);
		System.out.println("Monster HP: " + monsterHP);
		System.out.println("\n1: Attack");
		System.out.println("2: Run");
		System.out.println("\n------------------------------------------------------------------\n");
		}
		choice = myScanner.nextInt();
		Page chosenPage;
		switch (choice) {
			case 1:
				chosenPage = Page.attack;
				break;
			case 2:
			chosenPage = Page.crossRoad;
				break;
			default:
			chosenPage = Page.fight;
		}
		return chosenPage;
	}
	
	private static Page attack() {
		Page resultPage;
		int playerDamage = 0;
		if (playerWeapon.equals("Knife")) {
			playerDamage = myRandom.nextInt(5);
		}
		else {
			playerDamage = myRandom.nextInt(8); 
		}
		monsterHP -= playerDamage;
		System.out.println("You attacked the monster and gave " + playerDamage + " damage!");
		System.out.println("Monster HP: " + monsterHP);
		
		if (monsterHP < 1) { resultPage = Page.winFight; } 
		else {
			int monsterDamage = 0;
			monsterDamage = myRandom.nextInt(4);
			playerHP -= monsterDamage;
			if (playerHP < 1) { resultPage = Page.loseGame; } 
			else { resultPage = Page.fight;}
			System.out.println("The monster attacked you and gave " + monsterDamage + " damage!");
			System.out.println("Player HP: " + playerHP);
		}
		return resultPage;
	}
	
	private static void loseGame() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You are dead!!!");
		System.out.println("\n\nGAME OVER");
		System.out.println("\n------------------------------------------------------------------\n");
	}
	
	private static Page winFight() {
		silverRing = true;
		{ //text
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You killed the monster!");
		System.out.println("The monster dropped a ring!");
		System.out.println("You obtaind a silver ring!\n\n");
		System.out.println("1: Go east");
		System.out.println("\n------------------------------------------------------------------\n");		
		}
		choice = myScanner.nextInt();
		
		if (choice == 1) {
			return Page.crossRoad;
		}
		else {
			return Page.winFight;
		}
	}
	
	private static void winGame() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Guard: Oh you killed that goblin!?? Great!");
		System.out.println("Guard: It seems you are a trustworthy guy. Welcome to our town!");
		System.out.println("\n\n           THE END                    ");
		System.out.println("\n------------------------------------------------------------------\n");
	}
}