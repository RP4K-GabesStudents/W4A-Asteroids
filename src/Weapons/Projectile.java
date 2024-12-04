package Weapons;

import Core.Game;
import Core.MeshComponent;
import Core.StaticUtilties;
import Core.VectorSprite;

import java.awt.*;

public class Projectile extends VectorSprite {

    float SPEED = 700;

    float lifeTime = 5;


    public Projectile(double x, double y, double offset,  double angle, float scale) {
        super(x, y, scale);

        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        this.x += cos * offset;
        this.y += sin * offset;

        xspeed = cos * SPEED;
        yspeed = sin * SPEED;

        this.angle = angle;

        lifeTime *= ProjectileWeapon.rng.nextFloat(0.8f,1.2f);
    }

    public Projectile(double x, double y, int offset, double angle, float scale, float xSpeed, float ySpeed) {
        super(x, y, scale);

       this.angle = angle;

        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        this.x += cos * offset;
        this.y += sin * offset;

        xspeed = xSpeed;
        yspeed = ySpeed;

        lifeTime *= ProjectileWeapon.rng.nextFloat(0.8f,1.2f);
    }

    @Override
    protected MeshComponent GenerateMesh() {
        return new MeshComponent(StaticUtilties.defaultProjectileShape, Color.blue, scale);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);

        lifeTime -= (float) Game.deltaTime;
    }

    public boolean IsDead(){
        return lifeTime <= 0;
    }
}
