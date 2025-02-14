package file;
/**
 * @author Mokshidul Momin
 */
import java.io.FileNotFoundException;
import java.util.Formatter;
public class Fw {
    public static void main(String[] args) {
        try{
            Formatter fl=new Formatter("E:/Java code123/Cse72.txt");
           
            fl.format("%s\r\n","MOKSHIDUL");
            fl.format("%s\r\n","MOMIN");
            fl.close();
            System.out.println("File is Written");
        }catch(FileNotFoundException z){
            System.out.println(z);
        }
    }
    
}
