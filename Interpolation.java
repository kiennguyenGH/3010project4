import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
public class Interpolation
{

    static class Node
    {
        double coefficient, power;
        Node next;
    };

    static Node addNode(Node start, double coefficient, double power)
    {
        Node node = new Node();
        node.coefficient = coefficient;
        node.power = power;
        node.next = null;
        if (start == null)
        {
            return node;
        }

        Node pointer = start;
        while (pointer.next != null)
        {
            pointer = pointer.next;
        }
        pointer.next = node;
        return start;
    }
    
    static void printPolynomial(Node poly)
    {
        while (poly.next != null)
        {
            System.out.print(poly.coefficient + "x^" + poly.power + " + ");
            poly = poly.next;
        }
        System.out.println(poly.coefficient);
    }

    static void combineDegree(Node poly)
    {
        Node pointer1 = poly;
        Node pointer2;
        // Node temp;
        while (pointer1 != null && pointer1.next != null)
        {
            pointer2 = pointer1;
            while (pointer2.next != null)
            {
                if (pointer1.power == pointer2.power)
                {
                    pointer1.coefficient = pointer1.coefficient + pointer2.coefficient;
                    // temp = pointer2.next;
                    pointer2.next = pointer2.next.next;
                }
                else
                {
                    pointer2 = pointer2.next;
                }
            }
            pointer1 = pointer1.next;
        }
    }

    static Node multiplyPolynomial(Node polynomial1, Node polynomial2, Node target)
    {
        Node pointer1 = polynomial1;
        Node pointer2 = polynomial2;
        while (pointer1 != null)
        {
            while (pointer2 != null)
            {
                double coefficient = pointer1.coefficient * pointer2.coefficient;
                double power = pointer1.power + pointer2.power;
                target = addNode(target, coefficient, power);
                pointer2 = pointer2.next;
            }
            pointer2 = polynomial2;
            pointer1 = pointer1.next;
        }
        combineDegree(target);
        return target;

    }

    public static void printArray(double[] array, int index)
    {
        for (int i = index; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        
    }

    public static void simplifiedInterpolation(double[] fx, double[] x)
    {
        Node[][] polynomials = new Node[fx.length][fx.length];
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
            polynomials[i][0] = new Node();
            polynomials[i][0].coefficient = divide;
            polynomials[i][0].power = 0;
            polynomials[i][0].next = null;
            int count = 1;
            for (int k = 0; k < fx.length; k++)
            {
                if (k != i)
                {
                    polynomials[i][count] = new Node();
                    polynomials[i][count].coefficient = 1.0;
                    polynomials[i][count].power = 1;
                    polynomials[i][count].next = null;
                    polynomials[i][count] = addNode(polynomials[i][count], x[k], 0);

                    count++;
                }   
            }
        }   
        Node[] simplified = new Node[fx.length];
        for (int i = 0; i < simplified.length; i++)
        {
            // for (int k = 0)
        }
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
            lagrangeInterpolation(fx, x);
            // simplifiedInterpolation(fx, x);


        fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        scan.close();
    }
}