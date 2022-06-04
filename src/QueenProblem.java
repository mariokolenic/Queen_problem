import java.util.ArrayList;

public class QueenProblem {
    private static int insertCounter = 0;
    private static ArrayList<char[][]> successfullBoards = new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard = new char[8][8];

        printGameBoard(gameBoard);

        int queensCount = 0;
        boolean successfull = false;
        long start = System.currentTimeMillis();
        insertQueen(gameBoard, queensCount, successfull);
        long elapsedTimeMillis = System.currentTimeMillis()-start;

        printGameBoard(gameBoard);

        System.out.println("Insert counter: " + insertCounter);
        System.out.println("In-time: " + elapsedTimeMillis + " ms");
        System.out.println("Number of Boards from list: " + successfullBoards.size());

        for(char[][] board : successfullBoards) {
            printGameBoard(board);
            System.out.println("\n");
        }
    }

    static void printGameBoard(char[][] gameBoard) {
        for(int riadok = 0; riadok < gameBoard.length; riadok++) {
            System.out.print((8-riadok) + " ");
            for(int stlpec = 0; stlpec < gameBoard[riadok].length; stlpec++) {
                if(gameBoard[riadok][stlpec] == '♛') {
                    System.out.print(" ♛ ");
                }
                else
                    System.out.print(" . ");
            }
            System.out.println("");
        }
        System.out.println("   a  b  c  d  e  f  g  h ");
    }

    static boolean insertQueen(char[][] gameBoard, int queensCount, boolean successfull) {
        if(!successfull) {
            for(int riadok = 0; riadok < gameBoard.length; riadok++) {
                for(int stlpec = 0; stlpec < gameBoard[riadok].length; stlpec++) {
                    if(gameBoard[riadok][stlpec] == 0) {
                        if(hasCleanMoves(gameBoard, riadok, stlpec)) {
                            gameBoard[riadok][stlpec] = '♛';
                            queensCount++;
                            if(queensCount < 8) {
                                successfull = insertQueen(gameBoard, queensCount, false);

                                if(!successfull) {
                                    gameBoard[riadok][stlpec] = 0;
                                    queensCount--;
                                }
                                else
                                    return true;
                            }
                            else {
                                for(char[][] board : successfullBoards) {
                                    boolean equals = false;
                                    for(int x = 0; x < board.length; x++) {
                                        for (int y = 0; y < board[x].length; y++) {
                                            if((board[x][y] == 0 && gameBoard[x][y] == 0) ||
                                                    (board[x][y] == '♛' && gameBoard[x][y] == '♛')) {
                                                equals = true;
                                            }
                                            else {
                                                equals = false;
                                                break;
                                            }
                                        }
                                        if(!equals)
                                            break;
                                    }

                                    if(equals) {
                                        gameBoard[riadok][stlpec] = 0;
                                        return false;
                                    }
                                }
                                char[][] newBoard = new char[8][8];
                                for(int x = 0; x < newBoard.length; x++) {
                                    System.arraycopy(gameBoard[x], 0, newBoard[x], 0, newBoard[x].length);
                                }

                                successfullBoards.add(newBoard);
                                gameBoard[riadok][stlpec] = 0;
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return successfull;
    }

    static boolean hasCleanMoves(char[][] gameBoard, int queenRiadok, int queenStlpec) {
        for(int riadok = 0; riadok < gameBoard.length; riadok++) {
            for(int stlpec = 0; stlpec < gameBoard[riadok].length; stlpec++) {
                if(gameBoard[riadok][stlpec] == '♛') {
                    if(queenRiadok == riadok || queenStlpec == stlpec) {
                        return false;
                    }
                }
            }
        }

        return hasCleanDiagonal(gameBoard, queenRiadok, queenStlpec);
    }

    static boolean hasCleanDiagonal(char[][] gameBoard, int queenRiadok, int queenStlpec) {
        int tempRiadok = queenRiadok;
        int tempStlpec = queenStlpec;

        boolean stopPridavanie = false;

        // pravo hore
        while(!stopPridavanie) {
            tempRiadok--;
            tempStlpec++;
            if(tempRiadok >= 0 && tempStlpec < 8) {
                if(gameBoard[tempRiadok][tempStlpec] == '♛') {
                    return false;
                }
            }
            else
                stopPridavanie = true;
        }
        // System.out.println("[" + queenRiadok + "][" + queenStlpec + "]  Pravo hore -> OK");


        tempRiadok = queenRiadok;
        tempStlpec = queenStlpec;
        stopPridavanie = false;

        // pravo dolu
        while(!stopPridavanie) {
            tempRiadok++;
            tempStlpec++;
            if(tempRiadok < 8 && tempStlpec < 8) {
                if(gameBoard[tempRiadok][tempStlpec] == '♛') {
                    return false;
                }
            }
            else
                stopPridavanie = true;
        }
        // System.out.println("[" + queenRiadok + "][" + queenStlpec + "]  Pravo dolu -> OK");


        tempRiadok = queenRiadok;
        tempStlpec = queenStlpec;
        stopPridavanie = false;

        // vlavo dolu
        while(!stopPridavanie) {
            tempRiadok++;
            tempStlpec--;
            if(tempRiadok < 8 && tempStlpec >= 0) {
                if(gameBoard[tempRiadok][tempStlpec] == '♛') {
                    return false;
                }
            }
            else
                stopPridavanie = true;
        }
        // System.out.println("[" + queenRiadok + "][" + queenStlpec + "]  Vlavo dolu -> OK");


        tempRiadok = queenRiadok;
        tempStlpec = queenStlpec;
        stopPridavanie = false;

        // vlavo hore
        while(!stopPridavanie) {
            tempRiadok--;
            tempStlpec--;
            if(tempRiadok >= 0 && tempStlpec >= 0) {
                if(gameBoard[tempRiadok][tempStlpec] == '♛') {
                    return false;
                }
            }
            else
                stopPridavanie = true;
        }
        // System.out.println("[" + queenRiadok + "][" + queenStlpec + "]  Vlavo hore -> OK");

        return true;
    }
}
