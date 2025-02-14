package file;
/**
 * @author Mokshidul Momin
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Fr {
    public static void main(String[] args) {
        try{
            File fl =new File("E:/Java code123/Cse72.txt");
            Scanner inp=new Scanner(fl);
            while(inp.hasNext()){
                String name =inp.next();
                System.out.println("Data from Cse72 file : "+name);
            }
            inp.close();
        }catch(FileNotFoundException z){
            System.out.println("z");
        }
    }
    
}
