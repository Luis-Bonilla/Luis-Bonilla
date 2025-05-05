import javax.swing.JOptionPane;
public class RZ19 {
    public static void main(String[] args) {
        String [][] laberinto = new String[10][10];
        boolean showing = true;
        for (int i = 0; i < laberinto.length; i++) { //Asignar valores laberinto
            for (int k = 0; k < laberinto[i].length; k++) {
                cambiarElementoLaberinto(laberinto, i, k, "\u2B1C");
            }
        }        
        int posicionRobotY = 0;
        int posicionRobotX = 0;
        cambiarElementoLaberinto(laberinto, posicionRobotY, posicionRobotX, "\uD83E\uDD16"); //Robot
        cambiarElementoLaberinto(laberinto, laberinto.length - 1, laberinto[0].length - 1, "\uD83C\uDFC1"); //Nave
        while(showing){
            String menu = "Ingrese su opcion: \n1. Mostrar laberinto\n2. Mover Arriba\n3. Mover Abajo\n4. Mover Izquierda\n5. Mover Derecha\n6. Salir";
            int opcion = solicitarOpcion(menu);
            switch(opcion){
                case 1:
                    String laberintoString = ""; 
                    for (int i = 0; i < laberinto.length; i++) { //Crear entorno laberinto
                        for (int k = 0; k < laberinto[i].length; k++) {
                            laberintoString += laberinto[i][k] + "   ";
                        }
                        laberintoString += "\n";
                    }
                    JOptionPane.showMessageDialog(null, laberintoString); //Mostrar laberinto
                case 2: //Mover Arriba
                    negarMovimiento(laberinto, posicionRobotX, opcion);
                    moverArriba(laberinto, posicionRobotY, posicionRobotX);
                    posicionRobotY--;
                    break;
                case 3: //Mover Abajo
                    negarMovimiento(laberinto, posicionRobotX, opcion);
                    moverAbajo(laberinto, posicionRobotY, posicionRobotX);
                    posicionRobotY++;
                    break;
                case 4: //Mover Izquierda
                    negarMovimiento(laberinto, posicionRobotX, opcion);
                    moverIzquierda(laberinto, posicionRobotY, posicionRobotX);
                    posicionRobotX--;
                    break;
                case 5: //Mover Derecha
                    negarMovimiento(laberinto, posicionRobotX, opcion);
                    moverDerecha(laberinto, posicionRobotY, posicionRobotX);
                    posicionRobotX++;
                    break;
                case 6: //Salir
                    JOptionPane.showMessageDialog(null, "Gracias por jugar"); //Fin
                    showing = false;
                    break;              
            }
            cambiarElementoLaberinto(laberinto, posicionRobotY, posicionRobotX, "\uD83E\uDD16"); //Robot
        }     
    }
    public static int solicitarOpcion(String mensaje){
        return Integer.parseInt(JOptionPane.showInputDialog(mensaje));
    }
    public static void cambiarElementoLaberinto(String[][] laberinto, int fila, int columna, String nuevoElemento){
        laberinto[fila][columna] = nuevoElemento;
    }
    public static void negarMovimiento(String[][] laberinto, int fila, int opcion){
        if (opcion == 2) {
            if (fila - 1 >= 0) {
                JOptionPane.showMessageDialog(null, "No puedes moverte hacia arriba");
            }
        } 
        else if (opcion == 3) {
            if (fila + 1 < laberinto.length) {
                JOptionPane.showMessageDialog(null, "No puedes moverte hacia abajo");
            }
        } 
        else if (opcion == 4) {
            if (fila - 1 >= 0) {
                JOptionPane.showMessageDialog(null, "No puedes moverte hacia la izquierda");
            }
        } 
        else if (opcion == 5) {
            if (fila + 1 < laberinto[0].length) {
                JOptionPane.showMessageDialog(null, "No puedes moverte hacia la derecha");
            }
        }
    }
    public static void moverArriba(String[][] laberinto, int fila, int columna){
        if (fila - 1 >= 0) {
            cambiarElementoLaberinto(laberinto, fila - 1, columna, "\uD83E\uDD16");
            cambiarElementoLaberinto(laberinto, fila, columna, "\u2B1C");
        }
    }
    public static void moverAbajo(String[][] laberinto, int fila, int columna){
        if (fila + 1 < laberinto.length) {
            cambiarElementoLaberinto(laberinto, fila + 1, columna, "\uD83E\uDD16");
            cambiarElementoLaberinto(laberinto, fila, columna, "\u2B1C");
        }
    }
    public static void moverIzquierda(String[][] laberinto, int fila, int columna){
        if (columna - 1 >= 0) {
            cambiarElementoLaberinto(laberinto, fila, columna - 1, "\uD83E\uDD16");
            cambiarElementoLaberinto(laberinto, fila, columna, "\u2B1C");
        }
    }
    public static void moverDerecha(String[][] laberinto, int fila, int columna){
        if (columna + 1 < laberinto[0].length) {
            cambiarElementoLaberinto(laberinto, fila, columna + 1, "\uD83E\uDD16");
            cambiarElementoLaberinto(laberinto, fila, columna, "\u2B1C");
        }
    }
}