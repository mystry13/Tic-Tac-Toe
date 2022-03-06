package com.mygame;

import java.util.Scanner;

public class Game {
    private Player p1;
    private Player p2;
    private Player nextTurn;
    private String[][] board = new String[3][3];
    Scanner sc = new Scanner(System.in);

    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        nextTurn = p1;
    }

    public String getCharInBox(int boxNo) {
        switch (boxNo) {
            case 1:
                return board[0][0];
            case 2:
                return board[0][1];
            case 3:
                return board[0][2];
            case 4:
                return board[1][0];
            case 5:
                return board[1][1];
            case 6:
                return board[1][2];
            case 7:
                return board[2][0];
            case 8:
                return board[2][1];
            case 9:
                return board[2][2];
            default:
                throw new IllegalArgumentException("box no out of range");
        }
    }

    public int nextAttempt(int box) {
        int row = (box - 1) / 3;
        int col = (box - 1) % 3;

        if (box < 1 || box > 9) {
            try {
                throw new IllegalArgumentException("box no out of range");
            } catch (Exception e) {
                return -1;
            }
        }
        if (board[row][col] != null) {
            try {
                throw new IllegalStateException("This box is not empty");
            } catch (Exception e) {
                return -1;
            }
        }
        board[row][col] = nextTurn.getCharacter();
        if (nextTurn == p1) {
            nextTurn = p2;
        } else {
            nextTurn = p1;
        }
        return 0;
    }

    /**
     * Checks board state and tells if any winners
     *
     * @param boxNo
     * @param curr
     * @return p1 or p2 whoever has won; or null if no winner yet
     */
    public Player checkVictory(int boxNo, Player curr) {
        int row = (boxNo - 1) / 3;
        int col = (boxNo - 1) % 3;
        Player winner = null;

        winner = checkRowWise(curr);

        winner = checkColWise(curr);

        //diagnolly - 1
        int r = 0;
        int c = 0;
        winner = checkDiagnolly(curr, r, c);

        //diagnolly -2
        r = 2;
        c = 2;
        winner = checkDiagnolly(curr, r, c);
        
        //check is any box empty
        boolean isEmpty = checkIfAnyCellIsEmpty();
        if (!isEmpty) {
            return new Player("-");
        }
        return null;
    }

    private Player checkDiagnolly(Player curr, int r, int c) {
        int count = 0;
        while (r < board.length && c < board[0].length) {
            if (board[r][c] != curr.getCharacter()) {
                break;
            } else if (board[r][c] == curr.getCharacter()) {
                count++;
            }
            r++;
            c++;
        }
        if (count == 3) {
            return curr;
        } else {
            count = 0;
        }
        return null;
    }

    private Player checkColWise(Player curr) {
        int count = 0;
        //col-wise
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[j][i] != curr.getCharacter()) {
                    break;
                } else if (board[j][i] == curr.getCharacter()) {
                    count++;
                }
            }
            if (count == 3) {
                return curr;
            } else {
                count = 0;
            }
        }
        return null;
    }

    private Player checkRowWise(Player curr) {
        int count = 0;
        //row-wise
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != curr.getCharacter()) {
                    break;
                } else if (board[i][j] == curr.getCharacter()) {
                    count++;
                }
            }
            if (count == 3) {
                return curr;
            } else {
                count = 0;
            }
        }
        return null;
    }

    private boolean checkIfAnyCellIsEmpty() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public Player getNextTurn() {
        return nextTurn;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public void playGame() {
        System.out.println("Welcome to play Tic-Tac-Toe!!");
        System.out.println("Board state is as below.....");
        this.printGameState();
        System.out.println();

        Player winner = null;
        while (winner == null) {
            Player curr = this.nextTurn;

            System.out.println("Player " + curr.getPlayerId() + " enter the box number to fill: ");
            int boxNo = sc.nextInt();
            int isNext = this.nextAttempt(boxNo);

            if (isNext == -1) {
                continue;
            }

            winner = checkVictory(boxNo, curr);
            System.out.println();

            System.out.println("Now it's turn for Player " + this.nextTurn.getPlayerId());
            System.out.println("Board state is as below....");
            printGameState();
        }
        if (winner.getCharacter() == "-") {
            System.out.println("There is no winner!!");
        }
        System.out.println("Winner of the game is Player " + winner.getPlayerId());
    }

    private void printGameState() {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == null) {
                    System.out.print("_");
                } else {
                    System.out.print(board[i][j]);
                }
                System.out.print("|");
            }
        }
    }
}
