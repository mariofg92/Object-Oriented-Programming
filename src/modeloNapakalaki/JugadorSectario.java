/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloNapakalaki;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author rubens
 */
public class JugadorSectario extends Jugador {
    private static int numeroSectarios = 0;
    private Sectario miCartaSectario = new Sectario("",1);
    
    public Sectario getCartaSectario(){
        return miCartaSectario;
    }
    
    
    public JugadorSectario(Jugador jugador, Sectario sectario)
    {
        super(jugador);
        this.miCartaSectario = sectario;
        incrementarSectarios();
    }
    private static void incrementarSectarios()
    {
        numeroSectarios++;
    }
    public static int getSectarios()
    {
         return numeroSectarios;
    }
    public JugadorSectario convertirme(Carta sectario)
    {
        return this;
    }
    @Override
    public int nivelCombate()
    {
        int aux = super.nivelCombate();
        aux += miCartaSectario.getValorEspecial();
        return aux;
    }
    
    protected int obtenerNivelContrincante(Monstruo m)
    {
        int aux = super.obtenerNivelContrincante(m);
         aux += m.getValorEspecial();
        return aux;
    }

    public boolean puedoConvertirme (){
        return false;
    }

    @Override
    public int calcularNivelesAComprar(LinkedList <Tesoro> tesoros)
   {
       int niveles =0, oro=0;
       for(Tesoro tes : tesoros)
       {
          oro+=tes.getPiezasOro()*2;
           
       }
       niveles = oro/1000;

       return niveles;
   }
}

