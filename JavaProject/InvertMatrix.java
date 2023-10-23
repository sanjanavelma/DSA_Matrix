package JavaProject;

public class InvertMatrix {

    public static double[][] invertMatrix(double[][] matrix) {
        int n = matrix.length;
        double[][] augmentedMatrix = createAugmentedMatrix(matrix);

        for (int i = 0; i < n; i++) {
            // Forward Elimination
            int pivotRowIndex = findPivot(augmentedMatrix, i);
            swapRows(augmentedMatrix, i, pivotRowIndex);
            double pivotValue = augmentedMatrix[i][i];

            if (pivotValue == 0) {
                throw new IllegalArgumentException("Matrix is singular, cannot be inverted");
            }

            scaleRow(augmentedMatrix, i, 1.0 / pivotValue);

            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = augmentedMatrix[j][i];
                    subtractRow(augmentedMatrix, j, i, factor);
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            // Back Substitution
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = augmentedMatrix[j][i];
                    subtractRow(augmentedMatrix, j, i, factor);
                }
            }
        }

        return extractMatrix(augmentedMatrix);
    }

    // Helper functions for Gaussian elimination
    private static double[][] createAugmentedMatrix(double[][] matrix) {
        int n = matrix.length;
        double[][] augmentedMatrix = new double[n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
                augmentedMatrix[i][j + n] = (i == j) ? 1.0 : 0.0; // Identity matrix
            }
        }

        return augmentedMatrix;
    }

    private static int findPivot(double[][] matrix, int row) {
        int n = matrix.length;
        for (int i = row; i < n; i++) {
            if (matrix[i][row] != 0) {
                return i;
            }
        }
        throw new IllegalArgumentException("Matrix is singular, cannot be inverted");
    }

    private static void swapRows(double[][] matrix, int row1, int row2) {
        int n = matrix[0].length;
        for (int j = 0; j < n; j++) {
            double temp = matrix[row1][j];
            matrix[row1][j] = matrix[row2][j];
            matrix[row2][j] = temp;
        }
    }

    private static void scaleRow(double[][] matrix, int row, double scaleFactor) {
        int n = matrix[0].length;
        for (int j = 0; j < n; j++) {
            matrix[row][j] *= scaleFactor;
        }
    }

    private static void subtractRow(double[][] matrix, int targetRow, int sourceRow, double scaleFactor) {
        int n = matrix[0].length;
        for (int j = 0; j < n; j++) {
            matrix[targetRow][j] -= scaleFactor * matrix[sourceRow][j];
        }
    }

    private static double[][] extractMatrix(double[][] augmentedMatrix) {
        int n = augmentedMatrix.length;
        int m = augmentedMatrix[0].length / 2;
        double[][] result = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = augmentedMatrix[i][j + m];
            }
        }

        return result;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[][] matrix = {
                {2, 3, -1},
                {1, 1, -1},
                {1, 2, 1}
        };

        double[][] invertedMatrix = invertMatrix(matrix);
        printMatrix(invertedMatrix);
    }
}
