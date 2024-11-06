package Core;

import java.awt.*;

public abstract class VectorSprite
{
    protected double   xspeed;
    protected double   yspeed;
    protected  double   speed = 5;

    protected double angle;

    protected double x;
    protected double y;

    protected float radius = 15;

    protected double rotationSpeed;
    protected double angularVelocity = 90;
    protected double maxSpeed = 250;
    protected double baseDrag = 0.98; //0.98% of speed
    protected MeshComponent meshComponent;

    protected float scale;

    public VectorSprite(double x, double y, float scale)
    {
        this.x = x;
        this.y = y;

        this.scale = scale;

        angularVelocity = Math.toRadians(angularVelocity);
        meshComponent = GenerateMesh();
    }

    protected abstract MeshComponent GenerateMesh();

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

    protected void update(Graphics g)
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

    public boolean collision(VectorSprite other){
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
