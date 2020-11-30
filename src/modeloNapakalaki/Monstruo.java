
 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloNapakalaki;

import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author rubens
 */
public class Monstruo implements Carta{
private String nombre;
private int nivel;
private int gananciaTesoros;
private int gananciaNiveles;
private String malRollo;
private int nivelesPerdidos;
private int ocultosPerdidos;
private int visiblesPerdidos;
ArrayList<TipoTesoro> tipovisiblesPerdidos = new ArrayList();
ArrayList<TipoTesoro> tipoocultosPerdidos = new ArrayList();
private int nivelContraSectario;

public String getDirImagen(){
    return "/modeloNapakalaki/Monstruos/" + nombre +".png" ;
}

public Monstruo(String nom,int nl, int gt, int gn, String mr, int np, int vp, int
op, TipoTesoro[] vvp, TipoTesoro[] vop,int n)
{
    nivelContraSectario=n;
    nombre=nom;
    nivel=nl;
    gananciaTesoros=gt;
    gananciaNiveles=gn;
    malRollo=mr;
    nivelesPerdidos=np;
    ocultosPerdidos=op;
    visiblesPerdidos=vp;
    tipovisiblesPerdidos.addAll(Arrays.asList(vvp));
    tipoocultosPerdidos.addAll(Arrays.asList(vop));
}
public boolean malRollosesMuerte() //hacerla
{
    if(nivelesPerdidos==10)
        return true;
    return false;
}
public String getNombre()
{
    return nombre;
}
@Override
public int getValorBasico()
{
    return this.nivel;
}
@Override
public int getValorEspecial(){
    return nivelContraSectario;
}
public int getGananciaTesoros()
{
    return gananciaTesoros;
}
public int getGananciaNiveles()
{
    return gananciaNiveles;
}
public int getVisiblesPerdidos()
{
    return visiblesPerdidos;
}
public int getNivelesPerdidos()
{
    return nivelesPerdidos;
}
public int getOcultosPerdidos()
{
    return ocultosPerdidos;
}
public String getMalRollo()
{
    return malRollo;
}
public int getNivel(){
    return nivel;
}
public int getNivelContraSectario(){
    return nivelContraSectario;
}
public ArrayList<TipoTesoro> getTiposVisiblesPerdidos()
{
    return tipovisiblesPerdidos;
}
public ArrayList<TipoTesoro> getTiposOcultosPerdidos()
{
    return tipoocultosPerdidos;
}
}
