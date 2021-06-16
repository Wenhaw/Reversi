package piece.view;

import piece.constant.CommonConstant;
import piece.util.ComponentUtil;

import javax.swing.*;
import java.awt.*;

public class PieceFrame extends JFrame {

    public void init(){
        this.setLayout(null);
        this.setMinimumSize(new Dimension(CommonConstant.frameWith,CommonConstant.frameHeight));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.setBackground(Color.lightGray);
        this.setContentPane(ComponentUtil.PANEL);
    }



}
