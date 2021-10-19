public interface Piece {
    @Override
    String toString();

    @Override
    boolean equals(Object obj);

    int expand();

    boolean isBlank();

    void setBlank();

    void init();
}
