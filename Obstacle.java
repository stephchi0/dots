import java.awt.*;
public class Obstacle
{
    public int xStart = 0;
    public int yStart = 0;
    public int xEnd = 0;
    public int yEnd = 0;
    public Obstacle(int xStart, int xEnd, int yStart, int yEnd)
    {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }
    public boolean collision(int xPosition, int yPosition) {
        if (xPosition >= xStart && xPosition <= xEnd && yPosition >= yStart && yPosition <= yEnd) return true;
        return false;
    }
    public void show(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect(xStart, yStart, xEnd - xStart, yEnd - yStart);
        g.setColor(Color.black);
        g.drawRect(xStart, yStart, xEnd - xStart, yEnd - yStart);
    }
}
