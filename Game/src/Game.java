// Ayush Gupta
// February 14, 2024
// Assignment 4. Game
//
// This program lets users play Baggebo against a computer

import java.util.Scanner;
import java.util.Random;

public class Game {
    static final String moves[] = { "Fjallbo", "Oxberg", "Vittsjo", "Jattesta", "Ekenabben" };

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        printRules();

        int userChoice, computerChoice;
        int round = 0, roundsUserWon = 0, roundsComputerWon = 0;
        char play_game;
        boolean userWon;

        while(true) {
            play_game = getPlayGame(sc);
            
            if(play_game == 'n') {
                printStats(round, roundsUserWon, roundsComputerWon);
                System.exit(0);
            }


            userChoice = getUserChoice(sc);
            computerChoice = getComputerChoice();

            userWon = didUserWin(userChoice - 1, computerChoice);

            ++round;
            if(userWon) ++roundsUserWon;
            else ++roundsComputerWon;

            System.out.println("\nComputer's Move: " + moves[computerChoice]);
            System.out.println("Your Move: " + moves[userChoice - 1]);
            System.out.println("Winner: " + (userWon ? "You" : "Computer") + '\n');
        }
    }

    public static boolean didUserWin(int userChoice, int computerChoice) {
        if(userChoice == computerChoice) return false;

        switch(userChoice) {
            case 0: return computerChoice == 2 || computerChoice == 3;
            case 1: return computerChoice == 0 || computerChoice == 4;
            case 2: return computerChoice == 1 || computerChoice == 3;
            case 3: return computerChoice == 1 || computerChoice == 4;
            case 4: return computerChoice == 0 || computerChoice == 2;
        }
        
        return false;
    }

    public static char getPlayGame(Scanner sc) {
        char play_game;

        do {
            System.out.print("Would you like to play the game? y or n: ");
            play_game = sc.next().charAt(0);
        } while(!(play_game == 'y' || play_game == 'n'));

        return play_game;
    }

    public static int getUserChoice(Scanner sc) {
        int choice;

        do {
            printChoices();
            System.out.print("\nYour choice: ");
            choice = sc.nextInt();
        } while(!(choice > 0 && choice < 6));

        return choice;
    }

    public static int getComputerChoice() {
        Random rand = new Random();
        return rand.nextInt(moves.length);
    }

    public static void printRules() {
        System.out.println("Welcome to the game of Baggebo!\n");
        System.out.println("Here are the rules: \n");
        System.out.println("Ekenabben beats Vittsjo, Fjallbo");
        System.out.println("Vittsjo beats Oxberg, Jattesta");
        System.out.println("Oxberg beats Ekenabben, Fjallbo");
        System.out.println("Fjallbo Beats Jattesta, Vittsjo");
        System.out.println("Jattesta beats Ekenabben, Oxberg\n");
    }

    public static void printChoices() {
        System.out.println("\nChoices: ");
        for(int i = 0; i < moves.length; ++i) {
            System.out.println((i + 1) + ". " + moves[i]);
        }
    }

    public static void printStats(int round, int roundsUserWon, int roundsComputerWon) {
        System.out.println("\nTotal rounds played: " + round);
        System.out.println("Number of rounds you won: " + roundsUserWon);
        System.out.println("Number of rounds computer won: " + roundsComputerWon);
    }
}