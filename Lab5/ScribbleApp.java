package Lab5;

import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Line{
    ArrayList<Point2D> points = new ArrayList<>();
}

class DrawComponent extends JComponent {

    final ArrayList<Line> lines = new ArrayList<>();
    int pointSpan = 10;

    class MouseDrawListener extends MouseAdapter {
        //In Java (and most OO languages) a concrete class
        // MUST implement all the
        //methods of all the interfaces it implements
        //We are using the Observer Pattern Example

        Line currentLine = null;

        @Override
        public void mousePressed(MouseEvent e) {
            currentLine = new Line();
            currentLine.points.add(new Point2D.Double(e.getX(), e.getY()));
            lines.add(currentLine);
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            currentLine.points.add(new Point2D.Double(e.getX(), e.getY()));
             repaint();  // Repaint to update the drawing
        }


    }

    public  DrawComponent(){
        setBackground(Color.WHITE);
        MouseDrawListener listener = new MouseDrawListener();
        addMouseListener(listener);
        // set the background to white
        addMouseMotionListener(listener);

    }

    public void reset(){
        lines.clear();
        repaint();
    }

    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        // This makes the graphics look better
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Line line : lines) {
            if (line.points.size() > 1) {
                for (int i = 1; i < line.points.size(); i++) {
                    Point2D p1 = line.points.get(i - 1);
                    Point2D p2 = line.points.get(i);
                    g2.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
                }
            }
        }
    }


    public Dimension getPreferredSize(){
        return new Dimension(500, 300);
    }
}



class ScribblePanel extends JPanel {
    private final DrawComponent drawComponent = new DrawComponent();

    public ScribblePanel() {
        setLayout(new BorderLayout());
        add(drawComponent, BorderLayout.CENTER);
        // This is for the reset button
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton reset = new JButton("Reset");
        panel.setSize(500, 500);
        panel.add(reset);
        add(panel, BorderLayout.SOUTH);
        // ActionListener
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawComponent.reset();
            }
        });
    }
}

public class ScribbleApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Scribble App - Mouse Event Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ScribblePanel());
        frame.pack();
        frame.setVisible(true);
    }
}
