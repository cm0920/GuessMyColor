import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuessMyColor extends JFrame {

    private static JLabel check; //actual
    private static int R;
    private static int G;
    private static int B;

    private static JLabel guess; //vars for user guess
    private static int guessR = 0;
    private static int guessG = 0;
    private static int guessB = 0;


    GuessMyColor() {
        setTitle("Guess My Color");
        setPreferredSize(new Dimension(600, 350));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());

        initGUI();
        makeColors();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        setLocationRelativeTo(null);
        pack();
    }

    private void initGUI() {
        JPanel title = new JPanel();
        JLabel gTitle = new JLabel("Guess My Color", SwingConstants.CENTER);
        gTitle.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        gTitle.setOpaque(true);
        gTitle.setForeground(Color.WHITE);
        gTitle.setBackground(Color.BLACK);
        gTitle.setPreferredSize(new Dimension(600, 125));

        JPanel colors = new JPanel();
        guess = new JLabel();
        guess.setOpaque(true);
        guess.setBackground(Color.BLACK);
        guess.setPreferredSize(new Dimension(80, 80));

        check = new JLabel();
        check.setBackground(Color.GRAY);
        check.setOpaque(true);
        check.setPreferredSize(new Dimension(80, 80));

        colors.add(guess);
        colors.add(check);
        title.add(gTitle);
        getContentPane().add(colors, BorderLayout.NORTH);
        getContentPane().add(title, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        for (Color c: new Color[]{Color.RED, Color.GREEN, Color.BLUE}) {
            for (JButton b: createButtons(c)) {
                b.setPreferredSize(new Dimension(80, 80));
                buttonPanel.add(b);
            }
        }

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private static JButton[] createButtons(Color c) {
        JButton add = new JButton("+");
        JButton min = new JButton("-");
        add.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        min.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        add.setBackground(c);
        min.setBackground(c);

        add.addActionListener((ActionEvent e) -> {
            if (c == Color.RED && guessR != 255) {
                guessR += 30;
            }
            else if (c == Color.GREEN && guessG != 255) {
                guessG += 30;
            }
            else if (c == Color.BLUE && guessB != 255) {
                guessB += 30;
            }
            printStats();
        });
        min.addActionListener((ActionEvent e) -> {
            if (c == Color.RED && guessR != 0) {
                guessR -= 30;
            }
            else if (c == Color.GREEN && guessG != 0) {
                guessG -= 30;
            }
            else if (c == Color.BLUE && guessB != 0) {
                guessB -= 30;
            }
            printStats();
        });
        return new JButton[]{add, min};
    }

    private static void makeColors() {
        R = (int) (Math.random() * (255 / 30)) * 30;
        G = (int) (Math.random() * (255 / 30)) * 30;
        B = (int) (Math.random() * (255 / 30)) * 30;
        check.setBackground(new Color(R, G, B));
        System.out.printf("(%d, %d, %d)\n", R, G, B);
    }

    private static void printStats() {
        guess.setBackground(new Color(guessR, guessG, guessB));
        System.out.printf("Answer: (%d, %d, %d)\n", R, G, B); //answers
        System.out.printf("Current Guess: (%d, %d, %d)\n", guessR, guessG, guessB);
        if (R == guessR && G == guessG && B == guessB) {
            JOptionPane.showConfirmDialog(null, String.format("Congrats you guessed it. RGB: (%d, %d, %d)", R, G, B), "Congrats!", JOptionPane.OK_CANCEL_OPTION);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new GuessMyColor();
    }
}
