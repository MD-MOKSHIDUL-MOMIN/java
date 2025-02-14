
package file;

/**
 *
 * @author Mokshidul Momin
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

public class FibonacciInput {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try { 
          
             System.out.println("Enter a list of numbers separated by commas:");
        
            String input = scanner.nextLine();   
            String[] numbersStr = input.split(",");
            ArrayList<Integer> numbers = new ArrayList<>();
            for (String num : numbersStr) {
                numbers.add(Integer.parseInt(num.trim()));
            }
  

            FileWriter fibonacciFile = new FileWriter("fibonacci_numbers.txt");
            FileWriter otherFile = new FileWriter("other_numbers.txt");

            for (int num : numbers) {
                if (isFibonacci(num)) {
                    fibonacciFile.write(num + "\n");
                } else {
                    otherFile.write(num + "\n");
                }
            }

            fibonacciFile.close();
            otherFile.close();

            System.out.println("Files created: fibonacci_numbers.txt and other_numbers.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static boolean isFibonacci(int num) {
        int a = 0, b = 1;

        while (b < num) {
            int temp = b;
            b = a + b;
            a = temp;
        }

        return b == num;
    }
}