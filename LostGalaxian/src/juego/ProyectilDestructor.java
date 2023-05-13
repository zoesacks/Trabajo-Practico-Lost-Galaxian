package juego;

import java.awt.*;

import entorno.*;

public class ProyectilDestructor {
	double x; 
	double y; 
	double angulo; 
	Image img; 
	  
	public ProyectilDestructor(double x, double y) { 
		this.x = x; 
		this.y = y; 
		this.angulo = 0; 
		this.img = Herramientas.cargarImagen("proyectil-destructor.png"); 
		//  bajar(); 
	} 
	
	
	public void dibujarse(Entorno entorno) { 
		entorno.dibujarImagen(img, x, y, angulo);
	} 
	
	public void bajar() { 
		this.y += Math.cos(this.angulo) * 2;
	} 
	
	public double getX() { 
		return this.x; 
	} 
	
	public double getY() { 
		return this.y; 
	} 
}
