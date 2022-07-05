import java.util.*;

public class TicTac {
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Use the following mapping table:");
        printTableMapping();
        System.out.println();
        System.out.println("Let's go!");

        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };

        printGameBoard(gameBoard);

        while (true) {

            Scanner sc = new Scanner(System.in);
            int playerPos;

            do {
                System.out.print("\nPlease enter your placement (1-9): ");
                while (!sc.hasNextInt()) {
                    System.out.println("That's not a valid number! Try again! ");
                    sc.next(); // this is important!
                }
                playerPos = sc.nextInt();
            } while (!(playerPos >= 1 && playerPos <= 9) && ((playerPos % 1) == 0));

            System.out.printf("\nYou have entered %d.%n", playerPos);

            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
                System.out.println("Position is taken! Please, enter another position");
                playerPos = sc.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");
            printGameBoard(gameBoard);

            String winner = checkWinner();
            if (winner.length() > 0) {
                System.out.println(winner);
                break;
            }

            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = random.nextInt(9) + 1;
            }
            System.out.printf("\nComputer has entered %d.%n", cpuPos);

            placePiece(gameBoard, cpuPos, "cpu");
            printGameBoard(gameBoard);

            winner = checkWinner();
            if (winner.length() > 0) {
                System.out.println(winner);
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static void printTableMapping() {
        System.out.println("1|2|3");
        System.out.println("-+-+-");
        System.out.println("4|5|6");
        System.out.println("-+-+-");
        System.out.println("7|8|9");
    }

    public static String checkWinner() {
        List<Integer> topRow   = Arrays.asList(1, 2, 3);
        List<Integer> midRow   = Arrays.asList(4, 5, 6);
        List<Integer> botRow   = Arrays.asList(7, 8, 9);
        List<Integer> leftCol  = Arrays.asList(1, 4, 7);
        List<Integer> midCol   = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> crossOne = Arrays.asList(1, 5, 9);
        List<Integer> crossTwo = Arrays.asList(7, 5, 3);

        List<List<Integer>> winLine = new ArrayList<>();
        winLine.add(topRow);
        winLine.add(midRow);
        winLine.add(botRow);
        winLine.add(leftCol);
        winLine.add(midCol);
        winLine.add(rightCol);
        winLine.add(crossOne);
        winLine.add(crossTwo);

        for (List<Integer> l : winLine) {
            if (playerPositions.containsAll(l)) {
                return "Congrats, YOU WON!";
            } else if (cpuPositions.containsAll(l)) {
                return "Sorry, COMPUTER WON";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "DRAW";
            }
        }
        return "";
    }
}

