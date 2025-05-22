package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class ProyectilNave {
	double x;
	double y;
	double velocidad;
	Image img;

	public ProyectilNave(double x, double y) {

		this.x = x;
		this.y = y;
		this.img = Herramientas.cargarImagen("imagenes/proyectil-nave.png");
		this.velocidad = 10;

	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0, 0.04);

	}

	public void subir() {
		this.y -= velocidad;

	}

	public boolean colisionoAsteroide(Asteroide asteroide) {
		return ((asteroide.getY() > this.y - 20)
				&& (asteroide.getX() + 10 > this.x - 20 && asteroide.getX() - 10 < this.x + 10));
	}

	public boolean colisionoDestructor(Destructor destructor) {
		return ((destructor.getY() > this.y - 20)
				&& (destructor.getX() + 10 > this.x - 20 && destructor.getX() - 10 < this.x + 20));
	}

	public boolean colisionoDestructorJefe(DestructorJefe destructorJefe) {
		return ((destructorJefe.getY() > this.y - 20)
				&& (destructorJefe.getX() + 60 > this.x - 20 && destructorJefe.getX() - 60 < this.x + 20));
	}
}
