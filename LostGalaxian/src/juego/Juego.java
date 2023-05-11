package juego;

import java.util.Random;

import entorno.*;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	Nave nave;
	ProyectilNave proyectilNave;	
	
	Asteroide[] asteroides;
	Destructor[] destructores; 
	ProyectilDestructor[] proyectilesDestructor;
	
	boolean disparo, perdio;
	int tiempo, muertos, dispararDestructor;
	Random gen;

	
	
	Juego()
	{

		this.entorno = new Entorno(this, "Lost Galaxian - Grupo 3 - v1", 800, 600);
		
		nave = new Nave(400, 550);
		proyectilNave = new ProyectilNave(400, 550);		
		this.asteroides = new Asteroide[5]; 
		this.destructores = new Destructor[5]; 
		this.proyectilesDestructor = new ProyectilDestructor[5]; 
		
		for (int i = 0; i < this.destructores.length; i++) { 
			
			this.destructores[i] = null; 
		} 
		
		for (int i = 0; i< this.asteroides.length; i++) {
			
			if(i == 0) {
				this.asteroides[i] = new Asteroide();
			}
			else {
				this.asteroides[i] = null; 
			}
			
		}
		
		for (int i = 0; i< this.proyectilesDestructor.length; i++) {
			
			this.proyectilesDestructor[i] = null;

			
		}

		disparo = false; 
		perdio = false;
		
		tiempo = 0;
		muertos = 0;
		dispararDestructor = 0;
		gen = new Random();
		
		
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick(){ 
		
		tiempo += 1;
		
		/**NAVE**/
		
		nave.dibujarse(entorno);

		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			nave.moverIzquierda();
		}
		
		if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			nave.moverDerecha();
		}
		
		if(entorno.estaPresionada(entorno.TECLA_ESPACIO) && !disparo) {
			disparo = true;
			proyectilNave.x = nave.x;
		}
		
		if(disparo) {
      		if(proyectilNave.y > 0) {
      			proyectilNave.subir();
				proyectilNave.dibujarse(entorno);		
			}  
      		
      		else {
        		disparo = false;
        		proyectilNave.y = 600;    			
      		}
		}		
		
		
		
		/**ASTEROIDES**/
		
		if(hayNullAsteroides(asteroides) && tiempo % 70 == 0) {
			
			for (int i = 0; i< this.asteroides.length; i++) {
				if (this.asteroides[i] == null) {
					this.asteroides[i] = new Asteroide();
					break;
				}
			}
		}
		
			
		for (int i = 0; i< this.asteroides.length; i++) {
			
			if(this.asteroides[i] != null) {
				
				this.asteroides[i].dibujarse(entorno);
				this.asteroides[i].avanzar();
				
				if(nave.colisionAsteroide(this.asteroides[i])) {
						//System.exit(0);		
				}
				
				if(proyectilNave.colisionoAsteroide(this.asteroides[i])) {
	        		disparo = false;
	        		proyectilNave.y = 600;
				}	
				
				if(this.asteroides[i].getY() > 600) {
					this.asteroides[i] = null;
				}
			}
		}
		
		
		
		/**DESTRUCTORES**/
		
		if(hayNullDestructores(destructores) && tiempo % 200 == 0) {
			
			for (int i = 0; i< this.destructores.length; i++) {
				if (this.destructores[i] == null) {
					this.destructores[i] = new Destructor();
					break;
				}
			}
		}
		
		
		for (int i = 0; i < destructores.length ; i++) { 

			// DIBUJA LOS DESTRUCTORES  
			if (this.destructores[i] != null) { 
				
				destructores[i].dibujarse(entorno); 
				destructores[i].avanzar(); 
				
				if(gen.nextInt(100) == 1) {
					destructores[i].cambiarDireccion();
				}
				
				if(nave.colisionDestructor(this.destructores[i])) {
					//System.exit(0);		
				}
				
				// BORRA DE LA PANTALLA LOS DESTRUCTORES MUERTOS 
				if (proyectilNave.colisionoDestructor(destructores[i])) { 
					disparo = false;  
					destructores[i] = null; 
					muertos++; 
				}	
				
				if(this.destructores[i].getY() > 600) {
					destructores[i] = null;
				}				
			}
		}
		
		
		
		for (int i = 0 ; i < proyectilesDestructor.length ; i++) {
			
			if (this.destructores[i] != null) {
				
				if(proyectilesDestructor[i] == null) {
					
					if(gen.nextInt(10) == 1) {
						proyectilesDestructor[i] = new ProyectilDestructor(destructores[i].getX(), destructores[i].getY());
					}				
				}
				
				else {
					proyectilesDestructor[i].dibujarse(entorno);
					proyectilesDestructor[i].bajar(); 
					
					if(nave.colisionProyectilDestructor(proyectilesDestructor[i])) {
						//System.exit(0);
					}
					
					if(proyectilesDestructor[i].y > 600) {
						proyectilesDestructor[i] = null;
					}
				}
			}
		}
		   
		
		/** COLISION ASTEROIDES Y DESTRUCTORES no andaaaaaaaaaa**/
		
//		for(int d = 0 ; d < destructores.length ; d++) {
//			
//			for(int a = 0 ; a < asteroides.length ; a++) {
//				
//				if(destructores[d] != null && asteroides[a] != null) {
//					
//					if(destructores[d].colisionaAsteroide(asteroides[a])) {
//						
//						destructores[d].cambiarDireccion();
//					}
//				}
//			}
//		}


	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
	
	
	
	/** FUNCIONES **/
	
	public static boolean hayNullAsteroides(Asteroide[] asteroides) {
		
		for(Asteroide a : asteroides) {
			if(a == null) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public static boolean hayNullDestructores(Destructor[] destructores) {
		
		for(Destructor a : destructores) {
			if(a == null) {
				return true;
			}
		}
		
		return false;
	}
	
}



