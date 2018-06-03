package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.imageio.ImageIO;

import static model.FileOperator.letter;
import static model.FileOperator.number;
import static model.FileOperator.numlet;

/**
 * @Auther: cccis
 * @Date: 6/3/2018 09:43
 * @Description:
 */
public class PieChart {

    public PieChart() {
        DefaultPieDataset data = getDataSet();
        JFreeChart chart = ChartFactory.createPieChart3D("用户口令统计饼状图", data, true, false, false);
        //设置百分比
        PiePlot pieplot = (PiePlot) chart.getPlot();
        DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
        NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
        StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator对象
        pieplot.setLabelGenerator(sp1);//设置饼图显示百分比

        //没有数据的时候显示的内容
        pieplot.setNoDataMessage("无数据显示");
        pieplot.setCircular(false);
        pieplot.setLabelGap(0.02D);

        pieplot.setIgnoreNullValues(true);//设置不显示空值
        pieplot.setIgnoreZeroValues(true);//设置不显示负值
//        frame1=new ChartPanel (chart,true);
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体
        PiePlot piePlot = (PiePlot) chart.getPlot();//获取图表区域对象
        piePlot.setLabelFont(new Font("宋体", Font.BOLD, 10));//解决乱码
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));

        BufferedImage bim_PieChart = chart.createBufferedImage(550, 400);
        //要想保存这个对象的话你要把image声明为BufferedImage 类型
        try {
            ImageIO.write(bim_PieChart, "jpg", new File(System.getProperty("user.dir")+"/res/PieChart.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DefaultPieDataset getDataSet(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("仅数字",number);
        dataset.setValue("仅字母",letter);
        dataset.setValue("字母和数字",numlet);
        clear();
        return dataset;
    }

    private void clear(){
        number = 0;
        letter = 0;
        numlet = 0;
    }

}
