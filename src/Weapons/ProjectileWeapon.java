package Weapons;

import Core.Game;
import Core.VectorSprite;

import java.util.Random;

public class ProjectileWeapon extends Weapon{

    int numProjectiles;
    Projectile p;
    private float projectileSpeed = 1000;


    public static Random rng = new Random();
    double spread;

    public ProjectileWeapon(boolean isAutomatic, float fireTime, VectorSprite owner, Projectile p, int numProjectiles,double spread){
        super(isAutomatic, fireTime, owner);
        this.p = p;
        this.numProjectiles = numProjectiles;
        this.spread=spread;
    }

    @Override
    public void attackImplementation() {
        //We need to shoot numProjectiles
        //How do we have our projectiles move at a random velocity (math part)
        //Add them to our projectile list in our game
        float rads = (float)Math.toRadians(spread);
        for(int i = 0; i < numProjectiles; ++i) {
            double angle = rng.nextFloat(-rads,rads) + owner.angle;
            float xSpeed = (float) Math.cos(angle) * projectileSpeed;
            float ySpeed = (float) Math.sin(angle) * projectileSpeed;
            Game.projectiles.add(new Projectile(owner.x, owner.y, 15, angle, p.scale, xSpeed, ySpeed));
        }


    }
}
