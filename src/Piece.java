/**
 * 棋子接口
 */
public interface Piece {
    /**
     * 显示棋子内容时调用
     *
     * @return
     */
    @Override
    String toString();

    /**
     * 判断两个棋子内容是否相同
     *
     * @param obj
     * @return
     */
    @Override
    boolean equals(Object obj);

    /**
     * 发生棋子合并时调用，比如2、2合并为4
     *
     * @return 返回得分
     */
    int expand();

    /**
     * 判断棋子是否是空，也就是没有内容
     *
     * @return 是否为空
     */
    boolean isBlank();

    /**
     * 把棋子设置成空
     */
    void setBlank();

    /**
     * 给空棋子初始化一个值，比如数字棋子初始化为2
     */
    void init();
}
