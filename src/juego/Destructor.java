package juego;

import java.awt.*;
import java.util.*;
import entorno.*;

public class Destructor {
	private double x, y;
	private double angulo;
	private double velocidad;
	private int[] signo;
	Image img;

	public Destructor() {
		this.img = Herramientas.cargarImagen("imagenes/destructor.gif");
		this.establecerPosicion();
		this.velocidad = 1;
		this.signo = new int[2];
		this.signo[0] = -1;
		this.signo[1] = 1;
	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0, 0.3);
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

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public boolean colisionoAsteroide(Asteroide asteroide) {
		return ((asteroide.getY() + 50 > this.y - 30 && asteroide.getY() < this.y + 20)
				&& (asteroide.getX() + 10 > this.x - 40 && asteroide.getX() - 10 < this.x + 40));
	}

	public void cambiarDireccion() {
		this.angulo = Math.PI - this.angulo;
	}

	public void cambiarDireccionDerIzq(double d) {
		this.angulo = Math.PI - this.angulo;
		this.x += 15 * d;
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

	public void mover(double n) {
		this.x += 15 * n;

	}

}
