/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloNapakalaki;

/**
 *
 * @author rubens
 */
public class Main {
    
     public static void main(String[] args) {
     
         Napakalaki juego = Napakalaki.getInstance();
         Vista v = new VentanaPrincipal(juego);
         juego.setVista(v);
         v.mostrar(args);
     }
}
