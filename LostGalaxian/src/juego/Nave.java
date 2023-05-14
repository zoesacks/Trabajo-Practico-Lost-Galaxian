package juego;

import java.awt.Image;
import entorno.*;

public class Nave {
	// Variables de instancia
	double x;
	double y;
	double angulo;
	Image img;
	
	public Nave(double x, double y) 
	{
		this.x = x;
		this.y = y;
		this.img = Herramientas.cargarImagen("nave3.gif");
		this.angulo = 0;

	}


	public void dibujarse(Entorno entorno){
	
		entorno.dibujarImagen(img, x, y, angulo, 0.5);
	
	}
	
	
	public void moverIzquierda() {
		if(x > 60) {
			this.x -= Math.cos(this.angulo)*10;		
		}
	}
	
	
	public void moverDerecha() {
		if(x < 740) {
			this.x += Math.cos(this.angulo)*10;		
		}
	}
	
	
	public boolean colisionAsteroide(Asteroide asteroide) {
		return (asteroide.getY() > 375 && (asteroide.getX() > this.x - 60 && asteroide.getX() < this.x + 60)); 
	}
	
	
	public boolean colisionDestructor(Destructor destructor) { 
		return (destructor.getY() > 400 && (destructor.getX() > this.x -80 && destructor.getX() < this.x + 80));  
	} 
		   
	public boolean colisionProyectilDestructor(ProyectilDestructor proyectilDestructor) { 
		return (proyectilDestructor.getY() > 400 && (proyectilDestructor.getX() > this.x - 50 && proyectilDestructor.getX() < this.x + 50)); 
	}
}
