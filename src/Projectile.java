import java.awt.*;

public class Projectile extends VectorSprite
{

    final float SPEED = 700;

    public Projectile(double x, double y, double offset,  double angle) {
        super(x, y);

        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        this.x += cos * offset;
        this.y += sin * offset;

        xspeed = cos * SPEED;
        yspeed = sin * SPEED;
    }

    @Override
    MeshComponent GenerateMesh() {
        return new MeshComponent(StaticUtilties.defaultProjectileShape, Color.blue, 1);
    }



}
