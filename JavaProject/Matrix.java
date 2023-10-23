package JavaProject;

import java.util.Scanner;

public class Matrix {
    int[][] data;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    // Method to initialize the matrix with provided values
    public void initialize(int[][] values) {
        if (values.length != rows || values[0].length != cols) {
            System.out.println("Matrix dimensions do not match the provided values.");
            return;
        }
        this.data = values;
    }

    // Method to input matrix values from the user
    public void inputMatrix() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter matrix values row by row:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter value at row " + (i + 1) + ", column " + (j + 1) + ": ");
                data[i][j] = scanner.nextInt();
            }
        }
    }

    // Method to display the matrix
    public void displayMatrix() {
        System.out.println("Matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Getter methods for rows and columns
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
