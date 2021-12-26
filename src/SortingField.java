public enum SortingField {
    NAME(0),
    SIZE(1),
    DATE(2);

    private final int val;

    /**
     * constructor
     * @param val value for each enum
     */
    SortingField(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
