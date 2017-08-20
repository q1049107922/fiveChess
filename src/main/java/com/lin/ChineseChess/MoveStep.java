package com.lin.ChineseChess;

/**
 * Created by lin on 17-8-20.
 */
import java.awt.Point;

/**
 * 走步类
 *
 * @author cnlht
 *
 */
public class MoveStep implements java.io.Serializable {
    public Point pStart, pEnd;

    public MoveStep(Point p1, Point p2) {
        pStart = p1;
        pEnd = p2;
    }
}