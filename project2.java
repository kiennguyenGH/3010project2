
import java.util.Scanner;
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;

public class project2
{

    public static double[][] getMatrix(Scanner scan)
    {
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
        // scan.close();
        return rows;
    }

    public static void printArray(double[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void jacobi(double[][] matrix, Scanner scanner)
    {
        double[][] theMatrix = matrix;
        double[] solution = new double[theMatrix.length];
        boolean isDiagonal = true;
        for (int i = 0; i < theMatrix.length; i++)
        {
            double sum = 0;
            for(int k = 0; k < theMatrix[i].length-1; k++)
            {
                if (k == i)
                {
                    continue;
                }
                else
                {
                    sum += Math.abs(theMatrix[i][k]);
                }
            }
            if (sum > theMatrix[i][i])
            {
                isDiagonal = false;
                System.out.println("Cannot perform Jacobi Iteration: Matrix is not diagonally dominant");
                break;
            }
        }
        if (isDiagonal)
        {
            
            System.out.println("Enter the starting solution: ");
            String input = scanner.nextLine();
            // while (scan.hasNextLine())
            // {
            //     input = scan.nextLine();
            // }
            String[] split = input.split("\\s+");
            System.out.println("Enter the desired stopping error: ");
            double stoppingError = scanner.nextDouble();
            for (int i = 0; i < solution.length; i++)
            {
                solution[i] = Double.parseDouble(split[i]);
            }
            for (int i = 0; i < 50; i++)
            {
                
                for (int k = 0; k < solution.length; k++)
                {
                    double sum = 0;
                    for (int j = 0; j < theMatrix[k].length-1; j++)
                    {
                        if (k != j)
                        {
                            sum += (theMatrix[k][j] * solution[j]);
                        }
                    }
                    solution[k] = (theMatrix[k][theMatrix[k].length-1] - sum) / theMatrix[k][k];
                }
                printArray(solution);
            }
            
        }
        // scanner.close();
    }

    public static void main(String[] args)
    {
       Scanner input = new Scanner(System.in);
       int theInput = 0;
       while (theInput != 1 && theInput != 2)
       {
            System.out.println("Enter:\n1. To input the equations\n2. To read from a file");
            theInput = input.nextInt();
            // input.nextLine();
            if (theInput != 1 && theInput != 2)
            {
                System.out.println("Invalid input");
            }
       }
       System.out.println();
       
       //Get user matrix
       if (theInput == 1)
       {
            double[][] test = getMatrix(input);
            jacobi(test, input);
        
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