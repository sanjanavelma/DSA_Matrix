package JavaProject;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public int getUserChoice() {
        System.out.println("Matrix Operations Menu:");
        System.out.println("1. Matrix Addition");
        System.out.println("2. Matrix Multiplication");
        System.out.println("3. Matrix Inversion");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");

        int choice = scanner.nextInt();
        return choice;
    }

    public void displayMatrix(Matrix matrix) {
        System.out.println("Matrix:");
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                System.out.print(matrix.data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Matrix inputMatrix(String matrixName) {
        System.out.println("Enter matrix " + matrixName + ":");
        System.out.print("Number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Number of columns: ");
        int cols = scanner.nextInt();

        Matrix matrix = new Matrix(rows, cols);
        System.out.println("Enter matrix elements:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrixName + "[" + (i + 1) + "][" + (j + 1) + "]: ");
                matrix.data[i][j] = scanner.nextInt();
            }
        }

        return matrix;
    }
}