//package ppl_assignment;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 *
 * @author root
 */
public class Q1 {
    public static void main(String[] args) throws Exception {
        int p=10;
        int q = 20;
        ArrayList<Girls> allgirl=new ArrayList<Girls>();
        while(p>0) {
            StringBuilder sb = new StringBuilder();
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            Random rand = new Random();
            for (int i = 0; i < 5; i++) {
                char c = chars[rand.nextInt(chars.length)];
                sb.append(c);
            }
            String name = sb.toString();
            Random rand1 = new Random();
            int intelligence_level=rand1.nextInt(10) + 1;
            int maintenance_budget=rand1.nextInt(4000) + 1000;
            int attractiveness=rand1.nextInt(10) + 1;
            int typeofgirl=rand1.nextInt(3) + 1;
            Girls g=new Girls(name,intelligence_level,maintenance_budget,attractiveness,typeofgirl);
            allgirl.add(g);
            p--;
        }
        PrintWriter pw = new PrintWriter(new File("girl.csv"));
        StringBuilder sb1 = new StringBuilder();
        for(Girls x:allgirl) {
            sb1.append(x.name+","+x.intelligence_level+","+x.maintenance_budget+","+x.attractiveness+","+x.typeofgirl+"\n");     
        }
        pw.write(sb1.toString());
        pw.close();
        
        ArrayList<Boys> allboy=new ArrayList<Boys>();
        while(q>0) {
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            StringBuilder sb2 = new StringBuilder();
            Random rand = new Random();
            for (int i = 0; i < 5; i++) {
                char c = chars[rand.nextInt(chars.length)];
                sb2.append(c);
            }
            String name = sb2.toString();
            Random rand1 = new Random();
            int intelligence_level=rand1.nextInt(10) + 1;
            int budget=rand1.nextInt(5000) + 1000;
            int attractiveness=rand1.nextInt(10) + 1;
            Boys b=new Boys(name,intelligence_level,budget,attractiveness);
            allboy.add(b);
            q--;
        }
        PrintWriter pw2 = new PrintWriter(new File("boy.csv"));
        StringBuilder sb2 = new StringBuilder();
        for(Boys y:allboy) {
            sb2.append(y.name+","+y.intelligence_level+","+y.budget+","+y.attractiveness+"\n");     
        }
        pw2.write(sb2.toString());
        pw2.close();
        
        int index=-1,max=0;
        ArrayList<Boys> boylist=new ArrayList<Boys>(); 
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader("boy.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] str = line.split(cvsSplitBy);
                String n=str[0];
                int intelligence_level=Integer.parseInt(str[1]);
                int budget=Integer.parseInt(str[2]);
                int attractiveness=Integer.parseInt(str[3]);
                Boys b=new Boys(n,intelligence_level,budget,attractiveness);
                boylist.add(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ArrayList<Girls> girllist=new ArrayList<Girls>();
        String lin = "";
        String cvsSplit = ",";
        try (BufferedReader br1 = new BufferedReader(new FileReader("girl.csv"))) {
            while ((lin = br1.readLine()) != null) {
                String[] str = lin.split(cvsSplit);
                String n=str[0];
                int intelligence_level=Integer.parseInt(str[1]);
                int maintenance_budget=Integer.parseInt(str[2]);
                int attractiveness=Integer.parseInt(str[3]);
                int typeofgirl=Integer.parseInt(str[4]);
                Girls g=new Girls(n,intelligence_level,maintenance_budget,attractiveness,typeofgirl);
                girllist.add(g);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    try {
        for(Girls x:girllist) {
            int c=0;
            BufferedWriter bw=new BufferedWriter(new FileWriter("log.csv"));
            String str ="";
            String timeStamp1 = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
            String timeStamp2=new SimpleDateFormat("hh.mm.ss").format(new Date());
            if(x.typeofgirl==1) {
                max=0;
                for(Boys y:boylist) {
                    if(y.attractiveness>=max && x.maintenance_budget<=y.budget && y.available==0) {
                        max=y.attractiveness;
                        index=boylist.indexOf(y);
                    }   
                }
                str=x.name+" is committed to "+boylist.get(index).name + " on "+timeStamp1+" at "+timeStamp2;
                //System.out.println(x.name+" is committed to "+boylist.get(index).name + " on "+timeStamp1+" at "+timeStamp2);
                boylist.get(index).available=1;
            }
            else if(x.typeofgirl==2) {
                max=0;
                for(Boys y:boylist) {
                    if(y.budget>=max && x.maintenance_budget<=y.budget && y.available==0) {
                        max=y.budget;
                        index=boylist.indexOf(y);
                    }
                }
             str=x.name+" is committed to "+boylist.get(index).name + " on "+timeStamp1+" at "+timeStamp2;
                //System.out.println(x.name+" is committed to "+boylist.get(index).name + " on "+timeStamp1+" at "+timeStamp2);
                boylist.get(index).available=1;
            }
            else if(x.typeofgirl==3) {
                 max=0;
                for(Boys y:boylist) {
                    if(y.intelligence_level>=max && x.maintenance_budget<=y.budget && y.available==0) {
                        max=y.intelligence_level;
                        index=boylist.indexOf(y);
                    }   
                }
                str=x.name+" is committed to "+boylist.get(index).name + " on "+timeStamp1+" at "+timeStamp2;
                //System.out.println(x.name+" is committed to "+boylist.get(index).name + " on "+timeStamp1+" at "+timeStamp2);
                boylist.get(index).available=1;
            }
            //str=x.name+" is committed to "+boylist.get(index).name + " on "+timeStamp1+" at "+timeStamp2;
            System.out.println(str);
            bw.write(str);
            bw.flush();
            //bw.close();
        }  
        
    }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
