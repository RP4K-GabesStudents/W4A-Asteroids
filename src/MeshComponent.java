import java.awt.*;

public class MeshComponent
{

    final Color mycolor;

    final Polygon polygon;

    public MeshComponent(int [][] shipShape, Color color)
    {
        int [] x = new int[shipShape.length];
        int [] y = new int[shipShape.length];

        mycolor = color;

        for(int i = 0; i < shipShape.length; i++)
        {
            x[i] = shipShape[i][0];
            y[i] = shipShape[i][1];
        }

        polygon = new Polygon(x, y, shipShape.length);


    }

    void render(Graphics g)
    {
        g.setColor(mycolor);
        g.drawPolygon(polygon);
    }
}
