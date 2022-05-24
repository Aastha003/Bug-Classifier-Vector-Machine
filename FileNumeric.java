/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pro;


import java.io.*;
import java.util.StringTokenizer;

public class FileNumeric {
    public static File f2;
    PrintWriter pw;

    


     public void num(String att[],int val[],File dfile,int invoke)
     {
        try {
            f2 = new File(dfile.getParent() + "/Numeric.txt");
            pw = new PrintWriter(f2);
            RandomAccessFile raf1=new RandomAccessFile(dfile.getPath(),"r");
            String s="",tmp="",append="";
            int c=0;
            StringTokenizer st;
            while((s=raf1.readLine())!=null)
            {
                st=new StringTokenizer(s,",");
                while(st.hasMoreTokens())
                {
                    tmp=st.nextToken();
                    //System.out.println(tmp);
                    if(tmp!=null)
                    {
                        
                     for(int i=0;i<att.length;i++)
                     {
                         //System.out.println(FileParser.val[i]);
                        if(att[i]!=null)
                        {
                        if(invoke==1)
                        {
                          if(tmp.equalsIgnoreCase("FunctionalBug"))
                          {
                              c=1;
                              append=append+c+" ";
                              break;
                          }
         else if(tmp.equalsIgnoreCase("LogicalBug")||tmp.equalsIgnoreCase("UIBug")||
             tmp.equalsIgnoreCase("DesignBug")||tmp.equalsIgnoreCase("RequirementBug"))
                        {
                            c=-1;
                            append=append+c+" ";
                            break;
                        }
         else  if(tmp.equalsIgnoreCase(att[i]))
                          {
                              //System.out.println(val[i]);
                              c=val[i];
                              append=append+c+" ";
                              //System.out.println(append);
                              break;
                          }
                         }///1st invoke end
                 
                        ///2nd invoke

                        if(invoke==2)
                        {
                          if(tmp.equalsIgnoreCase("LogicalBug"))
                          {
                              c=1;
                              append=append+c+" ";
                              break;
                          }
         else if(tmp.equalsIgnoreCase("FunctionalBug")||tmp.equalsIgnoreCase("UIBug")||
             tmp.equalsIgnoreCase("DesignBug")||tmp.equalsIgnoreCase("RequirementBug"))
                        {
                            c=-1;
                            append=append+c+" ";
                            break;
                        }
         else  if(tmp.equalsIgnoreCase(att[i]))
                          {
                              //System.out.println(val[i]);
                              c=val[i];
                              append=append+c+" ";
                              //System.out.println(append);
                              break;
                          }
                         }///end of 2nd invoke

                         ///start of 3rd invoke
                         if(invoke==3)
                        {
                          if(tmp.equalsIgnoreCase("UIBug"))
                          {
                              c=1;
                              append=append+c+" ";
                              break;
                          }
         else if(tmp.equalsIgnoreCase("FunctionalBug")||tmp.equalsIgnoreCase("LogicalBug")||
             tmp.equalsIgnoreCase("DesignBug")||tmp.equalsIgnoreCase("RequirementBug"))
                        {
                            c=-1;
                            append=append+c+" ";
                            break;
                        }
         else  if(tmp.equalsIgnoreCase(att[i]))
                          {
                              //System.out.println(val[i]);
                              c=val[i];
                              append=append+c+" ";
                              //System.out.println(append);
                              break;
                          }
                         }//end of 3rd invoke

                        ///begin of 4th invoke
                 
               if(invoke==4)
                        {
                          if(tmp.equalsIgnoreCase("RequirementBug"))
                          {
                              c=1;
                              append=append+c+" ";
                              break;
                          }
         else if(tmp.equalsIgnoreCase("FunctionalBug")||tmp.equalsIgnoreCase("LogicalBug")||
             tmp.equalsIgnoreCase("DesignBug")||tmp.equalsIgnoreCase("UIBug"))
                        {
                            c=-1;
                            append=append+c+" ";
                            break;
                        }
         else  if(tmp.equalsIgnoreCase(att[i]))
                          {
                              //System.out.println(val[i]);
                              c=val[i];
                              append=append+c+" ";
                              //System.out.println(append);
                              break;
                          }
                         }
                            ///end of invoke 4
                            ///begin of invoke5

                if(invoke==5)
                        {
                          if(tmp.equalsIgnoreCase("DesignBug"))
                          {
                              c=1;
                              append=append+c+" ";
                              break;
                          }
         else if(tmp.equalsIgnoreCase("FunctionalBug")||tmp.equalsIgnoreCase("LogicalBug")||
             tmp.equalsIgnoreCase("RequirementBug")||tmp.equalsIgnoreCase("UIBug"))
                        {
                            c=-1;
                            append=append+c+" ";
                            break;
                        }
         else  if(tmp.equalsIgnoreCase(att[i]))
                          {
                              //System.out.println(val[i]);
                              c=val[i];
                              append=append+c+" ";
                              //System.out.println(append);
                              break;
                          }
                         }

            }



                      }
                    }
                }
                pw.write(append);
                pw.println();
                append="";
            }

            pw.close();
            raf1.close();
        }
        catch (Exception ex) {
           System.out.println("Error In FileNumeric "+ex);
        }
     }

      public File getNumeric()
    {
        //System.out.println(f2);
        return f2;

    }
}
