// Ayush Gupta
// February 14, 2024
// Assignment 4. Game
//
// This program lets users play Baggebo against a computer

import java.util.Scanner;
import java.util.Random;

public class Game {
    static final int FJALLBO = 0;
    static final int OXBERG = 1;
    static final int VITTSJO = 2;
    static final int JATTESTA = 3;
    static final int EKENABBEN = 4;

    // Creating array of moves to make things easier
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
                // Print stats when the user quits the game
                printStats(round, roundsUserWon, roundsComputerWon);
                System.exit(0);
            }

            userChoice = getUserChoice(sc);
            computerChoice = getComputerChoice();

            userWon = didUserWin(userChoice - 1, computerChoice);

            // Keep track of rounds and win count
            ++round;
            if(userWon) ++roundsUserWon;
            else ++roundsComputerWon;

            System.out.println("\nComputer's Move: " + moves[computerChoice]);
            System.out.println("Your Move: " + moves[userChoice - 1]);
            System.out.println("Winner: " + (userWon ? "You" : "Computer") + '\n');
        }
    }

    // This function validates the rules and decides the winner
    public static boolean didUserWin(int userChoice, int computerChoice) {
        // Computer wins in case of tie
        if(userChoice == computerChoice) return false;

        switch(userChoice) {
            case FJALLBO: return computerChoice == VITTSJO || computerChoice == JATTESTA;
            case OXBERG: return computerChoice == FJALLBO || computerChoice == EKENABBEN;
            case VITTSJO: return computerChoice == OXBERG || computerChoice == JATTESTA;
            case JATTESTA: return computerChoice == OXBERG || computerChoice == EKENABBEN;
            case EKENABBEN: return computerChoice == FJALLBO || computerChoice == VITTSJO;
        }
        
        return false;
    }

    // This function asks the user if they want to play the game
    public static char getPlayGame(Scanner sc) {
        char play_game;

        do {
            System.out.print("Would you like to play the game? y or n: ");
            play_game = sc.next().charAt(0);
        } while(!(play_game == 'y' || play_game == 'n'));

        return play_game;
    }

    // This function takes input of user's choice
    public static int getUserChoice(Scanner sc) {
        int choice;

        do {
            printChoices();
            System.out.print("\nYour choice: ");
            choice = sc.nextInt();
        } while(!(choice > 0 && choice < 6));

        return choice;
    }

    // This function generates random choice for the computer
    public static int getComputerChoice() {
        return new Random().nextInt(moves.length);
    }

    // This function prints the rules of the game
    public static void printRules() {
        System.out.println("Welcome to the game of Baggebo!\n");
        System.out.println("Here are the rules: \n");
        System.out.println("Ekenabben beats Vittsjo, Fjallbo");
        System.out.println("Vittsjo beats Oxberg, Jattesta");
        System.out.println("Oxberg beats Ekenabben, Fjallbo");
        System.out.println("Fjallbo Beats Jattesta, Vittsjo");
        System.out.println("Jattesta beats Ekenabben, Oxberg\n");
    }

    // This function prints the choices for user
    public static void printChoices() {
        System.out.println("\nChoices: ");
        for(int i = 0; i < moves.length; ++i) {
            System.out.println((i + 1) + ". " + moves[i]);
        }
    }

    // This function prints the stats after the user quits the game
    public static void printStats(int round, int roundsUserWon, int roundsComputerWon) {
        System.out.println("\nTotal rounds played: " + round);
        System.out.println("Number of rounds you won: " + roundsUserWon);
        System.out.println("Number of rounds computer won: " + roundsComputerWon);
    }
}