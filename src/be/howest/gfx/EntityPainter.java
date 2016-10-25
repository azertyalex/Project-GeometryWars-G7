package be.howest.gfx;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EntityPainter {
    
    private ColorSelection selectedColor;

    public static void main(String[] args) {
        new EntityPainter();
    }

    public EntityPainter() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                //FRAME    
                JFrame frame = new JFrame("Sprite Creator");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel pixelCanvas = new ColorPanel();

                addPixelCanvas(frame, pixelCanvas);
                addColorChooser(frame);
                addMenu(frame,pixelCanvas);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    private void addPixelCanvas(JFrame frame, JPanel pixelCanvas) {
        //PAINTER
        pixelCanvas.setOpaque(false);
        frame.add(pixelCanvas);
    }

    private void addColorChooser(JFrame frame) {
        //PICKER
        JColorChooser jcc = new JColorChooser();
        selectedColor = new ColorSelection(jcc);
        
        jcc.getSelectionModel().addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent ce) {
                System.out.println(jcc.getColor());
                selectedColor.setColor(jcc.getColor());
            }
        });
        
        frame.add(jcc, BorderLayout.PAGE_END);

        AbstractColorChooserPanel[] panels = jcc.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if (!accp.getDisplayName().equals("RGB")) {
                jcc.removeChooserPanel(accp);
            }
        }
        jcc.setPreviewPanel(new JPanel());
        jcc.setBorder(BorderFactory.createLineBorder(Color.black));

    }

    private void addMenu(JFrame frame, JPanel pixelCanvas) {
        //Button
        JButton btn = new JButton("Save Image");
        frame.add(btn, BorderLayout.PAGE_START);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save((ColorPanel) pixelCanvas);
            }
        });

    }

    public void save(ColorPanel colorPanel) {
        //Image
        BufferedImage fullImage = new BufferedImage(colorPanel.getWidth(), colorPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);

        //Max position of (pixel) creation
        int x_left = ((colorPanel.getLeft() * colorPanel.getWidth()) / ColorPanel.BOX_SIZE);
        int y_top = ((colorPanel.getTop() * colorPanel.getHeight()) / ColorPanel.BOX_SIZE);
        int x_right = ((colorPanel.getRight() * colorPanel.getWidth()) / ColorPanel.BOX_SIZE);
        int y_bottom = ((colorPanel.getBottom() * colorPanel.getHeight()) / ColorPanel.BOX_SIZE);

        //height and width of (pixel) creation
        int width = (x_right - x_left) + ColorPanel.BOX_SIZE;
        int height = (y_bottom - y_top) + ColorPanel.BOX_SIZE;

        //Cropped image
        BufferedImage croppedImage = fullImage.getSubimage(x_left, y_top, width, height);

        Graphics2D cg = fullImage.createGraphics();
        colorPanel.paintAll(cg);
        String creation_name = "";

        try {
            if (ImageIO.write(croppedImage, "png", new File("./Custom/" + "test" + ".png"))) {
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Ship isn't saved!	\n"
                    + "Please try again.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    public class ColorPanel extends JPanel {

        protected static final int ROWS = 25;
        protected static final int COLS = 25;
        protected static final int BOX_SIZE = 25;
        private int left = 25, right = 0, top = 25, bottom = 0;

        private Color[][] pixelCanvas = new Color[ROWS][COLS];

        public ColorPanel() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    myMousePressed(e);
                }
            });

            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    //Transparant
                    pixelCanvas[row][col] = new Color(0, 0, 0, 0);
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(COLS * BOX_SIZE, ROWS * BOX_SIZE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphic = (Graphics2D) g.create();

            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    graphic.setColor(pixelCanvas[row][col]);
                    graphic.fillRect(((col * getWidth()) / BOX_SIZE), ((row * getHeight()) / BOX_SIZE), BOX_SIZE, BOX_SIZE);
                }
            }
            graphic.setComposite(AlphaComposite.Clear);
            graphic.dispose();
        }

        private void myMousePressed(MouseEvent e) {
            // find relative position of mouse press on grid.
            int i = (e.getX() * BOX_SIZE) / getWidth();
            int j = (e.getY() * BOX_SIZE) / getHeight();

            if (selectedColor.getColor().getAlpha() == 255) {
                top = (j < top) ? j : top;
                bottom = (j > bottom) ? j : bottom;
                left = (i < left) ? i : left;
                right = (i > right) ? i : right;
            }

//            System.out.println("top: " + top + "bot: " + bottom + "left: " + left + "right: " + right);
            pixelCanvas[j][i] = selectedColor.getColor();

            repaint();
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        public int getTop() {
            return top;
        }

        public int getBottom() {
            return bottom;
        }

    }

    public class ColorSelection{

        public JColorChooser jcc;
        public Color color;

        ColorSelection(JColorChooser jcc){
            this.jcc = jcc;
        }
        
        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }
}
