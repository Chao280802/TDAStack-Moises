import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase ejecutable que imprime todas las soluciones de tableros de ajedrez NxN donde N reinas no se ataquen
 * @author Moisés Corpus García 116005560
 * @version 1.0 (Septiembre 2022)
 * @see ArrayList
 * @see Scanner
 */
public class NReinas {

    /**
     * Método principal donde se ejecuta el programa que muestre las posibles soluciones al problema de las N reinas
     */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        System.out.println("****BIENVENIDO****\n");
        System.out.println("Introduzca el numero de reinas que desea: ");
        int n = 0;

        //Depuración de la entrada
        do{
            try {
                n = in.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("\nPor favor introduzca un numero!\n");
                in.next();
                continue;
            }
            if(n <= 0){
                System.out.println("\nIntroduce un numero positivo!\n");
                continue;
            }
        }
        while(n <= 0);
        in.close();

        //Impresion de las soluciones
        ArrayList<ArrayList<String>> board;
        if(n < 4 && n != 1){
            System.out.println("\nNo existe solucion para "+n+" reinas en un tablero de "+n+"x"+n+"\n");
            return;
        }
        System.out.println("\nPOSIBLES SOLUCIONES: \n");
        if(n == 1){
            System.out.println("-----\n| Q |\n-----");
            return;
        }
        int[] solutions = new int[n];
        for(int i = 0;i < n;i++){
            board = createBoard(n);
            solutions[0] = i;
            if(recursiveSolution(solutions, 1, n)){
                solutionToBoard(board, solutions);
                printBoard(board);
            }
        }
    }

    /**
     * Método auxiliar que crea un tablero de NxN con apoyo de las ArrayList de java
     * @param n Numero de filas y columnas del tablero
     * @return Devuelve una Arraylist que almacena Arraylist de Strings "vacios" de manera que simulen una matriz para un tablero de ajedrez en limpio
     */
    private static ArrayList<ArrayList<String>> createBoard(int n){
        ArrayList<ArrayList<String>> board = new ArrayList<>(n);
        for(int i=0;i < n;i++){
            board.add(i,new ArrayList<>(n));
            for(int j=0;j < n;j++){
                board.get(i).add(j,"   ");
            }
        }
        return board;
    }

    /**
     * Método auxiliar que imprime un tablero de ajedrez basado en ArrayLists
     * @param board ArrayList de ArrayLists de Strings que representan el tablero
     */
    private static void printBoard(ArrayList<ArrayList<String>> board){
        String result = "";
        for(int i=0;i < board.size();i++){
            for(int j=0;j < board.get(i).size();j++){
                result += "----";
            }
            result += "-\n|";
            for(int j=0;j < board.get(i).size();j++){
                result += board.get(i).get(j)+"|";
            }
            result += "\n";
        }
        for(int i=0;i < board.size();i++){
            result += "----";
        }
        result += "-";
        System.out.println(result);
    }

    /**
     * Método auxiliar que introduce las reinas al tablero de ajedrez basandose en un arreglo de enteros que representa la solucion
     * @param board ArrayList de ArrayLists de Strings que representan el tablero
     * @param solution Arreglo de enteros de n tamaño que representa en que columna y fila se coloca cada una de las n reinas
     */
    private static void solutionToBoard(ArrayList<ArrayList<String>> board, int[] solution){
        for(int i=0;i < solution.length;i++){
            board.get(solution[i]).set(i," Q ");
        }
    }

    /**
     * Método auxiliar que verifica que en una solucion dada las reinas no se esten atacando
     * @param solution Arreglo de enteros de n tamaño que representa en que columna y fila se coloca cada una de las n reinas
     * @param column Un entero que representa cual fue la ultima columna en la que se introdujo una reina
     * @return Devuelve un boolean true si la solucion es válida o false de lo contrario
     * @throws IllegalStateException En caso de recibir una fase mayor al tamaño del arreglo pues sería incoherente
     */
    private static boolean validBoard(int[] solution, int column){
        if(column > solution.length)
            throw new IllegalStateException();
            
        // Revisamos que no haya reinas en la misma fila
        for(int i=0;i < column;i++){
            for(int j=i+1;j <= column;j++){
                if(solution[i] == solution[j])
                    return false;
            }
        }

        //Revisamos que no haya reinas en la misma diagonal
        for(int i=0;i < column;i++){
            for(int j=i+1;j <= column;j++){
                if(Math.abs(i-j) == Math.abs(solution[i] - solution[j])){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Método auxiliar que implementa una solucion recursiva al problema de las reinas
     * @param solution Arreglo de enteros de n tamaño que representa en que columna y fila se introduce cada una de las n reinas sobre el cual estaremos trabajando
     * @param column Entero que representa en que columna estamos introduciendo una reina
     * @param queenNumber El numero de reinas que debe tener el tablero al final de la solución
     * @return Devuelve un booleano true cuando se ha llegado a una solución y es válida o false si no se encontro una
     */
    private static boolean recursiveSolution(int[] solution, int column, int queenNumber){
        if(column > queenNumber-1)
            return false;
        boolean resolved = false;
        solution[column] = -1;
        while(solution[column] < queenNumber-1 && !resolved){
            solution[column]++;
            if(validBoard(solution,column)){
                if(column != queenNumber-1){
                    resolved = recursiveSolution(solution, column+1, queenNumber);
                }
                else{
                    resolved = true;
                }
            }
        }
        return resolved;
    }
    
}
