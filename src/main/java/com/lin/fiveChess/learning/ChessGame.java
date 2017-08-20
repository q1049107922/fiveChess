package com.lin.fiveChess.learning;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lin on 17-8-20.
 */
public class ChessGame {
    public int id;
    public int isWin;
    public int color;
    public String dateTime;

    public static ChessGame fill(ResultSet rs) throws SQLException {
        ChessGame step = new ChessGame();
        step.id = rs.getInt("id");
        step.isWin = rs.getInt("isWin");
        step.color = rs.getInt("color");
        step.dateTime = rs.getString("dateTime");
        return step;
    }
}
