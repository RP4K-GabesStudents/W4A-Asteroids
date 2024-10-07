import java.awt.*;

public class MeshComponent
{

    final Color mycolor;

    final Polygon polygon;
    final Polygon start;

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
        start = new Polygon(x,y, shipShape.length);

    }

    void render(Graphics g)
    {
        g.setColor(mycolor);
        g.drawPolygon(polygon);
    }

    void HandleMatrix(double x, double y, double angle)
    {
        polygon.invalidate();

        angle = Math.toRadians(angle);
        for (int i = 0; i < start.npoints; i++) {
            //TODO: Issue was cosX - sinY, sinX + cosY
            polygon.xpoints[i] = (int)  ((Math.cos(angle) * start.xpoints[i] - Math.sin(angle) * start.ypoints[i]) + x);
            polygon.ypoints[i] = (int)  ((Math.sin(angle) * start.xpoints[i] + Math.cos(angle) * start.ypoints[i]) + y);
        }
    }
}
