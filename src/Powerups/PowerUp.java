package Powerups;

import Core.MeshComponent;
import Core.StaticUtilties;
import Core.VectorSprite;

import java.awt.*;

public class PowerUp extends VectorSprite {

    public PowerUp(double x, double y)
    {
        super(x, y, 1);
    }

    public void GrantUpgrade()
    {
        //When the play collides with the powerup, we increase the weapons stats (more projectiles, and less accuracy)
    }

    @Override
    protected MeshComponent GenerateMesh() {
        return new MeshComponent(StaticUtilties.defaultProjectileShape, Color.green, scale);
    }
}
