
package pro;

import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.chart.plot.*;
import java.awt.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.category.BarRenderer;

public class BarGraph {

    public void graph(int one,int two)
    {
        DefaultCategoryDataset dcd=new DefaultCategoryDataset();
        int neg=10;
        dcd.setValue(one,"IN PERCENTAGE ","  ACCURACY OF 1st CLASSIFIER");
        dcd.setValue(two,"IN PERCENT ","  ACCURACY OF 2nd CLASSIFIER");
        //dcd.setValue(neg,"  -ve out of ", "                       THREAT DETECTED");
        //dcd.setValue(acc-10, poly,"Polynomial kernal accuracay in %");

        JFreeChart chart = ChartFactory.createBarChart("COMPARISON OF BUG CLASSIFICATION ACCURACY","RESULTS", "RECORDS", dcd,
        PlotOrientation.VERTICAL, false,true, false);
        chart.setBackgroundPaint(Color.CYAN);
        chart.getTitle().setPaint(Color.darkGray);
        //chart.getTitle().setFont(Font.getFont("Times New Roman"));
        CategoryPlot cp=chart.getCategoryPlot();
        cp.setRangeGridlinePaint(Color.white);


        //extra modification
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
       // rangeAxis.setRange(0, rec);
        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.BLUE,
                0.0f, 0.0f, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);




        ChartFrame g=new ChartFrame("Bar Chart",chart);

        g.setVisible(true);
        g.setSize(1020,764);
}
}