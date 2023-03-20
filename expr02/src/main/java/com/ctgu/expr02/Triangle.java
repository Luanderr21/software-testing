package com.ctgu.expr02;

public class Triangle {
    private int a;
    private int b;
    private int c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String classify() {
        //判断三角形合法
        if ((a < 1 || a > 100) ||
                (b < 1 || b > 100) ||
                (c < 1 || c > 100))
            return "错误边长";

        //判断三角形形状
        if (a == b && a == c) {
            return "等边三角形";
        } else if (!((a + b > c) && (a + c > b) && (b + c > a))) {
            return "非三角形";
        } else if (a != b && a != c && b != c) {
            return "非等边三角形";
        } else {
            return "等腰三角形";
        }

    }
}
