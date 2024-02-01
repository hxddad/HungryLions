import javax.swing.*;


public class Main extends JFrame {

    private JCheckBox checkBox1;
    private JRadioButton radioButton1;
    public Main() {
        setTitle("HungryLions - York University GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();

    }
}