package JavaProject;

public class Main {
        public static void main(String[] args) {
            UserInterface ui = new UserInterface();

            while (true) {
                int choice = ui.getUserChoice();
                Matrix result = null;

                switch (choice) {
                    case 1:
                        Matrix matrixA = ui.inputMatrix("A");
                        Matrix matrixB = ui.inputMatrix("B");
                        result = MatrixOperations.addMatrices(matrixA, matrixB);
                        break;
                    case 2:
                        matrixA = ui.inputMatrix("A");
                        matrixB = ui.inputMatrix("B");
                        result = MatrixOperations.multiplyMatrices(matrixA, matrixB);
                        break;
                    case 3:
                        Matrix matrix = ui.inputMatrix("A");
                        result = MatrixOperations.invertMatrix(matrix);
                        break;
                    case 4:
                        System.out.println("Exiting the application.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                        break;
                }

                if (result != null) {
                    System.out.println("Result:");
                    ui.displayMatrix(result);
                }
            }
        }
}
