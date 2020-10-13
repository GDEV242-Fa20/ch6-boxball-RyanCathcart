import java.awt.Color;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * INDEFINITELY simulates bouncing balls. Ball initial positions, speeds, colors are randomly generated.
     * @param numBalls the number of balls to generate in the box
     */
    public void boxBounce(int numBalls) {
        ArrayList<BoxBall> balls = new ArrayList<BoxBall>();
        Random r = new Random();
        
        Rectangle2D.Double box = new Rectangle2D.Double(100, 50, 400, 400); // bounds of the box, to be sent to each ball
        myCanvas.setVisible(true);

        // create the balls
        for (int i=0;i<numBalls;i++) {
            int xPos = 100 + r.nextInt(400);
            int yPos = 50 + r.nextInt(400);
            int xSpeed = 1 + r.nextInt(20);
            int ySpeed = 1 + r.nextInt(20);
            Color color = new Color(r.nextInt(240), r.nextInt(240), r.nextInt(240));
            
            balls.add(new BoxBall(xPos, yPos, xSpeed, ySpeed, 16, color, box, myCanvas));
            
        }
        
        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            
            // draw the box
            myCanvas.setForegroundColor(Color.BLACK);
            myCanvas.draw(box);

            for (BoxBall ball : balls) {
                ball.move();
            }
        }
    }
}
