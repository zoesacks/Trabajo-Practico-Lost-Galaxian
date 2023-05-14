package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class ProyectilNave {
	double x;
	double y;
	double angulo;
	Image img;
	
	public ProyectilNave(double x, double y) {
		
		this.x = x;
		this.y = y;
		this.img = Herramientas.cargarImagen("proyectil-nave.png");
		this.angulo = 0;

	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0, 0.04);
	
	}

	public void subir() {
		this.y -= Math.cos(this.angulo)*5;
		
	}

	public boolean colisionoAsteroide(Asteroide asteroide) {
		return ((asteroide.getY() > this.y - 20 )  && (asteroide.getX() > this.x -30 && asteroide.getX() < this.x + 20));
	}

	public boolean colisionoDestructor(Destructor destructor) { 
		  return ((destructor.getY() > this.y - 20 && destructor.getY() < this.y + 20)  && (destructor.getX() > this.x -30 && destructor.getX() < this.x + 30)); 
		 }
}
