import javax.swing.*;
public class Main
{
    public static void main(String args[]) {
        //Frame
        JFrame frame = new JFrame("TEST");
        frame.setSize(500, 522);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        //JPanel
        DotsNaturalSelection panel = new DotsNaturalSelection();
        frame.add(panel);
        frame.addKeyListener(panel);
        panel.addMouseListener(panel);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
