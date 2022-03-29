import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;
import javax.json.*;

public class Gallery implements MouseListener {
    private HashMap<String, JPanel> allPanels;
    private JFrame parentFrame;
    private JLayeredPane parentPanel;
    private ContentPanel presentedPanel;

    public Gallery() {
        allPanels = new HashMap<String, JPanel>();
        parentFrame = new JFrame();
        parentFrame.addMouseListener(this);
        parentFrame.setSize(new Dimension(1720, 1160));
        parentFrame.getContentPane().setBackground(Color.BLACK);
        parentPanel = new JLayeredPane();
        parentPanel.setPreferredSize(parentFrame.getSize());
        loadPanels();
    }

    private void loadPanels() {
        try {
            int location = 0;
            for (HashMap<String, String> map : loadFromJson()) {
                File imageLocation = new File("./assets/images/" + map.get("image"));
                BufferedImage backgroundImage = ImageIO.read(imageLocation);
                String name = map.get("name");
                String info = map.get("info");
                PreviewPanel preview = new PreviewPanel(name, info, backgroundImage);
                preview.setBounds(location % 5 * 340 + 20, location / 5 * 540 + 40, 320, 500);
                allPanels.put(name, preview);
                parentPanel.add(preview, JLayeredPane.DEFAULT_LAYER);
                location++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayParentFrame() {
        parentFrame.add(parentPanel);
        parentFrame.setVisible(true);
        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentFrame.setLocationRelativeTo(null);
        parentFrame.pack();
    }

    private void displayPanel(PreviewPanel panel) {
        presentedPanel = panel.getPanel();
        presentedPanel.setVisible(true);
        int height = presentedPanel.getPreferredSize().height;
        presentedPanel.setBounds(300, 200, 1100, height);
        presentedPanel.displayLabel();
        parentPanel.add(presentedPanel, JLayeredPane.POPUP_LAYER);
        parentFrame.pack();
    }

    public ArrayList<HashMap<String, String>> loadFromJson() throws IOException {
        InputStream fis = new FileInputStream("./assets/gallery.json");
        JsonReader reader = Json.createReader(fis);
        JsonArray array = reader.readArray();
        reader.close();
        ArrayList<HashMap<String, String>> allItems = new ArrayList<>();
        for (JsonValue value : array) {
            JsonObject valueObject = value.asJsonObject();
            HashMap<String, String> map = new HashMap<String, String>();
            for (String key : valueObject.keySet())
                map.put(key, valueObject.getString(key));
            allItems.add(map);
        }
        return allItems;
    }

    private void presentPanelAtPoint(Point p) {
        for (String key : allPanels.keySet()) {
            PreviewPanel preview = (PreviewPanel) allPanels.get(key);
            if (preview.getBounds().contains(p)) {
                displayPanel(preview);
                return;
            }
        }
    }

    private void hidePresentedPanel() {
        presentedPanel.setVisible(false);
        parentPanel.remove(presentedPanel);
        parentFrame.pack();
        presentedPanel = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickedPoint = e.getPoint();
        if (presentedPanel == null)
            presentPanelAtPoint(clickedPoint);
        else
            hidePresentedPanel();
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
}