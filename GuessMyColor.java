import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class GuessMyColor extends JFrame {


    private static JLabel check; //actual colour
    private static int R;
    private static int G;
    private static int B;

    private static JLabel guess; //values for user guess
    private static int guessR;
    private static int guessG;
    private static int guessB;


    GuessMyColor() {
        setTitle("Guess My Color");
        setPreferredSize(new Dimension(600, 350));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());

        initGUI();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        setLocationRelativeTo(null);
        pack();
    }

    private void initGUI() {
        getContentPane().setLayout(new BorderLayout());

        JPanel colors = new JPanel(); // both colours that appear
        guess = new JLabel();
        guess.setOpaque(true);
        guess.setBackground(Color.BLACK);
        guess.setPreferredSize(new Dimension(50, 50));

        check = new JLabel();
        check.setOpaque(true);
        check.setBackground(Color.GRAY);
        check.setPreferredSize(new Dimension(50, 50));

        colors.add(guess);
        colors.add(check);
        getContentPane().add(colors, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        for (Color c : new Color[]{Color.RED, Color.GREEN, Color.BLUE}) {
            for (JButton b : makeButtons(c)) {
                buttons.add(b);
            }
        }
        getContentPane().add(buttons, BorderLayout.SOUTH);
    }

    private static JButton[] makeButtons(Color c) {
        JButton add = new JButton("+");
        JButton dec = new JButton("-");

        add.setBackground(c);
        dec.setBackground(c);

        add.addActionListener((ActionEvent a) -> {
            if (c == Color.RED && guessR != 255) {
                guessR += 30;
            } else if (c == Color.GREEN && guessG != 255) {
                guessG += 30;
            } else if (c == Color.BLUE && guessB != 255) {
                guessB += 30;
            }

        });

        dec.addActionListener((ActionEvent a) -> {
            if (c == Color.RED && guessR != 255) {
                guessR -= 30;
            } else if (c == Color.GREEN && guessG != 255) {
                guessG -= 30;
            } else if (c == Color.BLUE && guessB != 255) {
                guessB -= 30;
            }

        });

        return new JButton[] {add, dec};
    }


    public static void main(String[] args) {
        new GuessMyColor();
    }
}
