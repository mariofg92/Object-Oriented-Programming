/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloNapakalaki;

/**
 *
 * @author rubens
 */
public class Tesoro implements Carta{
    private String nombre;
    private int bonusMinimo;
    private int bonusMaximo;
    private int piezasOro;
    private TipoTesoro tipo;
    
    public String getDirImagen(){
        return "/modeloNapakalaki/Tesoros/" + nombre +".png";
    }
    
    public Tesoro(String nom, int bmin, int bmax, int poro, TipoTesoro t)
    {
        nombre = nom;
        bonusMinimo = bmin;
        bonusMaximo = bmax;
        piezasOro = poro;
        tipo = t;
    }
    
    public String getNombre()
    {
    return nombre;
    }
    @Override
    public int getValorBasico()
    {
    return bonusMinimo;
    }
    @Override
    public int getValorEspecial(){
        return bonusMaximo;
    }
    public int getPiezasOro()
    {
    return piezasOro;
    }
    public TipoTesoro getTipoTesoro()
    {
    return tipo;
    }
    
}