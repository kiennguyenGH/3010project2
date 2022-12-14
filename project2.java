
import java.util.Scanner;
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;

public class project2
{

    public static double EuclideanNorm(double[] array, double[] array2)
    {
        double norm = 0;
        double norm2 = 0;
        for (int i = 0; i < array.length; i++)
        {
            norm += Math.pow((array[i] - array2[i]), 2);
        }
        for (int i = 0; i < array.length; i++)
        {
            norm2 += Math.pow(array[i], 2);
        }
        norm = Math.sqrt(norm);
        norm2 = Math.sqrt(norm2);
        return (norm/norm2);
    }

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
        System.out.print("[ ");
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.print("]");
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
            String[] split = input.split("\\s+");
            while (split.length != theMatrix.length)
            {
                System.out.println("Invalid input");
                System.out.println("Enter the starting solution: ");
                input = scanner.nextLine();
                split = input.split("\\s+");
            }
            System.out.println("Enter the desired stopping error: ");
            double stoppingError = scanner.nextDouble();
            for (int i = 0; i < solution.length; i++)
            {
                solution[i] = Double.parseDouble(split[i]);
            }
            for (int i = 0; i < 50; i++)
            {
                double[] solution2 = solution.clone();
                
                //Compute x values for each iteration
                for (int k = 0; k < solution.length; k++)
                {
                    double sum = theMatrix[k][theMatrix.length];
                    // int count = 0;
                    //Calculate sum
                    for (int j = 0; j < solution.length; j++)
                    {
                        if (k != j)
                        {
                            // System.out.println(sum + " " + solution2[j] + " " + theMatrix[k][j]);
                            sum = (sum - (solution2[j] * theMatrix[k][j]));
                            // count++; 
                        }
                        
                    }
                    // System.out.println(sum);
                    solution[k] = (sum/theMatrix[k][k]);
                    
                    // System.out.println(sum);
                }
                
                printArray(solution);
                if (EuclideanNorm(solution, solution2) < stoppingError)
                {
                    break;
                }
            }
            
        }
    }

    public static void GaussSeidel(double[][] matrix, Scanner scanner)
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
                System.out.println("Cannot perform Gauss-Seidel Method: Matrix is not diagonally dominant");
                break;
            }
        }
        if (isDiagonal)
        {
            
            System.out.println("Enter the starting solution: ");
            String input = scanner.nextLine();
            String[] split = input.split("\\s+");
            while (split.length != theMatrix.length)
            {
                System.out.println("Invalid input");
                System.out.println("Enter the starting solution: ");
                input = scanner.nextLine();
                split = input.split("\\s+");
            }
            System.out.println("Enter the desired stopping error: ");
            double stoppingError = scanner.nextDouble();
            for (int i = 0; i < solution.length; i++)
            {
                solution[i] = Double.parseDouble(split[i]);
            }
            for (int i = 0; i < 50; i++)
            {
                double[] solution2 = solution.clone();
                
                //Compute x values for each iteration
                for (int k = 0; k < solution.length; k++)
                {
                    double sum = theMatrix[k][theMatrix.length];
                    //Calculate sum
                    for (int j = 0; j < solution.length; j++)
                    {
                        if (k != j)
                        {
                            sum = (sum - (solution[j] * theMatrix[k][j]));
                        }
                        
                    }
                    solution[k] = (sum/theMatrix[k][k]);

                }
                
                printArray(solution);
                if (EuclideanNorm(solution, solution2) < stoppingError)
                {
                    break;
                }
            }
            
        }
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int theInput = 0;
        while (theInput != 1 && theInput != 2)
        {
            System.out.println("Enter:\n1. Jacobi's Method\n2. Gauss-Seidel Method");
            theInput = input.nextInt();
            if (theInput != 1 && theInput != 2)
            {
                System.out.println("Invalid input");
            }
        }
        
        // Jacobi
       if (theInput == 1)
       {
            theInput = 0;
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
                     jacobi(test, input);
                     fileReader.close();
                 } catch (FileNotFoundException e) {
                     System.out.println("File not found.");
                 }
            }
       }
       else
       {
            theInput = 0;
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
                 double[][] test = getMatrix(input);
                 GaussSeidel(test, input);
             
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
                     GaussSeidel(test, input);
                     fileReader.close();
                 } catch (FileNotFoundException e) {
                     System.out.println("File not found.");
                 }
            }
       }


       input.close();
    }
}