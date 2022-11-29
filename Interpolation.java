import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
public class Interpolation
{
    
    public static void printArray(double[] array, int index)
    {
        for (int i = index; i < array.length; i++)
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
        printArray(solution, 0);
        for (int j = 1; j < fx.length; j++)
        {
            for (int i = fx.length-1; i >= j; i--)
            {
                solution[i] = (solution[i] - solution[i-1])/(x[i] - x[i-j]);
            }
            printArray(solution, j);
        }
        System.out.print(solution[0] + " ");
        for (int i = 1; i < solution.length; i++)
        {
            if (solution[i] > 0)
            {
                System.out.print("+ " + Math.abs(solution[i]));
            }
            else
            {
                System.out.print("- " + Math.abs(solution[i]));
            }
            for (int k = 0; k < i; k++)
            {
                System.out.print("(x ");
                if (x[k] > 0)
                {
                    System.out.print("- " + Math.abs(x[k]));
                }
                else if (x[k] < 0)
                {
                    System.out.print("+ " + Math.abs(x[k]));
                }
                System.out.print(") ");
            }
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