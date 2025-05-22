package juego;

import java.awt.*;

import entorno.*;

public class ProyectilDestructor {
	double x;
	double y;
	double velocidad;
	Image img;

	public ProyectilDestructor(double x, double y) {
		this.x = x;
		this.y = y;
		this.velocidad = 2;
		this.img = Herramientas.cargarImagen("imagenes/proyectil-destructor.png");
	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0, 0.03);
	}

	public void bajar() {
		this.y += velocidad;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
}
