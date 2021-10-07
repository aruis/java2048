import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {


    int size;

    Piece[][] board;
    long score;

    Random random = new Random();

    boolean isOver = false;

    public Game(int size) {
        this.size = size;
        board = new Piece[size][size];
    }

    public static void main(String[] args) {
        Game game = new Game(4);
//
        game.play();
        game.print();

//        game.test();

    }

    public void test() {
        Piece[] pieces = new Piece[4];
        for (int i = 0; i < 4; i++) {
            pieces[i] = new Piece();
        }

        pieces[0].setValue(2);
        pieces[1].setValue(0);
        pieces[2].setValue(2);
        pieces[3].setValue(4);

        printRow(pieces);

        mergeRow(pieces);

        printRow(pieces);

        mergeRow(pieces);

        printRow(pieces);

    }

    public void printRow(Piece[] pieces) {
        for (int i = 0; i < 4; i++) {
            Piece piece = pieces[i];
            System.out.print(piece);
            System.out.print("\t");
        }
        System.out.println();
    }

    public void mergeRow(Piece[] pieces) {
        compressRow(pieces);
        for (int i = 0; i < pieces.length; i++) {

            if (i < 3) {
                Piece piece = pieces[i];
                Piece rightPiece = pieces[i + 1];

                if (piece.getValue() == rightPiece.getValue()) {
                    piece.setValue(piece.getValue() * 2);
                    rightPiece.setValue(0);
                    compressRow(pieces);
                }

            }


        }

    }

    public void compressRow(Piece[] pieces) {
        ArrayList<Piece> temp = new ArrayList<>();
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].getValue() != 0) {
                temp.add(pieces[i]);
            }
        }

        for (int i = 0; i < pieces.length; i++) {
            if (temp.size() > i) {
                pieces[i] = temp.get(i);
            } else {
                pieces[i] = new Piece();
            }
        }

    }


    /**
     * 开始游戏
     */
    public void play() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Piece();
            }
        }

        this.randomOnePiece();
        this.randomOnePiece();

        Scanner scanner = new Scanner(System.in);

        while (!isOver) {
            print();
            String key = scanner.next();
            System.out.println(key);
            this.directionMerge(key);
        }


    }

    /**
     * 响应用户输入
     *
     * @param key
     */
    public void directionMerge(String key) {

        //a s d w
        if (key.equals("a")) { // 向左
            for (int i = 0; i < size; i++) {
                Piece[] pieces = board[i];
                mergeRow(pieces);
            }

        }

        if (key.equals("d")) { // 向右

        }

        if (key.equals("w")) {

        }

        if (key.equals("s")) {

        }

    }

    public void randomOnePiece() {

        ArrayList<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getValue() == 0) {
                    pieces.add(board[i][j]);
                }
            }
        }

        if (pieces.size() == 0) {
            this.over();
        } else {
            int i = random.nextInt(pieces.size());
            Piece piece = pieces.get(i);

            piece.setValue(2);

        }

    }


    /**
     * 游戏结束
     */
    public void over() {
        System.out.println("游戏结束");
        isOver = true;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j]);
                System.out.print("\t");
            }

            System.out.println();

        }

    }


}
