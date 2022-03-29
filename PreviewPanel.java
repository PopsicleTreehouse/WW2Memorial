import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

public class PreviewPanel extends JPanel {
    private String title;
    private BufferedImage backgroundImage;
    private ContentPanel presentedPanel;

    public PreviewPanel(String title, String info, BufferedImage backgroundImage) {
        this.title = title;
        this.backgroundImage = backgroundImage;
        this.presentedPanel = new ContentPanel(info, backgroundImage);
        this.setBackground(Color.BLACK);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image scaledImage = backgroundImage.getScaledInstance(-1, getPreferredSize().height - 40, Image.SCALE_SMOOTH);
        g.drawImage(scaledImage, 0, 0, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", 20, 20));
        g.drawString(title, 0, scaledImage.getHeight(null) + 25);
    }

    public ContentPanel getPanel() {
        return presentedPanel;
    }
}