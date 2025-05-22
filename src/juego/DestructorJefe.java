package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class DestructorJefe {
	// Variables de instancia
	double x;
	double y;
	double angulo;
	Image img;
	double velocidad;
	

	public DestructorJefe() {
		this.x = 380;
		this.y = 40;
		this.img = Herramientas.cargarImagen("imagenes/boss.gif");
		this.angulo = 0;
		this.velocidad = 3;

	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0, 0.6);
	}

	public void mover() {
		if (this.y < 150) {
			this.y += 0.5 * Math.cos(this.angulo);
		} 
		
		else {
			this.x += this.velocidad * Math.cos(this.angulo);
			if (this.x < 100) {
				this.x = 100;
				this.angulo = Math.PI - this.angulo;
			}
			if (this.x > 700) {
				this.x = 700;
				this.angulo = Math.PI - this.angulo;
			}
		}

	}

	public double getY() {
		return this.y;
	}

	public double getX() {
		return this.x;
	}

	public double getAngulo() {
		return this.angulo;
	}
}
