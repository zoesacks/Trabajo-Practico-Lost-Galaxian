package juego;

import entorno.*;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	Nave nave;
	ProyectilNave proyectilNave;
	boolean disparo;
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian - Grupo 3 - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		nave = new Nave(400, 550);
		proyectilNave = new ProyectilNave(400, 550);
		disparo = false;
		
		
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
