import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class MemoryBlock {
    int size;
    String name;

    public MemoryBlock(String name, int size) {
        this.name = name;
        this.size = size;
    }
}

public class MemoryModule {

    private JFrame frame;
    private JPanel memoryPanel;
    private ArrayList<MemoryBlock> blocks = new ArrayList<>();
    private int totalMemory = 100;

    public MemoryModule() {

        frame = new JFrame("Memory Management");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        // Top Panel
        JPanel topPanel = new JPanel();
        JTextField nameField = new JTextField(5);
        JTextField sizeField = new JTextField(5);

        JButton allocateBtn = new JButton("Allocate");
        JButton freeBtn = new JButton("Free Last");

        topPanel.add(new JLabel("Process:"));
        topPanel.add(nameField);
        topPanel.add(new JLabel("Size:"));
        topPanel.add(sizeField);
        topPanel.add(allocateBtn);
        topPanel.add(freeBtn);

        // Memory Panel
        memoryPanel = new JPanel();
        memoryPanel.setBackground(Color.BLACK);
        memoryPanel.setLayout(new FlowLayout());

        JScrollPane scrollPane = new JScrollPane(memoryPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Memory Blocks"));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Actions
        allocateBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int size = Integer.parseInt(sizeField.getText());

                if (getUsedMemory() + size > totalMemory) {
                    JOptionPane.showMessageDialog(frame, "Not enough memory!");
                    return;
                }

                blocks.add(new MemoryBlock(name, size));
                nameField.setText("");
                sizeField.setText("");
                refreshUI();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input");
            }
        });

        freeBtn.addActionListener(e -> {
            if (!blocks.isEmpty()) {
                blocks.remove(blocks.size() - 1);
                refreshUI();
            }
        });

        refreshUI();
    }

    private int getUsedMemory() {
        int sum = 0;
        for (MemoryBlock b : blocks) {
            sum += b.size;
        }
        return sum;
    }

    private void refreshUI() {
        memoryPanel.removeAll();

        for (MemoryBlock b : blocks) {
            memoryPanel.add(createBlock(b.name, b.size));
        }

        int free = totalMemory - getUsedMemory();
        memoryPanel.add(createBlock("Free", free));

        memoryPanel.revalidate();
        memoryPanel.repaint();
    }

    private JPanel createBlock(String name, int size) {
        JPanel block = new JPanel();
        block.setPreferredSize(new Dimension(size * 3, 60));
        block.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        if (name.equals("Free")) {
            block.setBackground(Color.GRAY);
        } else {
            block.setBackground(new Color(34, 139, 34));
        }

        JLabel label = new JLabel(name + " (" + size + ")");
        label.setForeground(Color.WHITE);
        block.add(label);

        return block;
    }
}