public class Piece {

    private int value = 0;

    private boolean isNew = true;

    @Override
    public String toString() {
        if (value == 0) return "_";

        if (isNew) {
            isNew = false;
            return "*" + value;
        } else {
            return String.valueOf(value);
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        Piece b = (Piece) obj;
        return this.getValue() == b.getValue();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
