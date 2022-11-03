package hello;

public enum Good {
    ONE(1, "hello"),
    TWO(2, "good"),
    THREE(3, "hi");

    public int getNum() {
        return num;
    }

    public String getStr() {
        return str;
    }

    private final int num;
    private final String str;

    Good(int num, String str) {
        this.num = num;
        this.str = str;
    }
}
