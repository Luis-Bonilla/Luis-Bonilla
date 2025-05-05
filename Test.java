import java.util.Random;
import javax.swing.JOptionPane;

public class Test {
    private static final int SIZE = 10;
    private static final String EMPTY = "\u2B1C   "; // ⬜️
    private static final String ROBOT = "\uD83E\uDD16   "; // 🤖
    private static final String EXIT = "\uD83D\uDE80   "; // 🚀
    private static final String OBSTACLE = "\uD83E\uDDF1   "; // 🧱
    private static final String ENERGY = "\u26A1   "; // ⚡

    private static String[][] grid = new String[SIZE][SIZE];
    private static int robotRow = 0;
    private static int robotCol = 0;
    private static int energy = 15;

    public static void main(String[] args) {
        initializeGrid();
        placeObstacles();
        placeEnergyStations();

        while (true) {
            printGrid();
            JOptionPane.showMessageDialog(null, "Energía restante: " + energy, "Estado del Robot", JOptionPane.INFORMATION_MESSAGE);

            String menu = """
                Menú:
                1. Mostrar matriz
                2. Mover arriba
                3. Mover abajo
                4. Mover izquierda
                5. Mover derecha
                6. Salir
                """;
            String input = JOptionPane.showInputDialog(menu + "\nElige una opción:");
            if (input == null) {
                JOptionPane.showMessageDialog(null, "¡Gracias por jugar!", "Juego terminado", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            int option;
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida. Intenta de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            if (option == 6) {
                JOptionPane.showMessageDialog(null, "¡Gracias por jugar!", "Juego terminado", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            switch (option) {
                case 1:
                    printGrid();
                    break;
                case 2:
                    moveRobot(-1, 0);
                    break;
                case 3:
                    moveRobot(1, 0);
                    break;
                case 4:
                    moveRobot(0, -1);
                    break;
                case 5:
                    moveRobot(0, 1);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intenta de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            if (energy <= 0) {
                JOptionPane.showMessageDialog(null, "El robot se quedó sin energía. ¡Has perdido!", "Juego terminado", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    private static void initializeGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = EMPTY;
            }
        }
        grid[robotRow][robotCol] = ROBOT;
        grid[SIZE - 1][SIZE - 1] = EXIT;
    }

    private static void placeObstacles() {
        Random random = new Random();
        int obstacles = 20;

        while (obstacles > 0) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);

            if (isValidObstaclePosition(row, col)) {
                grid[row][col] = OBSTACLE;
                obstacles--;
            }
        }
    }

    private static boolean isValidObstaclePosition(int row, int col) {
        if (grid[row][col].equals(EMPTY)) {
            if (Math.abs(row - robotRow) <= 1 && Math.abs(col - robotCol) <= 1) {
                return false;
            }
            if (row == SIZE - 1 && col == SIZE - 1) {
                return false;
            }
            return true;
        }
        return false;
    }

    private static void placeEnergyStations() {
        Random random = new Random();
        int energyStations = 5;

        while (energyStations > 0) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);

            if (grid[row][col].equals(EMPTY)) {
                grid[row][col] = ENERGY;
                energyStations--;
            }
        }
    }

    private static void printGrid() {
        StringBuilder gridDisplay = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gridDisplay.append(grid[i][j]);
            }
            gridDisplay.append("\n");
        }
        JOptionPane.showMessageDialog(null, gridDisplay.toString(), "Matriz del Laberinto", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void moveRobot(int rowChange, int colChange) {
        int newRow = robotRow + rowChange;
        int newCol = robotCol + colChange;

        if (newRow < 0 || newRow >= SIZE || newCol < 0 || newCol >= SIZE) {
            JOptionPane.showMessageDialog(null, "Movimiento inválido. El robot no puede salir del laberinto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String targetCell = grid[newRow][newCol];
        if (targetCell.equals(OBSTACLE)) {
            JOptionPane.showMessageDialog(null, "El camino está bloqueado por un obstáculo.", "Movimiento bloqueado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (targetCell.equals(ENERGY)) {
            energy += 10;
            JOptionPane.showMessageDialog(null, "¡El robot recargó energía!", "Recarga de Energía", JOptionPane.INFORMATION_MESSAGE);
        }

        if (targetCell.equals(EXIT)) {
            JOptionPane.showMessageDialog(null, "¡El robot ha escapado con éxito!", "Juego terminado", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        grid[robotRow][robotCol] = EMPTY;
        robotRow = newRow;
        robotCol = newCol;
        grid[robotRow][robotCol] = ROBOT;

        energy--;
    }
}
