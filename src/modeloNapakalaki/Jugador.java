/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloNapakalaki;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Administrador
 */
public class Jugador {
    private String nombre;
    private int nivel = 1;
    private static int NIVELMINIMO = 1;
    private static int NIVELMAXIMO = 10;
    private static int TESOROS_OCULTOS_MAXIMO = 4;
    private int numeroVisibles = 0;
    private int numeroOcultos = 0;
    private LinkedList <Tesoro> tesorosVisibles = new LinkedList();
    private LinkedList <Tesoro> tesorosOcultos = new LinkedList();
    private LinkedList <TipoTesoro> malRolloVisible  = new LinkedList();
    private LinkedList <TipoTesoro> malRolloOcultos  = new LinkedList();
    
    public Jugador(String nom){
        this.nombre = nom;
    }
    
    public Jugador(Jugador j)
    {
        this.nombre = j.nombre;
        this.nivel = j.nivel;
        this.numeroOcultos = j.numeroOcultos;
        this.numeroVisibles = j.numeroVisibles;
        this.tesorosVisibles = j.tesorosVisibles;
        this.tesorosOcultos = j.tesorosOcultos;
        this.malRolloOcultos = j.malRolloOcultos;
        this.malRolloVisible = j.malRolloVisible;
    }
    
    protected int obtenerNivelContrincante(Monstruo m)
    {
        return m.getValorBasico();
    }
    public JugadorSectario convertirme(Sectario sectario)
    {
       JugadorSectario j = new JugadorSectario(this,sectario);
       return j;
    }
    public boolean puedoConvertirme()
    {
        int dado = tirarDado(nombre, "Saca un 6 para convertirte en sectario");
        if(dado==6)
            return true;
        return false;
    }
    public String getNombre()
    {
        return nombre;
    }
            
    
    public int obtenerNivel()
    { 
        return nivel;
    }
    
    public void modificarNivel(int incDec)
    {
        nivel = incDec;
    }
    
    public int nivelCombate()
    {
    int niveltesoros = 0;
    boolean maximo = false;
    if(tieneCollar())
            maximo = true;
        for(Tesoro tes :tesorosVisibles)
        {
          if(tes.getTipoTesoro() != TipoTesoro.COLLAR){
            if(maximo)
                niveltesoros+=tes.getValorEspecial();
            else
                niveltesoros+=tes.getValorBasico();
            }
        }    
        return nivel+niveltesoros;
    }
    
    public void robarTesoro(Tesoro unTesoro){
        this.tesorosOcultos.add(unTesoro);     
    }
    
    private boolean puedoEquipar(Tesoro unTesoro){
        int aux=0;
        boolean a_devolver = true;
        for (Tesoro tes: tesorosVisibles){
            if(tes.getTipoTesoro() == TipoTesoro.MANO && unTesoro.getTipoTesoro() == TipoTesoro.DOSMANOS)
                    a_devolver = false;
            if(tes.getTipoTesoro() == TipoTesoro.DOSMANOS && unTesoro.getTipoTesoro() == TipoTesoro.MANO)
                    a_devolver = false;
            if (tes.getTipoTesoro() == unTesoro.getTipoTesoro()){
                if (tes.getTipoTesoro() == TipoTesoro.MANO)
                    aux++;
                else
                    a_devolver = false;
            }
        }
        if (aux >= 2)
            a_devolver = false;
        
        return a_devolver;
    }
    
    
    
    private void actualizarMalRollo(LinkedList<Tesoro> tesVisibles,LinkedList<Tesoro> tesOcultos)
    {
    if(numeroVisibles!=0) 
    {
       if(!malRolloVisible.isEmpty()){           
           for(Tesoro tes : tesorosVisibles){
               TipoTesoro aborrar = null;
               boolean encontrado = false;
               for (Iterator i = malRolloVisible.iterator(); i.hasNext() && !encontrado;) {     
                            TipoTesoro test = (TipoTesoro)i.next();
               if(test.equals(tes.getTipoTesoro()))
               {
                   aborrar = test;
                   encontrado=true;
                  
               }
                   }
               if(encontrado){
                    malRolloVisible.remove(aborrar); 
                    numeroVisibles--;
                            }
       }
       }
    }
       else{
           for(Tesoro tes: tesorosVisibles)
            {                
                numeroVisibles--;                                               
            }
     }
       
    if(numeroOcultos!=0)
    {
       if(!malRolloOcultos.isEmpty()){           
           for(Tesoro tes : tesorosOcultos){
               TipoTesoro aborrar = null;
               boolean encontrado = false;
               for (Iterator i = malRolloOcultos.iterator(); i.hasNext() && !encontrado;) {     
                            TipoTesoro test = (TipoTesoro)i.next();
               if(test.equals(tes.getTipoTesoro()))
               {
                   aborrar = test;
                   encontrado=true;
                  
               }
                   }
               if(encontrado)
                    {
                    malRolloOcultos.remove(aborrar); 
                    numeroOcultos--;
                    }
       }
       }
        else
        {
             for(Tesoro tes: tesorosOcultos)
            {                
                numeroOcultos--;                                               
            }
        }
    }
    
    }
    
  public LinkedList<Tesoro> getTesorosVisibles()
  {
      return tesorosVisibles;
  }
  
   public LinkedList<Tesoro> getTesorosOcultos()
  {
      return tesorosOcultos;
  }
    private boolean cumploMalRollo() {
        int contador=0;
        
        contador = this.numeroOcultos+this.numeroVisibles;
        
        if(this.malRolloVisible.isEmpty() && this.malRolloOcultos.isEmpty() && contador<=0)
            return true;
        
        return false;
    }
    
    public boolean descartarTesoros(LinkedList<Tesoro> tesVisibles,LinkedList<Tesoro> tesOcultos ){
       
           
            this.actualizarMalRollo(tesVisibles, tesOcultos);
            this.descartarTesorosVisibles(tesVisibles);
            this.descartarTesorosOcultos(tesOcultos);
      
            return this.cumploMalRollo();
        
    }
    
    
    public void descartarTesorosOcultos(LinkedList<Tesoro> listaTesoros)
    {
            for(Tesoro tes: listaTesoros){
                this.tesorosOcultos.remove(tes);
                
            }
        
    }
    
    public void descartarTesorosVisibles(LinkedList<Tesoro> listaTesoros)
    {
            for(Tesoro tes: listaTesoros){
                this.tesorosVisibles.remove(tes);
                
            }
        
    }
    
    public int puedoPasar(){
        int a_devolver = 0;
        if (!cumploMalRollo())
            a_devolver = -1;
        if (tesorosOcultos.size() > 4)
            a_devolver = tesorosOcultos.size()-4;
        
        return a_devolver;
    }
    
    public void equiparTesoros(LinkedList<Tesoro> listaTesoros){  
        boolean puedo;
        for(Tesoro tes : listaTesoros){
            puedo = puedoEquipar(tes);
            if (puedo){
                tesorosVisibles.add(tes);
                tesorosOcultos.remove(tes);
            }
        }
    }
    
    public boolean tengoTesoros(){
        if(tesorosVisibles.isEmpty() && tesorosOcultos.isEmpty())
                return false;
        return true;
    }
    
   public int calcularNivelesAComprar(LinkedList <Tesoro> tesoros)
   {
       int niveles =0, oro=0;
       for(Tesoro tes : tesoros)
       {
          oro+=tes.getPiezasOro();
       }
       niveles = oro/1000;

       return niveles;
   }

   public boolean comprarNivelesJugador(LinkedList <Tesoro> tesoros){
       boolean puedo=false;
       int niveles=calcularNivelesAComprar(tesoros);

       if(niveles>0)
       {
       if((this.nivel + niveles) < 10)
           puedo = true;
       else
           puedo = false;

       if(puedo){
           this.incDecNivel(niveles);

       tesorosOcultos.removeAll(tesoros);
       tesorosVisibles.removeAll(tesoros);
       }
      }
       return puedo;
   }

    
    public void incDecNivel(int incDec)
    {
        nivel+=incDec;
        if(nivel<=0)
            nivel=1;
    }
    
    public int combatir(Monstruo monstruoEnJuego){ 
        int nivelM = monstruoEnJuego.getNivel();
        int nivelJ = nivelCombate();
        int nivelesGanados;
        boolean muerte;
        int nivelesPerdidos;
        int resultado = 93344;
        int dado;
        
        if (nivelJ > nivelM){
            nivelesGanados = monstruoEnJuego.getGananciaNiveles();
            incDecNivel(nivelesGanados);
            if (nivel >= 10){ // Aqui es el nivel del jugador sin bonus de tesoros para saber si ha ganado el juego
                resultado = 10;
            }else{
                resultado = 1;
            }
        }
        if (nivelJ <= nivelM){
            resultado = 0;
            dado = this.tirarDado(nombre, "Saca un 5 o 6 para escapar"); //solo se tira el dado si el lv del monstruo >= lv jugador
       
            if (dado < 5){ 
                muerte = monstruoEnJuego.malRollosesMuerte();
                if (muerte){
                    modificarNivel(NIVELMINIMO);
                    resultado = -2;
                }else
                if (!muerte){                   
                    incluirMalRollo(monstruoEnJuego);
                    nivelesPerdidos = monstruoEnJuego.getNivelesPerdidos();
                    incDecNivel(nivelesPerdidos);
                    resultado = -1;
                }
            
            }
        }
        
        return resultado;
    }
    
    private int tirarDado(String S1, String S2) 
    {
         return Napakalaki.getVista().getDado(S1,S2);
    }
    
    public LinkedList<Tesoro> dameTodosTusTesoros(){
        LinkedList<Tesoro> a_devolver = new LinkedList();
        a_devolver.addAll(tesorosOcultos);
        a_devolver.addAll(tesorosVisibles);
       
        tesorosOcultos.clear();
        tesorosVisibles.clear();
       
        return a_devolver;
    }
    
    public void incluirMalRollo(Monstruo mons)
    {
        int unamano = 0;
       if(!mons.tipovisiblesPerdidos.isEmpty())
       {
          
           for(Tesoro tes: tesorosVisibles)
           {
               TipoTesoro aborrar = null;
               boolean encontrado=false;
               TipoTesoro test = null;
               
               for (Iterator<TipoTesoro> itAsig = mons.tipovisiblesPerdidos.iterator(); 
						itAsig.hasNext()&& !encontrado;){
                  test = itAsig.next();
               if(test.equals(tes.getTipoTesoro()))
               {
                   encontrado=true;
                   if(tes.getTipoTesoro()==TipoTesoro.MANO){
                       unamano++;
                   
                   numeroVisibles++;
                   malRolloVisible.add(test);
                   if(unamano>=2)
                       aborrar = test;
                   //
               }
                   else
                   {
                   numeroVisibles++;
                   malRolloVisible.add(test);
                   aborrar = test;
                   }
               }
           }
               if(encontrado)
               mons.tipovisiblesPerdidos.remove(aborrar);
       }
           
       }
       
       if(!mons.tipoocultosPerdidos.isEmpty())
       {
           for(Tesoro tes: tesorosOcultos)
           {
               TipoTesoro aborrar = null;
           boolean encontrado=false;
               TipoTesoro test = null;
               for (Iterator<TipoTesoro> itAsig = mons.tipoocultosPerdidos.iterator();
						itAsig.hasNext()&& !encontrado;){
                  test = itAsig.next();
               if(test.equals(tes.getTipoTesoro()))
               {
                   if(tes.getTipoTesoro()==TipoTesoro.MANO){
                       unamano++;
                   
                   numeroOcultos++;
                   malRolloOcultos.add(test);
                   if(unamano>=2)
                  aborrar = test;
               }
                   else
                   {
                   encontrado=true;
                   numeroOcultos++;
                   malRolloOcultos.add(test);
                   aborrar = test;
                  
                   }
               }
           }
               if(encontrado)
               mons.tipoocultosPerdidos.remove(aborrar);
       }
            
       }
      if(mons.tipovisiblesPerdidos.isEmpty() && mons.getVisiblesPerdidos()>0 && tesorosVisibles.size()>0) // ESTO QUE CO****PIIIIII****O ES???
                if(mons.getVisiblesPerdidos() > tesorosVisibles.size())
                    numeroVisibles = tesorosVisibles.size();
                 else
                    numeroVisibles = mons.getVisiblesPerdidos();
      if(mons.tipoocultosPerdidos.isEmpty() && mons.getOcultosPerdidos()>0 && tesorosOcultos.size()>0) // ESTO QUE CO****PIIIIII****O ES???
          if(mons.getOcultosPerdidos() > tesorosOcultos.size())
                    numeroOcultos = tesorosOcultos.size();
                 else
                    numeroOcultos = mons.getOcultosPerdidos();   
        
    }

    public Tesoro devuelveCollar()
    {
       Tesoro a_devolver = null;
       boolean tieneCollar = tieneCollar();
       if (tieneCollar){
            for(Tesoro tes: tesorosVisibles)  {        
                  if(tes.getTipoTesoro() == TipoTesoro.COLLAR)
                  {
                      a_devolver = tes;
                      
                      
                  }
            }
            tesorosVisibles.remove(a_devolver);
            
        } 
      
        return a_devolver;
    }
    
    public boolean tieneCollar()
    {
         	 for (Tesoro tes : tesorosVisibles)
                 {
                        if(tes.getTipoTesoro() == TipoTesoro.COLLAR)
                            return true;
                 }
       		   
        return false;
    }
    
    public boolean soySectario(){
        return (this instanceof JugadorSectario);
    }
}