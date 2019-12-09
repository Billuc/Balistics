import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
 
public class Panneau extends JPanel{
	int click = 0;
	boolean plus = true;
	double angle = 45;
	double force = 50;
	int dx;
	int dy;
	int xp = 100;
	int yp = 400;
	double t = 0.1;
	int x;
	int y;
	double grav = 9.81;
	int xr= (int)(250+Math.random()*((4050/grav)-50));
	int cote = 50;
	Color coul = new Color(0,255,255);
	boolean collide = false;
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		if(click<2) {
			collide = false;
			g2.setStroke(new BasicStroke(3.0f));
			g2.setColor(coul);
			g2.fillRect(0,0,700,600);
			g2.drawLine(100,400,100+dx,400-dy);
			g2.setColor(Color.BLACK);
			g2.fillRect(99,0,2,600);
			g2.fillRect(0,399,700,2);
			g2.setColor(Color.GREEN);
			if (xr>=650){
				xr=(int)(250+Math.random()*((675/grav)-50));
			}
			g2.fillRect(xr,400-cote,cote,cote);
			g2.setColor(Color.RED);
			dx = (int)(force*Math.cos(Math.toRadians(angle)));
			dy = (int)(force*Math.sin(Math.toRadians(angle)));		
			g2.drawLine(100,400,100+dx,400-dy);
			animFleche();
			this.repaint();
		} else if(click == 2) {
			g2.setStroke(new BasicStroke(2.0f));
			g2.setColor(Color.BLUE);
			if ((yp<=400)&&(xp<=700)&&(!collide)) {
				x = ((int)(force*t*Math.cos(Math.toRadians(angle))))+100;
				y = (int)((grav/2)*t*t)-((int)(force*t*Math.sin(Math.toRadians(angle))))+400;
				t+=0.1;
				if ((x>=xr)&&(x<=xr+cote)){
					double tr1 = (xr-100)/(force*Math.cos(Math.toRadians(angle)));
					double delta = (force*Math.sin(Math.toRadians(angle)))*(force*Math.sin(Math.toRadians(angle)))-2*cote*grav;
					double tr2 = 0;
					if (delta>=0) {
						tr2 = ((force*Math.sin(Math.toRadians(angle)))+Math.sqrt(delta))/grav;
					}
					double yr1= ((grav/2)*tr1*tr1)-(force*tr1*Math.sin(Math.toRadians(angle)))+400;
					double xr1= (force*tr2*Math.cos(Math.toRadians(angle)))+100;
					if ((yr1>=400-cote)&&(yr1<=400)) {
						g2.drawLine(xp,yp,xr,(int)(yr1));
						g2.setColor(Color.magenta);
						g2.drawString("Bravo ! Tu as atteint la cible a une distance de "+(xr-100)+" m !",370,50);
						collide = true;
					} else if ((xr1>=xr)&&(xr1<=xr+cote)) {
						g2.drawLine(xp,yp,(int)(xr1),400-cote);
						g2.setColor(Color.magenta);
						g2.drawString("Bravo ! Tu as atteint la cible a une distance de "+(xr-100)+" m !",370,50);
						collide = true;
					} else {
						g2.drawLine(xp,yp,x,y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {}
					}
				} else {
					if ((x<700)&&(y>=400)){
						double ta = 2*force*Math.sin(Math.toRadians(angle))/grav;
						int xa = (int)(force*Math.cos(Math.toRadians(angle))*ta)+100;
						g2.drawLine(xp,yp,xa,400);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {}
					} else {
						
						g2.drawLine(xp,yp,x,y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {}
					}
				}
				this.repaint();
			} else if(!collide) {
				g2.setColor(Color.magenta);
				g2.drawString("Loupe ! Dommage ! Try again",450,50);
				this.repaint();
			}
			xp=x;
			yp=y;
			g2.setColor(coul);
			g2.fillRect(520,420,200,100);
			g2.setColor(Color.magenta);
			g2.drawString((xp-100)+" m !",550,450);
		} else {
			click = 0;
			xp=100;
			yp=400;
			t=0.1;
			angle = 45;
			force = 50;
			plus = true;
			xr= (int)(250+Math.random()*((4050/grav)-100));
			this.repaint();
		}
	}
	
	public void animFleche(){
		if ((click==0)&&(plus)) {
			angle++;
			if (angle==90){
				plus = false;
			}
		} else if ((click==0)&&(!plus)) {
			angle--;
			if (angle==0){
				plus=true;
			}
		} else if((click==1)&&(plus)) {
			force+=2;
			if (force==90){
				plus = false;
			}
		} else if((click==1)&&(!plus)) {
			force-=2;
			if (force==30){
				plus = true;
			}
		}
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {}
	}
}