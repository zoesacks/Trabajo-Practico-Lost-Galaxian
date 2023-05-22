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

	//booleanos para pantallas
	boolean termino, menu, juego;
	//booleanos para botones
	boolean abandonar, reintentar;
	//reiniciar todo
	boolean reintento;
	
	int tiempo, muertos, vidas, vidaDestJefe;
	
	Random gen;
	Color colorTexto;
	Image fondo1, fondoInicio1, fondoPerdio, reintentarGris, reintentarColor, abandonarGris, nivel, abandonarColor, volverJuegarColor, volverJugarGris, corazon, win;

	Juego() {

		this.entorno = new Entorno(this, "Lost Galaxian - Grupo 3 - v1", 800, 600);

		nave = new Nave(400, 500);
		proyectilNave = null;
		this.asteroides = new Asteroide[5];
		this.destructores = new Destructor[5];
		this.proyectilesDestructor = new ProyectilDestructor[5];
		destJefe = null;
		proyectilJefe = null;

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
		vidas = 5;
		vidaDestJefe = 5;

		abandonar = false;
		reintentar = true;
		menu = true;
		juego = false;
		termino = false;
		reintento = false;

		gen = new Random();

		fondoInicio1 = Herramientas.cargarImagen("imagenes/fondo-inicio1.gif");
		fondoPerdio = Herramientas.cargarImagen("imagenes/game-over.gif");
		fondo1 = Herramientas.cargarImagen("imagenes/fondo.gif");
		reintentarGris = Herramientas.cargarImagen("imagenes/reintentar-gris.png");
		reintentarColor = Herramientas.cargarImagen("imagenes/reintentar-color.png");
		abandonarGris = Herramientas.cargarImagen("imagenes/abandonar-gris.png");
		abandonarColor = Herramientas.cargarImagen("imagenes/abandonar-color.png");
		volverJuegarColor = Herramientas.cargarImagen("imagenes/volver-jugar-color.png");
		volverJugarGris = Herramientas.cargarImagen("imagenes/volver-jugar-gris.png");
		corazon = Herramientas.cargarImagen("imagenes/corazon.png");
		win = Herramientas.cargarImagen("imagenes/win.gif");
		nivel = Herramientas.cargarImagen("imagenes/nivel.png");

		colorTexto = new Color(255, 255, 255);

		this.entorno.iniciar();

	}

	public void tick() {
		//pantalla "presiona enter para comenzar"
		if (menu) {
			InicioMenu();
		}
		//pantalla del juego
		if (juego) {
			InicioJuego();
		}
		//pantalla de ganaste o perdiste para volver a jugar o abandonar
		if (termino && !juego) {
			ganoPerio();
		}
	}

	public void InicioMenu() {
		if (entorno.sePresiono(entorno.TECLA_ENTER)) {
			juego = true;
			menu = false;
			return;
		}

		entorno.dibujarImagen(fondoInicio1, 400, 300, 0, 0.6);

	}

	public void InicioJuego() {
		tiempo += 1;

		/** FONDO **/
		entorno.dibujarImagen(fondo1, 400, 300, 0, 1.9);

		/** DIBUJAR CANTIDAD DE ENEMIGOS ELIMINADOS **/
		if(destJefe == null) {
			entorno.cambiarFont("Arial", 18, colorTexto);
			entorno.escribirTexto("Destructores eliminados: " + muertos, 570, 35);			
		}

		//Si esta reintentantando/volviendo a jugar se reinician todos los enemigos, destructores y vidas como la primera vez
		if (reintento) {
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

			destJefe = null;
			proyectilJefe = null;
			vida = null;
			vidaDestJefe = 5;
			tiempo = 40;
			muertos = 0;
			vidas = 5;
			reintento = false;

		}
		

		/** NAVE **/

		nave.dibujarse(entorno);

		//moverse
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			nave.moverIzquierda();
		}
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			nave.moverDerecha();
		}
		
		//disparar
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

		
		/** CREACION DE NUEVOS ASTEROIDES **/
		if (tiempo % 90 == 0 && destJefe == null) {
			for (int i = 0; i < this.asteroides.length; i++) {
				if (this.asteroides[i] == null) {
					this.asteroides[i] = new Asteroide();
					break;
				}
			}
		}
		
		/** CREACION DE NUEVOS DESTRUCTORES **/
		if (tiempo % 140 == 0 && destJefe == null) {
			for (int i = 0; i < this.destructores.length; i++) {
				if (this.destructores[i] == null) {
					this.destructores[i] = new Destructor();
					break;
				}
			}
		}

		
		/** ASTEROIDES **/
		for (int i = 0; i < this.asteroides.length; i++) {

			//dibujar
			if (this.asteroides[i] != null) {
				this.asteroides[i].dibujarse(entorno);
				this.asteroides[i].avanzar();
			}

			//asteroide colisiona con nave 
			if (this.asteroides[i] != null && nave.colisionAsteroide(this.asteroides[i])) {
				vidas--;
				this.asteroides[i] = null;
			}

			//proyectil de la nave colisiona con asteroide
			if (this.asteroides[i] != null && proyectilNave != null && proyectilNave.colisionoAsteroide(this.asteroides[i])) {
				proyectilNave = null;
			}
			
			//el asteroide sale de la pantalla
			if (this.asteroides[i] != null && this.asteroides[i].getY() > 600) {
				this.asteroides[i] = null;
			}
			
			//borra el asteroide si esta el jefe final
			if(this.asteroides[i] != null && this.destJefe != null) {
				this.asteroides[i] = null;
			}
		}

		
		/** DESTRUCTORES **/
		for (int i = 0; i < destructores.length; i++) {

			// dibuja y cada una cantidad random de tiempo cambia la direccion
			if (this.destructores[i] != null) {

				if (gen.nextInt(200) == 1) {
					destructores[i].cambiarDireccion();
				}

				destructores[i].dibujarse(entorno);
				destructores[i].avanzar();
			}

			//destructor colisiona con nave
			if (this.destructores[i] != null && nave.colisionDestructor(this.destructores[i])) {
				vidas--;
				destructores[i] = null;
			}

			//proyectil de nave colisiona con destructor
			if (this.destructores[i] != null && proyectilNave != null && proyectilNave.colisionoDestructor(destructores[i])) {
				proyectilNave = null;
				destructores[i] = null;
				muertos++;
			}

			//destructor sale de pantalla
			if (this.destructores[i] != null && this.destructores[i] != null && this.destructores[i].getY() > 600) {
				destructores[i] = null;
			}
			
			//borra los destructores y sus proyectiles cuando aparece el jefe final
			if(this.destJefe != null && this.destructores[i] != null) {
				destructores[i] = null;
			}
		}

		//proyectiles del destructor
		for (int i = 0; i < proyectilesDestructor.length; i++) {
			
			//genera los proyectiles si esta el destructor correspondiente y cada un tiempo random
			if (this.destructores[i] != null && proyectilesDestructor[i] == null) {
				if (gen.nextInt(10) == 1) {
					proyectilesDestructor[i] = new ProyectilDestructor(destructores[i].getX(), destructores[i].getY());
				}
			}

			//dibuja
			if (proyectilesDestructor[i] != null) {
				proyectilesDestructor[i].dibujarse(entorno);
				proyectilesDestructor[i].bajar();
			}

			//proyectil colisiona con nave
			if (proyectilesDestructor[i] != null && nave.colisionProyectilDestructor(proyectilesDestructor[i])) {
				vidas--;
				proyectilesDestructor[i] = null;
			}

			//proyectil sale de la panmtalla
			if (proyectilesDestructor[i] != null && proyectilesDestructor[i].y > 600) {
				proyectilesDestructor[i] = null;
			}
			
			//borra proyectiles de la pantalla si esta el jefefinal
			if(destJefe != null) {
				proyectilesDestructor[i] = null;
			}

		}

		
		/** DESTRUCTOR JEFE **/
		//crea el jefe
		if (destJefe == null && muertos == 3) {
			destJefe = new DestructorJefe();
		}

		//crea el proyectil
		if (destJefe != null && proyectilJefe == null) {
			if (gen.nextInt(2) == 1) {
				proyectilJefe = new ProyectilJefe(destJefe.getX(), destJefe.getY());
			}
		}
		
		if(destJefe != null && destJefe.getY() < 150) {
			entorno.dibujarImagen(nivel, 400, 300, 0, 2);		}
		//dibuja proyectil y jefe
		if (proyectilJefe != null) {
			proyectilJefe.dibujarse(entorno);
			proyectilJefe.bajar();
		}

		if (destJefe != null) {
			destJefe.mover();
			destJefe.dibujarse(entorno);
		}

		//nave coliona con proyectil jefe
		if (proyectilJefe != null && nave.colisionProyectilJefe(proyectilJefe)) {
			vidas--;
			proyectilJefe = null;
		}

		//proyectil jefe sale de la pantalla
		if (proyectilJefe != null && proyectilJefe.y > 600) {
			proyectilJefe = null;
		}

		//proyectil nave colisiona con jefe
		if (destJefe != null && proyectilNave != null && proyectilNave.colisionoDestructorJefe(this.destJefe)) {
			vidaDestJefe--;
			proyectilNave = null;
		}
		
		
		/** VIDA EXTRA **/
		if (tiempo % 500 == 0 && vidas < 5 && destJefe == null) {
			vida = new Vida();
		}

		if (vida != null) {

			vida.dibujarse(entorno);
			vida.avanzar();

			if (nave.colisionVida(vida)) {
				vidas++;
				vida = null;
			}

			if (vida != null && vida.getY() > 600) {
				vida = null;
			}
			
			if(destJefe != null) {
				vida = null;
			}

		}
		
		//dibuja vidas de la nave
		for (int i = 0; i < vidas; i++) {
			entorno.dibujarImagen(corazon, 30 + i * 35, 30, 0, 0.3);
		}
		
		//dibuja vidas del destructor
		if(destJefe != null) {
			for (int i = 0; i < vidaDestJefe; i++) {
				entorno.dibujarImagen(destJefe.img, 770 - i * 35, 30, 0, 0.11);
			}
		}
		
		
		/** GANO o PERDIO **/

		if (vidaDestJefe == 0 || vidas == 0 ) {
			juego = false;
			termino = true;
			return;
		}

	}

	public void ganoPerio() {
		entorno.dibujarImagen(fondoPerdio, 400, 300, 0, 0.6);

		// cartel de ganador
		if (vidaDestJefe == 0) {
			entorno.dibujarImagen(win, 400, 250, 0, 1);
		}

		// si la bandera reintentar esta activada con las flechas del teclado se puede cambiar a abamndonmar o presionar enter para volver al juego
		if (reintentar) {
			if (vidaDestJefe == 0) {
				entorno.dibujarImagen(volverJuegarColor, 300, 400, 0, 1);
			} else {
				entorno.dibujarImagen(reintentarColor, 300, 400, 0, 1);
			}
			entorno.dibujarImagen(abandonarGris, 505, 400, 0, 1);

			if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				reintentar = false;
				abandonar = true;
			}

			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				juego = true;
				reintento = true;
				termino = false;
				return;
			}
		}

		// si la bandera abandonar esta activada con las flechas del teclado se puede cambiar a reintentar o presionar enter para salir
		if (abandonar) {
			if (vidaDestJefe == 0) {
				entorno.dibujarImagen(volverJugarGris, 300, 400, 0, 1);
			} else {
				entorno.dibujarImagen(reintentarGris, 300, 400, 0, 1);
			}

			entorno.dibujarImagen(abandonarColor, 505, 400, 0, 1);

			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				abandonar = false;
				reintentar = true;
			}

			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				System.exit(0);
			}
		}
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
