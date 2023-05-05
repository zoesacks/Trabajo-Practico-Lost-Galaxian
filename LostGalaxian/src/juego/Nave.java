package juego;

import java.awt.Image;
import entorno.*;

public class Nave {
	// Variables de instancia
	double x;
	double y;
	double angulo;
	Image img;
	
	public Nave(int x, int y) 
	{
		this.x = x;
		this.y = y;
		this.img = Herramientas.cargarImagen("nave.png");
		this.angulo = 0;

	}


	public void dibujarse(Entorno entorno)
	{
	
		entorno.dibujarImagen(img, x, y, angulo);;
	
	}
	
	public void moverIzquierda() {
		if(x > 60) {
			this.x -= Math.cos(this.angulo)*20;		
		}

	}
	
	public void moverDerecha() {
		if(x < 740) {
			this.x += Math.cos(this.angulo)*20;		
		}
	}
}
