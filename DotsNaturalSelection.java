import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
public class DotsNaturalSelection extends JPanel implements ActionListener, KeyListener, MouseListener{
    boolean start = false;
    boolean showAll = true;
    Timer timer = new Timer(25, this);
    
    Population population = new Population(1000);
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!start) start();
        setBackground(Color.white);
        
        //goal
        g.setColor(Color.blue);
        g.fillRect(240, 25, 20, 20);
        g.setColor(Color.black);
        g.drawString("Generation: " + Integer.toString(population.generationNumber), 10, 20);
        
        //obstacles
        Course.show(g);
        
        //show/update dots
        if (showAll) population.show(g);
        if (population.generationNumber > 1) population.bestDot.show(g);
        population.update();
        
        //genetic algorithm
        if (population.extinct) {
            population.cull();
        }
    }
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    private void start() {
        timer.start();
        start = true;
    }
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            showAll = !showAll;
        }
    }
    int x = 0;
    int y = 0;
    int i = 0;
    public void mousePressed(MouseEvent e){
        x = e.getX();
        y = e.getY();
    }
    public void mouseReleased(MouseEvent e){
        if (i < 10) {
            Course.obstacles[i + 4] = new Obstacle(x, e.getX(), y, e.getY());
            i++;
        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){}
}
