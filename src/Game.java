import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener
{
    public static final int WIDTH = 1920, HEIGHT = 1080;
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
        player = new Player(WIDTH/2, HEIGHT/2, 3, 5);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){
            player.xspeed = player.speed;
        }

        if(e.getKeyCode() == KeyEvent.VK_A){
            player.xspeed = -player.speed;
        }

        if(e.getKeyCode() == KeyEvent.VK_W){
            player.yspeed = -player.speed;
        }

        if(e.getKeyCode() == KeyEvent.VK_S){
            player.yspeed = player.speed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){
            player.xspeed =0;
        }

        if(e.getKeyCode() == KeyEvent.VK_A){
            player.xspeed = 0;
        }

        if(e.getKeyCode() == KeyEvent.VK_W){
            player.yspeed = 0;
        }

        if(e.getKeyCode() == KeyEvent.VK_S){
            player.yspeed = 0;
        }
    }

    public void updateWindow(Graphics g)
    {
        player.update(g);
    }
}