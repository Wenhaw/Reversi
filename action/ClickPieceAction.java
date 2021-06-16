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
                     if(type==2){
                         //将格子中放置黑色棋子
                         cell.type=2;
                         //将中间的白色棋子变为黑
                         PieceUtil.changePieceColor(cell);
                     }else{
                         //将格子中放置白色棋子
                         cell.type=3;
                         //将中间的黑色棋子变为白色
                         PieceUtil.changePieceColor(cell);
                     }
                     PieceUtil.downPieceAfter(cell);
                 }
                 //机器模式执行逻辑
                 else{
                     //将格子中放置白色棋子
                     cell.type=2;
                     //将中间的白色棋子变为黑色
                     PieceUtil.changePieceColor(cell);
                     //落子之后的一些处理逻辑
                     PieceUtil.downPieceAfter(cell);
                     new Thread(new Runnable() {
                         @Override
                         public void run() {
                             //机器操作
                             try {
                                 Thread.sleep(2000);
                             } catch (InterruptedException ex) {
                                 ex.printStackTrace();
                             }
                             int size=ComponentUtil.CAN_USE_CELLS.size();
                             int random=new Random().nextInt(size)+1;
                             int count=0;
                             for(Cell cell1:ComponentUtil.CAN_USE_CELLS){
                                 count++;
                                 if(count==random){
                                     //将格子中放置白色棋子
                                     cell1.type=3;
                                     //将中间的黑色棋子变为白色
                                     PieceUtil.changePieceColor(cell1);
                                     //落子之后的一些处理逻辑
                                     PieceUtil.downPieceAfter(cell1);
                                 }
                             }
                         }
                     }).start();
                 }
             }
    }
}
