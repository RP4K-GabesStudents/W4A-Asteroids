import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener
{
    public static final int WIDTH = 640, HEIGHT = 420;
    Window window;

    Player player;



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
        player = new Player(WIDTH/2, HEIGHT/2, 0.1, 5);
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
            player.Accelerate();
        }

        if(e.getKeyCode() == KeyEvent.VK_S){
            player.Decelerate();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A){
            player.rotationSpeed = 0;
        }
    }

    public void updateWindow(Graphics g)
    {
        player.update(g);
    }
}