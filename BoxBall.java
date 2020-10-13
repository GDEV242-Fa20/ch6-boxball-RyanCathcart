import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Write a description of class BoxBall here.
 * A modification of the BouncingBall class.
 *
 * @author Ryan Cathcart
 * @version 10.12.2020
 */
public class BoxBall
{
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private int xSpeed;
    private int ySpeed;
    
    private final Rectangle2D.Double box;
    private Canvas canvas;

    
    /**
     * Constructor for objects of class BoxBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int xVel, int yVel, int ballDiameter, Color ballColor, Rectangle2D.Double boxPos, Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        xSpeed = xVel;
        ySpeed = yVel;
        color = ballColor;
        diameter = ballDiameter;
        box = boxPos; //handles the top, right, bottom, and left walls of the box.
        canvas = drawingCanvas;
    }
    
    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        xPosition += xSpeed;
        yPosition += ySpeed;

        Random r = new Random();
        // check if it has hit the box walls
        if (xPosition <= (box.getX()) && xSpeed < 0) {
            xPosition = (int)(box.getX() + diameter);
            xSpeed = -xSpeed;
        }
        
        if (xPosition >= ((box.getX() + box.getWidth()) - diameter) && xSpeed > 0) {
            xPosition = (int)((box.getX() + box.getWidth()) - diameter);
            xSpeed = -xSpeed;
        }
        
        // check if it has hit the box ceiling and floor
        if (yPosition <= (box.getY()) && ySpeed < 0) {
            yPosition = (int)(box.getY() + diameter);
            ySpeed = -ySpeed;
        }
        
        if (yPosition >= ((box.getY() + box.getHeight()) - diameter) && ySpeed > 0) {
            yPosition = (int)((box.getY() + box.getHeight()) - diameter);
            ySpeed = -ySpeed;
        }

        // draw again at new position
        draw();
    }  
    
    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }
    
    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
