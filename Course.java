import java.awt.*;
public class Course
{
    static Obstacle[] obstacles = {
        //border
        new Obstacle(-50, 550, -50, 5),
        new Obstacle(-50, 550, 495, 550),
        new Obstacle(-50, 5, -50, 550),
        new Obstacle(495, 550, -50, 550),
        
        //added walls
        new Obstacle(-1, -1, -1, -1),
        new Obstacle(-1, -1, -1, -1),
        new Obstacle(-1, -1, -1, -1),
        new Obstacle(-1, -1, -1, -1),
        new Obstacle(-1, -1, -1, -1),
        new Obstacle(-1, -1, -1, -1),
        new Obstacle(-1, -1, -1, -1),
        new Obstacle(-1, -1, -1, -1),
        new Obstacle(-1, -1, -1, -1),
        new Obstacle(-1, -1, -1, -1),
        
        //walls
        //new Obstacle(0, 350, 300, 320),
        //new Obstacle(375, 395, 0, 250),
    };
    public static boolean collision(int xPosition, int yPosition) {
        for (int i = 0; i < obstacles.length; i++) {
            if (obstacles[i].collision(xPosition, yPosition)) return true;
        }
        return false;
    }
    public static void show(Graphics g) {
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i].show(g);
        }
    }
}
