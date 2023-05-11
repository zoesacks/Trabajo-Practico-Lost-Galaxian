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
		this.img = Herramientas.cargarImagen("asteroide.png");
		this.establecerPosicion();
		this.velocidad = 2.0;
		this.signo = new int[2];
		this.signo[0] = -1;
		this.signo[1] = 1;
		//System.out.println(this.x+" "+this.y+" "+this.velocidad+" "+  this.angulo);
	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, angulo);
	}
	
	private void establecerPosicion() {
		Random gen = new Random();
		this.x = gen.nextInt(600) + 1;
		this.y = 20;
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
	
	public double getY() {
		return this.y;
	}

	public double getX() {
		return this.x;
	}


	
	

}
