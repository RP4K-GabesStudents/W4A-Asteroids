import java.awt.*;

public abstract class VectorSprite
{
    double   xspeed;
    double   yspeed;
    double   speed = 5;

    double angle;

    double x;
    double y;

    double acceleration;

    double deceleration;

    double rotationSpeed;
    double angularVelocity = 0.1; // 3 Degrees TODO: Have a multiplier variable that's modified in key pressed, and convert to radians in constructor

    MeshComponent meshComponent;

    public VectorSprite(double x, double y, double acceleration, double deceleration)
    {
        this.x = x;
        this.y=y;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        angularVelocity = Math.toRadians(angularVelocity);
        meshComponent = GenerateMesh();
    }

    abstract MeshComponent GenerateMesh();

    void move()
    {
        x += xspeed;
        y += yspeed;
        angle += rotationSpeed;
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

    void Accelerate()
    {
        xspeed += Math.cos(angle) * acceleration;
        yspeed += Math.sin(angle) * acceleration;
    }

    void Decelerate()
    {
        xspeed /= deceleration;
        yspeed /= deceleration;
    }
}
