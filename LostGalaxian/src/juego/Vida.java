package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Vida {
	private double x, y;
	private double velocidad;
	Image img;

	public Vida() {
		this.img = Herramientas.cargarImagen("imagenes/vida-extra.png");
		this.establecerPosicion();
		this.velocidad = 1;
	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0, 0.09);
	}

	private void establecerPosicion() {

		Random gen = new Random();
		this.x = gen.nextInt(800) + 1;
		this.y = gen.nextInt(20) + 1;
	}

	public void avanzar() {
		this.y += this.velocidad;
	}

	public double getY() {
		return this.y;
	}

	public double getX() {
		return this.x;
	}

}
