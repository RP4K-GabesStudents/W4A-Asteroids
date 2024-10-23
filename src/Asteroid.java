import java.awt.*;
import java.util.Random;


public class Asteroid extends VectorSprite {

    static Random rng = new Random();

    public Asteroid()
    {
        super(rng.nextInt(-200,200), rng.nextInt(-200,200));

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


    @Override
    MeshComponent GenerateMesh() {
        return new MeshComponent(StaticUtilties.defaultAsteroidShape
                [rng.nextInt(StaticUtilties.defaultAsteroidShape.length)], Color.RED);
    }
}
