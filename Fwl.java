package file;
/**
 * @author Mokshidul Momin
 */
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
public class Fwl {
    public static void main(String[] args) {
        String name;
        try{
            Formatter fl=new Formatter("E:/Java code123/Cse72.txt");
            Scanner inp=new Scanner(System.in);
            System.out.println("Enter the totall name :");
            int n=inp.nextInt();
            System.out.println("Enter the name");
            for(int i=0;i<n;i++){
                 name=inp.next();
                 fl.format("%s\r\n", name); 
            }
            System.out.println("File witre successfully");
            fl.close();
        }catch(FileNotFoundException z){
            System.out.println("z");
        }
    }
    
    
}
