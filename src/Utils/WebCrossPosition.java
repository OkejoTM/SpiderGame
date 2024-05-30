package Utils;

public class WebCrossPosition {
    private final int _row;
    private final int _column;

    public int row() {
        return _row;
    }

    public int column() {
        return _column;
    }

    private void validate(int row, int col) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException();
        }
    }

    public WebCrossPosition(int row, int column) {
        validate(row, column);
        _row = row;
        _column = column;
    }

    // ------------------ Сравнение позиций ----------------
    @Override
    public boolean equals(Object other) {

        if (other instanceof WebCrossPosition otherPosition) {
            return row() == otherPosition.row() && column() == otherPosition.column();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return row() * 1000 + column();
    }
}
