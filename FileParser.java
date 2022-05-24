
package pro;
import java.io.*;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileParser {

    static int i=0,j=0;
    static int index=2000;
    static String att[]=new String[index];
    static int val[]=new int[index];
    static int x=0;
    static int invoke=0;
    String s="",temp="",wrt="",forcount="";
    StringTokenizer st=new StringTokenizer(s);
    String stpword="about above across after afterwards again against all almost alone along already also although always  among amongst amount an and another any anyhow anyone anything anyway anywhere are around     as at back be became because become becomes becoming been before beforehand behind being below beside besides between beyond bill both bottom but by call can cannot cant could couldnot cry describe detail done down due during each eg eight either eleven elsewhere empty" +
            "enough etc even ever every everyone everything everywhere except few fifteen fifty fill find fire first five former formerly forty found four from front full further get give go had has hasn’t have he hence her here hereafter hereby herein hereupon hers herself him himself his how however hundred ice in inc indeed interest into is it its itself keep last latter latterly least less ltd made many may me meanwhile might mill mine more moreover most mostly move much must my" +
            "myself name namely neither never nevertheless next nine no nobody none no one nor not nothing now nowhere of off often on once one only onto or other others otherwise our ours ourselves out over own part per perhaps please put rather re same see seem seemed seeming seems serious several she should show side since sincere six sixty so some somehow someone something sometime sometimes somewhere still such system take ten than that the their them themselves then thence there"+
            "these they thick thin third those though three through throughout thru thus to together too top toward towards twelve twenty two under until up upon us very via was we well were what whatever when whence whenever where after whereas whereby wherein whereupon wherever whether which whither who whoever whole whom whose why will with within without would yet you your yours yourself yourselves the";


static File f;

/*  public static void main(String ar[])
    {
       FileParser fp=new FileParser();
      f=new File("F:/BugClassifier/Dataset/Training.txt");
       /* invoke=1;
       fp.input(f,invoke);
       invoke=2;
       fp.input(f,2);
       invoke=3;
       fp.input(f,3);
       invoke=4;
       fp.input(f,4);
       invoke=5;
       fp.input(f,5);
       FileNumeric fn=new FileNumeric();
       File f1=new File("F:/BugClassifier/Dataset/processed.txt");
       fn.num(att, val, f1,1);
       fn.num(att, val, f1,2);
       GenerateMatrix gm=new GenerateMatrix();
       gm.genMat("F:/BugClassifier/Dataset/numeric.txt");

       FileGenerator fg=new FileGenerator();
       File f2=new File("F:/BugClassifier/Dataset/numeric.txt");
       fg.filegen(f2);
       
      for(int t=0;t<att.length;t++)
        {
           if(att[t]!=null)
            System.out.println(att[t]+" "+val[t]);
        }*/
   //   fp.run();
 //   }

    @SuppressWarnings("empty-statement")
    public void input(File dfile,int invoke)
    {
        try {
            String imp="";
            File ok=new File(dfile.getParent()+"/processed.txt");
            //PrintWriter pw=new PrintWriter(ok);
            BufferedWriter writer = new BufferedWriter(
                new FileWriter(ok,true)) ;
            
            RandomAccessFile raf = new RandomAccessFile(dfile,"r");
            if(invoke==1)
            {
            imp="/";
            }
            else if(invoke==2)
            {
                while(!(raf.readLine().equals("/")));
                imp="//";
            }
            else if(invoke==3)
            {
                while(!(raf.readLine().equals("//")));
                imp="///";
            }
            else if(invoke==4)
            {
                while(!(raf.readLine().equals("///")));
                imp="////";
            }
            else if(invoke==5)
            {
                while(!(raf.readLine().equals("////")));
                imp="";
            }
            

            while(!(s=raf.readLine()).equals(imp))
            {
                st=new StringTokenizer(s," ");
                while(st.hasMoreTokens())
                {
                    temp=st.nextToken();
                    //System.out.println(temp);
                    if(temp.equals("/")||temp.equals("//")||temp.equals("///")||temp.equals("////"))
                    {
                    }
                    else
                    {
                    if(stpword.contains(temp))
                    {
                        temp="";
                    }
                    else if(temp.contains(","))
                    {
                        StringTokenizer stok=new StringTokenizer(temp,",");
                        while(stok.hasMoreTokens())
                        {
                            att[i]=stok.nextToken();
                            wrt+=att[i]+",";
                            i++;
                        }
                    }
                    else 
                    {
                        att[i]=temp;
                        wrt+=temp+",";
                        i++;
                    }
                   }
                }
                //System.out.println(wrt);
                writer.write(wrt);
                writer.newLine();
                forcount+=wrt;
                wrt="";
                
            }
            count(invoke);
           // System.out.println(invoke+"---------------");
           //System.out.println(forcount);
            forcount="";
            writer.close();
            raf.close();
            

          /*  for(int p=0;p<att.length;p++)
            {
                if(att[p]!=null)
                System.out.println(att[p]);
            }*/

        } catch (Exception ex) {
            System.out.println("Error In FileParser ");
            ex.printStackTrace();
        }
    }

    public void count(int invoke)
    {
        int counter=0;
       // System.out.println(invoke+"-------- ----"+j);
        for(int p=j;p<att.length;p++)
            {
               counter=0;
               
                StringTokenizer stt=new StringTokenizer(forcount,",");
                if(att[p]!=null)
                {
                    j++;
                    //System.out.println(att[p]);
                    //System.out.println(invoke+" "+p);
                    while(stt.hasMoreTokens())
                    {
                      if(att[p].equalsIgnoreCase(stt.nextToken()))
                      {
                                             
                              counter++;
                           val[p]=counter;
                      }
                       
                      
                  }
                }
                
        }


       // for(int t=0;t<att.length;t++)
      //  {
      //     if(att[t]!=null)
      //      System.out.println(att[t]+" "+val[t]);
      //  }
           
    }

 public int getnoofatt()//returns the no. of attributes
   {
       return 0;
   }

   public String[] getAttlist()
   {
       return att;
   }

   public int[] getAttval()
   {
       return val;
   }


   


   ///test func
  public void run(int coeff,int kc,int gamma,int degree,File f)
   {
      //f=new File("F:/BugClassifier/Dataset/Training.txt");
       //int kc=1,coeff=1,degree=2,gamma=1;
       FileParser fp=new FileParser();
       invoke=1;
       fp.input(f,invoke);
       invoke=2;
       fp.input(f,2);
       invoke=3;
       fp.input(f,3);
       invoke=4;
       fp.input(f,4);
       invoke=5;
       fp.input(f,5);
       invoke=1;
       FileNumeric fn=new FileNumeric();
       File t=new File(f.getParent()+"/processed.txt");
       fn.num(att, val, t,1);
       //FileNumeric fn=new FileNumeric();
       File fl=fn.getNumeric();
      // System.out.println(fl+" "+fl.getParent());
       new FileGenerator().filegen(fl);
       Main mn=new Main();
        mn.prepareData(fl.getParent()+"/TrainingLab.txt",kc,coeff,degree,gamma);
        mn.buildfile(invoke);
         for(int count=0;count<10;count++)
              {
                mn.prepareData(fl.getParent()+"/combine.txt",kc,coeff,degree,gamma);
                mn.buildfile(invoke);
               // m.S3VM_Unalabeled("E:/FinalProject/DataSet/Testing.txt",1);
               // m.accuracy();
              }
         mn.S3VM_Unalabeled(fl.getParent()+"/Testing.txt",1);



       invoke=2;
       //fp.input(f,2);
      //fp.input(f,invoke);

       fn.num(att, val, t,2);
       fl=fn.getNumeric();
       new FileGenerator().filegen(fl);
       mn.prepareData(fl.getParent()+"/TrainingLab.txt",kc,coeff,degree,gamma);
        mn.buildfile(invoke);
         for(int count=0;count<10;count++)
              {
                mn.prepareData(fl.getParent()+"/combine.txt",kc,coeff,degree,gamma);
                mn.buildfile(invoke);
               // m.S3VM_Unalabeled("E:/FinalProject/DataSet/Testing.txt",1);
               // m.accuracy();
              }
         mn.S3VM_Unalabeled(fl.getParent()+"/Testing.txt",1);




       invoke=3;
       //fp.input(f,3);
       //fp.input(f,invoke);
       fn.num(att, val, t,3);
        fl=fn.getNumeric();
       new FileGenerator().filegen(fl);
       mn.prepareData(fl.getParent()+"/TrainingLab.txt",kc,coeff,degree,gamma);
        mn.buildfile(invoke);
         for(int count=0;count<10;count++)
              {
                mn.prepareData(fl.getParent()+"/combine.txt",kc,coeff,degree,gamma);
                mn.buildfile(invoke);
               // m.S3VM_Unalabeled("E:/FinalProject/DataSet/Testing.txt",1);
               // m.accuracy();
              }
         mn.S3VM_Unalabeled(fl.getParent()+"/Testing.txt",1);




       invoke=4;
       //fp.input(f,4);
       //fp.input(f,invoke);
       fn.num(att, val, t,4);
        fl=fn.getNumeric();
       new FileGenerator().filegen(fl);
       mn.prepareData(fl.getParent()+"/TrainingLab.txt",kc,coeff,degree,gamma);
        mn.buildfile(invoke);
         for(int count=0;count<10;count++)
              {
                mn.prepareData(fl.getParent()+"/combine.txt",kc,coeff,degree,gamma);
                mn.buildfile(invoke);
               // m.S3VM_Unalabeled("E:/FinalProject/DataSet/Testing.txt",1);
               // m.accuracy();
              }
         mn.S3VM_Unalabeled(fl.getParent()+"/Testing.txt",1);



       invoke=5;
       //fp.input(f,5);
       //fp.input(f,invoke);
       fn.num(att, val, t,5);
        fl=fn.getNumeric();
       new FileGenerator().filegen(fl);
       mn.prepareData(fl.getParent()+"/TrainingLab.txt",kc,coeff,degree,gamma);
        mn.buildfile(invoke);
         for(int count=0;count<10;count++)
              {
                mn.prepareData(fl.getParent()+"/combine.txt",kc,coeff,degree,gamma);
                mn.buildfile(invoke);
               // m.S3VM_Unalabeled("E:/FinalProject/DataSet/Testing.txt",1);
               // m.accuracy();
              }
         mn.S3VM_Unalabeled(fl.getParent()+"/Testing.txt",1);

         File acc=new File(fl.getParent()+"/processed.txt");
        try {
            mn.mainclasscoding();
            mn.accuracy(acc);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        }


   }
}
