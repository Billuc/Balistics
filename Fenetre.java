import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame implements ActionListener{

	private Panneau pan = new Panneau();
	JPanel container = new JPanel();
	JButton bouton = new JButton("Pret !");
	JButton terre = new JButton("Mode Terre !");
	JButton lune = new JButton("Mode Lune !");
	JButton jupiter = new JButton("Mode Jupiter !");
	JButton again = new JButton("Rejouer !");
	
	public Fenetre(){
		this.setTitle("Shoot'Em");
		this.setSize(700,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.BLUE);
		
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);
		
		bouton.addActionListener(this);
		terre.addActionListener(this);
		lune.addActionListener(this);
		jupiter.addActionListener(this);
		again.addActionListener(this);
		
		JPanel north = new JPanel();
		north.add(terre);
		north.add(lune);
		north.add(jupiter);
		
		JPanel south = new JPanel();
		south.add(bouton);
		south.add(again);
		
		again.setEnabled(false);
		terre.setEnabled(false);
		lune.setEnabled(false);
		jupiter.setEnabled(false);
		
		container.add(north, BorderLayout.NORTH);
		container.add(south, BorderLayout.SOUTH);	
		
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == bouton) {
			pan.click++;
			if (pan.click == 2){
				bouton.setEnabled(false);
				again.setEnabled(true);
				terre.setEnabled(true);
				lune.setEnabled(true);
				jupiter.setEnabled(true);
			}
			pan.repaint();
		}
		if(arg0.getSource() == terre) {
			pan.grav = 9.81;
			pan.coul = new Color(0,255,255);
		}
		if (arg0.getSource() == lune) {
			pan.grav = 1.62;
			pan.coul = new Color(192,192,192);
		}
		if (arg0.getSource() == jupiter) {
			pan.grav = 24.8;
			pan.coul = new Color(255,165,79);
		}
		if(arg0.getSource()==again) {
			pan.click++;
			again.setEnabled(false);
			bouton.setEnabled(true);
			pan.repaint();
		}
	}
}