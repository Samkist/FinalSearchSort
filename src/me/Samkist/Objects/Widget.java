package me.Samkist.Objects;

/**
 * Created by Samkist
 * https://github.com/Samkist
 */
public class Widget implements Comparable<Widget> {
    private String code;
    private int sold;

    public Widget(String code, int sold) {
        try {
            if (code.length() != 3) throw new Exception();
            Integer.parseInt(code);
        } catch(Exception e) {
            throw new IllegalArgumentException("Invalid Code");
        }
        this.code = code;
        this.sold = sold;
    }


    public String getCode() {
        return code;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    @Override
    public int compareTo(Widget widget) {
        return sold - widget.getSold();
    }

    @Override
    public String toString() {
        return getCode() + " - " + getSold();
    }
}
