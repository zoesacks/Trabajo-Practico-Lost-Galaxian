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
		this.img = Herramientas.cargarImagen("destructor.png"); 
		this.establecerPosicion(); 
		this.velocidad = 2.0; 
		this.signo = new int[2]; 
		this.signo[0] = -1; 
		this.signo[1] = 1; 
	} 
	  
	public void dibujarse(Entorno entorno) { 
		entorno.dibujarImagen(img, x, y, angulo); 
	} 
	  
	private void establecerPosicion() { 
		Random gen = new Random(); 
		this.x = gen.nextInt(600) + 1; 
		this.y = gen.nextInt(20) + 1; 
		this.signo = new int[2]; 
		this.signo[0] = -1; 
		this.signo[1] = 1; 
		this.angulo = Math.PI/2 + Math.PI/4 * signo[gen.nextInt(2)]; 
	} 
	  
	public void avanzar() {
		this.y+= this.velocidad * Math.sin(this.angulo);
		this.x+= this.velocidad * Math.cos(this.angulo);
			
			if (this.x < 20 )
			   {
				this.x = 20; 
			    this.angulo = Math.PI - this.angulo;
			   }
			if (this.x > 780 )
			   {
				this.x = 780;
				this.angulo = Math.PI - this.angulo;
			   }
		}	 
	 
	public void cambiarDireccion() {
		this.angulo = Math.PI - this.angulo;
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

//	public boolean colisionaAsteroide(Asteroide asteroide) {
//		return ((asteroide.getY() > this.x - 100 && asteroide.getY() < this.x + 100) && (asteroide.getX() > this.x - 100 && asteroide.getX() < this.x + 10));
//	}

	  
}