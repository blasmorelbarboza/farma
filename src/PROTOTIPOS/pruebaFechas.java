package PROTOTIPOS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class pruebaFechas {
   

public static void main(String[] args) {
        String fecha;
        Scanner leer=new Scanner(System.in);
    boolean res=true;
        System.out.println("Dame la fecha");
        fecha=leer.next();
        res=validarFecha(fecha);
        if(res==true){
            System.out.println("La fecha es valida");
             }else
                 System.out.println("La fecha no es valida");
         }
 
public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
    

