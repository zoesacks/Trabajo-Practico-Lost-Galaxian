package juego;

import java.awt.Image;
import entorno.*;

public class Nave {
	double x;
	double y;
	double angulo;
	Image img;

	public Nave(double x, double y) {
		this.x = x;
		this.y = y;
		this.img = Herramientas.cargarImagen("imagenes/nave3.gif");
		this.angulo = 0;

	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, angulo, 0.5);
	}

	public void moverIzquierda() {
		if (x > 60) {
			this.x -= Math.cos(this.angulo) * 5;
		}
	}

	public void moverDerecha() {
		if (x < 740) {
			this.x += Math.cos(this.angulo) * 5;
		}
	}

	/**COLISIONES**/
	
	public boolean colisionAsteroide(Asteroide asteroide) {
		return (asteroide.getY() + 125 > 500 && (asteroide.getX() + 10 > this.x - 50 && asteroide.getX() - 20 < this.x + 50));
	}

	public boolean colisionDestructor(Destructor destructor) {
		return (destructor.getY() + 100 > 500 && (destructor.getX() + 40 > this.x - 50 && destructor.getX() - 40 < this.x + 50));
	}

	public boolean colisionProyectilDestructor(ProyectilDestructor proyectilDestructor) {
		return (proyectilDestructor.getY() + 100 > 500 && (proyectilDestructor.getX() + 10 > this.x - 50 && proyectilDestructor.getX() - 10  < this.x + 50));

	}

	public boolean colisionProyectilJefe(ProyectilJefe proyectilJefe) {
		return (proyectilJefe.getY() + 100 > 500 && (proyectilJefe.getX() + 20 > this.x - 50 && proyectilJefe.getX() - 20 < this.x + 50));
	}

	public boolean colisionVida(Vida vida) {
		return (vida.getY() + 140 > 500 && (vida.getX() + 10 > this.x - 50 && vida.getX() - 10 < this.x + 50));
	}
}
