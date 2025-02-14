package file;
/**
 * @author Mokshidul Momin
 */
import java.io.File;
import java.io.IOException;

public class Fc {
    public static void main(String[] args) {
        File dir = new File("E:/Java code123");
        dir.mkdir();
        String l = dir.getAbsolutePath();
        System.out.println( "Location :"+l);
        File fl=new File(l+"/Cse72.txt");
        File fe =new File(l+"/Roll21.txt");
        try{
            fl.createNewFile();
            fe.createNewFile();
        }catch(IOException z){
            System.out.println(z);
        }
        if(fl.exists()){
            System.out.println("Fl file is found");
        }
        fe.delete();
        if(fe.exists()){
            System.out.println("fe file is found");
        }
        else{
            System.out.println("fe file is not found");
        }
    }
    
}


