package piece.action;

import piece.action.supers.MouseListenerParent;
import piece.constant.CommonConstant;
import piece.model.Cell;
import piece.util.ComponentUtil;
import piece.util.PieceUtil;

import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Set;

public class ClickPieceAction extends MouseListenerParent {
    @Override
    public void mouseClicked(MouseEvent e) {
             //获取用户点击点的坐标
             int x=e.getX();
             int y=e.getY();
             Cell cell =PieceUtil.getCell(x,y);
             //如果为空则代表当前点击点在棋子上，或者是在棋盘之外
             if(cell!=null){
                 //正常模式执行逻辑
                 if(ComponentUtil.MODEL.equals("対戦モード")){
                     int type=ComponentUtil.CURRENT_PIECE_COLOR;
                     PieceUtil.putPiece(cell, type);
                 }
                 //机器模式执行逻辑
                 else{
                     PieceUtil.putPiece(cell, 2);
                     new Thread(new Runnable() {
                         @Override
                         public void run() {
                             //机器操作
                             try {
                                 Thread.sleep(2000);
                             } catch (InterruptedException ex) {
                                 ex.printStackTrace();
                             }
                             Cell minPosCell = null;
                             int minPosCellNum = -1;
                             for(Cell cell1 : ComponentUtil.CAN_USE_CELLS) {
                                 if (ComponentUtil.CURRENT_PIECE_COLOR == 3){
                                     int posCellNum = PieceUtil.checkPossibleCells(cell1, 3);
                                 if (minPosCell == null || posCellNum < minPosCellNum) {
                                     minPosCell = cell1;
                                     minPosCellNum = posCellNum;
                                 }
                             }
                             }                             
                             PieceUtil.putPiece(minPosCell, 3);
                         }
                     }).start();
                 }
             }
    }
}
