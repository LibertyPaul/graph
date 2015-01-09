import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


public class GraphNode<Value> {
	private static int keyCounter = 0;
	private static int getNewKey(){
		return keyCounter++;
	}
	
	private int key;
	private Value value;
	
	private ArrayList<GraphNode<Value>> neighbours;
	private static int lastAccessFlag = 0;
	private int lastAccess = lastAccessFlag;
	
	private int xCoord;
	private int yCoord;
	private static int minDistance = 200;
	private static int radius = 50;
	
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.fillOval(getX(), getY(), getRadius(), getRadius());
		g.setColor(Color.black);
		g.drawOval(getX(), getY(), getRadius(), getRadius());
		g.drawString((String) value, getX(), getY() + radius / 2);
		setAccessFlag();
		for(GraphNode<Value> node : neighbours){
			g.drawLine(getX() + radius / 2, getY() + radius / 2, node.getX() + radius / 2, node.getY() + radius / 2);
			
			if(node.isAccessed() == false)
				node.draw(g);
		}
	}
	
	private int getX(){
		return xCoord;
	}
	
	private int getY(){
		return yCoord;
	}
	
	private static int getRadius(){
		return radius;
	}
	
	public static void refreshAccessFlag(){
		lastAccessFlag += 1;
	}
	
	private boolean isAccessed(){
		return lastAccess == lastAccessFlag;
	}
	
	private void setAccessFlag(){
		lastAccess = lastAccessFlag;
	}
	
	
	
	public GraphNode(Value value){
		this.key = getNewKey();
		Random r = new Random();
		this.xCoord = r.nextInt(Main.screenW - radius);
		this.yCoord = r.nextInt(Main.screenH - radius);
		this.value = value;
		this.neighbours = new ArrayList<GraphNode<Value>>();
	}
	
	public void die() throws Exception{
		for(GraphNode<Value> node : neighbours)
			removeNeighbour(node);
	}
	
	public int getKey(){
		return key;
	}
	
	public Value getValue(){
		return value;
	}
	
	public void setValue(Value value){
		this.value = value;
	}
	
	public void addNeighbour(GraphNode<Value> node){
		this.neighbours.add(node);
		node.neighbours.add(this);
	}
	
	public void removeNeighbour(GraphNode<Value> node) throws Exception{
		if(this.neighbours.contains(node) == false)
			throw new Exception("No connection");
		if(node.neighbours.contains(this) == false)
			throw new Exception("Broken connection!!!");
		
		this.neighbours.remove(node);
		node.neighbours.remove(this);
	}
	
	public Value DFS(int key){
		if(isAccessed())
			return null;
		
		setAccessFlag();
		
		if(this.key == key)
			return value;
		
		for(GraphNode<Value> node : neighbours){
			Value result = node.DFS(key);
			if(result != null)
				return result;
		}
		return null;
	}
	
	public Value BFS(int key){
		if(isAccessed())
			return null;
		
		setAccessFlag();
		
		if(this.key == key)
			return value;
		
		for(GraphNode<Value> node : neighbours){
			if(node.getKey() == key)
				return node.getValue();
		}
		
		for(GraphNode<Value> node : neighbours){
			Value result = node.BFS(key);
			if(result != null)
				return result;
		}
		return null;
	}
}





