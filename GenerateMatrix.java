/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pro;

import java.io.RandomAccessFile;
import java.util.StringTokenizer;

/**
 *
 * @author Chayan
 */
public class GenerateMatrix {

    double fmat[][];
     int row=0;
     public static int col=0;
    int column[]=new int[500];

    public void genMat(String anyfile)
    {
        try {
            RandomAccessFile raf3 = new RandomAccessFile(anyfile, "r");

            String attval;
            String temp = raf3.readLine();
            StringTokenizer stt;
            int count=0,i=0,j=0,flag=0;

            while (temp != null) {
                temp = raf3.readLine();
                //System.out.println(temp);
                row++;
            }
            //System.out.println(i);

            raf3.seek(0);

            while((temp=raf3.readLine())!=null)
            {
            stt=new StringTokenizer(temp," ");

            while(stt.hasMoreTokens())
            {
                col++;
                stt.nextToken();
            }
             column[flag]=col;
            //System.out.println(col);
             flag++;
             col=0;
            }

           col=maxofcol();
           //System.out.println(row+" "+col);
           col++;
           fmat=new double[row][col];

           raf3.seek(0);

           while((temp=raf3.readLine())!=null)
           {
            //System.out.println(temp);

            stt=new StringTokenizer(temp," ");

            j=0;
            while(stt.hasMoreTokens())
            {
               attval=stt.nextToken();
               fmat[i][j]=Double.parseDouble(attval);
               j++;
            }
                i++;
           }

        /* System.out.println(fmat.length);
           for(i=0;i<fmat.length;i++)
           {
               for(j=0;j<col;j++)
               {
                   System.out.print(fmat[i][j]+" ");
               }
                System.out.println();
           }*/

            raf3.close();

        } catch (Exception ex) {
            System.out.println("ERROR in GenerateMatrix ");
            ex.printStackTrace();
        }

    }


    public int maxofcol()
    {
        int temp=0,index=0;
        temp=column[index];
        for(index=1;index<column.length;index++)
        {
            if(temp<column[index])
            {
                temp=column[index];
            }
        }
        return temp;
        //System.out.println("temp="+temp);
    }


    public double[][] getDataMatrix()
    {
        double send[][] = new double[row][col-1];
        for(int i=0;i<row;i++)
           {
               for(int j=0;j<col-1;j++)
               {
                  send[i][j]=fmat[i][j];
                  //System.out.print(send[i][j]+" ");
               }
               //System.out.println();
        }

        return send;
    }

    public double[][] getUnDataMatrix()
    {
        double send[][] = new double[row][col];
        
        for(int i=0;i<row;i++)
           {
               for(int j=0;j<col;j++)
               {
                  send[i][j]=fmat[i][j];
                 //System.out.print(send[i][j]+" ");
               }
              //System.out.println();
        }

        return send;
    }

     public double[][] getClassMatrix()
    {
        double out[][]=new double[row][1];
        int r[]=new int[500];
        int c[]=new int[500];
        int rcount=0,ccount=0;
        for(int i=0;i<row;i++)
           {
               for(int j=0;j<col;j++)
               {
                  if(fmat[i][j]==0)
                  {
                   r[rcount]=i;
                   c[ccount]=j;
                   //System.out.println(r[rcount]+" "+c[ccount]);
                   ccount++;rcount++;
                   break;
                  }
               }
             //  System.out.println();
        }
        rcount=0;
        ccount=0;
        int sm=0;
        for(int i=0;i<row;i++)
           {
                sm=0;
               for(int j=0;j<col;j++)
               {
                   if((i==r[rcount])&&j==(c[ccount]-1))
                   {
                    out[i][sm]=fmat[i][j];
                    //System.out.println(out[i][sm]);
                    ccount++;rcount++;sm++;
                    
                   }
                  
               }
              
               
        }

        return out;

    }

      public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

}
