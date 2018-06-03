package controller;

import javafx.scene.chart.Chart;
import model.BarChart;
import model.FileOperator;
import model.PieChart;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @Auther: cccis
 * @Date: 6/3/2018 09:33
 * @Description:
 */
public class MainContro {

    private FileOperator fo = new FileOperator();

    public Image getPieChart(){
        fo.readFile(System.getProperty("user.dir")+"/res/password_yahoo.txt");
        new PieChart();
        Image image = null;
        try {
            image = ImageIO.read(new File(System.getProperty("user.dir")+"/res/PieChart.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public Image getBarChart(){
        fo.readFile(System.getProperty("user.dir")+"/res/password_yahoo.txt");
        new BarChart();
        Image image = null;
        try {
            image = ImageIO.read(new File(System.getProperty("user.dir")+"/res/BarChart.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }


}
