package piece.util;

import piece.constant.CommonConstant;
import piece.model.Cell;

import java.util.HashSet;
import java.util.Set;

public class PieceUtil {
    //清除绿色的格子
    public static void clearGreenPiece() {
        for (Cell cell : ComponentUtil.CELLS) {
            if (cell.type == 4) {
                cell.type = 1;
            }
        }
        ComponentUtil.CAN_USE_CELLS = new HashSet();
    }
    //计算并保存绿色格子
    public static void countAndSaveGreenPiece() {
        Set<Cell> nodes = new HashSet();
        //第一次过滤,主要是收集棋子旁边的格子数据
        for (Cell piece : ComponentUtil.PIECES) {
            int x = piece.x / CommonConstant.cellLength;
            int y = piece.y / CommonConstant.cellLength;

            Cell top = ComponentUtil.CELLS_MAP.get((y - 1) + "-" + x);
            Cell down = ComponentUtil.CELLS_MAP.get((y + 1) + "-" + x);
            Cell left = ComponentUtil.CELLS_MAP.get(y + "-" + (x - 1));
            Cell right = ComponentUtil.CELLS_MAP.get(y + "-" + (x + 1));

            Cell leftTop = ComponentUtil.CELLS_MAP.get((y - 1) + "-" + (x - 1));
            Cell rightTop = ComponentUtil.CELLS_MAP.get((y - 1) + "-" + (x + 1));
            Cell leftDown = ComponentUtil.CELLS_MAP.get((y + 1) + "-" + (x - 1));
            Cell rightDown = ComponentUtil.CELLS_MAP.get((y + 1) + "-" + (x + 1));

            filterData(top, nodes);
            filterData(down, nodes);
            filterData(left, nodes);
            filterData(right, nodes);
            filterData(leftTop, nodes);
            filterData(rightTop, nodes);
            filterData(leftDown, nodes);
            filterData(rightDown, nodes);
        }
        //第二次过滤,主要是验证哪些格子能翻子
        for (Cell cell : nodes) {
              boolean flag=checkData(cell);
              if(flag){
                  ComponentUtil.CAN_USE_CELLS.add(cell);
                  cell.type=4;
              }
        }

    }

    //如果坐标一样则不进行数据的添加,不过这里直接add也行，因为Set数据类型是不运行存在重复的数据。
    private static void filterData(Cell data, Set needSave) {
        if (data == null || ComponentUtil.PIECES.contains(data)) {
            return;
        }
        needSave.add(data);
    }
    //判断该格子是否能翻子
    private static boolean checkData(Cell cell) {
        int x = cell.x / CommonConstant.cellLength;
        int y = cell.y / CommonConstant.cellLength;
        boolean flagTop = false;
        boolean flagDown = false;
        boolean flagLeft = false;
        boolean flagRight = false;
        boolean flagRightTop = false;
        boolean flagRightDown = false;
        boolean flagLeftTop = false;
        boolean flagLeftDown = false;
        int count;
        int tempX;
        int tempY;

        //上
        count = 0;
        for (int i = y-1; i >= 1; i--) {
            count++;
            Cell top = ComponentUtil.CELLS_MAP.get(i + "-" + x);
            if(top==null)break;
            if (top.type == 1 && count == 1) {
                break;
            }
            if (top.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && top.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && top.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagTop = true;
                break;
            }
        }
        if (flagTop) return true;

        //下
        count = 0;
        for (int i = y+1; i <= 8; i++) {
            count++;
            Cell down = ComponentUtil.CELLS_MAP.get(i + "-" + x);
            if(down==null)break;
            if (down.type == 1 && count == 1) {
                break;
            }
            if (down.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && down.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && down.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagDown = true;
                break;
            }
        }
        if (flagDown) return true;

        //左
        count = 0;
        for (int i = x-1; i >= 1; i--) {
            count++;
            Cell left = ComponentUtil.CELLS_MAP.get(y + "-" + i);
            if(left==null)break;
            if (left.type == 1 && count == 1) {
                break;
            }
            if (left.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && left.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && left.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagLeft = true;
                break;
            }
        }
        if (flagLeft) return true;

        //右
        count = 0;
        for (int i = x+1; i <= 8; i++) {
            count++;
            Cell right = ComponentUtil.CELLS_MAP.get(y + "-" + i);
            if(right==null)break;
            if (right.type == 1 && count == 1) {
                break;
            }
            if (right.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && right.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && right.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagRight = true;
                break;
            }
        }
        if (flagRight) return true;

        //右上
        count = 0;
        tempX = x;
        tempY = y;
        while (tempX <= 8 && tempY >= 1) {
            count++;
            tempX++;
            tempY--;
            Cell rightTop = ComponentUtil.CELLS_MAP.get(tempY + "-" + tempX);
            if(rightTop==null)break;
            if (rightTop.type == 1 && count == 1) {
                break;
            }
            if (rightTop.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && rightTop.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && rightTop.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagRightTop = true;
                break;
            }
        }
        if (flagRightTop) return true;
        //右下
        count = 0;
        tempX = x;
        tempY = y;
        while (tempX <= 8 && tempY <= 8) {
            count++;
            tempX++;
            tempY++;
            Cell rightDown = ComponentUtil.CELLS_MAP.get(tempY + "-" + tempX);
            if(rightDown==null)break;
            if (rightDown.type == 1 && count == 1) {
                break;
            }
            if (rightDown.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && rightDown.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && rightDown.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagRightDown = true;
                break;
            }
        }
        if (flagRightDown) return true;
        //左上
        count = 0;
        tempX = x;
        tempY = y;
        while (tempX >= 1 && tempY >= 1) {
            count++;
            tempX--;
            tempY--;
            Cell leftTop = ComponentUtil.CELLS_MAP.get(tempY + "-" + tempX);
            if(leftTop==null)break;
            if (leftTop.type == 1 && count == 1) {
                break;
            }
            if (leftTop.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && leftTop.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && leftTop.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagLeftTop = true;
                break;
            }
        }
        if (flagLeftTop) return true;
        //左下
        count = 0;
        tempX = x;
        tempY = y;
        while (tempX >= 1 && tempY <= 8) {
            count++;
            tempX--;
            tempY++;
            Cell leftDown = ComponentUtil.CELLS_MAP.get(tempY + "-" + tempX);
            if(leftDown==null)break;
            if (leftDown.type == 1 && count == 1) {
                break;
            }
            if (leftDown.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && leftDown.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && leftDown.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagLeftDown = true;
                break;
            }
        }
        if (flagLeftDown) return true;
        return false;
    }

    public static void changeCurrentPieceColor(){
        if(ComponentUtil.CURRENT_PIECE_COLOR==2){
            ComponentUtil.CURRENT_PIECE_COLOR=3;
            ComponentUtil.TIP.setText("白番");
        }else{
            ComponentUtil.CURRENT_PIECE_COLOR=2;
            ComponentUtil.TIP.setText("黑番");
        }
    }

    public static void downPieceAfter(Cell cell){
        //记录位置
        String name="";
        if(ComponentUtil.CURRENT_PIECE_COLOR==2){
            name="黑の位置:";
        }else {
            name="白の位置:";
        }
        int i=cell.y/CommonConstant.cellLength;
        int k=cell.x/CommonConstant.cellLength;
        String j = null;
        switch (k){
            case 1:
                j = "A";
                break;
            case 2:
                j = "B";
                break;
            case 3:
                j = "C";
                break;
            case 4:
                j = "D";
                break;
            case 5:
                j = "E";
                break;
            case 6:
                j = "F";
                break;
            case 7:
                j = "G";
                break;
            case 8:
                j = "H";
                break;
        }
        name+=i+" 行 ";
        name+=j+" 列 ";
        ComponentUtil.DETAILS.setText(name);
        //将该棋子放入棋子集合中
        ComponentUtil.PIECES.add(cell);
        //清空可下棋子
        PieceUtil.clearGreenPiece();
        //将当前该下棋子颜色转换
        changeCurrentPieceColor();
        //重新计算课下棋子
        PieceUtil.countAndSaveGreenPiece();
        //验证(该颜色)是否存在可下棋子
        if(ComponentUtil.CAN_USE_CELLS.size()==0){
            //验证是否结束
            if(ComponentUtil.PIECES.size()==64){
                System.out.println("ゲーム終了");
                System.exit(0);
            }

            //如果没有结束则将当前该下棋子的颜色反转
            changeCurrentPieceColor();
            //重新计算课下棋子
            PieceUtil.countAndSaveGreenPiece();
        }
        //重新绘画
        ComponentUtil.FRAME.repaint();
    }



    public static void changePieceColor(Cell cell) {
        int x = cell.x / CommonConstant.cellLength;
        int y = cell.y / CommonConstant.cellLength;
        boolean flagTop = false;
        boolean flagDown = false;
        boolean flagLeft = false;
        boolean flagRight = false;
        boolean flagRightTop = false;
        boolean flagRightDown = false;
        boolean flagLeftTop = false;
        boolean flagLeftDown = false;
        int count;
        int tempX;
        int tempY;

        //上
        count = 0;
        for (int i = y-1; i >= 1; i--) {
            count++;
            Cell top = ComponentUtil.CELLS_MAP.get(i + "-" + x);
            if(top==null)break;
            if (top.type == 1 && count == 1) {
                break;
            }
            if (top.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && top.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && top.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagTop = true;
                break;
            }
        }
        //下
        count = 0;
        for (int i = y+1; i <= 8; i++) {
            count++;
            Cell down = ComponentUtil.CELLS_MAP.get(i + "-" + x);
            if(down==null)break;
            if (down.type == 1 && count == 1) {
                break;
            }
            if (down.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && down.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && down.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagDown = true;
                break;
            }
        }
        //左
        count = 0;
        for (int i = x-1; i >= 1; i--) {
            count++;
            Cell left = ComponentUtil.CELLS_MAP.get(y + "-" + i);
            if(left==null)break;
            if (left.type == 1 && count == 1) {
                break;
            }
            if (left.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && left.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && left.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagLeft = true;
                break;
            }
        }
        //右
        count = 0;
        for (int i = x+1; i <= 8; i++) {
            count++;
            Cell right = ComponentUtil.CELLS_MAP.get(y + "-" + i);
            if(right==null)break;
            if (right.type == 1 && count == 1) {
                break;
            }
            if (right.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && right.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && right.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagRight = true;
                break;
            }
        }
        //右上
        count = 0;
        tempX = x;
        tempY = y;
        while (tempX <= 8 && tempY >= 1) {
            count++;
            tempX++;
            tempY--;
            Cell rightTop = ComponentUtil.CELLS_MAP.get(tempY + "-" + tempX);
            if(rightTop==null)break;
            if (rightTop.type == 1 && count == 1) {
                break;
            }
            if (rightTop.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && rightTop.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && rightTop.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagRightTop = true;
                break;
            }
        }
        //右下
        count = 0;
        tempX = x;
        tempY = y;
        while (tempX <= 8 && tempY <= 8) {
            count++;
            tempX++;
            tempY++;
            Cell rightDown = ComponentUtil.CELLS_MAP.get(tempY + "-" + tempX);
            if(rightDown==null)break;
            if (rightDown.type == 1 && count == 1) {
                break;
            }
            if (rightDown.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && rightDown.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && rightDown.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagRightDown = true;
                break;
            }
        }
        //左上
        count = 0;
        tempX = x;
        tempY = y;
        while (tempX >= 1 && tempY >= 1) {
            count++;
            tempX--;
            tempY--;
            Cell leftTop = ComponentUtil.CELLS_MAP.get(tempY + "-" + tempX);
            if(leftTop==null)break;
            if (leftTop.type == 1 && count == 1) {
                break;
            }
            if (leftTop.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && leftTop.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && leftTop.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagLeftTop = true;
                break;
            }
        }
        //左下
        count = 0;
        tempX = x;
        tempY = y;
        while (tempX >= 1 && tempY <= 8) {
            count++;
            tempX--;
            tempY++;
            Cell leftDown = ComponentUtil.CELLS_MAP.get(tempY + "-" + tempX);
            if(leftDown==null)break;
            if (leftDown.type == 1 && count == 1) {
                break;
            }
            if (leftDown.type == 4 && count == 1) {
                break;
            }
            if (count == 1 && leftDown.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                break;
            }
            if (count > 1 && leftDown.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                flagLeftDown = true;
                break;
            }
        }


        if(flagTop){
            count = 0;
            for (int i = y-1; i >= 1; i--) {
                count++;
                Cell top = ComponentUtil.CELLS_MAP.get(i + "-" + x);
                if(top==null)break;
                if (count > 1 && top.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                    break;
                }
                top.type=ComponentUtil.CURRENT_PIECE_COLOR;
            }
        }
        if(flagDown){
            count = 0;
            for (int i = y+1; i <= 8; i++) {
                count++;
                Cell down = ComponentUtil.CELLS_MAP.get(i + "-" + x);
                if(down==null)break;
                if (count > 1 && down.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                    break;
                }
                down.type=ComponentUtil.CURRENT_PIECE_COLOR;
            }
        }
        if(flagLeft){
            count = 0;
            for (int i = x-1; i >= 1; i--) {
                count++;
                Cell left = ComponentUtil.CELLS_MAP.get(y + "-" + i);
                if(left==null)break;
                if (count > 1 && left.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                    break;
                }
                left.type=ComponentUtil.CURRENT_PIECE_COLOR;
            }
        }
        if(flagRight){
            count = 0;
            for (int i = x+1; i <= 8; i++) {
                count++;
                Cell right = ComponentUtil.CELLS_MAP.get(y + "-" + i);
                if(right==null)break;
                if (count > 1 && right.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                    break;
                }
                right.type=ComponentUtil.CURRENT_PIECE_COLOR;
            }
        }
        if(flagRightTop){
            count = 0;
            tempX = x;
            tempY = y;
            while (tempX <= 8 && tempY >= 1) {
                count++;
                tempX++;
                tempY--;
                Cell rightTop = ComponentUtil.CELLS_MAP.get(tempY + "-" + tempX);
                if(rightTop==null)break;
                if (count > 1 && rightTop.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                    break;
                }
                rightTop.type=ComponentUtil.CURRENT_PIECE_COLOR;
            }
        }
        if(flagRightDown){
            count = 0;
            tempX = x;
            tempY = y;
            while (tempX <= 8 && tempY <= 8) {
                count++;
                tempX++;
                tempY++;
                Cell rightDown = ComponentUtil.CELLS_MAP.get(tempY + "-" + tempX);
                if(rightDown==null)break;
                if (count > 1 && rightDown.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                    break;
                }
                rightDown.type=ComponentUtil.CURRENT_PIECE_COLOR;
            }
        }
        if(flagLeftTop){
            count = 0;
            tempX = x;
            tempY = y;
            while (tempX >= 1 && tempY >= 1) {
                count++;
                tempX--;
                tempY--;
                Cell leftTop = ComponentUtil.CELLS_MAP.get(tempY + "-" + tempX);
                if(leftTop==null)break;
                if (count > 1 && leftTop.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                    break;
                }
                leftTop.type=ComponentUtil.CURRENT_PIECE_COLOR;
            }
        }
        if(flagLeftDown){
            count = 0;
            tempX = x;
            tempY = y;
            while (tempX >= 1 && tempY <= 8) {
                count++;
                tempX--;
                tempY++;
                Cell leftDown = ComponentUtil.CELLS_MAP.get(tempY + "-" + tempX);
                if(leftDown==null)break;
                if (count > 1 && leftDown.type == ComponentUtil.CURRENT_PIECE_COLOR) {
                    break;
                }
                leftDown.type=ComponentUtil.CURRENT_PIECE_COLOR;
            }
        }
    }
    //判断坐标是否存在棋盘内
    public static Cell getCell(int x, int y) {
        //判断是否在棋盘内
        if(x< CommonConstant.cellLength
                ||y<CommonConstant.cellLength
                ||x>CommonConstant.cellLength*8+CommonConstant.cellLength
                ||y>CommonConstant.cellLength*8+CommonConstant.cellLength){
            return null;
        }
        //判断是否是空的格子
        int tempX=x/CommonConstant.cellLength;
        int tempY=y/CommonConstant.cellLength;
        Cell cell=ComponentUtil.CELLS_MAP.get(tempY+"-"+tempX);
        if(cell.type==2||cell.type==3){
            return null;
        }
        //判断是否在可下范围之内
        boolean flag=ComponentUtil.CAN_USE_CELLS.contains(cell);
        if(!flag)return null;


        return cell;
    }


}
