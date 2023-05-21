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
	
	public DestructorJefe ()
	{
		this.x = 380;
		this.y = 10;
		this.img = Herramientas.cargarImagen("imagenes/destructor.gif");
		this.angulo = 0;

	}
	public void dibujarse(Entorno entorno){
	
		entorno.dibujarImagen(img, x, y, angulo, 0.7);
	
	}
	public void mover() {
		if (this.y < 90) {
			this.y += 2 * Math.cos(this.angulo);
		}
		else {
		this.x+= 1 * Math.cos(this.angulo);
		//SE MUEVE DE UN LADO AL OTRO PERO DA VUELTA LA NAVE :(
			if (this.x < 100 )
			   {
				this.x = 100; 
			    this.angulo = Math.PI - this.angulo;
			   }
			if (this.x > 700 )
			   {
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
