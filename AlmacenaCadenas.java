import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase ejecutable que permite almacenar cadenas de caracteres en una pila de la manera indicada para la práctica
 * @author Moisés Corpus García 116005560
 * @version 1.0 (Septiembre 2022)
 * @see Stack
 * @see Scanner
 */
public class AlmacenaCadenas {

    /**
     * Pila ligada de caracter estatico donde se almacenan las cadenas
     */
    private static Stack<Object> linkedStack = new Stack<>();

    /**
     * Método main donde se ejecuta todo el programa principal de almacenado de cadenas de caracteres
     */
    public static void main(String[] args){
        //Strings
        Scanner in = new Scanner(System.in);

        //Integers
        Scanner in2 = new Scanner(System.in);

        int choose;

        do{
            //Menu
            System.out.println("**** BIENVENIDO ****\n");
            System.out.println("Desea almacenar o extraer cadenas de la pila?\n" +
            "1. Almacenar\n" +
            "2. Extraer\n" +
            "3. Salir\n");

            // Depuración de la entrada
            try{
                choose = in2.nextInt();
                if(choose < 1 || choose > 3){
                    System.out.println("\nPor favor escoja una de las opciones indicadas!\n");
                }
            }
            catch(InputMismatchException e){
                System.out.println("\nPor favor introduzca un numero!\n");
                in2.next();
                choose = 0;
            }

            //Almacenaje o extracción de cadenas
            switch(choose){
                case 1:
                    System.out.println("\nIntroduzca la cadena de caracteres que se desea almacenar:");
                    pushString(in.nextLine());
                    System.out.print("\n");
                    break;
                case 2:
                    if(linkedStack.isEmpty()){
                        System.out.println("\nNo hay nada almacenado en la pila!\n");
                        break;
                    }
                    else{
                    System.out.println("\nCadena devuelta: "+popString()+"\n");
                    }
                    break;
            }
        }
        while(choose != 3);
        in.close();
        in2.close();
    }

    /**
     * Método auxiliar que introduce las cadenas y su longitud en la pila
     * @param s Cadena de caracteres que se desea almacenar
     * @throws NullPointerException En caso de recibir un parámetro null
     */
    private static void pushString(String s){
        if(s == null)
            throw new NullPointerException();
        char[] chars = s.toCharArray();
        for(int i=chars.length-1;i >= 0;i--){
            AlmacenaCadenas.linkedStack.push(chars[i]);
        }
        AlmacenaCadenas.linkedStack.push(chars.length);
    }

    /**
     * Método auxiliar que extrae la ultima cadena almacenada en la pila
     * @return Devuelve la ultima cadena de caracteres que se haya almacenado en la pila
     */
    private static String popString(){
        String pop = "";
        int stringSize = (int) linkedStack.pop();
        for(int i=1;i<=stringSize;i++){
            pop += linkedStack.pop();
        }
        return pop;
    }
}
