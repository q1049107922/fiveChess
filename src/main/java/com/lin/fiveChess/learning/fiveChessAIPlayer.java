package com.lin.fiveChess.learning;

import com.lin.common.util;
import com.lin.fiveChess.ChessBoard;
import com.lin.fiveChess.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by lin on 17-8-20.
 */
public class fiveChessAIPlayer {

    int gameId;
    Connection conn;

    Map<Integer, ChessStep> oldStep = new HashMap<Integer, ChessStep>();

    Map<Integer, ChessStep> newStep = new HashMap<Integer, ChessStep>();

    ChessBoard chessBoard;
    /**
     * 如果之前的棋局胜利，尽量维持原有的下棋策略
     */
    public fiveChessAIPlayer(int gameId , ChessBoard chessBoard){
        this.gameId = gameId;
        this.chessBoard=chessBoard;
        try {
            conn = util.getConn();
            ResultSet rs = conn.createStatement().executeQuery("select * from ChessStep where gameId=" + (gameId-1) + " order by stepNo");
            while (rs.next()) {
                ChessStep s = ChessStep.fill(rs);
                oldStep.put(s.stepNo, s);
            }
        }catch (Exception e){

        }
    }

    public ChessStep setPoint(int stepNo) throws SQLException {
        ChessStep step;
        if (oldStep != null && oldStep.isEmpty()) {
            //之前棋局胜利
            if (oldStep.containsKey(stepNo)) {
                step = oldStep.get(stepNo);
                //如果x，y位置已经有棋子存在，不能下,如果之前的棋局胜利，尽量维持原有的下棋策略
                if (ChessBoard.findChess(step.pointX, step.pointY)) {
                    step = randomStep(gameId, stepNo);
                }
            }
            else{
                step = randomStep(gameId, stepNo);
            }
        }else {//之前棋局失败
            step = randomStep(gameId, stepNo);
        }
        newStep.put(stepNo, step);
        conn.createStatement().execute("INSERT  into ChessStep (gameId,pointX,pointY,stepNo,createTime) VALUE (" +
                step.gameId +","+ step.pointX +","+ step.pointY+"," + stepNo +",'"+ util.getDateTimeNow() + "')");

        return step;
    }

    public ChessStep randomStep(int gameId, int stepNo) {
        ChessStep step = new ChessStep();
        Random random = new Random();
        step.pointX = random.nextInt(14)+1;
        step.pointY = random.nextInt(14)+1;
        if (ChessBoard.findChess(step.pointX, step.pointY)) {
            return randomStep(gameId, stepNo);
        } else {
            step.gameId = gameId;
            step.stepNo = stepNo;
            return step;
        }
    }

    public void autoPlay(int stepNo){
        try {
            chessBoard.printPoint(setPoint(stepNo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
