package Core;

import Powerups.PowerUp;
import Weapons.Projectile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

enum EGameState
{
    MainMenu,
    Game,
    EndScreen
}

public class Game extends JFrame implements KeyListener
{
    public static final int WIDTH = 640, HEIGHT = 420; //1920x1080 is "full screen" on most devices
    Window window;

    Player player;

    private static long previousTime;
    public static double deltaTime;

    float currentAsteroidSpawnTime;
    float endAsteroidSpawnTime = 5;
    float spawnTimeAcceleration;

    static ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
   public static ArrayList<Projectile> projectiles = new ArrayList<>();

   private ArrayList<PowerUp> powerUps = new ArrayList<>();

    EGameState currentGameState = EGameState.MainMenu;


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

        reset();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(currentGameState == EGameState.MainMenu) {
            currentGameState = EGameState.Game;
        }
        else if(currentGameState == EGameState.EndScreen){
            reset();
        }
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

        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            player.currentWeapon.beginAttack();
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

        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            player.currentWeapon.endAttack();
        }

    }

    public void updateWindow(Graphics g)
    {
        switch(currentGameState)
        {
            case EGameState.Game:
                UpdateGame(g);
                break;
            case EGameState.MainMenu:
                UpdateMainMenu(g);
                break;
            case EGameState.EndScreen:
                UpdateEndScreen(g);
                break;
        }
    }

    void UpdateGame(Graphics g)
    {
        player.update(g);
        player.currentWeapon.update();

        long currentTime = System.currentTimeMillis();
        deltaTime = (currentTime - previousTime)/1000.0;
        previousTime = currentTime;

        SpawnAsteroid();

        for(int i = asteroids.size() - 1; i >= 0 ; --i)
        {
            Asteroid current = asteroids.get(i);
            current.update(g);
            if(player.collision(current))
            {
                System.out.println("Hit");
                currentGameState = EGameState.EndScreen;
            }
        }

        for(int i = projectiles.size() -1; i >=0; --i)
        {
            Projectile current = projectiles.get(i);
            current.update(g);

            if(current.IsDead()) {
                projectiles.remove(i);
                continue;
            }

            for(int j = asteroids.size() - 1; j >= 0; --j)
            {
                if(current.collision(asteroids.get(j)))
                {
                    SpawnPowerup((float)asteroids.get(j).x, (float)asteroids.get(j).y);

                    asteroids.get(j).Break();
                    asteroids.remove(j);
                    projectiles.remove(i);
                    break;
                }
            }
        }

        for(int i = powerUps.size() -1; i >=0; --i) {
            PowerUp current = powerUps.get(i);
            current.update(g);
            if (current.collision(player)) {
                powerUps.remove(i);
            }
        }

    }

    void UpdateMainMenu(Graphics g)
    {

    }

    void UpdateEndScreen(Graphics g)
    {

    }

    void reset()
    {
        player = new Player(WIDTH/2, HEIGHT/2, 1,250, 9.5);

        asteroids.clear();

        projectiles.clear();

        for(int i = 0; i < 5; i++) {
            //Min and max starting sizes.
            asteroids.add(new Asteroid(Asteroid.rng.nextFloat(1,2)));
        }

        currentGameState = EGameState.MainMenu;
    }

    void SpawnAsteroid(){
        currentAsteroidSpawnTime += deltaTime;

        if(currentAsteroidSpawnTime >= endAsteroidSpawnTime)
        {
            asteroids.add(new Asteroid(Asteroid.rng.nextFloat(1,2)));
            //currentAsteroidSpawnTime = Math.max(0,reset - spawnTimeAcceleration);
            currentAsteroidSpawnTime = spawnTimeAcceleration;
            spawnTimeAcceleration += 0.05f;
        }
    }

    void SpawnPowerup(float x, float y)
    {
        if(Asteroid.rng.nextFloat(1) < 0.2f)
        {
            powerUps.add(new PowerUp(x,y));
        }
    }

}

