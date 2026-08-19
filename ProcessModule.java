import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Process {
    int id;
    String state;

    public Process(int id) {
        this.id = id;
        this.state = "Ready";
    }
}

public class ProcessModule {

    private JFrame frame;
    private JPanel processPanel;
    private ArrayList<Process> processes = new ArrayList<>();
    private int processCount = 1;

    public ProcessModule() {

        frame = new JFrame("Process Management");
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        // Top Buttons
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(30, 30, 30));

        JButton addBtn = createButton("Add Process");
        JButton runBtn = createButton("Run");
        JButton ioBtn = createButton("I/O Interrupt");
        JButton completeBtn = createButton("Complete");

        topPanel.add(addBtn);
        topPanel.add(runBtn);
        topPanel.add(ioBtn);
        topPanel.add(completeBtn);

        // Process Display Panel
        processPanel = new JPanel();
        processPanel.setLayout(new FlowLayout());
        processPanel.setBackground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(processPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Processes"));

        // Add components
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Actions
        addBtn.addActionListener(e -> addProcess());
        runBtn.addActionListener(e -> runProcess());
        ioBtn.addActionListener(e -> ioInterrupt());
        completeBtn.addActionListener(e -> completeProcess());
    }

    private void addProcess() {
        Process p = new Process(processCount++);
        processes.add(p);
        refreshUI();
    }

    private void runProcess() {
        for (Process p : processes) {
            if (p.state.equals("Ready")) {
                p.state = "Running";
                break;
            }
        }
        refreshUI();
    }

    private void ioInterrupt() {
        for (Process p : processes) {
            if (p.state.equals("Running")) {
                p.state = "Waiting";
                break;
            }
        }
        refreshUI();
    }

    private void completeProcess() {
        processes.removeIf(p -> p.state.equals("Running"));
        refreshUI();
    }

    private void refreshUI() {
        processPanel.removeAll();

        for (Process p : processes) {
            JLabel label = new JLabel("P" + p.id + " - " + p.state);
            label.setOpaque(true);
            label.setForeground(Color.WHITE);
            label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            switch (p.state) {
                case "Ready":
                    label.setBackground(Color.ORANGE);
                    break;
                case "Running":
                    label.setBackground(Color.GREEN);
                    break;
                case "Waiting":
                    label.setBackground(Color.RED);
                    break;
            }

            processPanel.add(label);
        }

        processPanel.revalidate();
        processPanel.repaint();
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(70, 130, 180));
        btn.setForeground(Color.WHITE);
        return btn;
    }
}