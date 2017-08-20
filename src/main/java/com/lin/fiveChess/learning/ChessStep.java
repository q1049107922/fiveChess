package com.lin.fiveChess.learning;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lin on 17-8-20.
 */
public class ChessStep {
    public int id;
    public int gameId;
    public int stepNo;
    public int pointX;
    public int pointY;


    public static ChessStep fill(ResultSet rs) throws SQLException {
        ChessStep step = new ChessStep();
        step.id = rs.getInt("id");
        step.gameId = rs.getInt("gameId");
        step.stepNo = rs.getInt("stepNo");
        step.pointX = rs.getInt("pointX");
        step.pointY = rs.getInt("pointY");
        return step;
    }
}
