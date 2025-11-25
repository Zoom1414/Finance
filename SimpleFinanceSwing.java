import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SimpleFinanceSwing {

    private ArrayList<String> expenses = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public SimpleFinanceSwing() {
        // สร้างหน้าต่างหลัก
        JFrame frame = new JFrame("Simple Personal Finance Tracker");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ฟิลด์กรอกข้อมูล
        JTextField categoryField = new JTextField(10);
        JTextField amountField = new JTextField(10);
        JTextField dateField = new JTextField(10);
        dateField.setText("15/01/2025");

        JButton addButton = new JButton("Save");
         JButton summaryButton = new JButton("Month Summary");


        // List แสดงรายการ
        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        // Panel สำหรับกรอกข้อมูล
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 4, 5, 5)); 
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Amout:"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Date:"));
        inputPanel.add(dateField);
        inputPanel.add(addButton);
        inputPanel.add(summaryButton);

        // Layout หลัก
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Event บันทึกค่าใช้จ่าย
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = categoryField.getText();
                String amount = amountField.getText();
                String date = dateField.getText();

                if(!category.isEmpty() && !amount.isEmpty() && !date.isEmpty()) {
                    String record = date + " | " + category + " | " + amount;
                    expenses.add(record);
                    listModel.addElement(record);

                    categoryField.setText("");
                    amountField.setText("");
                    dateField.setText("xxxx/xx/xxxx");
                } else {
                    JOptionPane.showMessageDialog(frame, "Data Fail");
                }
            }
            
        });

         summaryButton.addActionListener(e -> {
            double total = 0;
            for(String record : expenses) {
                String[] parts = record.split("\\|");
                if(parts.length == 3) {
                    try {
                        total += Double.parseDouble(parts[2].trim());
                    } catch(NumberFormatException ex) {
                        // ข้ามถ้าไม่ใช่ตัวเลข
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, "Total Expenses: " + total, "Total", JOptionPane.INFORMATION_MESSAGE);
        });


        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Run GUI
        SwingUtilities.invokeLater(() -> new SimpleFinanceSwing());
    }
}