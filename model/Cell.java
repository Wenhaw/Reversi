package piece.model;

import piece.constant.CommonConstant;

import java.awt.*;
import java.util.Objects;

/**
 * 格子
 */
public class Cell {
    public int x;
    public int y;
    public int type=1;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void drawPiece(Graphics g){
        if(2==type){
            g.setColor(Color.BLACK);
            g.fillOval(this.x+5,this.y+5, CommonConstant.cellLength-10,CommonConstant.cellLength-10);
        }
        if(3==type){
            g.setColor(Color.WHITE);
            g.fillOval(this.x+5,this.y+5, CommonConstant.cellLength-10,CommonConstant.cellLength-10);
        }
        if(4==type){
            g.setColor(Color.cyan);
            g.fillRect(this.x+5,this.y+5, CommonConstant.cellLength-10,CommonConstant.cellLength-10);
        }
    }

}
