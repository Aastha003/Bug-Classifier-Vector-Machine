package pro;

import java.awt.Color;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.*;

public class PieGraph{
public void chart(int fun,int log,int ui,int req,int des){
  DefaultPieDataset pieDataset = new DefaultPieDataset();
  pieDataset.setValue("Functional Bug", new Integer(fun));
  pieDataset.setValue("Logical Bug", new Integer(log));
  //pieDataset.setValue("", new Integer(0));
  pieDataset.setValue("UI Bug", new Integer(ui));
  pieDataset.setValue("Requirement Bug", new Integer(req));
  pieDataset.setValue("Design Bug", new Integer(des));
  
  
  JFreeChart chart = ChartFactory.createPieChart3D
  ("Bug Classification - Result", pieDataset, true,true,true);
  PiePlot3D p=(PiePlot3D)chart.getPlot();
  p.setForegroundAlpha(0.5f);
  ChartFrame frame1=new ChartFrame(" Bug Classification - Result ",chart);
  //chart.setBackgroundPaint(new Color(56,70, 60));
  frame1.getContentPane().setBackground(Color.red);
  frame1.setVisible(true);
  frame1.setSize(1024,750);
  }
}