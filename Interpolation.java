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

    public static void printMatrix(double[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int k = 0; k < matrix[i].length; k++)
            {
                System.out.print(matrix[i][k] + " ");
            }
            System.out.println();
        }
    }

    public static void printPolynomial(double[] polynomial)
    {
        for (int i = polynomial.length -1; i >= 0; i--)
        {
            if (polynomial[i] != 0 && i > 1)
            {
                System.out.print(polynomial[i] + "x^" + i + " + ");
            } 
            else if (i == 1 && polynomial[1] != 0)
            {
                System.out.print(polynomial[i] + "x");
                if (polynomial[0] != 0)
                {
                    System.out.print(" + ");
                }
            }
            else if (i == 0 && polynomial[i] != 0)
            {
                System.out.print(polynomial[i]);
            }
        }
    }

    public static double[] multiply(double[] polynomial1, double[] polynomial2)
    {
        double[] product = new double[polynomial1.length + polynomial2.length -1];
        for (int i = 0; i < product.length; i++)
        {
            product[i] = 0;
        }
        for (int i = 0; i < polynomial1.length; i++)
        {
            for (int k = 0; k < polynomial2.length; k++)
            {
                product[i + k] += polynomial1[i] * polynomial2[k];
            }
        }
        return product;
    }

    public static void simplifiedInterpolation(double[] fx, double[] x)
    {
        double[][][] polynomials = new double[fx.length][fx.length][2];
        for (int i = 0; i < fx.length; i++)
        {
            double divide = fx[i];
            for (int k = 0; k < fx.length; k++)
            {
                if (k != i)
                {
                    divide /= (x[i] - x[k]);
                }
            }
            polynomials[i][0][0] = divide;
            polynomials[i][0][1] = 0;
            for (int k = 1; k < fx.length; k++)
            {
                polynomials[i][k][1] = 1;
            }
            int count = 1;
            for (int k = 0; k < fx.length; k++)
            {
                if (k != i)
                {
                    polynomials[i][count][0] = 0 - x[k];
                    count++;
                }
            }
        }
        // for (int i = 0; i < polynomials.length; i++)
        // {
        //     for (int k = 0; k < polynomials.length; k++)
        //     {
        //         printPolynomial(polynomials[i][k]);
        //         System.out.println();
        //     }
        // }
        double[][] simplified = new double[fx.length][];
        // simplified[0] = polynomials[0][0];
        // simplified[0] = multiply(simplified[0], polynomials[0][1]);
        for (int i = 0; i < simplified.length; i++)
        {
            simplified[i] = polynomials[i][0];
            for (int k = 1; k < simplified.length; k++)
            {
                simplified[i] = multiply(simplified[i], polynomials[i][k]);
            }
        }
        // printMatrix(simplified);
        double[] finalPoly = new double[simplified.length];
        for (int i = 0; i < finalPoly.length; i++)
        {
            finalPoly[i] = 0;
        }
        for (int i = 0; i < simplified.length; i++)
        {
            for (int k = 0; k < simplified.length; k++)
            {
                finalPoly[i] += simplified[k][i];
            }
        }
        printArray(finalPoly, 0);
        
    }

    public static void lagrangeInterpolation(double[] fx, double[] x)
    {
        for (int i = 0; i < fx.length; i++)
        {
            double divide = fx[i];
            for (int k = 0; k < fx.length; k++)
            {
                if (k != i)
                {
                    divide /= (x[i] - x[k]);
                }
            }
            if (i > 0)
            {
                if (divide > 0)
                {
                    System.out.print(" + " + divide);
                }
                else
                {
                    System.out.print(" - " + Math.abs(divide));
                }
            }
            else
            {
                System.out.print(divide);
            }
            for (int k = 0; k < fx.length; k++)
            {
                
                if (k != i)
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
                    System.out.print(")");
                }   
            }
        }
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
            // newtonInterpolation(fx, x);
            // lagrangeInterpolation(fx, x);
            simplifiedInterpolation(fx, x);


        fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        scan.close();
    }
}