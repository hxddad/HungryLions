package rewards;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChallengesGUI {

    private ChallengesManager challengesManager;

    public ChallengesGUI() {
        challengesManager = new ChallengesManager();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        challengesManager.addMonthlyChallenges();
        JFrame frame = new JFrame("One-time Challenges");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("One-Time Challenges");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        List<Challenge> allChallenges = challengesManager.getChallenges();
        for (Challenge challenge : allChallenges) {
            JLabel challengeLabel = new JLabel("<html><body>" +
                    "<b>" + challenge.getName() + "</b>: " +
                    challenge.getDescription() + "<br>" +
                    "Progress: " + challenge.getProgress() + "/" + challenge.getGoal() + "</body></html>");
            challengeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(challengeLabel);
            panel.add(new JLabel(" "));
        }
        
        

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(700, 350);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChallengesGUI::new);
    }
}
