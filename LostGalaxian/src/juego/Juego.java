package juego;

import entorno.*;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	Nave nave;
	Asteroide[] asteroides;
	ProyectilNave proyectilNave;
	boolean disparo, perdio;
	int tiempo;

	Juego()
	{

		this.entorno = new Entorno(this, "Lost Galaxian - Grupo 3 - v1", 800, 600);
		
		nave = new Nave(400, 550);
		proyectilNave = new ProyectilNave(400, 550);		
		this.asteroides = new Asteroide[6];
		
		for (int i = 0; i< this.asteroides.length; i++) {
			
			if(i == 0) {
				this.asteroides[i] = new Asteroide();
			}
			else {
				this.asteroides[i] = null;
			}
			
		}		

		disparo = false; 
		perdio = false;
		
		tiempo = 0;
		
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
		
		/** nave **/
		
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
		
		
		
		/**asteroides**/
		
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
				
				if(nave.colision(this.asteroides[i])) {
						System.exit(0);		
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
	
}



