import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JFrame;


public final class Main extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int screenW = 640;
	public static final int screenH = 480;

	public Main(){
		
	}
	
	private static GraphNode<String> head;
	
	public void paint(Graphics g){
		GraphNode.refreshAccessFlag();
		head.draw(g);
	}
	
	public static void main(String[] args) {
		GraphNode<String> n1 = new GraphNode<>("node 1");
		GraphNode<String> n2 = new GraphNode<>("node 2");
		GraphNode<String> n3 = new GraphNode<>("node 3");
		GraphNode<String> n4 = new GraphNode<>("node 4");
		
		n1.addNeighbour(n2);
		n2.addNeighbour(n3);
		n3.addNeighbour(n4);
		n4.addNeighbour(n1);
		
		head = new GraphNode<>("head");
		n1.addNeighbour(head);
		n2.addNeighbour(head);
		
		Main m = new Main();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(screenW, screenH);
		frame.getContentPane().add(m);
		frame.setVisible(true);
		
		
		
		
		
		
		GraphNode.refreshAccessFlag();
		

	}
}
