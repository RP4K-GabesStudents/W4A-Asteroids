import java.awt.*;

public class Player extends VectorSprite
{

    public Player(double x, double y, double acceleration, double deceleration) {
        super(x, y, acceleration, deceleration);
    }

    @Override
    MeshComponent GenerateMesh() {
        return new MeshComponent(StaticUtilties.defaultShipShape, Color.pink);
    }
}