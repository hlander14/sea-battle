package by.overone.hakatton.seabattle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Player {

    private String name;
    private char[][] playerField = new char[10][10];
    private char[][] playerBattleField1 = new char[10][10];

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int shot(int y, int x) {
        if (playerField[y][x] == '1') {
            playerField[y][x] = 'X';
            playerBattleField1[y][x] = 'X';
            System.out.println("Вы попали!");
            return 1;
        }
        playerBattleField1[y][x] = '*';
        System.out.println("Мимо!");
        return 0;
    }

    public boolean isAlive() {
        for (char[] cells : playerField) {
            for (char cell : cells) {
                if ('1' == cell) {
                    return true;
                }
            }
        }
        return false;
    }

    public void fillPlayerField() throws IOException {
        FileWriter fileWriter = new FileWriter("LOG.txt", true);
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        int y = 0;
        int positioning = 0;
        int countOfShips = 10;
        fileWriter.write(this.getName());

        for (int i = 4; i >= 1; i--) {
            fileWriter.write(i + ":");
            for (int k = i; k < 5; k++) {
                System.out.println("Расставляем " + i + "-палубный корабль. Осталось расставить: " + (countOfShips));

                int validationResult = 1;
                while (validationResult != 0) {
                    System.out.println("Введите координату x: ");
                    x = scanner.nextInt() - 1;

                    System.out.println("Введите координату y: ");
                    y = scanner.nextInt() - 1;
                    do {
                        System.out.println("Выберите направление:\n1.Горизонтальное направление;\n2.Вертикальное направление");
                        positioning = scanner.nextInt();
                    } while (positioning <= 1 && positioning >= 2);

                    validationResult = 0; //checkingForCoordinates(y, x, positioning, i);
                    if (validationResult != 0) {
                        System.err.println("Место занято, попробуйте ещё раз...");
                    }
                }
                int j;
                fileWriter.write(y + x + "_");
                if (positioning == 1) {
                    for (j = 0; j < i; j++) {
                        playerField[y][x + j] = '1';
                    }
                    fileWriter.write(String.valueOf(y) + (x + j));
                } else if (positioning == 2) {
                    for (j = 0; j < i; j++) {
                        playerField[y + j][x] = '1';
                    }
                    fileWriter.write(String.valueOf(y + j) + x);
                }

                fileWriter.write("|");
                printField();
                countOfShips--;
            }
        }
        fileWriter.close();
    }

    public void printField() {
        System.out.println("\n---------------------");
        for (char[] cells : playerField) {
            System.out.print("|");
            for (char cell : cells) {
                if (cell == 0) {
                    System.out.print(" |");
                } else {
                    System.out.print(cell + "|");
                }
            }
            System.out.println("\n---------------------");
        }
    }

    private int checkingForCoordinates(int y, int x, int positioning, int shipType) {
        if (positioning == 1) {//горизонт
            for (int i = 0; i < shipType - 1; i++) {
                if (x + i - 1 == 0 || y - 1 == 0) {
                    if ('1' == playerField[y][x + i]
                            || '1' == playerField[y + 1][x + i]
                            || '1' == playerField[y][x + i + 1]
                            || '1' == playerField[y + 1][x + i + 1]
                            || (x + i) > 9) {
                        if (x + i - 1 == 0) {
                            if ('1' == playerField[y - 1][x + i]
                                    || '1' == playerField[y - 1][x + i + 1]
                            )
                                return -1;
                        }
                        if (y - 1 == 0) {
                            if ('1' == playerField[y][x + i - 1]
                                    || '1' == playerField[y + 1][x + i - 1]
                            )
                                return -1;
                        }
                        return -1;
                    }//----------------------------------------------------
                } else if (x + i + 1 == 10 || y + 1 == 10) {
                    if ('1' == playerField[y][x + i]
                            || '1' == playerField[y - 1][x + i]
                            || '1' == playerField[y][x + i - 1]
                            || '1' == playerField[y - 1][x + i - 1]
                            || (x + i) > 9) {
                        if (x + i - 1 == 0) {
                            if ('1' == playerField[y - 1][x + i]
                                    || '1' == playerField[y - 1][x + i + 1]
                            )
                                return -1;
                        }
                        if (y - 1 == 0) {
                            if ('1' == playerField[y][x + i - 1]
                                    || '1' == playerField[y + 1][x + i - 1]
                            )
                                return -1;
                        }
                        return -1;
                    }
                } else if ('1' == playerField[y][x + i]
                        || '1' == playerField[y - 1][x + i - 1]
                        || '1' == playerField[y - 1][x + i]
                        || '1' == playerField[y][x + i - 1]
                        || '1' == playerField[y + 1][x + i]
                        || '1' == playerField[y + 1][x + i - 1]


                        || '1' == playerField[y - 1][x + i + 1]//
                        || '1' == playerField[y][x + i + 1]//
                        || '1' == playerField[y + 1][x + i + 1]//

                        || (x + i) > 9) {
                    return -1;
                }
            }


        } else if (positioning == 2) {
            for (int i = 0; i < shipType - 1; i++) {
                if (y + i + 1 == 10 || x + 1 == 10) {
                    if ('1' == playerField[y + i][x]
                            || '1' == playerField[y + i - 1][x]
                            || '1' == playerField[y + i][x - 1]
                            || '1' == playerField[y + i - 1][x - 1]
                            || (y + i) > 9) {
                        if (y + i + 1 == 10) {
                            if ('1' == playerField[y + i][x + 1]
                                    || '1' == playerField[y + i - 1][x + 1]) {
                                return -1;
                            }
                        }
                        if (x + 1 == 0) {
                            if ('1' == playerField[y + i + 1][x]
                                    || '1' == playerField[y + i + 1][x - 1]) {
                                return -1;
                            }
                        }
                    }
                }
                if (y + i - 1 == 0 || x - 1 == 0) {
                    if ('1' == playerField[y + i][x]
                            || '1' == playerField[y + i + 1][x]
                            || '1' == playerField[y + i][x + 1]
                            || '1' == playerField[y + i + 1][x + 1]
                            || (y + i) > 9) {
                        if (y + i - 1 == 10) {
                            if ('1' == playerField[y + i][x - 1]
                                    || '1' == playerField[y + i - 1][x - 1]) {
                                return -1;
                            }
                        }
                        if (x - 1 == 0) {
                            if ('1' == playerField[y + i - 1][x]
                                    || '1' == playerField[y + i - 1][x + 1]) {
                                return -1;
                            }
                        }
                    }
                } else if ('1' == playerField[y + i][x]
                        || '1' == playerField[y + i - 1][x]
                        || '1' == playerField[y + i + 1][x]
                        || '1' == playerField[y + i][x + 1]
                        || '1' == playerField[y + i][x - 1]

                        || '1' == playerField[y + i - 1][x - 1]
                        || '1' == playerField[y + i - 1][x + 1]
                        || '1' == playerField[y + i + 1][x - 1]
                        || '1' == playerField[y + i + 1][x + 1]


                        || (y + i) > 9) {
                    return -1;
                }
            }
        }
        return 0;
    }
}
