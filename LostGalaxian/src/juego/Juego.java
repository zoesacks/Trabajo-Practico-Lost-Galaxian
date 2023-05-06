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

	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian - Grupo 3 - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		nave = new Nave(400, 550);
		proyectilNave = new ProyectilNave(400, 550);
		disparo = false; 
		perdio = false;
		
		this.asteroides = new Asteroide[5];
		for (int i = 0; i< this.asteroides.length; i++) {
			this.asteroides[i] = new Asteroide();
		}
		
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick()
	{ 
		nave.dibujarse(entorno);

		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			nave.moverIzquierda();
		}
		if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			nave.moverDerecha();
		}
		
		for (Asteroide a : this.asteroides) {
			a.dibujarse(entorno);
			a.avanzar();
			if(nave.colision(a)) {
					System.exit(0);		
			}
			if(proyectilNave.colisionoAsteroide(a)) {
        		disparo = false;
        		proyectilNave.y = 600;
			}
		}
		
		//pregunto disparo para que solo se pueda hacer una por vez
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

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
