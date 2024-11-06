import java.awt.*;

public class MeshComponent
{

    final Color mycolor;

    final Polygon polygon;
    final Polygon start;

    float scale;

    public MeshComponent(int [][] shipShape, Color color, float scale)
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
        start = new Polygon(x,y, shipShape.length);

        this.scale = scale;
    }

    void render(Graphics g)
    {
        g.setColor(mycolor);
        g.drawPolygon(polygon);
    }

    void HandleMatrix(double x, double y, double angle)
    {
        polygon.invalidate();

        for (int i = 0; i < start.npoints; i++) {
            polygon.xpoints[i] = (int)  ((Math.cos(angle) * start.xpoints[i] * scale - Math.sin(angle) * start.ypoints[i] * scale) + x);
            polygon.ypoints[i] = (int)  ((Math.sin(angle) * start.xpoints[i] * scale + Math.cos(angle) * start.ypoints[i] * scale) + y);
        }
    }
}
