import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class ContentPanel extends JPanel {
    private TextPanel panel;
    private Image backgroundImage;

    public ContentPanel(String text, BufferedImage backgroundImage) {
        this.setLayout(null);
        this.panel = new TextPanel(text);
        this.backgroundImage = backgroundImage.getScaledInstance(575, -1, Image.SCALE_SMOOTH);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        this.setBackground(Color.BLACK);
        this.add(this.panel, BorderLayout.EAST);
        this.panel.setBounds(585, 15, 500, getPreferredSize().height - 30);
    }

    public void displayLabel() {
        panel.displayLabel();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1100, Math.min(600, backgroundImage.getHeight(null)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image scaledImage = backgroundImage.getScaledInstance(575, -1, Image.SCALE_SMOOTH);
        g.drawImage(scaledImage, 0, 0, null);
    }
}