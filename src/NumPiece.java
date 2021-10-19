public class NumPiece implements Piece {

    private int value = 0;
    private boolean isNew = true;

    @Override
    public int expand() {
        value = value * 2;
        return value;
    }

    @Override
    public boolean isBlank() {
        return value == 0;
    }

    @Override
    public void setBlank() {
        this.value = 0;
    }

    @Override
    public void init() {
        this.value = 2;
    }

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
        NumPiece b = (NumPiece) obj;
        return this.value == b.value;
    }
}
