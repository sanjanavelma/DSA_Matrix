package JavaProject;
public class MatrixOperations {
    public static Matrix addMatrices(Matrix matrixA, Matrix matrixB) {
        int rowsA = matrixA.getRows();
        int colsA = matrixA.getCols();
        int rowsB = matrixB.getRows();
        int colsB = matrixB.getCols();

        if (rowsA != rowsB || colsA != colsB) {
            System.out.println("Matrix dimensions are not compatible for addition.");
            return null;
        }

        Matrix result = new Matrix(rowsA, colsA);

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                result.data[i][j] = matrixA.data[i][j] + matrixB.data[i][j];
            }
        }

        return result;
    }

    public static Matrix multiplyMatrices(Matrix matrixA, Matrix matrixB) {
        int rowsA = matrixA.getRows();
        int colsA = matrixA.getCols();
        int colsB = matrixB.getCols();

        if (colsA != matrixB.getRows()) {
            System.out.println("Matrix dimensions are not compatible for multiplication.");
            return null;
        }

        Matrix result = new Matrix(rowsA, colsB);

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                int sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += matrixA.data[i][k] * matrixB.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }

        return result;
    }

    public static Matrix invertMatrix(Matrix matrix) {
        int n = matrix.getRows();
        int m = 2 * n; // Augmented matrix [A | I]

        // Create the augmented matrix [A | I]
        Matrix augmentedMatrix = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix.data[i][j] = matrix.data[i][j];
                augmentedMatrix.data[i][j + n] = (i == j) ? 1 : 0; // Identity matrix
            }
        }

        // Perform Gaussian elimination
        for (int i = 0; i < n; i++) {
            // Find the pivot row
            int pivotRow = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(augmentedMatrix.data[j][i]) > Math.abs(augmentedMatrix.data[pivotRow][i])) {
                    pivotRow = j;
                }
            }

            // Swap the current row and the pivot row
            for (int j = 0; j < m; j++) {
                double temp = augmentedMatrix.data[i][j];
                augmentedMatrix.data[i][j] = augmentedMatrix.data[pivotRow][j];
                augmentedMatrix.data[pivotRow][j] = (int) temp;
            }

            // Normalize the pivot row
            double pivot = augmentedMatrix.data[i][i];
            for (int j = 0; j < m; j++) {
                augmentedMatrix.data[i][j] /= pivot;
            }

            // Eliminate other rows
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = augmentedMatrix.data[j][i];
                    for (int k = 0; k < m; k++) {
                        augmentedMatrix.data[j][k] -= (int) (factor * augmentedMatrix.data[i][k]);
                    }
                }
            }
        }

        // Extract the inverted matrix
        Matrix invertedMatrix = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                invertedMatrix.data[i][j] = augmentedMatrix.data[i][j + n];
            }
        }

        return invertedMatrix;
    }
}
