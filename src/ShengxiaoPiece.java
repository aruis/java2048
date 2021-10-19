public class ShengxiaoPiece implements Piece {

    private int cycle = 0;

    private String[] shengxiao = new String[]{
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
        if (index == -1) return "_";

        if (isNew) {
            isNew = false;
            return "*" + shengxiao[index];
        } else {
            return shengxiao[index];
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        ShengxiaoPiece b = (ShengxiaoPiece) obj;
        return this.index == b.index;
    }
}
