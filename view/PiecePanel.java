package piece.view;

import piece.action.ClickPieceAction;
import piece.constant.CommonConstant;
import piece.model.Cell;
import piece.util.ComponentUtil;

import javax.swing.*;
import java.awt.*;

public class PiecePanel extends JPanel {

    public void init(){
        this.setSize(new Dimension(CommonConstant.panelWith,CommonConstant.panelHeight));
        this.setLayout(null);
        //设置组件
        this.add(ComponentUtil.MAN_MACHINE_MODE);
        this.add(ComponentUtil.NORMAL_MODE);
        this.add(ComponentUtil.TIP);
        this.add(ComponentUtil.DETAILS);
        this.add(ComponentUtil.MAPR);
        this.add(ComponentUtil.MAPC);
        this.addMouseListener(new ClickPieceAction());
    }
    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        for(Cell cell: ComponentUtil.CELLS){
            g.setColor(Color.BLACK);
            g.drawRect(cell.x,cell.y, CommonConstant.cellLength,CommonConstant.cellLength);
            cell.drawPiece(g);
        }
    }
}