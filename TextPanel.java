import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

public class TextPanel extends JPanel implements MouseInputListener {
    private JTextArea textArea;
    private JScrollPane containerPanel;

    public TextPanel(String text) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        textArea = new JTextArea(text);
        textArea.setFont(loadFont());
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(new Color(51, 255, 51));
        textArea.setBorder(new EmptyBorder(10, 3, 10, 0));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setHighlighter(null);
        containerPanel = new JScrollPane(textArea);
        containerPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        containerPanel.setBorder(BorderFactory.createEmptyBorder());
        textArea.addMouseListener(this);
    }

    public static final Font loadFont() {
        try {
            InputStream myStream = new BufferedInputStream(new FileInputStream("./assets/fonts/CONSOLA.TTF"));
            Font consolasFont = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(20.0f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(consolasFont);
            return consolasFont;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void scrollIfPossible() {
        JScrollBar verticalScrollBar = containerPanel.getVerticalScrollBar();
        if (verticalScrollBar.getValue() + verticalScrollBar.getVisibleAmount() >= verticalScrollBar.getMaximum()) {
            verticalScrollBar.setValue(0);
            return;
        }
        verticalScrollBar.setValue(verticalScrollBar.getValue() + 20);
    }

    public void displayLabel() {
        containerPanel.setPreferredSize(new Dimension(getBounds().width, getBounds().height
                - 10));
        add(containerPanel, BorderLayout.NORTH);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        scrollIfPossible();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}