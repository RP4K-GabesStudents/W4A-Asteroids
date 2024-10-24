import java.awt.*;

public class Player extends VectorSprite
{

    double acceleration;
    double deceleration;

    boolean wantsToAccelerate;
    boolean wantsToDecelerate;

    //TODO: Homework - make the character shoot projectiles

    public Player(double x, double y, double acceleration, double deceleration) {
        super(x, y);
        this.acceleration = acceleration;
        this.deceleration = deceleration;
    }

    @Override
    MeshComponent GenerateMesh() {
        return new MeshComponent(StaticUtilties.defaultShipShape, Color.green);
    }

    void Accelerate()
    {
        xspeed += Math.cos(angle) * acceleration * Game.deltaTime;
        yspeed += Math.sin(angle) * acceleration * Game.deltaTime;
    }

    void Decelerate()
    {
        if(deceleration * Game.deltaTime < 0.01) return;
        System.out.println(1+ (deceleration * Game.deltaTime) +", " + xspeed);
        xspeed /= 1+ (deceleration * Game.deltaTime);
        yspeed /= 1+(deceleration * Game.deltaTime);
    }

    @Override
    void move() {
        super.move();
        if(wantsToAccelerate) Accelerate();
        if(wantsToDecelerate) Decelerate();


        Slowdown();
    }


    void Slowdown(){
        xspeed -= xspeed * baseDrag * Game.deltaTime;
        yspeed -= yspeed * baseDrag * Game.deltaTime;
    }
}