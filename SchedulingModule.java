import javax.swing.*;
import java.awt.*;
import java.util.*;

class SProcess {
    int id, burstTime;

    public SProcess(int id, int burstTime) {
        this.id = id;
        this.burstTime = burstTime;
    }
}

public class SchedulingModule {

    private JFrame frame;
    private JPanel ganttPanel;
    private ArrayList<SProcess> processes = new ArrayList<>();
    private int processCount = 1;

    public SchedulingModule() {

        frame = new JFrame("CPU Scheduling");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        // Top Panel
        JPanel topPanel = new JPanel();
        JTextField burstField = new JTextField(5);
        JButton addBtn = new JButton("Add Process");
        JButton fcfsBtn = new JButton("Run FCFS");
        JButton rrBtn = new JButton("Run RR");

        topPanel.add(new JLabel("Burst:"));
        topPanel.add(burstField);
        topPanel.add(addBtn);
        topPanel.add(fcfsBtn);
        topPanel.add(rrBtn);

        // Gantt Panel
        ganttPanel = new JPanel();
        ganttPanel.setBackground(Color.BLACK);
        ganttPanel.setLayout(new FlowLayout());

        JScrollPane scrollPane = new JScrollPane(ganttPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Gantt Chart"));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Actions
        addBtn.addActionListener(e -> {
            try {
                int bt = Integer.parseInt(burstField.getText());
                processes.add(new SProcess(processCount++, bt));
                burstField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Enter valid number");
            }
        });

        fcfsBtn.addActionListener(e -> runFCFS());
        rrBtn.addActionListener(e -> runRR());
    }

    private void runFCFS() {
        ganttPanel.removeAll();

        for (SProcess p : processes) {
            ganttPanel.add(createBlock("P" + p.id, p.burstTime));
        }

        refresh();
    }

    private void runRR() {
        ganttPanel.removeAll();

        int quantum = 2;
        Queue<SProcess> queue = new LinkedList<>();

        for (SProcess p : processes) {
            queue.add(new SProcess(p.id, p.burstTime));
        }

        while (!queue.isEmpty()) {
            SProcess p = queue.poll();

            int execTime = Math.min(p.burstTime, quantum);
            ganttPanel.add(createBlock("P" + p.id, execTime));

            p.burstTime -= execTime;

            if (p.burstTime > 0) {
                queue.add(p);
            }
        }

        refresh();
    }

    private JPanel createBlock(String name, int widthFactor) {
        JPanel block = new JPanel();
        block.setPreferredSize(new Dimension(widthFactor * 40, 50));
        block.setBackground(new Color(70, 130, 180));
        block.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        JLabel label = new JLabel(name);
        label.setForeground(Color.WHITE);
        block.add(label);

        return block;
    }

    private void refresh() {
        ganttPanel.revalidate();
        ganttPanel.repaint();
    }
}