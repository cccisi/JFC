package model;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static model.FileOperator.letter;
import static model.FileOperator.number;
import static model.FileOperator.numlet;

/**
 * @Auther: cccis
 * @Date: 6/3/2018 09:43
 * @Description:
 */
public class BarChart {

    public  BarChart(){
        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(
                "用户口令统计柱状图", // 图表标题
                "口令类型", // 目录轴的显示标签
                "数量", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );

        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体

        //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

        BufferedImage bim_PieChart = chart.createBufferedImage(550, 400);
        //要想保存这个对象的话你要把image声明为BufferedImage 类型
        try {
            ImageIO.write(bim_PieChart, "jpg", new File(System.getProperty("user.dir")+"/res/BarChart.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(number, "仅数字", "数量");
        dataset.addValue(letter, "仅字母", "数量");
        dataset.addValue(numlet, "字母和数字", "数量");
        clear();
        return dataset;
    }

    private void clear(){
        number = 0;
        letter = 0;
        numlet = 0;
    }
}
