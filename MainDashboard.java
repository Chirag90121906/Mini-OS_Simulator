import javax.swing.*;
import java.awt.*;

public class MainDashboard {

    public static void main(String[] args) {

        // Create Frame
        JFrame frame = new JFrame("Mini OS Simulator");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center screen

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30)); // dark theme

        // Title
        JLabel title = new JLabel("Mini OS Simulator", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 20, 20));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 40, 100));

        // Buttons
        JButton processBtn = createButton("Process Management");
        JButton schedulingBtn = createButton("CPU Scheduling");
        JButton memoryBtn = createButton("Memory Management");

        // Add buttons
        buttonPanel.add(processBtn);
        buttonPanel.add(schedulingBtn);
        buttonPanel.add(memoryBtn);

        // Add to panel
        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);

        // Button Actions (for now just placeholders)
        processBtn.addActionListener(e -> new ProcessModule());
        schedulingBtn.addActionListener(e -> new SchedulingModule());
        memoryBtn.addActionListener(e -> new MemoryModule());
    }

    // Button styling method
    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return button;
    }
}