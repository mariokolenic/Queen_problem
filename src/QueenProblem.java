import java.util.ArrayList;

public class QueenProblem {
    private static ArrayList<int[][]> successfullBoards = new ArrayList<>();

    public static void main(String[] args) {
        int[][] gameBoard = new int[8][8];

        for(int riadok = 0; riadok < gameBoard.length; riadok++) {
            for (int stlpec = 0; stlpec < gameBoard[riadok].length; stlpec++) {
                gameBoard[riadok][stlpec] = 0;
            }
        }

        long start = System.currentTimeMillis();
        insertQueen(gameBoard, 0);
        long elapsedTimeMillis = System.currentTimeMillis()-start;

        printGameBoard(gameBoard);

        System.out.println("In-time: " + elapsedTimeMillis + " ms");
        System.out.println("Number of Boards from list: " + successfullBoards.size());

        for(int[][] board : successfullBoards) {
            printGameBoard(board);
            System.out.println("\n");
        }
    }

    static void printGameBoard(int[][] gameBoard) {
        for(int riadok = 0; riadok < gameBoard.length; riadok++) {
            System.out.print((8-riadok) + " ");
            for(int stlpec = 0; stlpec < gameBoard[riadok].length; stlpec++) {
                if(gameBoard[riadok][stlpec] == 1) {
                    System.out.print(" ♛ ");
                }
                else
                    System.out.print(" . ");
            }
            System.out.println("");
        }
        System.out.println("   a  b  c  d  e  f  g  h ");
    }

    static void insertQueen(int[][] gameBoard, int stlpec) {
        if(stlpec >= gameBoard.length) {
            int[][] newBoard = new int[8][8];
            for(int x = 0; x < newBoard.length; x++) {
                System.arraycopy(gameBoard[x], 0, newBoard[x], 0, newBoard[x].length);
            }
            successfullBoards.add(newBoard);
        }
        else {
            for (int riadok = 0; riadok < gameBoard.length; riadok++) {

                gameBoard[riadok][stlpec] = 1;

                if (hasCleanMoves(gameBoard, riadok, stlpec)) {
                    insertQueen(gameBoard, (stlpec+1));
                }
                gameBoard[riadok][stlpec] = 0;
            }
        }
    }

    static boolean hasCleanMoves(int[][] gameBoard, int queenRiadok, int queenStlpec) {
        for (int stlpec = 0; stlpec < queenStlpec; stlpec++) {
            if (gameBoard[queenRiadok][stlpec] == 1) {
                return false;
            }
        }

        for (int riadok = queenRiadok-1, stlpec = queenStlpec-1;
             riadok >= 0 && stlpec >= 0; riadok--, stlpec--) {
            if (gameBoard[riadok][stlpec] == 1) {
                return false;
            }
        }

        for (int riadok = queenRiadok+1, stlpec = queenStlpec-1;
             stlpec >= 0 && riadok < gameBoard.length; riadok++, stlpec--) {
            if (gameBoard[riadok][stlpec] == 1) {
                return false;
            }
        }

        return true;
    }
}
