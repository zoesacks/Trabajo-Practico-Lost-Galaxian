package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Asteroide {

	private double x, y;
	private double angulo;
	private double velocidad;
	private int[] signo;
	Image img;

	public Asteroide() {
		this.img = Herramientas.cargarImagen("imagenes/asteroide.gif");
		this.establecerPosicion();
		this.velocidad = 1;
		this.signo = new int[2];
		this.signo[0] = -1;
		this.signo[1] = 1;
	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0, 0.4);
	}

	private void establecerPosicion() {
		Random gen = new Random();
		this.x = (gen.nextInt(800) + 1);
		this.y = gen.nextInt(20) + 1;
		this.signo = new int[2];
		this.signo[0] = -1;
		this.signo[1] = 1;
		this.angulo = Math.PI / 2 + Math.PI / 4 * signo[gen.nextInt(2)];
	}

	public void avanzar() {
		this.y += this.velocidad * Math.sin(this.angulo);
		this.x += this.velocidad * Math.cos(this.angulo);

		if (this.x < 20) {
			this.x = 20;
			this.angulo = Math.PI - this.angulo;
		}
		if (this.x > 780) {
			this.x = 780;
			this.angulo = Math.PI - this.angulo;
		}
	}

	public void cambiarDireccion() {
		this.angulo = Math.PI - this.angulo;
	}

	public void cambiarDireccionDerIzq(double d) {
		this.angulo = Math.PI - this.angulo;
		this.x += 15 * d;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAngulo() {
		return angulo;
	}

	public double getY() {
		return this.y;
	}

	public double getX() {
		return this.x;
	}

	public void mover(double n) {
		this.x += 15 * n;

	}

}
