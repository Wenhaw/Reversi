package piece.action;

import piece.util.ComponentUtil;
import piece.util.PieceUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NormalAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ComponentUtil.MODEL="対戦モード";
                PieceUtil.countAndSaveGreenPiece();
                while (true){
                    ComponentUtil.FRAME.repaint();
                }
            }
        }).start();
    }
}
