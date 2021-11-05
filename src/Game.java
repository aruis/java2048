import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    //棋盘边长
    int size;

    //棋盘
    Piece[][] board;

    //得分
    long score;

    //随机数发生器
    Random random = new Random();

    //是否游戏结束
    boolean isOver = false;

    public Game(int size) {
        this.size = size;
        board = new Piece[size][size];
    }

    //创造棋子
    public static Piece giveAPiece() {
//        return new NumPiece();
        return new ShengxiaoPiece();
    }

    public static void main(String[] args) {
        Game game = new Game(4);
        game.play();
    }


    /**
     * 合并棋子，向左合并，比如 2 2 0 0 ，会合并为 4 0 0 0
     *
     * @param pieces 棋子数组
     */
    public void mergeRow(Piece[] pieces) {
        compressRow(pieces);
        for (int i = 0; i < pieces.length; i++) {

            if (i < 3) {
                Piece piece = pieces[i];
                Piece rightPiece = pieces[i + 1];

                if (!piece.isBlank() && piece.equals(rightPiece)) {
                    score = score + piece.expand();
                    rightPiece.setBlank();
                    compressRow(pieces);
                }

            }


        }

    }

    /**
     * 压缩棋子，向左压缩，比如2 0 0 2 ，会压缩为 2 2 0 0
     *
     * @param pieces
     */
    public void compressRow(Piece[] pieces) {
        ArrayList<Piece> temp = new ArrayList<>();
        for (int i = 0; i < pieces.length; i++) {
            if (!pieces[i].isBlank()) {
                temp.add(pieces[i]);
            }
        }

        for (int i = 0; i < pieces.length; i++) {
            if (temp.size() > i) {
                pieces[i] = temp.get(i);
            } else {
                pieces[i] = giveAPiece();
            }
        }

    }


    /**
     * 开始游戏
     */
    public void play() {

        //重置得分
        score = 0;

        //重置棋盘
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = giveAPiece();
            }
        }

        this.randomOnePiece(); //随机生成一个棋子
        this.randomOnePiece(); //随机生成一个棋子

        //捕捉用户输入
        Scanner scanner = new Scanner(System.in);

        while (!isOver) { //如果游戏没有结束就响应用户输入
            print(); //打印当前结果
            String key = scanner.next();
            System.out.println(key);
            if (this.directionMerge(key)) { //响应用户输入
                this.randomOnePiece(); //用户每次输入后，还会再随机生成一个棋子
                System.out.println("当前得分为：" + score);
            } else { //用户输入不合法的话，给出友好提示
                System.out.println("您只可以输入[a、s、d、w]中的一个字符。");
            }
        }

    }

    /**
     * 响应用户输入
     *
     * @param key 输入的键盘字符
     */
    public boolean directionMerge(String key) {

        //a s d w
        if (key.equals("a")) { // 向左
            for (int i = 0; i < size; i++) {
                Piece[] pieces = board[i];
                mergeRow(pieces);
            }

        } else if (key.equals("d")) { // 向右
            for (int i = 0; i < size; i++) {
                Piece[] pieces = board[i];
                Piece[] nPieces = new Piece[pieces.length];

                for (int j = 0; j < pieces.length; j++) {
                    nPieces[nPieces.length - 1 - j] = pieces[j];
                }

                mergeRow(nPieces);

                for (int j = 0; j < pieces.length; j++) {
                    pieces[j] = nPieces[nPieces.length - 1 - j];
                }
            }

        } else if (key.equals("w")) { // 向上
            for (int i = 0; i < size; i++) {
                Piece[] nPieces = new Piece[size];
                for (int j = 0; j < size; j++) {
                    nPieces[j] = board[j][i];
                }
                mergeRow(nPieces);

                for (int j = 0; j < size; j++) {
                    board[j][i] = nPieces[j];
                }
            }
        } else if (key.equals("s")) { // 向下
            for (int i = 0; i < size; i++) {
                Piece[] nPieces = new Piece[size];
                for (int j = 0; j < size; j++) {
                    nPieces[j] = board[size - 1 - j][i];
                }
                mergeRow(nPieces);

                for (int j = 0; j < size; j++) {
                    board[size - 1 - j][i] = nPieces[j];
                }
            }
        } else {
            return false; // 用户输入不合法
        }

        return true;
    }

    /**
     * 随机生成一个棋子
     */
    public void randomOnePiece() {

        ArrayList<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].isBlank()) {
                    pieces.add(board[i][j]);
                }
            }
        }

        if (pieces.size() == 0) {
            if (!canMerge()) { //没有空间，也没有能合并的棋子，游戏结束
                this.over();
            }
        } else {
            int i = random.nextInt(pieces.size());
            Piece piece = pieces.get(i);
            piece.init();

            // 随机生成的棋子，其内容也按某种概率随机膨胀

            int randomInt = random.nextInt(100) + 1;

            if (randomInt > 85) {
                piece.expand();
            }

            if (randomInt > 95) {
                piece.expand();
            }

            if (randomInt > 98) {
                piece.expand();
            }


        }

    }

    /**
     * 判断当前棋盘是否还有合并机会
     *
     * @return 是否可以合并
     */
    public boolean canMerge() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].equals(board[i + 1][j])) {
                    return true;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (board[i][j].equals(board[i][j + 1])) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 游戏结束
     */
    public void over() {
        System.out.println("游戏结束");
        isOver = true;
    }

    /**
     * 向控制台打印棋盘及内容
     */
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
