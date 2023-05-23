package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class ProyectilJefe {
	double x;
	double y;
	double velocidad;
	Image img;

	public ProyectilJefe(double x, double y) {
		this.x = x;
		this.y = y;
		this.img = Herramientas.cargarImagen("imagenes/proyectil-destructor.png");
		this.velocidad = 5;

	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0, 0.06);

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
