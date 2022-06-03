public class Main {
    private static int insertCounter = 0;

    public static void main(String[] args) {
        QueenFigure[][] gameBoard = new QueenFigure[8][8];

        printGameBoard(gameBoard);

        int queensCount = 0;
        boolean successfull = false;
        long start = System.currentTimeMillis();
        insertQueen(gameBoard, queensCount, successfull);
        long elapsedTimeMillis = System.currentTimeMillis()-start;

        printGameBoard(gameBoard);

        System.out.println("Insert counter: " + insertCounter);
        System.out.println("In-time: " + elapsedTimeMillis + " ms");
    }

    static void printGameBoard(QueenFigure[][] gameBoard) {
        for(int riadok = 0; riadok < gameBoard.length; riadok++) {
            System.out.print((8-riadok) + " ");
            for(int stlpec = 0; stlpec < gameBoard[riadok].length; stlpec++) {
                if(gameBoard[riadok][stlpec] != null) {
                    System.out.print(" " + gameBoard[riadok][stlpec].getImage() + " ");
                }
                else
                    System.out.print(" . ");
            }
            System.out.println("");
        }
        System.out.println("   a  b  c  d  e  f  g  h ");
    }

    static boolean insertQueen(QueenFigure[][] gameBoard, int queensCount, boolean successfull) {
        if(!successfull) {
            for(int riadok = 0; riadok < gameBoard.length; riadok++) {
                for(int stlpec = 0; stlpec < gameBoard[riadok].length; stlpec++) {
                    QueenFigure queen = new QueenFigure(riadok, stlpec);
                    if(hasCleanMoves(gameBoard, riadok, stlpec) && gameBoard[riadok][stlpec] == null) {
                        gameBoard[riadok][stlpec] = queen;
                        insertCounter++;
                        // printGameBoard(gameBoard);
                        queensCount++;
                        // System.out.println("Queens counter: " + queensCount);
                        if(queensCount < 8) {
                            successfull = insertQueen(gameBoard, queensCount, false);

                            if(!successfull) {
                                gameBoard[riadok][stlpec] = null;
                                queensCount--;
                            }
                            else
                                return true;
                        }
                        else
                            return true;
                    }
                }
            }
        }
        return successfull;
    }

    static boolean hasCleanMoves(QueenFigure[][] gameBoard, int queenRiadok, int queenStlpec) {
        for(int riadok = 0; riadok < gameBoard.length; riadok++) {
            for(int stlpec = 0; stlpec < gameBoard[riadok].length; stlpec++) {
                if(gameBoard[riadok][stlpec] != null) {
                    if(queenRiadok == riadok || queenStlpec == stlpec) {
                        return false;
                    }
                }
            }
        }

        return hasCleanDiagonal(gameBoard, queenRiadok, queenStlpec);
    }

    static boolean hasCleanDiagonal(QueenFigure[][] gameBoard, int queenRiadok, int queenStlpec) {
        int tempRiadok = queenRiadok;
        int tempStlpec = queenStlpec;

        boolean stopPridavanie = false;

        // pravo hore
        while(!stopPridavanie) {
            tempRiadok--;
            tempStlpec++;
            if(tempRiadok >= 0 && tempStlpec < 8) {
                if(gameBoard[tempRiadok][tempStlpec] != null) {
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
                if(gameBoard[tempRiadok][tempStlpec] != null) {
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
                if(gameBoard[tempRiadok][tempStlpec] != null) {
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
                if(gameBoard[tempRiadok][tempStlpec] != null) {
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
