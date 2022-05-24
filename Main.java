/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pro;

/**
 *
 * @author Chayan
 */
import Jama.Matrix;
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    double training_data[][];
    double training_class[][];
    double kernelmat[][];
    double lagrange_multiplier[][];
    double right_side_value[][];
    double bias[][];
    double test_dataset[][];
    double test_class[][];
    int row, col, temp;
    double bias_average = 0.0;

    double gamma = 1;
    double coeff = 1;
    double degree = 2;
    int kc = 0;
    int pos = 0, neg = 0;
    int tc = 0, l = 0, ok = 0;
    static int tr = 0, check = 0;
    static double Label[][];
    static double Label1[][];
    static double Label2[][];
    static double Label3[][];
    static double Label4[][];
    public static int fun=0,log=0,ui=0,req=0,des=0;
    String final_class[][];

    FileNumeric fn=new FileNumeric();
    static String kfunc = "";

    public void prepareData(String anyfile, int kc, int coeff, int degree, double gamma)//call some functions for calculating kernel matrix
    {
        GenerateMatrix fg = new GenerateMatrix();

        fg.genMat(anyfile);
        training_data = fg.getDataMatrix();
        training_class = fg.getClassMatrix();
        //System.out.println(training_data[1]);
        row = fg.getRow();
        col = fg.getCol() - 1;
        //System.out.println(row+" "+col);
        setValues(kc, coeff, degree, gamma);

        double kernel[][] = new double[row][row];
        right_side_value = new double[row][1];

        for (int i = 0; i < row; i++) {
            right_side_value[i][0] = 1;
        }

        Matrix y = new Matrix(training_class);

        double mulres[][] = MatrixMultiply(training_class, y.transpose().getArray());
        double kerres[][] = KernelMatrix(training_data, kc);

        kernel = kernel_mul_class(kerres, mulres);
        //for(int i=0;i<mulres.length;i++)
        //System.out.println(mulres[i][0]);

        Matrix matkernel = new Matrix(kernel);
        Matrix rsv = new Matrix(right_side_value);

        lagrange_multiplier = matkernel.solve(rsv).getArray();
        //for(int i=0;i<lagrange_multiplier.length;i++)
        //System.out.println(lagrange_multiplier[i][0]+" "+i);

        calculatebias(training_class, lagrange_multiplier, training_data);
        //System.out.println(fn.getNumeric().getParent());
        S3VM_Unalabeled(fn.getNumeric().getParent()+ "/TrainingUlab.txt", 0);

    }

    public void calculatebias(double t_c[][], double lag_mul[][], double t_data[][]) {

        bias = new double[row][1];
        for (int i = 0; i < row; i++) {
            if (lag_mul[i][0] > 0) {
                bias[i][0] = t_c[i][0] - imp(lag_mul, t_c, t_data, t_data[i]);
                //System.out.print(bias[i][0]+"--");
            }
        }
        bias_average = 0;
        temp = 0;
        for (int i = 0; i < bias.length; i++) {
            if (bias[i][0] > 0) {
                bias_average = bias_average + bias[i][0];
                temp++;
            }
        }

        bias_average = bias_average / temp;
        // System.out.println(bias_average);
    }

     public void S3VM_Unalabeled(String TestDataSet, int vimp) {
        GenerateMatrix gmt = new GenerateMatrix();
        gmt.genMat(TestDataSet);
        int i=0;
        ok++;
       // System.out.println(vimp+" ");
        if (vimp == 0) {
            test_dataset = gmt.getUnDataMatrix();
            // test_class=gmt.getClassMatrix();
            tr = gmt.getRow();
           // System.out.println(tr+" ");
            tc = gmt.getCol();
        } else {
            test_dataset = gmt.getDataMatrix();
            test_class = gmt.getClassMatrix();
            tr = gmt.getRow();
            tc = gmt.getCol() - 1;
            check=new FileGenerator().getTotaldata()/14;
            System.out.print(tr+"  check "+check);
        }

        //System.out.println(tr+"\\"+tc);
       /* for(int i=0;i<tr;i++)
        {


        System.out.print(test_class[i][0]+" ");

        System.out.println();
        }*/
        if(FileParser.invoke==1)
        {
        Label = new double[tr][1];
        for (l = 0; l < tr; l++) {
           // System.out.print(l+"  ");
            Label[l][0] = find_class(test_dataset[l],vimp);
            //System.out.println(Label[l][0]+" ");
        }
        }
        else if(FileParser.invoke==2)
        {
            //System.out.println("LOGICAL BUG-----------");
        Label1 = new double[tr][1];
        for (l = 0; l < tr; l++) {
            //System.out.print(l+"  ");
            Label1[l][0] = find_class(test_dataset[l],vimp);
            //System.out.println(Label1[l][0]+" ");
        }
        }
        else if(FileParser.invoke==3)
        {
            //System.out.println("UI BUG-----------");
            Label2 = new double[tr][1];
        for (l = 0; l < tr; l++) {
            //System.out.print(l+"  ");
            Label2[l][0] = find_class(test_dataset[l],vimp);
            //System.out.println(Label2[l][0]+" ");
        }

        }
          else if(FileParser.invoke==4)
        {
            //System.out.println("UI BUG-----------");
            Label3 = new double[tr][1];
        for (l = 0; l < tr; l++) {
            //System.out.print(l+"  ");
            Label3[l][0] = find_class(test_dataset[l],vimp);
            //System.out.print("FINEEEEE");
            //System.out.println(Label3[l][0]+" ");
        }

        }
          else if(FileParser.invoke==5)
        {
            //System.out.println("UI BUG-----------");
            Label4 = new double[tr][1];
        for (l = 0; l < tr; l++) {
            //System.out.print(l+"  ");
            Label4[l][0] = find_class(test_dataset[l],vimp);
            //System.out.println(Label4[l][0]+" ");
        }

        }
                //buildfile();

        //print values of labels
    }


     public double find_class(double tds[],int vimp) {
        double clas = 0;

        clas = imp(lagrange_multiplier, training_class, training_data, tds) + bias_average;
        //System.out.println(clas);
        if(vimp==1)
        {

        if (ok != 12) {
            //System.out.println(clas);

            if (clas < 0) {

                return  clas;
            } else {
                return  clas;
            }
         } else if (clas < 0) {
            neg++;
            return  clas;
         } else {
            pos++;
            return clas;
         }
        }
        else
        {

             if (ok != 12) {
            if (clas < 0) {

                return -1;
            } else {
                return 1;
            }
          } else if (clas < 0) {
            neg++;
            return -1;
         } else {
            pos++;
            return 1;
         }
        }
    }


      public void buildfile(int invoke) {
        File file = new File(fn.getNumeric().getParent()+"/combine.txt");
        File tra_d = new File(fn.getNumeric().getParent()+"/TrainingLab.txt");

        String s = " ";
        int i = 0, count = 0;

        File lab_d = new File(fn.getNumeric().getParent()+"/TrainingUlab.txt");

        try {
            PrintWriter prwr = new PrintWriter(file);
            RandomAccessFile raf = new RandomAccessFile(tra_d, "r");
            while ((s = raf.readLine()) != null) {
                count++;
                prwr.print(s);
                prwr.println();
            }

            raf.close();
            //  prwr.close();

            raf = new RandomAccessFile(lab_d, "r");

            while ((s = raf.readLine()) != null) {
                count++;
                if(invoke==1)
                s += Label[i][0];
                else if(invoke==2)
                    s += Label1[i][0];
                else if(invoke==3)
                    s += Label2[i][0];
                else if(invoke==4)
                    s+=Label3[i][0];
                else if(invoke==5)
                    s+=Label4[i][0];
                //System.out.println(s);
                prwr.print(s);
                prwr.println();
                i++;
            }
            raf.close();
            prwr.close();
            //System.out.println(count);
        } catch (Exception ex) {
            System.out.println("Error in Main ");
            ex.printStackTrace();
        }

    }

       public void mainclasscoding()
    {
        double result=0;
        double lab,lab1,lab2,lab3 = 0,lab4;
        int i=0;
        //lineno=new FileGenerator().getlinenooftesting();

        final_class=new String[Label.length][1];
        //System.out.println("MY LINE"+lineno[2]);
       for(i=0;i<Label.length;i++)
       {
            result=0;
            lab=Label[i][0];
            lab1=Label1[i][0];
            lab2=Label2[i][0];
            lab3=Label3[i][0];
            lab4=Label4[i][0];
            //System.out.println(lab+" "+lab1+" "+lab2+" "+lab3+" "+lab4);
            result=Math.max(lab,lab1);
            result=Math.max(result, lab2);
            result=Math.max(result,lab3);
            result=Math.max(result, lab4);

          // System.out.println(result);
               if(result==Label[i][0])
               {
                  final_class[i][0]="FunctionalBug";
               }
               else if(result==Label1[i][0])
               {
                   final_class[i][0]="LogicalBug";
               }
               else if(result==Label2[i][0])
               {
                  final_class[i][0]="UIBug";
               }
               else if(result==Label3[i][0])
               {
                  final_class[i][0]="RequirementBug";
               }
               else if(result==Label4[i][0])
               {
                  final_class[i][0]="DesignBug";
               }

           //System.out.print(lab+" "+lab1+" "+lab2);
           //System.out.println(result);
            //System.out.println(final_class[i][0]);
        }

    }

    @SuppressWarnings("empty-statement")
public void accuracy(File f) throws FileNotFoundException, IOException {

        BufferedReader br=new BufferedReader(new FileReader(f));
        String s=null,value=null;
        int count=0;
        //int check=0;
        int p=0;
        String test_class[][]=new String[FileGenerator.lineno.length][1];
        StringTokenizer st;
       // while(!(s=br.readLine()).equalsIgnoreCase("@DATA"));

        while((s=br.readLine())!=null)
        {

            count++;

            for(int i=0;i<FileGenerator.lineno.length;i++)
            {
                //System.out.println(count+"=="+lineno[i]);
                if(count==FileGenerator.lineno[i])
                {   //System.out.println(count+"  "+FileGenerator.lineno[i]);

                    st=new StringTokenizer(s,",");
                    //System.out.println(s+"  "+st.countTokens());
                    int notokens=st.countTokens();
                    for(int j=1;j<=notokens;j++)
                    {

                    value=st.nextToken();
                    if(j==notokens)
                    {
                        //System.out.println(value);
                        test_class[p][0]=value;
                    }
                    }
                    p++;
                }
            }
        }
       // System.out.println(test_class.length);
        for(int i=0;i<final_class.length;i++)
        {
           // System.out.println(final_class[i][0]+"  "+test_class[i][0]);
            if(final_class[i][0].equals(test_class[i][0]))
                check++;

            if(final_class[i][0].equalsIgnoreCase("FunctionalBug"))
                fun++;
            else if(final_class[i][0].equalsIgnoreCase("LogicalBug"))
                log++;
            else if(final_class[i][0].equalsIgnoreCase("UIBug"))
                ui++;
            else if(final_class[i][0].equalsIgnoreCase("RequirementBug"))
                req++;
            else if(final_class[i][0].equalsIgnoreCase("DesignBug"))
                des++;
          //  else
             //   check--;
        }
        System.out.println(fun+" "+log+" "+ui+" "+req+" "+des);
        System.out.println("Accuracy "+check+" "+final_class.length);

     /*   for (int i = 0; i < Label.length; i++) {
            if (Label[i][0] == test_class[i][0]) {
                check++;
            } else {
                check--;
            }
            //System.out.println(Label[i][0]+"  "+test_class[i][0]);
        }
        System.out.println(">>>>>" + (check * 100) / tr + " " + check + " " + ok + " " + pos + " " + neg);


        // FinalGUI fg=new FinalGUI();
        // FileGenerator fi=new FileGenerator();
        //System.out.println(kfunc+" "+tr+" "+fi.getlabdata()+" "+fi.getunlabdata()+" "+check);

        //fg.showpanel(kfunc,tr,fi.getlabdata(),fi.getunlabdata(),(check*100)/tr);

        //PerGraph pg=new PerGraph();
        //pg.graph(check,tr,pos,neg,kfunc);
        */
    }

public void showGraph() {
        //PerGraph pg = new PerGraph();
        //pg.graph(check, tr, pos, neg, kfunc);
        check = 0;
        kfunc = "";
        tr = 0;
        pos = 0;
        neg = 0;
        ok = 0;
    }

    public double[][] KernelMatrix(double tra_d[][], int choice)//find which kernel has been chosen
    {

        int count = 0;
        double kernal[][] = new double[tra_d.length][tra_d.length];

        if (choice == 0) {
            for (int i = 0; i < tra_d.length; i++) {

                for (int j = 0; j < tra_d.length; j++) {
                    kernal[i][j] = polynomial(tra_d[i], tra_d[j]);

                }
            }
        } else if (choice == 1) {
            for (int i = 0; i < tra_d.length; i++) {

                for (int j = 0; j < tra_d.length; j++) {
                    kernal[i][j] = gaussian(tra_d[i], tra_d[j]);

                }
            }
        } else if (choice == 2) {
            for (int i = 0; i < tra_d.length; i++) {

                for (int j = 0; j < tra_d.length; j++) {
                    kernal[i][j] = exponential(tra_d[i], tra_d[j]);

                }
            }
        } else if (choice == 3) {
            for (int i = 0; i < tra_d.length; i++) {

                for (int j = 0; j < tra_d.length; j++) {
                    kernal[i][j] = multiquadric(tra_d[i], tra_d[j]);

                }
            }
        } else if (choice == 4) {
            for (int i = 0; i < tra_d.length; i++) {

                for (int j = 0; j < tra_d.length; j++) {
                    kernal[i][j] = power(tra_d[i], tra_d[j]);

                }
            }
        }



        return kernal;
    }

    public void setValues(int k, int c, int d, double g) {
        kc = k;
        if (c == 0) {
            coeff = 1;
        } else {
            coeff = c;
        }
        if (d == 0) {
            d = 2;
        } else {
            degree = d;
        }
        if (g == 0) {
            gamma = 1;
        } else {
            gamma = g;
        }

        if (kc == 0) {
            kfunc = "INVERSE MULTIQUADRIC";
        } else if (kc == 1) {
            kfunc = "RADIAL BASIS";
        } else if (kc == 2) {
            kfunc = "SIGMOID";
        } else if (kc == 3) {
            kfunc = "MULTIQUADRIC";
        } else {
            kfunc = "POWER";
        }
        //System.out.println(kc+" "+coeff+" "+degree+" "+gamma+" "+kfunc );
    }

    public double polynomial(double a[], double b[])//calculate the kernel func value
    {
        double res = 0;
         for (int i = 0; i < a.length; i++) {
            res += Math.pow(a[i] - b[i], 2);
        }
        res += Math.pow(coeff, 2);
        res = Math.sqrt(res);
        res=1/res;
        return res;
      /*  for (int i = 0; i < a.length; i++) {
            res += a[i] * b[i];
        }
        res = res * gamma;
        res = res + coeff;
        res = Math.pow(res, degree);
        return res;*/
    }

    public double gaussian(double a[], double b[]) {
        double res = 0;
        //System.out.println(a.length+" "+b.length);
        for (int i = 0; i < a.length; i++) {
            res += Math.pow(a[i] - b[i], 2);
        }
        res = Math.sqrt(res);
        res = Math.exp(-res * gamma);
        return res;
    }

    public double exponential(double a[], double b[]) {
        double res = 0;
        
        for (int i = 0; i < a.length; i++) {
            res += Math.pow(a[i] * b[i], 2);
        }
        res = gamma*res;
        res += coeff;
        res=Math.atan(res);
        return res;
        
    }

    public double multiquadric(double a[], double b[]) {
        double res = 0;
        for (int i = 0; i < a.length; i++) {
            res += Math.pow(a[i] - b[i], 2);
        }
        res += Math.pow(coeff, 2);
        res = Math.sqrt(res);
        return res;
    }

    public double power(double a[], double b[]) {
        double res = 0;
        //System.out.println(kc+"--"+coeff+"--"+degree+"--"+gamma);
        for (int i = 0; i < a.length; i++) {
            res += Math.pow(a[i] - b[i], 2);
        }
        res = Math.sqrt(res);
        res = -Math.pow(res, degree);
        return res;
    }

    public double imp(double lagrange[][], double classy[][], double t_d[][], double t[]) {
        double cal = 0.0;
       // System.out.println(t_d.length);
        for (int i = 0; i < t_d.length; i++) {
            cal += lagrange[i][0] * classy[i][0] * againKernel(t_d[i], t, kc);
        }
        return cal;
    }

    public double againKernel(double a[], double b[], int choice) {
        if (choice == 0) {
            return polynomial(a, b);
        } else if (choice == 1) {
            return gaussian(a, b);
        } else if (choice == 2) {
            return exponential(a, b);
        } else if (choice == 3) {
            return multiquadric(a, b);
        } else {
            return power(a, b);
        }
    }

    public double[][] MatrixMultiply(double y[][], double trany[][]) //multiply the output matrix and
    {

        int r = y.length;
        int c = trany[0].length;
        //  System.out.println(col);
        double mult[][] = new double[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < trany.length; k++) {

                    mult[i][j] += y[i][k] * trany[k][j];
                    //System.out.println(mult[i][j]);
                }
            }
        }
        return mult;
    }

    public double[][] kernel_mul_class(double ker[][], double muly[][]) //
    {

        int r = ker.length;
        int c = muly[0].length;
        double mul_res[][] = new double[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mul_res[i][j] = ker[i][j] * muly[i][j];
            }
        }

        return mul_res;
    }
    //  public static void main(String[] args) {
    //prepareData("E:/FinalProject/DataSet/TrainingLab.txt");
    //  }

    

}
