import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Interpolation
{


    public static void newtonInterpolation(double[] fx, double[] x)
    {
           
    }
    
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the file: ");
        String input = scan.nextLine();
        try {
            File file = new File(input);
            Scanner fileReader = new Scanner(file);
            String[] fxString = fileReader.nextLine().split("\\s+");
            String[] xString = fileReader.nextLine().split("\\s+");
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