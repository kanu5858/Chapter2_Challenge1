import java.applet.Applet;
import java.awt.*;

// Class must extend Applet and implement Runnable for the thread [cite: 5]
public class BouncingTextApplet extends Applet implements Runnable {
    // Variables for the animation state
    private Thread animationThread; // The thread for animation [cite: 7]
    private String name = "KANU TECHOME"; // Initialize String with your name [cite: 6]
    private int xCoord = 0; // Current x-coordinate for the text [cite: 10]
    private final int yCoord = 50; // Fixed y-coordinate (vertical position)
    private final int textSpeed = 3; // Speed of movement

    /**
     * Called when the applet is first loaded.
     */
    public void init() {
        // Set the applet's size (required for AppletViewer) [cite: 6]
        setSize(400, 100);
        // Set a background color [cite: 6]
        setBackground(Color.BLACK);
    }

    /**
     * Called to create and start the animation thread.
     */
    public void start() {
        // Create and start a new thread (this, because the class implements Runnable) [cite: 7]
        if (animationThread == null) {
            animationThread = new Thread(this);
            animationThread.start();
        }
    }

    /**
     * The thread's main execution loop.
     */
    public void run() {
        // Use a while loop to keep the animation running [cite: 9]
        while (true) {
            // 1. Update the x-coordinate of the text [cite: 10]
            xCoord += textSpeed;

            // 2. If the text hits the right edge, reset its position [cite: 11]
            // We use getSize().width to get the current width of the applet window.
            if (xCoord > getSize().width) {
                xCoord = -100; // Reset text slightly off-screen to the left
            }

            // 3. Request the applet to redraw the screen (calls paint()) [cite: 12]
            repaint();

            // 4. Pause the animation for 100 milliseconds [cite: 13]
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // If the thread is interrupted, break the loop and stop the animation
                break;
            }
        }
    }

    /**
     * Called to stop the thread when the applet is inactive.
     */
    public void stop() {
        // Stop the thread by setting it to null [cite: 8]
        if (animationThread != null) {
            animationThread = null;
        }
    }

    /**
     * Draws the text on the screen.
     */
    public void paint(Graphics g) {
        // Set the color for the text
        g.setColor(Color.WHITE);
        // Use g.drawString() to draw the text at the current x-coordinate [cite: 14]
        g.drawString(name, xCoord, yCoord);
    }
}