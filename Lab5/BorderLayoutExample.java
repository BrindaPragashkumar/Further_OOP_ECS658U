package Lab5;

import javax.swing.*;
import java.awt.*;

class ColorComponent extends JComponent{
    private Color color;
    private Dimension dimension;


    public ColorComponent(Color color, Dimension dimension){
        this.color = color;
        this. dimension = dimension;

        setPreferredSize(dimension);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setColor(color);

        g.fillRect(0, 0, getWidth(), getHeight());
    }

}

public class BorderLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BorderLayout Example");
        frame.setLayout(new BorderLayout());
        frame.add(new ColorComponent(Color.RED, new Dimension(400,50)), BorderLayout.NORTH);
        frame.add(new ColorComponent(Color.BLUE, new Dimension(400,50)), BorderLayout.SOUTH);
        frame.add(new ColorComponent(Color.GREEN, new Dimension(50,100)), BorderLayout.EAST);
        frame.add(new ColorComponent(Color.YELLOW, new Dimension(50,200)), BorderLayout.WEST);
        frame.add(new ColorComponent(Color.ORANGE,new Dimension(50,200)), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}