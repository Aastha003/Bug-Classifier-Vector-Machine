/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pro;

import java.io.*;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chayan
 */
public class FileGenerator {

    static int labeled;
    static int ulabeled,test;
    static int totaldata;
    static public int lineno[]=new int[250];
    int lab=0,unlab=0,te=0;
    public void filegen(File nfile)
    {
        PrintWriter pw=null;
        try {
            File f = new File(nfile.getParent() + "/Testing.txt");
            File f1=new File(nfile.getParent()+"/TrainingUlab.txt");
            File f2=new File(nfile.getParent()+"/TrainingLab.txt");
            RandomAccessFile raf = new RandomAccessFile(nfile, "r");
            PrintWriter pw1=new PrintWriter(f1);
            PrintWriter pw2=new PrintWriter(f2);
            pw = new PrintWriter(f);
            String temp="";
            int con=1,index=0,tot=0;
            while((temp=raf.readLine())!=null)
            {
                //System.out.println(con);
                tot++;
                if(con%3==0)
                {
                    te++;
                    lineno[index]=con;
                    index++;
                    //System.out.println(con);
                    pw.write(temp);
                    pw.println();
                }
                else if(con%4==0)
                {
                    unlab++;
                    //System.out.println("unlab"+unlab);
                    int flag=1;
                    String s="";
                    StringTokenizer st=new StringTokenizer(temp," ");
                   int i= st.countTokens();
                   //System.out.println(temp);
                    while(flag<i)
                    {
                        flag++;
                        s+=st.nextToken()+" ";
                    }
                   //System.out.println(s);
                    pw1.write(s);
                    pw1.println();
                }
                else
                {
                    lab++;
                    pw2.write(temp);
                    pw2.println();
                }

                con++;
                
            }
            pw.close();
            pw1.close();
            pw2.close();
            raf.close();
            //setTotalData(tot);
            setlab(lab);
            setunlab(unlab);
            setTotalData(lab+unlab+te);
            settest(te);
           // System.out.println(lab+" "+unlab+" "+test);

        } catch (Exception ex) {
           System.out.println("Error In FileGenerator ");
           ex.printStackTrace();
        } finally {
            pw.close();
        }
    }

    public void settest(int te)
    {
        test=te;
    }

    public void setlab(int lab)
    {
        labeled=lab;
    }

    public void setunlab(int ulb)
    {
        ulabeled=ulb;
    }

    public void setTotalData(int tot)
    {
        totaldata=tot;
    }
    public int gettest()
    {
        return test;
    }

    public int getlabdata()
    {
        return labeled;
    }
    public int getunlabdata()
    {
        return ulabeled;
    }

    public int getTotaldata()
    {
        return totaldata;
    }

    public int[] getlinenooftesting()
    {
        return lineno;
    }




}
