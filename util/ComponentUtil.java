package piece.util;



import piece.action.NormalAction;
import piece.action.PersonAndMachineAction;
import piece.constant.CommonConstant;
import piece.model.Cell;
import piece.view.PieceFrame;
import piece.view.PiecePanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ComponentUtil {
    public static PieceFrame FRAME;
    public static PiecePanel PANEL;

    public static JButton MAN_MACHINE_MODE;
    public static JButton NORMAL_MODE;
    /**
     * 上一个人落子的位置说明
     */
    public static JLabel DETAILS;

    //提示文本
    public static JLabel TIP;

    /**
     * 棋盘列
     */
    public static JLabel MAPC;

    /**
     * 棋盘行
     */
    public static JLabel MAPR;

    /**
     * 棋盘格子(list形式存储)
     */
    public static List<Cell> CELLS;
    /**
     * 棋盘格子(map形式存储)
     */
    public static Map<String,Cell> CELLS_MAP;
    /**
     * 棋盘中的棋子
     */
    public static Set<Cell> PIECES;
    /**
     * 可以落子的几个格子
     */
    public static Set<Cell> CAN_USE_CELLS;

    /**
     * 现在运行的模式
     */
    public static String MODEL;
    /**
     * 当前该下子的棋子颜色
     */
    public static int CURRENT_PIECE_COLOR;




    static {
        CURRENT_PIECE_COLOR=2;
        MODEL="対戦モード";


        MAPC=new JLabel(" A   B   C   D   E   F   G   H");
        MAPC.setBounds(50,420,500,100);

        MAPR=new JLabel("<html>1<br><br>2<br><br>3<br><br>4<br><br>5<br><br>6<br><br>7<br><br>8</html>");
        MAPR.setBounds(30,0,50,500);

        MAPC.setFont(new
                Font("微软雅黑",Font.BOLD,
                30));
        MAPR.setFont(new
                Font("微软雅黑",Font.BOLD,
                20));

        DETAILS=new JLabel("");
        DETAILS.setBounds(550,200,150,40);


        TIP=new JLabel("黑番");
        TIP.setBounds(700,0,50,40);


        NORMAL_MODE=new JButton("対戦モード");
        NORMAL_MODE.setBounds(200,0,100,40);
        NORMAL_MODE.addActionListener(new NormalAction());

        MAN_MACHINE_MODE=new JButton("START");
        MAN_MACHINE_MODE.setBounds(400,0,100,40);
        MAN_MACHINE_MODE.addActionListener(new PersonAndMachineAction());

        initCells();


        PANEL=new PiecePanel();
        FRAME=new PieceFrame();
    }

    public static void initCells(){
        CELLS=new ArrayList();
        CELLS_MAP=new HashMap();
        PIECES=new HashSet();
        CAN_USE_CELLS=new HashSet();
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                Cell cell=new Cell(j* CommonConstant.cellLength,i*CommonConstant.cellLength);
//                if(i == 1){
//                    cell.type=2;
//                }
//                if(i == 2){
//                    cell.type=3;
//                }
                if(i==4&&j==4){
                    cell.type=3;//1是空子，2是黑子，3是白子，4是蓝子
                }
                if(i==4&&j==5){
                    cell.type=2;
                }
                if(i==5&&j==5){
                    cell.type=3;
                }
                if(i==5&&j==4){
                    cell.type=2;
                }
                if(cell.type==2||cell.type==3){
                    PIECES.add(cell);
                }
                CELLS_MAP.put(i+"-"+j,cell);
                CELLS.add(cell);
            }
        }



    }


}
