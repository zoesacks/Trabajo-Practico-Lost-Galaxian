package juego;

import java.awt.*;
//import java.util.Arrays;
import java.util.Random;

import entorno.*;

public class Juego extends InterfaceJuego {
 	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo
	Nave nave;
	ProyectilNave proyectilNave;
	Vida vida;
	Asteroide[] asteroides;
	Destructor[] destructores;
	ProyectilDestructor[] proyectilesDestructor;
	DestructorJefe destJefe;
	ProyectilJefe proyectilJefe;

	boolean perdio, menu, juego, abandonar, reintentar, reintento, vidaExtra;
	int tiempo, muertos, dispararDestructor, vidas, vidaDestJefe;
	Random gen;
	Color colorTexto;
	Image fondo1, fondoInicio1, fondoPerdio, reintentarGris, reintentarColor, abandonarGris, abandonarColor, corazon;

	Juego() {

		this.entorno = new Entorno(this, "Lost Galaxian - Grupo 3 - v1", 800, 600);

		nave = new Nave(400, 500);
		proyectilNave = null;
		this.asteroides = new Asteroide[5];
		this.destructores = new Destructor[5];
		this.proyectilesDestructor = new ProyectilDestructor[5];
		destJefe = new DestructorJefe();
  		proyectilJefe = null;
		
		vidaExtra = false;

		for (int i = 0; i < this.destructores.length; i++) {
			this.destructores[i] = null;
		}

		for (int i = 0; i < this.asteroides.length; i++) {
			this.asteroides[i] = null;
		}

		for (int i = 0; i < this.proyectilesDestructor.length; i++) {
			this.proyectilesDestructor[i] = null;

		}
				
		vida = null;
		tiempo = 40;
		muertos = 0;
		dispararDestructor = 0;
		vidas = 5;
		vidaDestJefe = 3;
		
		abandonar = false;
		reintentar = true;
		menu = true;
		juego = false;
		perdio = false;
		reintento = false;
		
		gen = new Random();
		
		fondoInicio1 = Herramientas.cargarImagen("imagenes/fondo-inicio1.gif");
		fondoPerdio = Herramientas.cargarImagen("imagenes/game-over.gif");
		fondo1 = Herramientas.cargarImagen("imagenes/fondo.gif");	
		reintentarGris = Herramientas.cargarImagen("imagenes/reintentar-gris.png") ;
		reintentarColor = Herramientas.cargarImagen("imagenes/reintentar-color.png") ; 
		abandonarGris = Herramientas.cargarImagen("imagenes/abandonar-gris.png") ;
		abandonarColor = Herramientas.cargarImagen("imagenes/abandonar-color.png"); 
		corazon = Herramientas.cargarImagen("imagenes/corazon.png");
		
		
		colorTexto = new Color(255, 255, 255);

		this.entorno.iniciar();
	
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		if (menu) {
			InicioMenu();
		}
		if(juego) {
			InicioJuego();
		}
		if (perdio && !juego) {
			GameOver();
		}
//		if (!perdio && !juego) {
//			
//		}
	}
	
	public void InicioMenu() {
		if(entorno.sePresiono(entorno.TECLA_ENTER)) {
			juego = true;
			menu = false;
			return;
		}

		entorno.dibujarImagen(fondoInicio1, 400, 300, 0, 0.6);

	}
	
	public void InicioJuego() {
		tiempo += 1;
		
		/**FONDO**/
		entorno.dibujarImagen(fondo1, 400, 300, 0, 1.9);
		
		for (int i = 0; i < vidas; i++) {
			entorno.dibujarImagen(corazon, 30 + i * 35, 30, 0, 0.3);
		}
		
		/**DIBUJAR CANTIDAD DE ENEMIGOS ELIMINADOS**/
		entorno.cambiarFont("Arial", 18, colorTexto);
		entorno.escribirTexto("Destructores eliminados: " + muertos, 20, 580);
		
		
		if(reintento) {
			proyectilNave = null;
			
			for (int i = 0; i < this.destructores.length; i++) {
				this.destructores[i] = null;
			}

			for (int i = 0; i < this.asteroides.length; i++) {
				this.asteroides[i] = null;
			}

			for (int i = 0; i < this.proyectilesDestructor.length; i++) {
				this.proyectilesDestructor[i] = null;

			}
			
			tiempo = 40;
			muertos = 0;
			dispararDestructor = 0;
			vidas = 5;
			vidaExtra = false;
			
			reintento = false;
		}
		
		
		/** NAVE **/

		nave.dibujarse(entorno);

		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			nave.moverIzquierda();
		}

		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			nave.moverDerecha();
		}

		if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && proyectilNave == null) {
			proyectilNave = new ProyectilNave(nave.x, 450);
		}

		if (proyectilNave != null) {
			if (proyectilNave.y > 0) {
				proyectilNave.subir();
				proyectilNave.dibujarse(entorno);
			}

			else {
				proyectilNave = null;
			}
		}
		
		/**CREACION DE NUEVOS ASTEROIDES**/

				
		if (tiempo%90== 0 && muertos<3) {
					for (int i=0; i<this.asteroides.length; i++) {
						if (this.asteroides[i] == null) {
							this.asteroides[i] = new Asteroide();
							break;
						}

					}
			
		}
		
		/**CREACION DE NUEVOS DESTRUCTORES **/
				
		if (tiempo % 140 == 0 && muertos<3){
		
					for (int i=0; i<this.destructores.length; i++) {
						if (this.destructores[i] == null) {
								this.destructores[i] = new Destructor();
								break;
						}

					}
				
				
			} 
		
		/** VIDA EXTRA **/
		
		if (tiempo% 1000 == 0 && vidas < 5) {
			vida = new Vida();
			vidaExtra = true;
		}
		
		if (vidaExtra) {
			
			vida.dibujarse(entorno);
			vida.avanzar();
			
			if (nave.colisionVida(vida)) {
				vidas++;
				vida = null;
				vidaExtra = false;
			}
			
			if (vida != null && vida.getY() > 600) {
				vida = null;
				vidaExtra = false;
			}
			
		}

		/** ASTEROIDES **/

		for (int i = 0; i < this.asteroides.length; i++) {

			if (this.asteroides[i] != null) {
				this.asteroides[i].dibujarse(entorno);
				this.asteroides[i].avanzar();
			}
			
			if (this.asteroides[i] != null && nave.colisionAsteroide(this.asteroides[i])) {
	        	vidas--;
	        	this.asteroides[i] = null;
			}

			if (this.asteroides[i] != null && proyectilNave != null) {

				if (proyectilNave.colisionoAsteroide(this.asteroides[i])) {
					proyectilNave = null;
				}
			}

			if (this.asteroides[i] != null && this.asteroides[i].getY() > 600) {
				this.asteroides[i] = null;
			}
		}
		

		/** DESTRUCTORES**/
		
		for (int i = 0; i < destructores.length; i++) {

			// DIBUJA LOS DESTRUCTORES
			if (this.destructores[i] != null) {
				
				if (gen.nextInt(200) == 1 ) {
					destructores[i].cambiarDireccion();
				}
				
				destructores[i].dibujarse(entorno);
				destructores[i].avanzar();
			}

			if (this.destructores[i] != null && nave.colisionDestructor(this.destructores[i])) {
				vidas--;
				destructores[i] = null;
			}
			
			if (this.destructores[i] != null && proyectilNave != null && proyectilNave.colisionoDestructor(destructores[i])) {
				proyectilNave = null;
				destructores[i] = null;
				muertos++;
			}					

			if (this.destructores[i] != null && this.destructores[i] != null && this.destructores[i].getY() > 600) {
				destructores[i] = null;
			}
		}
		
		
		for (int i = 0; i < proyectilesDestructor.length; i++) {
			if (this.destructores[i] != null && proyectilesDestructor[i] == null) {
				if (gen.nextInt(10) == 1) {
					proyectilesDestructor[i] = new ProyectilDestructor(destructores[i].getX(), destructores[i].getY());
				}

			}
			
			if (proyectilesDestructor[i] != null) {
				proyectilesDestructor[i].dibujarse(entorno);
				proyectilesDestructor[i].bajar();	
			}
			
			if (proyectilesDestructor[i] != null && nave.colisionProyectilDestructor(proyectilesDestructor[i])) {
				vidas--;	
				proyectilesDestructor[i] = null;
			}
			
			if (proyectilesDestructor[i] != null && proyectilesDestructor[i].y > 600) { 
				proyectilesDestructor[i] = null;
			}
			
		}
		/**DESTRUCTOR JEFE**/
		if(muertos >=3 && vidaDestJefe > 0) {
			destJefe.mover();
			destJefe.dibujarse(entorno);
			
		}
		
		//PROYECTILES JEFE
		
		if (muertos >=3 && vidaDestJefe > 0 && proyectilJefe == null) {
			if (gen.nextInt(2) == 1) {
				proyectilJefe = new ProyectilJefe(destJefe.getX(), destJefe.getY());
					
			}
		}
			
		if (proyectilJefe != null) {
			proyectilJefe.dibujarse(entorno);
			proyectilJefe.bajar();
		}
			
		if (proyectilJefe != null && nave.colisionProyectilJefe(proyectilJefe)) {
			vidas--;	
			proyectilJefe = null;
		}
			
		if (proyectilJefe != null && proyectilJefe.y > 600) { 
			proyectilJefe = null;
		}
		
		if (proyectilNave.colisionoDestructorJefe(this.destJefe)) {
			vidaDestJefe --;
			proyectilNave = null;
		}
	
		if(vidas == 0) {
			juego = false;
			perdio = true;
			return;		
		} 
		
	}
	
	
	public void GameOver() {
		entorno.dibujarImagen(fondoPerdio, 400, 300, 0, 0.6);
		
		if(reintentar) {
			entorno.dibujarImagen(reintentarColor, 300, 400, 0, 1);	
			entorno.dibujarImagen(abandonarGris, 505, 400, 0, 1);
			
			if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				reintentar = false;
				abandonar = true;
			}
			
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				juego = true; 
				reintento = true;
				return;
			}
		}
		
		if(abandonar) {
			entorno.dibujarImagen(abandonarColor, 505, 400, 0, 1);
			entorno.dibujarImagen(reintentarGris, 300, 400, 0, 1);
			
			if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				abandonar = false;
				reintentar = true;
			}
			
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				System.exit(0);
			}
		}
		
		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
