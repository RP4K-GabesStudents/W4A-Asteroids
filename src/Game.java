import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Game extends JFrame implements KeyListener
{
    public static final int WIDTH = 640, HEIGHT = 420;
    Window window;

    Player player;

    private static long previousTime;
    public static double deltaTime;


    public Game()
    {
        setVisible(true);
        setSize(new Dimension(WIDTH,HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("samba is the goat");
        window = new Window(this);
        add(window,null);
        pack();
        addKeyListener(this);
        player = new Player(WIDTH/2, HEIGHT/2, 250, 9.5);


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){
            player.rotationSpeed = player.angularVelocity;
        }

        if(e.getKeyCode() == KeyEvent.VK_A){
            player.rotationSpeed = -player.angularVelocity;
        }

        if(e.getKeyCode() == KeyEvent.VK_W){
            player.wantsToAccelerate= true;
        }

        if(e.getKeyCode() == KeyEvent.VK_S){
            player.wantsToDecelerate = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A){
            player.rotationSpeed = 0;
        }

        if(e.getKeyCode() == KeyEvent.VK_W){
            player.wantsToAccelerate= false;
        }

        if(e.getKeyCode() == KeyEvent.VK_S){
            player.wantsToDecelerate = false;
        }

    }

    public void updateWindow(Graphics g)
    {
        player.update(g);

        long currentTime = System.currentTimeMillis();
        deltaTime = (currentTime - previousTime)/1000.0;
        previousTime = currentTime;
    }
}