/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//Ruben Jimenez Martinez
//Valeriano Jesus Martin Martinez
//Mario Fernández Gómez
package modeloNapakalaki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author rubens
 */
public class Napakalaki {
    private static final Napakalaki instance = new Napakalaki();
    private ArrayList <Monstruo> descartesMonstruos = new ArrayList();
    private LinkedList <Monstruo> mazoMonstruos = new LinkedList();
    private Monstruo monstruoActivo;
    private Jugador jugadorActivo;
    private ArrayList <Jugador> Participantes = new ArrayList();
    private LinkedList <Tesoro> mazoTesoros = new LinkedList();
    private LinkedList <Tesoro> descarteTesoros = new LinkedList();
    private LinkedList <Sectario> mazoSectarios = new LinkedList();
    private static Vista vista;
    
    private Napakalaki(){};
    public static Napakalaki getInstance()
    {
        return instance;
    }
    public static void setVista(Vista unaVista)
    {
        vista = unaVista;
    }
    public static Vista getVista()
    {
        return vista;
    }
    public void comenzarJuego(String [] nombrejugadores) throws Exception
    {
        if (nombrejugadores.length < 3 || nombrejugadores.length > 4)
            throw new Exception("Numero de jugadores no permitido");
        inicializarjuego();
        inicializarJugadores(nombrejugadores);
        repatirCartas();
        siguienteTurno(); 
    }

    
    private void inicializarjuego()
    {
        mazoTesoros.add(new Tesoro("Si mi amo", 4, 7, 0, TipoTesoro.CASCO));
        mazoTesoros.add(new Tesoro("Botas de investigacion", 3, 4, 600, TipoTesoro.CALZADO));
        mazoTesoros.add(new Tesoro("Capucha de Cthulhu", 3, 5, 500, TipoTesoro.CASCO));
        mazoTesoros.add(new Tesoro("A prueba de babas", 2, 5, 400, TipoTesoro.ARMADURA));
        mazoTesoros.add(new Tesoro("Botas de lluvia acida", 1, 1, 800, TipoTesoro.DOSMANOS));
        mazoTesoros.add(new Tesoro("Casco minero", 2, 4, 400, TipoTesoro.CASCO));
        mazoTesoros.add(new Tesoro("Ametralladora Thompson", 4, 8, 600, TipoTesoro.DOSMANOS));
        mazoTesoros.add(new Tesoro("Camiseta de la UGR", 1, 7, 100, TipoTesoro.ARMADURA));
        mazoTesoros.add(new Tesoro("Clavo de rail ferroviario", 3, 6, 400, TipoTesoro.MANO));
        mazoTesoros.add(new Tesoro("Cuchillo de sushi arcano", 2, 3, 300, TipoTesoro.MANO));
        mazoTesoros.add(new Tesoro("Fez alopodo", 3, 5, 700, TipoTesoro.CASCO));       
        mazoTesoros.add(new Tesoro("Hacha prehistorica", 2, 5, 500, TipoTesoro.MANO));
        mazoTesoros.add(new Tesoro("El aparato de Pr Tesla", 4, 8, 900, TipoTesoro.ARMADURA));
        mazoTesoros.add(new Tesoro("Gaita", 4, 5, 500, TipoTesoro.DOSMANOS));
        mazoTesoros.add(new Tesoro("Insecticida", 2, 3, 300, TipoTesoro.MANO));
        mazoTesoros.add(new Tesoro("Escopeta de 3 cañones", 4, 6, 700, TipoTesoro.DOSMANOS));
        mazoTesoros.add(new Tesoro("Garabato mistico", 2, 2, 300, TipoTesoro.MANO));
        mazoTesoros.add(new Tesoro("La fuerza de MR T", 1000, 1000, 1000, TipoTesoro.COLLAR));
        mazoTesoros.add(new Tesoro("La rebeca metalica", 2, 3, 400, TipoTesoro.ARMADURA));
        mazoTesoros.add(new Tesoro("Mazo de los antiguos", 3, 4, 200, TipoTesoro.MANO));
        mazoTesoros.add(new Tesoro("Necro-playboycon", 3, 5, 300, TipoTesoro.MANO));
        mazoTesoros.add(new Tesoro("Lanzallamas", 4, 8, 800, TipoTesoro.DOSMANOS));
        mazoTesoros.add(new Tesoro("Necro-comicon", 1, 1, 100, TipoTesoro.MANO));
        mazoTesoros.add(new Tesoro("Necrocomicon", 5, 7, 800, TipoTesoro.DOSMANOS));
        mazoTesoros.add(new Tesoro("Linterna a 2 manos", 3, 6, 400, TipoTesoro.DOSMANOS));
        mazoTesoros.add(new Tesoro("Necro-gnomicon", 2, 4, 200, TipoTesoro.MANO));
        mazoTesoros.add(new Tesoro("Necrotelecom", 2, 3, 300, TipoTesoro.CASCO));
        mazoTesoros.add(new Tesoro("Porra preternatural", 2, 3, 200, TipoTesoro.MANO));
        mazoTesoros.add(new Tesoro("Tentaculo de pega", 0, 1, 200, TipoTesoro.CASCO));
        mazoTesoros.add(new Tesoro("Zapato deja-amigos", 0, 1, 500, TipoTesoro.CALZADO));
        mazoTesoros.add(new Tesoro("Shogulador", 1, 1, 600, TipoTesoro.DOSMANOS));
        mazoTesoros.add(new Tesoro("Varita de atizamiento", 3, 4, 400, TipoTesoro.MANO));
        
        
        //Revisar orden en el constructor y objetos construidos para todos los monstruso, tmabién los nuevos.
        mazoMonstruos.add(new Monstruo("3 Bayakhees de bonanza", 8, 2, 1, 
                "Pierdes tu armadura visible y otra oculta"
                , 0, 1, 1, new TipoTesoro[]{TipoTesoro.ARMADURA}, new TipoTesoro[]{TipoTesoro.ARMADURA},0));
        
        mazoMonstruos.add(new Monstruo("Chibithulhu", 2, 1, 1, 
                "Embobados con el lindo primigenio te descartas de tu casco visible"
                , 0, 1,0, new TipoTesoro[0], new TipoTesoro[]{TipoTesoro.CASCO},0));
        
        mazoMonstruos.add(new Monstruo("El sopor de Dunwich", 2, 1, 1, 
                "El primordial bostezo contagioso. Pierdes el calzado visible"
                , 0, 1, 0, new TipoTesoro[0], new TipoTesoro[]{TipoTesoro.CALZADO},0));
        
        mazoMonstruos.add(new Monstruo("Angeles de la noche ibicenca", 14, 4, 1, 
                "Te atrapan para llevarte de esta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta"
                , 0, 1, 1, new TipoTesoro[]{TipoTesoro.MANO}, new TipoTesoro[]{TipoTesoro.MANO},0));
        
        mazoMonstruos.add(new Monstruo("El gorron en el umbral", 10, 3, 1, 
                "Pierdes tus tesoros visibles"
                , 0, 6, 0, new TipoTesoro[]{TipoTesoro.ARMADURA,TipoTesoro.MANO,TipoTesoro.DOSMANOS,TipoTesoro.CASCO,
                    TipoTesoro.CALZADO,TipoTesoro.COLLAR}, new TipoTesoro[0],0));
        
        mazoMonstruos.add(new Monstruo("HP Munchcraft", 6, 2, 1, 
                "Pierdes la armadura visible"
                , 0, 1, 0, new TipoTesoro[]{TipoTesoro.ARMADURA}, new TipoTesoro[0],0));
        
        mazoMonstruos.add(new Monstruo("Bichgooth", 2, 1, 1, 
                "Sientes bichos bajo la ropa. Descarta la armadura visible"
                , 0, 1, 0, new TipoTesoro[]{TipoTesoro.ARMADURA}, new TipoTesoro[0],0));
        
        mazoMonstruos.add(new Monstruo("El rey de rosa", 13, 4, 2, 
                "Pierdes 5 niveles y 3 tesoros visibles."
                , 5, 3, 0, new TipoTesoro[0], new TipoTesoro[0],0));
        
        mazoMonstruos.add(new Monstruo("La que redacta en las tinieblas", 2, 1, 1, 
                "Toses los pulmones y pierdes 2 niveles."
                , 2, 0, 0, new TipoTesoro[0], new TipoTesoro[0],0));
        
        mazoMonstruos.add(new Monstruo("Los hondos", 8, 2, 1, 
                "Estos monstruos resultanbastante superficiales y te aburren mortalmente. Estas muerto"
                , 10, 0, 0, new TipoTesoro[0], new TipoTesoro[0],0));
        
        mazoMonstruos.add(new Monstruo("Roboggoth", 8, 2, 1, 
                "La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible"
                , 2, 1, 0, new TipoTesoro[]{TipoTesoro.DOSMANOS}, new TipoTesoro[0],0));
        
        mazoMonstruos.add(new Monstruo("Yskhtihyssg-Goth", 12, 3, 1, 
                "No le hace gracia que pronuncien mal su nombre. Estas muerto"
                , 10, 0, 0, new TipoTesoro[0], new TipoTesoro[0],0));
        
        mazoMonstruos.add(new Monstruo("Pollipolipo volante", 3, 1, 1, 
                "Da mucho asquito. Pierdes 3 niveles"
                , 3, 0, 0, new TipoTesoro[0], new TipoTesoro[0],0));
        
        mazoMonstruos.add(new Monstruo("Semillas Cthulhu", 4, 2, 1, 
                "Pierdes 2 niveles y 2 tesoros ocultos."
                , 2, 0, 2, new TipoTesoro[0], new TipoTesoro[0],0));
        
        mazoMonstruos.add(new Monstruo("El espia", 5, 1, 1, 
                "Te asusta en la noche.Pierdes un casco visible."
                , 0, 1, 0, new TipoTesoro[]{TipoTesoro.CASCO}, new TipoTesoro[0],0));
        
        
        //Monsturos nuevos
        mazoMonstruos.add(new Monstruo("El mal indecible impronunciable", 10, 3, 1, 
                "Pierdes 1 mano visible."
                , 0, 1, 0, new TipoTesoro[]{TipoTesoro.MANO}, new TipoTesoro[0],-2));
        mazoMonstruos.add(new Monstruo("Testigos Oculares", 6, 2, 1, 
                "Pierdes tus tesoros visibles. Jajaja."
                , 0, 6, 0, new TipoTesoro[]{TipoTesoro.ARMADURA,TipoTesoro.MANO,TipoTesoro.DOSMANOS,TipoTesoro.CASCO,
                    TipoTesoro.CALZADO,TipoTesoro.COLLAR}, new TipoTesoro[0],2));
        mazoMonstruos.add(new Monstruo("El gran cthulhu", 20, 2, 5, 
                "Hoy no es tu día de suerte. Mueres."
                , 10, 0, 0, new TipoTesoro[0], new TipoTesoro[0],4));
        mazoMonstruos.add(new Monstruo("Serpiente Politico", 8, 2, 1, 
                "Tu gobierno te recorta 2 niveles."
                , 2, 0, 0, new TipoTesoro[0], new TipoTesoro[0],-2));
        mazoMonstruos.add(new Monstruo("Felpuggoth", 2, 1, 1, 
                "Pierdes tu casco y tu armadura visible. Pierdes 3 tesoros ocultos."
                , 0, 2, 3, new TipoTesoro[]{TipoTesoro.CASCO, TipoTesoro.ARMADURA}, new TipoTesoro[0],-2));
        mazoMonstruos.add(new Monstruo("Shoggoth", 16, 4, 2, 
                "Pierdes 2 niveles."
                , 2, 0, 0, new TipoTesoro[0], new TipoTesoro[0],-4));
        mazoMonstruos.add(new Monstruo("Lolitagooth", 2, 1, 1, 
                "Pintalabios negro. Pierdes 2 niveles."
                , 2, 0, 0, new TipoTesoro[0], new TipoTesoro[0],3));
        
        //Cartas sectarias
        mazoSectarios.add(new Sectario("Sectario1", 1));
        mazoSectarios.add(new Sectario("Sectario2", 2));
        mazoSectarios.add(new Sectario("Sectario3", 1));
        mazoSectarios.add(new Sectario("Sectario4", 2));
        mazoSectarios.add(new Sectario("Sectario5", 1));
        mazoSectarios.add(new Sectario("Sectario6", 1));
        
        Collections.shuffle(mazoMonstruos);
        Collections.shuffle(mazoSectarios);
        Collections.shuffle(mazoTesoros);
    }
    
    private void inicializarJugadores(String nombres[])
    {
        for(String nom: nombres)
        {
            Jugador jugador = new Jugador(nom);
            Participantes.add(jugador);
        }
    }
    public Monstruo monstruoEnJuego()
    {
        return monstruoActivo;
    }
    
    public boolean descartarTesoros(LinkedList<Tesoro> tesVisibles,LinkedList<Tesoro> tesOcultos ){
       
           
            boolean b = jugadorActivo.descartarTesoros(tesVisibles, tesOcultos);
            descarteTesoros.addAll(tesVisibles);
            descarteTesoros.addAll(tesOcultos);
           
      
            return b;
        
    }
    
    public Jugador getJugadorActivo()
    {
        return jugadorActivo;
    }
    private void repatirCartas()
    {
        for(Jugador j : Participantes)
        {
            int dado = tirarDado("Repartir tesoros", j.getNombre());
            if(dado==1)
            {
             j.robarTesoro(siguienteTesoro());
             mazoTesoros.remove();
            }
            else
                if(dado==6)
                {
                    for(int i=0; i<3; i++)
                    {
                    j.robarTesoro(siguienteTesoro());
                     mazoTesoros.remove();
                    }
                }
            else
                {
                   for(int i=0; i<2; i++)
                    {
                    j.robarTesoro(siguienteTesoro());
                     mazoTesoros.remove();
                    } 
                }
        }
    }
     private int tirarDado(String S1, String S2) 
    {
         return vista.getDado(S1, S2);
    }
     
    private Jugador primerJugador()
    {
        
        int maximo = 1;
        Jugador primerJugador = null;
        
        for(Jugador j : Participantes)
        {
            String aux = "Saca un " + maximo + " o más para ser el primer Jugador";
            int dado = tirarDado(j.getNombre(), aux);
            if (dado >= maximo){
                maximo = dado;
                primerJugador = j;
            }
        }
        
        return primerJugador;
    }
           
    public int siguienteTurno()
    {
       int fin;
        if(jugadorActivo==null)
        {
            fin=0;
            jugadorActivo = primerJugador();
            monstruoActivo = siguienteMonstruo();
        }
        else{
            fin=jugadorActivo.puedoPasar();
        
            if(fin==0)
            {
               
               jugadorActivo = siguienteJugador();
               boolean tieneTesoros = jugadorActivo.tengoTesoros();
               if(!tieneTesoros)
               {
                   int dado = tirarDado(jugadorActivo.getNombre(),"Tira el dado para robar tesoros");
                if(dado==1)
                {
                 jugadorActivo.robarTesoro(siguienteTesoro());                 
                }
                else
                    if(dado==6)
                    {
                        for(int i=0; i<3; i++)
                        {
                         jugadorActivo.robarTesoro(siguienteTesoro());                         
                        }
                    }
                else
                    {
                       for(int i=0; i<2; i++)
                        {
                         jugadorActivo.robarTesoro(siguienteTesoro());                         
                        } 
                    }
               }                                                                       
                descartesMonstruos.add(monstruoActivo);
                monstruoActivo=siguienteMonstruo();
            }
        }
       return fin; 
       
    }
    
    private Monstruo siguienteMonstruo () {
        if(mazoMonstruos.isEmpty()){
            mazoMonstruos.addAll(descartesMonstruos);
            descartesMonstruos.clear();
            Collections.shuffle(mazoMonstruos);
        }    
        return mazoMonstruos.remove();
    }
    
    private Jugador siguienteJugador(){
        int indi = Participantes.indexOf(jugadorActivo);
        if (indi == Participantes.size()-1)
            return Participantes.get(0);
        else
            return Participantes.get(indi+1);
    }
     
    private Tesoro siguienteTesoro () {
        if(mazoTesoros.isEmpty()){
            mazoTesoros.addAll(descarteTesoros);
            descarteTesoros.clear();
            Collections.shuffle(mazoTesoros);
        }
        return mazoTesoros.remove();
    }
    
    private Sectario siguienteSectario() {
        return mazoSectarios.remove();
    }
    
    public int desarrollarCombate()
    {
        int resultado = jugadorActivo.combatir(monstruoActivo);
        Tesoro collar;
        
        if (resultado == 1){
            int tesorosGanados = monstruoActivo.getGananciaTesoros();
            for (int i=0;i<tesorosGanados;i++){
                jugadorActivo.robarTesoro(siguienteTesoro());
            }
                if(jugadorActivo.tieneCollar()){
                    collar = jugadorActivo.devuelveCollar();                
                    descarteTesoros.add(collar);                  
                }
       }
                if(resultado == -2){ 
                      descarteTesoros.addAll(jugadorActivo.dameTodosTusTesoros());
                     
                }
                if(resultado==-1){
                    if( jugadorActivo.puedoConvertirme() ){
                        JugadorSectario jugadorSectario=jugadorActivo.convertirme(siguienteSectario());
                        int k=Participantes.indexOf(jugadorActivo);
                        Participantes.remove(jugadorActivo);
                        Participantes.add(k, jugadorSectario);
                        jugadorActivo=jugadorSectario;
                    }
                    
                }
        return resultado;
    }
        
        
}