import java.awt.*;

public abstract class VectorSprite
{
    double   xspeed;
    double   yspeed;
    double   speed = 5;

    double angle;

    double x;
    double y;

    float radius = 15;

    double rotationSpeed;
    double angularVelocity = 90;
    double maxSpeed = 250;
    double baseDrag = 0.98; //0.98% of speed
    MeshComponent meshComponent;

    public VectorSprite(double x, double y)
    {
        this.x = x;
        this.y = y;

        angularVelocity = Math.toRadians(angularVelocity);
        meshComponent = GenerateMesh();
    }

    abstract MeshComponent GenerateMesh();

    void move()
    {
        double magnitude = Math.sqrt(xspeed * xspeed + yspeed * yspeed);



        //If we're moving too fast
        if(magnitude > maxSpeed)
        {
            //Vector Normalization, Dividing a vector, by it's length gives a unit vector (size of 1)
            xspeed = xspeed / magnitude * maxSpeed;
            yspeed = yspeed / magnitude * maxSpeed;
        }

        x += xspeed * Game.deltaTime;
        y += yspeed * Game.deltaTime;

        angle += rotationSpeed * Game.deltaTime;

        screenWrap();
    }

    void update(Graphics g)
    {
        meshComponent.HandleMatrix(x,y,angle);

        //Render
        meshComponent.render(g);

        //Handle Pausing (Later)

        //Move
        move();
    }

    void screenWrap()
    {
        if(y > Game.HEIGHT + radius && yspeed > 0)
        {
            y =  - radius;
        }
        if(x > Game.WIDTH + radius && xspeed > 0)
        {
            x =  - radius;
        }
        if(y < - radius && yspeed < 0)
        {
            y = Game.HEIGHT + radius;
        }
        if(x < -radius && xspeed < 0)
        {
            x = Game.WIDTH + radius;
        }
    }

    boolean collision(VectorSprite other){
        for(int i = 0; i < meshComponent.polygon.npoints; i++ )
        {
            if(other.meshComponent.polygon.contains(meshComponent.polygon.xpoints[i], meshComponent.polygon.ypoints[i]))
            {
                return true;
            }
        }

        for(int i = 0; i < other.meshComponent.polygon.npoints; i++ )
        {
            if(meshComponent.polygon.contains(other.meshComponent.polygon.xpoints[i], other.meshComponent.polygon.ypoints[i]))
            {
                return true;
            }
        }
        return false;
    }
}
