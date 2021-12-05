package by.overone.hakatton.seabattle;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        FileWriter fileWriter = new FileWriter("LOG.txt");
        fileWriter.write("GAME: SEA_BATTLE\nGAME_MODE:P_P\n");

        System.out.println("Игрок 1 введите имя ");
        String player1Name = scanner.nextLine();
        Player player1 = new Player(player1Name);
        fileWriter.write("P1:" + player1.getName() + "\n");

        System.out.println("Игрок 2 введите имя ");
        String player2Name = scanner.nextLine();
        Player player2 = new Player(player2Name);
        fileWriter.write("P2:" + player2.getName() + "\nFIELD:\n");
        fileWriter.close();

        player1.fillPlayerField();
        player2.fillPlayerField();

        if (random.nextBoolean()) {
            playGame(player1, player2);
            System.out.println("Первым ходит " + player1.getName());
        } else {
            playGame(player2, player1);
            System.out.println("Первым ходит " + player2.getName());
        }

    }

    public static void playGame(Player player1Name, Player player2Name) throws IOException {
        FileWriter fileWriter = new FileWriter("LOG.txt",true);
        Player gamer = player1Name;
        fileWriter.write(gamer.getName() + ":");
        while (player1Name.isAlive() && player2Name.isAlive()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(player1Name.getName() + " ,пожалуйста, введите координату y");
            int yShot = scanner.nextInt();
            System.out.println(player1Name.getName() + " ,пожалуйста, введите координату x");
            int xShot = scanner.nextInt();

            int shotResult = player1Name.shot(yShot, xShot);
            if (shotResult == 0) {
                gamer = player2Name;
            }
            fileWriter.write(String.valueOf(yShot) + xShot + "\n");
        }
        System.out.println(gamer.getName() + " вы выйграли!");
        fileWriter.write("WIN\n" + gamer.getName());
        fileWriter.close();
    }
}


