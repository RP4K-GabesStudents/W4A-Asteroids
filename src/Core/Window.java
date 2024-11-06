package Core;

import javax.swing.*;
import java.awt.*;

public class Window extends JPanel
{
    private Game game;


    public Window(Game game)
    {
        this.game = game;
        setBackground(Color.BLACK);
        setPreferredSize(game.getSize());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.updateWindow(g);
        repaint();

    }

}