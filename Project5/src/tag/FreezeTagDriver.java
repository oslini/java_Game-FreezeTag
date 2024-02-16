package tag;


import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdDraw;



public class FreezeTagDriver {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner (System.in);
		Random rand = new Random();		
		MovingRectangle [] rectangle = new MovingRectangle[6]; //6 because I am keeping the first empty
		
		int []freeze = new int [6];
		int []xv = new int [6]; //store the randomly generated velocity
		int []yv = new int [6]; //store the randomly generated velocity
		boolean go = true; //to stop the loop when all rectangles have been frozen
			
		//Set Up rectangles
		for ( int i =0; i < 6; i++){
			int canvas = 600;
			int xCord = rand.nextInt(canvas); 
			int yCord = rand.nextInt(canvas);
			int  halfWidth = rand.nextInt(canvas/10)+20;
			int halfHeight = rand.nextInt(canvas/10)+20;
			int velocityX =	rand.nextInt(3)+1; xv[i] = velocityX;
			int velocityY = rand.nextInt(3)+1; yv[i] = velocityX;
			
			rectangle[i]= new MovingRectangle(xCord, yCord, halfWidth, halfHeight, velocityX,
					velocityY, canvas);	
			rectangle[i].setRandomColor();
		}
		
///BEGIINING
		
		while (go){
			
			StdDraw.clear();
			StdDraw.enableDoubleBuffering();
			
				for (int i =1; i< 6; i++) {
				
				rectangle[i].move();
//PART ONE	
				if (StdDraw.mousePressed()) { //beginning of mouse click
					double mouseX = StdDraw.mouseX();
					double mouseY = StdDraw.mouseY();
					if (rectangle[i].containsPoint(mouseX, mouseY) == true) {
						
						rectangle[i].setFrozen(true);
						System.out.println(i);
						
						if (freeze[i] !=i) {
							freeze [i] = i;
							rectangle[i].draw();//makes sure the last rec turns red
						}
					}
				}//end of mouse click for-loop
				
//PART TWO				
				for (int j = 1; j < 6; j++) {//beginning of intersectiion
					if (freeze [j] > 0) {
						if (rectangle[j].isIntersecting(rectangle[i])) {
							//rectangle[j].setFrozen(false);
							rectangle[j].move(xv[j], yv[j]);
							freeze [j] = 0;
						}
					}	
				}//end of intersecting for-loop				
							
				}//end of moving rectangle for-loop
				
				StdDraw.show();
				StdDraw.pause(20);
				
				if ( freeze[1] > 0)
					if ( freeze[2] > 0)
						if ( freeze[3] > 0)
							if ( freeze[4] > 0) 
								if ( freeze[5] > 0) {
								StdDraw.setPenColor(StdDraw.BLACK);
								StdDraw.filledCircle(300, 300, 50);
								StdDraw.setPenColor(StdDraw.WHITE);
								StdDraw.text(300, 300, "You win!");
								StdDraw.show();	
								go = false;//stops the entire animation
							}
		}
//END
	}

}