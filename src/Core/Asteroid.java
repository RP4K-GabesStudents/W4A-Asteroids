package Core;

import java.awt.*;
import java.util.Random;


public class Asteroid extends VectorSprite {

    static Random rng = new Random();

    public Asteroid(float scale)
    {
        super(rng.nextInt(-200,200), rng.nextInt(-200,200), scale);

        if(x > 0)
        {
            x += Game.WIDTH;
        }
        if(y > 0)
        {
            y += Game.HEIGHT;
        }

        double xDir = rng.nextInt(Game.WIDTH) - x;
        double yDir = rng.nextInt(Game.HEIGHT) - y;

        double dist = Math.sqrt(xDir * xDir + yDir * yDir);

        double speed = rng.nextDouble(250,500);

        //Normalize the vector, and multiply our soeed.
        xspeed = xDir/dist * speed;
        yspeed = yDir/dist * speed;
    }

    public Asteroid(double x, double y, float scale)
    {
        super(x,y,scale);

        speed = 100;
        xspeed = rng.nextFloat(-1,1) * speed;
        yspeed = rng.nextFloat(-1,1) * speed;

    }


    @Override
    protected MeshComponent GenerateMesh() {
        return new MeshComponent(StaticUtilties.defaultAsteroidShape[rng.nextInt(StaticUtilties.defaultAsteroidShape.length)], Color.RED, scale);
    }


    public void Break()
    {
        if(scale <= 0.4f) return;

        int num = rng.nextInt(2,5);
        for(int i = 0; i < num; ++i) {
            Game.asteroids.add(new Asteroid(x, y, Math.max(rng.nextFloat(scale/num/2, scale/num), 0.3f)));
        }
    }
}
