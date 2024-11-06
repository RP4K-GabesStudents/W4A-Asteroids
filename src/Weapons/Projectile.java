package Weapons;

import Core.MeshComponent;
import Core.StaticUtilties;
import Core.VectorSprite;

import java.awt.*;

public class Projectile extends VectorSprite
{

    final float SPEED = 700;

    public Projectile(double x, double y, double offset,  double angle, float scale) {
        super(x, y, scale);

        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        this.x += cos * offset;
        this.y += sin * offset;

        xspeed = cos * SPEED;
        yspeed = sin * SPEED;
    }

    @Override
    protected MeshComponent GenerateMesh() {
        return new MeshComponent(StaticUtilties.defaultProjectileShape, Color.blue, scale);
    }



}
