
 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloNapakalaki;

/**
 *
 * @author MacMario
 */
public class Sectario implements Carta{
    private String nombre;
    private int gananciaNivel;
    
    public String getDirImagen(){
        return "/modeloNapakalaki/Sectarios/" + nombre +".png";
    }
    
    public Sectario(String nom, int gNivel){
        nombre = nom;
        gananciaNivel = gNivel;
    }

    @Override
    public String getNombre()
    {
        return nombre;
    }
    @Override
    public int getValorBasico()
    {
    return gananciaNivel;
    }
    @Override
    public int getValorEspecial(){
        return this.getValorBasico()*JugadorSectario.getSectarios();
    }
}
