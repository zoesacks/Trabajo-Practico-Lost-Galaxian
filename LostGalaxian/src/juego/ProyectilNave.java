package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class ProyectilNave {
	double x;
	double y;
	double angulo;
	Image img;
	
	public ProyectilNave(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.img = Herramientas.cargarImagen("proyectil-nave.png");
		this.angulo = 0;

	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, angulo);;
	
	}

	public void subir() {
		this.y -= Math.cos(this.angulo)*10;
		
	}

	public boolean colisionoAsteroide(Asteroide asteroide) {
		return ((asteroide.getY() > this.y - 20 && asteroide.getY() < this.y + 20)  && (asteroide.getX() > this.x -20 && asteroide.getX() < this.x + 20));
	}
}
