package view;

import controller.MainContro;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Auther: cccis
 * @Date: 6/1/2018 14:11
 * @Description:
 */
public class Mainview extends JFrame{

    private boolean Pie = true; //默认Pie图

    private static Color textColour = new Color(48, 58, 73);
    private static Color buttonBackground = new Color(0, 186, 184);
    private static Color backgroundColour = new Color(26,37,49);

    public Mainview() throws HeadlessException {
        initializeGUI();
    }

    // Initialize GUI
    public void initializeGUI() {

        //创建window.
        JFrame frame = new JFrame("陈朝晖大作业-用户口令统计");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置大小
        frame.setSize(600, 500);

        //设置内容pane.
        addComponentsToPane(frame.getContentPane());

        //展示window.
        frame.setVisible(true);
    }

    public void addComponentsToPane(Container pane) {

        // Set form layout
        GroupLayout layout = new GroupLayout(pane);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        pane.setLayout(layout);

        // Layout colours and attributes
        pane.setBackground(backgroundColour);

        // 创建两个单选按钮
        JRadioButton radioBtn01 = new JRadioButton("饼状图");
        JRadioButton radioBtn02 = new JRadioButton("柱状图");

        // 创建按钮组，把两个单选按钮添加到该组
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(radioBtn01);
        btnGroup.add(radioBtn02);

        // 设置第一个单选按钮选中
        radioBtn01.setSelected(true);

        //图片显示框
        MyPanel mp = new MyPanel();

        // Sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup().addComponent(radioBtn01).addComponent(radioBtn02);

        GroupLayout.ParallelGroup hParGroup = layout.createParallelGroup().addGroup(GroupLayout.Alignment.CENTER, hGroup).addComponent(mp, GroupLayout.Alignment.CENTER);
        layout.setHorizontalGroup(hParGroup);

        // Sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(radioBtn01).addComponent(radioBtn02));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mp));
        layout.setVerticalGroup(vGroup);

        radioBtn01.addActionListener(new ActionListener() {     // 捕获单选按钮被选中的事件
            public void actionPerformed(ActionEvent e) {
                Pie = true;
                mp.removeAll();
                mp.repaint();
            } });
        radioBtn02.addActionListener(new ActionListener() {     // 捕获单选按钮被选中的事件
            public void actionPerformed(ActionEvent e) {
                Pie = false;
                mp.removeAll();
                mp.repaint();
            } });
    }

    class MyPanel extends JPanel {
        private MainContro mc = new MainContro();
        int used = 0;
        Image image = null;
        public void paint(Graphics g) {
            try {
                System.out.println(System.getProperty("user.dir"));
//                System.out.println(System.getProperty());
//                image = ImageIO.read(new File("E:\\Utli\\JFCProject\\res/icon.jpg"));
//                if(Pie){
//                    image = mc.getPieChart();
//                }else {
//                    image = mc.getPieChart();
//                }
                image = Pie?  mc.getPieChart():mc.getBarChart();
                if (used == 0){
                    image = null;
                    used++;
                }
                g.drawImage(image, 0, 0, 550, 400, null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//    private JButton chooseFileButton(String text) {
//        JButton button = new JButton(new AbstractAction(text) {
//            public void actionPerformed(ActionEvent e) {
//
//                //Create a file chooser
//                final JFileChooser fc = new JFileChooser();
//
//                //In response to a button click:
//                int returnVal = fc.showOpenDialog(getContentPane());
//            }
//        });
//        button.setBackground(buttonBackground);
//        button.setBorderPainted(false);
//        button.setFocusPainted(false);
//        button.setContentAreaFilled(true);
//
//        return button;
//
//    }
}