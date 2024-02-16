/**
* Author: Angela Burns
* November 5, 2018
* project 5
*/
package tag;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import java.awt.geom.Rectangle2D;

import edu.princeton.cs.introcs.StdDraw;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class MovingRectangle {
	
	
	private int xCord = 0;
	private int yCord = 0;
	private int width;
	private int height;
	private int xVelocity;
	private int yVelocity =0;
	private int canvasSize;
	private Color color;
	private boolean frozen;
	private boolean intersect;
	
	public MovingRectangle(int x, int y, int halfWidth, int halfHeight, int velocityX,
			int velocityY, int canvas) {
		//set the canvas
		canvasSize = canvas;
		StdDraw.setCanvasSize(canvasSize,  canvasSize);
		StdDraw.setXscale(0, canvasSize);
        StdDraw.setYscale(0, canvasSize);
        xCord = x;
        yCord = y;
        width = halfWidth;
        height = halfHeight;
        xVelocity = velocityX;
        yVelocity = velocityY;
	}
	
	
	public void move() {

		if ( xCord  < canvasSize ) {
			xVelocity = -xVelocity;
		}
		 if  (xCord > 0) {
			xVelocity = -xVelocity;
		}
		 if ( yCord  > (canvasSize)) {
			yVelocity = -yVelocity ;
		}	
		 if ( yCord  < 0) {
			yVelocity = -yVelocity ;
		}	
		 
		 xCord -= xVelocity;
		 yCord -= yVelocity;
		 draw();

		 if ( (xCord < 1) || (xCord > canvasSize-1) || 
			(yCord< 1) || yCord > (canvasSize-1) ) {
				color = setRandomColor();
		}
		
	}
	
		
		public void draw() {
			StdDraw.setPenColor( setColor(color));
			StdDraw.filledRectangle(xCord, yCord , width, height);
			StdDraw.setPenColor(color.black);	
		}
		
		public Color setColor(Color c) {
				color = c;
			return c;
		}
	
	
		public Color setRandomColor() {
			//available colors
			Color [] randomColor = new Color [] { color.blue, 
						color.green, color.yellow, color.orange, color.pink,
						color.cyan, color.magenta, color.magenta, new Color(153, 50, 204),
						new Color(219,112,147), new Color (127,255,212), new Color (0,100,0), 
						new Color (250,128,114), new Color(0,128,128), new Color (100,149,237),
						new Color (128,128,0), new Color (154,205,50), new Color (255,140,0), 
						new Color (0,128,128), new Color (46,139,87), new Color (135,206,250),
						new Color (147,112,219), new Color (160,82,45), new Color (188,143,143)
						};
			List<Color> colorList = Arrays.asList(randomColor);	
			Random rand = new Random();		
			//choose random color		
			int randomIndex = rand.nextInt(colorList.size());
			Color randColor = colorList.get(randomIndex);
			color = randColor;
		
		return color;
	}
	
//Determines id the mouse clicked on a rectangle	
		public boolean containsPoint(double px, double py) {
			boolean contains = false;
		    double top, bottom, left, right;
		    right = xCord + width;
		    left = xCord - width;
		    top = yCord + height;
		    bottom = yCord - height; 
		    
		    if ((py > bottom ) && (py < top ))
		    		if ((px < right) && (px> left))
		    			contains = true;    
		return contains;
		}
	
//freezes rectangles that have been clicked on	
		public boolean setFrozen(boolean val) {
			if (val = true) {
				xVelocity = 0;
				yVelocity =0;
				frozen = true;
				setColor(Color.red);
			}
			else {
				frozen = false;
			}
		return frozen;
		}
		
//re-stars a frozen rectangle if intersected
		public void move (int xv, int yv) {
			xVelocity = -xv;
			yVelocity = -yv;
			setColor(setRandomColor());
			move();
		}

//detects if frozen rectangles are intersecting with moving ones
		public boolean isIntersecting (MovingRectangle r) {
			intersect = false;
		
			double top, bottom, left, right;	    
			right = xCord + width;
			left = xCord - width;
			top = yCord + height;
	    	bottom = yCord - height;
	    
	    	double recTop, recBottom, recLeft, recRight;
	    	recRight = r.xCord + r.width;
	    	recLeft = r.xCord - r.width;
	    	recTop = r.yCord + r.height;
	    	recBottom = r.yCord - r.height;
	     
	    	if ( (top > recTop) && (recTop > bottom ) ) 
	    		if ( (left < recLeft ) && (recLeft < right) ) {
	    			intersect = true;	
	    		}
	    		else if( (left < recRight) && (recRight < right)){
	    		intersect = true;
	    		}
	    	
	    	if ( (recTop > top) && (top > recBottom ) )
	    		if ( (recLeft < left ) && (left < recRight) ) {
	    			intersect = true;	
	    		}
	    		else if( (recLeft < right) && (right < recRight)){
	    			intersect = true;
	    		}
	    
	    	if (intersect)
	    			setFrozen(false);
		
	    return intersect;
	}	
}
