package Weapons;

import Core.Game;
import Core.VectorSprite;

public abstract class Weapon
{

    protected VectorSprite owner;

    float currentFireTime;

    boolean isAutomatic;
    boolean enabled;

    float shootingTime;


    public Weapon(boolean isAutomatic, float shootingTime, VectorSprite owner)
    {
        this.isAutomatic = isAutomatic;
        this.shootingTime = shootingTime;
        this.owner = owner;

        currentFireTime = shootingTime;
    }


    public void update()
    {
        currentFireTime += Game.deltaTime;

        if(enabled)
        {
            tryAttack();
        }

    }

    public void beginAttack(){
        if(isAutomatic)
        {
            enabled = true;
        }
        else
        {
            tryAttack();
        }
    }

    void tryAttack()
    {
        if(canAttack())
        {
            attack();
        }
    }

   boolean canAttack(){
        return currentFireTime > shootingTime;
   }

    void attack(){
        currentFireTime = 0;
        attackImplementation();
    }

    public void endAttack(){
        enabled = false;
    }

    public abstract void attackImplementation();

}
