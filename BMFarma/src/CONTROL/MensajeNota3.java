/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import java.util.Scanner;

/**
 *
 * @author blasm
 */
public class MensajeNota3 {
    public static void main( String[] args ){
        
        System.out.printf("Ejemplo break\n"); int i = 0;
        while (i <= 20) {
            i++;
            if (i % 5 == 0) break;
            System.out.println(i);
        
        }
        System.out.printf("Ejemplo continue\n"); i = 1;
            while (i <= 20) {
            i++;
            if (i % 5 == 0) continue;
            System.out.println(i);
        } 
        
    }
    
}
