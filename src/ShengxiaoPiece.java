/**
 * 十二生肖棋子
 */
public class ShengxiaoPiece implements Piece {

    //十二生肖不像数字是无限的，所有走到"猪"的时候，会开启一个新的周期，此字段用来记录周期数
    private int cycle = 0;

    //十二生肖周期数组
    private final static String[] SHENGXIAO = new String[]{
            "鼠",
            "牛",
            "虎",
            "兔",
            "龙",
            "蛇",
            "马",
            "羊",
            "猴",
            "鸡",
            "狗",
            "猪",
    };

    //当前生肖索引，针对 SHENGXIAO 数组的索引
    private int index = -1;

    private boolean isNew = true;

    @Override
    public int expand() {
        index++;
        if (index > 11) {
            index = 0;
            cycle++;
        }
        return cycle * 12 + index;
    }

    @Override
    public boolean isBlank() {
        return index == -1;
    }

    @Override
    public void setBlank() {
        this.index = -1;
    }

    @Override
    public void init() {
        this.index = 0;
    }

    @Override
    public String toString() {
        if (isBlank()) return "_";

        if (isNew) {
            isNew = false;
            return "*" + SHENGXIAO[index];
        } else {
            return SHENGXIAO[index];
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        ShengxiaoPiece b = (ShengxiaoPiece) obj;
        return this.index == b.index;
    }
}
