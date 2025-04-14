import javax.swing.*;

public class Game extends JFrame {

    public Game() {
        super("Game");

        setSize(1080, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }


    public static void main(String[] args) {
        new Game();
    }
}

