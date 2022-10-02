/**
 * Clase ejecutable que pone a prueba un algoritmo backtracking para la resolución de un laberinto
 * @author Moisés Corpus García 116005560
 * @version 1.0 (Septiembre 2022)
 */
public class Laberinto {

    /**
     * Método principal donde se pone a prueba el algoritmo backtracking con un laberinto específico
     */
    public static void main(String[] args){
        boolean[][] labyrinth = createLabyrinth();
        int[] position = new int[2];
        position[0] = 0;
        position[1] = 0;
        printLabyrinth(labyrinth);
        System.out.println("Partimos de la posicion ["+position[0]+","+position[1]+"]\n");
        solveLabyrinth(labyrinth, position,null);
        if(position[0] == labyrinth.length-1 && position[1] == labyrinth.length-1)
            System.out.println("\nHemos llegado a la posicion ["+position[0]+","+position[1]+"]");
        else
            System.out.println("\nEl laberinto no tiene solucion!!");
    }

    /**
     * Método auxiliar que crea una matriz booleana que representa un laberinto donde true son paredes y false pasillos
     * @return Por defecto siempre devuelve una matriz de 2 dimensiones con ambos tamaños 4 y paredes predefinidas basadas en el pdf de la práctica
     */
    private static boolean[][] createLabyrinth(){
        boolean[][] labyrinth = new boolean[4][4];
        labyrinth[1][0] = true;
        labyrinth[1][1] = true;
        labyrinth[1][3] = true;
        labyrinth[3][1] = true;
        labyrinth[3][2] = true;

        /* 
        Otro laberinto prueba
        labyrinth[2][0] = true;
        labyrinth[2][1] = true;
        labyrinth[2][3] = true;
        labyrinth[1][3] = true;
        */

        return labyrinth;
    }

    /**
     * Método auxiliar que imprime un laberinto con valores binarios donde 0 es pasillo y 1 es pared
     * @param labyrinth Matriz booleana que representa dicho laberinto
     */
    private static void printLabyrinth(boolean[][] labyrinth){
        String result="";
        for(int i=0;i < labyrinth.length;i++){
            result += "-";
            for(int j=0;j < labyrinth[i].length;j++){
                result += "----";
            }
            result += "\n|";
            for(int j=0;j < labyrinth[i].length;j++){
                result += (labyrinth[i][j]) ? " 1 |" : " 0 |";
            }
            result += "\n";
        }
        result += "-";
        for(int j=0;j < labyrinth.length;j++){
            result += "----";
        }
        System.out.println(result);
    }

    /**
     * Método auxiliar recursivo que resuelve el laberinto con principios de backtracking
     * @param labyrinth Matriz booleana que representa dicho laberinto y sobre la que se trabajará
     * @param position Arreglo de enteros de tamaño 2 que contiene la fila y columna donde estamos posicionados actualmente
     * @param lastDirection Ultima dirección a la que nos movimos en la anterior llamada recursiva o null si es la primera llamada
     * @return Devuelve true de encontrar la meta o de encontrar un callejon sin salida
     * @throws IllegalArgumentException En caso de recibir un arreglo de posicion con tamaño distinto de 2
     */
    private static boolean solveLabyrinth(boolean[][] labyrinth, int[] position, Direction lastDirection){
        if(position.length != 2)
            throw new IllegalArgumentException();
        if(position[0] == labyrinth.length-1 && position[1] == labyrinth.length-1){
            return true;
        }
        Direction[] posibleDirections = posibleDirections(labyrinth, position);
        for(Direction d : posibleDirections){
            if(lastDirection != null && d == opositeDirection(lastDirection))
                continue;
            move(d, position, labyrinth.length);
            System.out.println("Probamos con la posicion ["+position[0]+","+position[1]+"] ...");
            boolean solved = solveLabyrinth(labyrinth, position,d);
            if(solved && !(position[0] == labyrinth.length-1 && position[1] == labyrinth.length-1))
                move(opositeDirection(d),position,labyrinth.length);
            if(solved && (position[0] == labyrinth.length-1 && position[1] == labyrinth.length-1)){
                break;
            }
        }
        return true;
    }

    /**
     * Método auxiliar que devuelve un arreglo de direcciones a las que nos podemos mover desde nuestra posicion actual en el laberinto
     * @param labyrinth Matriz booleana que representa el laberinto donde estamos posicionados
     * @param position Arreglo de enteros de tamaño 2 que contiene la fila y columna en la que estamos actualmente
     * @return Devuelve un arreglo de tamaño n que contiene cada una de las n direcciones a las que nos podemos mover sin salirnos del laberinto o chocar con pared
     * @throws IllegalArgumentException En caso de recibir un arreglo de posicion con tamaño distinto de 2
     */
    private static Direction[] posibleDirections(boolean[][] labyrinth, int[] position){
        if(position.length != 2)
            throw new IllegalArgumentException();
        Direction[] initial = new Direction[4];
        int hallways = 0;
        if(position[0]+1 < labyrinth.length && !labyrinth[position[0]+1][position[1]]){
            initial[hallways++] = Direction.ABAJO;
        }
        if(position[0]-1 >= 0 && !labyrinth[position[0]-1][position[1]]){
            initial[hallways++] = Direction.ARRIBA;
        }
        if(position[1]+1 < labyrinth.length && !labyrinth[position[0]][position[1]+1]){
            initial[hallways++] = Direction.DERECHA;
        }
        if(position[1]-1 >= 0 && !labyrinth[position[0]][position[1]-1]){
            initial[hallways++] = Direction.IZQUIERDA;
        }
        Direction[] result = new Direction[hallways];
        for(int i=0;i < result.length;i++){
            result[i] = initial[i];
        }
        return result;
    }

    /**
     * Método auxiliar que modifica nuesta posicion en el laberintp
     * @param d Direccion a la que nos pretendemos mover
     * @param actualPosition Arreglo de enteros de tamaño 2 que contiene la fila y columna en la que estamos actualmente
     * @param n Entero que simboliza el largo y ancho del laberinto en el que estamos
     * @return Devuelve un arreglo de enteros que representa la fila y columna de la matriz en la que estamos despues de movernos
     * @throws IllegalArgumentException En caso de recibir un arreglo de posicion de tamaño distinto de 2
     * @throws IllegalStateException En caso de pretender moverse fuera de las dimensiones del laberinto
     */
    private static int[] move(Direction d, int[] actualPosition, int n){
        if(actualPosition.length != 2)
            throw new IllegalArgumentException();
        switch(d){
            case ARRIBA:
                if(actualPosition[0] == 0)
                    throw new IllegalStateException();
                actualPosition[0]--;
                break;
            case ABAJO:
                if(actualPosition[0] == n-1)
                    throw new IllegalStateException();
                actualPosition[0]++;
                break;
            case DERECHA:
                if(actualPosition[1] == n-1)
                    throw new IllegalStateException();
                actualPosition[1]++;
                break;
            case IZQUIERDA:
                if(actualPosition[1] == 0)
                    throw new IllegalStateException();
                actualPosition[1]--;
                break;
        }
        return null;
    }

    /**
     * Método auxiliar que devuelve la dirección opuesta a la dirección dada
     * @param d Dirección de la que se desea la direccion opuesta
     * @return Devuelve un enum que representa la dirección opuesta a la dada
     * @throws IllegalArgumentException En caso de recibir un parametro null o una dirección fuera de este enum
     */
    private static Direction opositeDirection(Direction d){
        switch(d){
            case ARRIBA:
                return Direction.ABAJO;
            case ABAJO:
                return Direction.ARRIBA;
            case DERECHA:
                return Direction.IZQUIERDA;
            case IZQUIERDA:
                return Direction.DERECHA;
        }
        throw new IllegalArgumentException();
    }

    /**
     * Enum auxiliar que representa cada una de las direcciones a las que podriamos movernos en el laberinto
     */
    private static enum Direction{
        ARRIBA, ABAJO, DERECHA, IZQUIERDA
    }
}
