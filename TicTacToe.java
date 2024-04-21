import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

enum Symbol {
    X('x'),
    O('o');

    private final char symbol;

    Symbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(this.symbol);
    }
}

class Player {
    Symbol symbol;
    InputStreamReader isr;
    BufferedReader br;

    public Player(Symbol symbol) {
        this.symbol = symbol;
        this.isr = new InputStreamReader(System.in);
        this.br = new BufferedReader(isr);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int[] playMove() throws IOException {
        int[] pos = null;
        System.out.print("enter row and column of your move (space separated) :: ");
        pos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        return pos;
    }
}

class Board {
    Symbol[][] board;

    Board() {
        this.board = new Symbol[3][3];
    }

    public boolean registerMove(Symbol symbol, int x, int y) {
        if (this.board[x][y] != null)
            return false;
        this.board[x][y] = symbol;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                boardString.append((board[row][col] != null) ? board[row][col].toString() : " ");

                if (col < board[row].length - 1) {
                    boardString.append(" | ");
                }
            }

            boardString.append("\n");

            if (row < board.length - 1) {
                boardString.append("---------\n");
            }
        }

        return boardString.toString();
    }

    private boolean checkWin(Symbol s) {
        int cnt;
        for (int i = 0; i < 3; i++) {
            cnt = 0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == s)
                    cnt++;
            }
            if (cnt == 3)
                return true;
        }

        for (int i = 0; i < 3; i++) {
            cnt = 0;
            for (int j = 0; j < 3; j++) {
                if (board[j][i] == s)
                    cnt++;
            }
            if (cnt == 3)
                return true;
        }

        cnt = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][i] == s)
                cnt++;
        }
        if (cnt == 3)
            return true;

        cnt = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][2 - i] == s)
                cnt++;
        }
        if (cnt == 3)
            return true;

        return false;
    }

    public Symbol checkWin() {
        if (checkWin(Symbol.X))
            return Symbol.X;
        else if (checkWin(Symbol.O))
            return Symbol.O;
        else
            return null;
    }
}

class GamePlay {
    private Player px;
    private Player po;
    private Board board;

    public GamePlay() {
        this.px = new Player(Symbol.X);
        this.po = new Player(Symbol.O);
        this.board = new Board();
    }

    public void play() throws IOException {
        Symbol winner = null;
        int cnt = 0;
        while (board.checkWin() == null) {
            System.out.println(board);
            System.out.println("X moves\n\n");
            boolean validMove = false;
            int[] moveX;
            while (!validMove) {
                moveX = px.playMove();
                validMove = board.registerMove(px.getSymbol(), moveX[0], moveX[1]);
                if (!validMove) {
                    System.err.println("position already occupied, try again.");
                }
            }
            if ((winner = board.checkWin()) != null)
                break;

            System.out.println(board);
            System.out.println("O moves\n\n");
            validMove = false;
            int[] moveO;
            while (!validMove) {
                moveO = po.playMove();
                validMove = board.registerMove(po.getSymbol(), moveO[0], moveO[1]);
                if (!validMove) {
                    System.err.println("position already occupied, try again.");
                }
            }
            board.checkWin();
            if ((winner = board.checkWin()) != null)
                break;

            cnt++;
            if (cnt == 9) {
                break;
            }
        }

        System.out.println(board);
        if (winner == null) {
            System.out.println("Game was draw.");
        } else {
            System.out.println(winner + " wins");
        }
    }
}

public class TicTacToe {
    public static void main(String[] args) throws IOException {
        GamePlay game = new GamePlay();
        game.play();
    }
}
