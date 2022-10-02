import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * Clase ejecutable que verifica el balance de etiquetas en un texto html
 * @author Moisés Corpus García 116005560
 * @version 1.0 (Septiembre 2022)
 * @see Stack
 * @see Scanner
 */
public class BalanceHTML {

    /**
     * Método principal donde se pide el texto html y se devuelve el resultado del balance
     */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        String code;

        System.out.println("**** BIENVENIDO ****\n");
        System.out.println("Introduzca el etiquetado HTML que desea verificar: \n");
        code = in.nextLine();
        if(isHTMLMatched(code)){
            System.out.println("\nSu codigo esta bien balanceado!");
        }
        else {
            System.out.println("\nSu codigo no está bien balanceado, revise su etiquetado");
        }
        in.close();
    }

    /**
     * Método auxiliar que revisa el balance de etiquetas html con apoyo de una pila ligada
     * @param s Texto en HTML por verificar que se abran y cierren correctamente las etiquetas
     * @return Devuelve un booleano true de estar balanceado o false de no estarlo o de recibir un texto sin etiquetas
     */
    private static boolean isHTMLMatched(String s){
        Stack<Object> linkedStack = new Stack<>();
        boolean balance = false;
        char[] chars = s.toCharArray();

        // Introduccion y expulsion de etiquetas
        try {
            for(int i=0; i<chars.length;i++){
                if(!linkedStack.isEmpty() && ((char) linkedStack.top()) != '>')
                    return false;
                if(chars[i] == '<' && chars[i+1] != '/'){
                    balance = true;
                    linkedStack.push(i++);
                    while(chars[i] != '>'){
                        linkedStack.push(chars[i++]);
                    }
                    linkedStack.push(chars[i]);
                }
                else if(chars[i] == '<' && chars[i+1] == '/'){
                    i++;
                    while(chars[i] != '>'){
                        linkedStack.pop();
                        i++;
                    }
                    linkedStack.pop();
                }
            }
        }
        catch(EmptyStackException e){
            return false;
        }

        //Resultado final
        return balance && linkedStack.isEmpty();
    }
}
