import java.awt.*;

public abstract class VectorSprite
{
    double   xspeed;
    double   yspeed;
    double   speed = 5;

    double x;
    double y;

    double acceleration;

    double deceleration;

    MeshComponent meshComponent;

    public VectorSprite(double x, double y, double acceleration, double deceleration)
    {
        this.x = x;
        this.y=y;
        this.acceleration = acceleration;
        this.deceleration = deceleration;

        meshComponent = GenerateMesh();
    }

    abstract MeshComponent GenerateMesh();

    void move()
    {
        x += xspeed;
        y += yspeed;
    }

    void update(Graphics g)
    {
        //Render
        meshComponent.render(g);

        //Handle Pausing (Later)

        //Move
        move();
    }
}
