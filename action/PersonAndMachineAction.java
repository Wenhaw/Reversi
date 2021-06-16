package piece.action;


import piece.util.ComponentUtil;
import piece.util.PieceUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonAndMachineAction  implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ComponentUtil.MODEL="人机模式";
                PieceUtil.countAndSaveGreenPiece();
                while (true){
                    ComponentUtil.FRAME.repaint();
                }
            }
        }).start();
    }

}
