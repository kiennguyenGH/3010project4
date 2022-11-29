import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Interpolation
{
    
    public static void printArray(double[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        
    }

    public static void newtonInterpolation(double[] fx, double[] x)
    {
        double[] solution = new double[fx.length];
        for (int i = 0; i < fx.length; i++)
        {
            solution[i] = fx[i];
        }
        printArray(solution);
        for (int j = 1; j < fx.length; j++)
        {
            for (int i = fx.length-1; i >= j; i--)
            {
                solution[i] = (solution[i] - solution[i-1])/(x[i] - x[i-j]);
            }
            printArray(solution);
        }        
    }
    
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the file: ");
        String input = scan.nextLine();
        try {
            File file = new File(input);
            Scanner fileReader = new Scanner(file);
            String[] xString = fileReader.nextLine().split("\\s+");
            String[] fxString = fileReader.nextLine().split("\\s+");
            double[] fx = new double [fxString.length];
            double[] x = new double [xString.length];
            for (int i = 0 ;i < fxString.length; i++)
            {
                fx[i] = Double.parseDouble(fxString[i]);
                x[i] = Double.parseDouble(xString[i]);
            }
            newtonInterpolation(fx, x);


        fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        scan.close();
    }
}