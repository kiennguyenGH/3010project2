
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class project2
{

    public static double[][] getMatrix()
    {
        Scanner scan = new Scanner(System.in);
        int matrixSize = 0;
        while (matrixSize < 1 || matrixSize > 10)
        {
            System.out.println("How many rows is the matrix? (max 10)");
            matrixSize = scan.nextInt();
            scan.nextLine();
            if (matrixSize < 1 || matrixSize > 10)
            {
                System.out.println("Invalid input");
            }
            
        }
        double[][] rows = new double[matrixSize][matrixSize + 1];
        for (int i = 0; i < rows.length; i++)
        {
            String rowInput = "";
            System.out.println("Enter the coefficients for row " + (i + 1) + " (separated by spaces)");
            rowInput = scan.nextLine();
            String[] splitTest = rowInput.split("\\s+");
            while (splitTest.length != matrixSize + 1)
            {
                System.out.println("Invalid input");
                System.out.println("Enter the coefficients for row " + (i + 1) + " (separated by spaces)");
                rowInput = scan.nextLine();
                splitTest = rowInput.split("\\s+");
            }
            System.out.println();
            
            for (int k = 0; k < splitTest.length; k++)
            {
                rows[i][k] = Double.parseDouble(splitTest[k]);
            }
        }

        scan.close();
        return rows;
    }

    public static void jacobi(double[][] matrix)
    {
        double[][] theMatrix = matrix;
        double[] solution = new double[theMatrix.length];
    }

    public static void main(String[] args)
    {
       Scanner input = new Scanner(System.in);
       int theInput = 0;
       while (theInput != 1 && theInput != 2)
       {
            System.out.println("Enter:\n1. To input the equations\n2. To read from a file");
            theInput = input.nextInt();
            input.nextLine();
            if (theInput != 1 && theInput != 2)
            {
                System.out.println("Invalid input");
            }
       }
       System.out.println();
       
       //Get user matrix
       if (theInput == 1)
       {
            double[][] test = getMatrix();
            // computeGaussian(test);
        
       }
       //Read file
       else
       {
            System.out.println("Enter the name of the file: ");
            String fileName = input.nextLine();
            double[][] test;
            try {
                File file = new File(fileName);
                Scanner fileReader = new Scanner(file);
                String[] array = fileReader.nextLine().split("\\s+");
                test = new double[array.length-1][array.length];
                int count = 0;
                for (int i = 0; i < array.length; i++)
                {
                    test[count][i] = Double.parseDouble(array[i]);
                }
                count++;
                while(fileReader.hasNextLine())
                {
                    array = fileReader.nextLine().split("\\s+");
                    for (int i = 0; i < array.length; i++)
                    {
                        test[count][i] = Double.parseDouble(array[i]);
                    }
                    count++;
                }
                // computeGaussian(test);
                fileReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
            

       }
       input.close();
    }
}