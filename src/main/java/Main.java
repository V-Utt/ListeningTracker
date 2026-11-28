import gui.FrontPage;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrontPage frame = new  FrontPage();
            frame.setVisible(true);
        });
    }
}
