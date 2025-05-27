package atm;

import javax.swing.*;
import java.awt.*;

public class DepositPanel extends JFrame {

    public DepositPanel(User user) {
        setTitle("Deposit Money");
        setSize(400, 220);
        setMinimumSize(new Dimension(350, 220));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout(15, 15));

        // Header label
        JLabel header = new JLabel("💵 Deposit Money", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setOpaque(true);
        header.setBackground(new Color(46, 139, 87));  // dark green
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(header, BorderLayout.NORTH);

        // Center panel for form controls
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(245, 250, 255));
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel amountLabel = new JLabel("Enter amount to deposit:");
        amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        centerPanel.add(amountLabel, gbc);

        JTextField amountField = new JTextField();
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        amountField.setPreferredSize(new Dimension(200, 35));  // Bigger, taller input box
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;           // Allow horizontal expansion
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(amountField, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 250, 255));
        JButton depositBtn = new JButton("Deposit");
        depositBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        depositBtn.setBackground(new Color(46, 139, 87));
        depositBtn.setForeground(Color.WHITE);
        depositBtn.setFocusPainted(false);
        depositBtn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        depositBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(depositBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        depositBtn.addActionListener(e -> {
            String input = amountField.getText().trim();
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) {
                    user.setBalance(user.getBalance() + amount);
                    showMessage("Deposited $" + String.format("%.2f", amount) + "\nNew Balance: $" + String.format("%.2f", user.getBalance()), "Success");
                    dispose();
                } else {
                    showMessage("Enter a valid positive amount", "Error");
                }
            } catch (NumberFormatException ex) {
                showMessage("Enter a valid number", "Error");
            }
        });

        setVisible(true);
    }

    private void showMessage(String message, String title) {
        JLabel label = new JLabel("<html><body style='width: 200px; font-family:Segoe UI; font-size:14px;'>" + message.replace("\n", "<br>") + "</body></html>");
        label.setForeground(title.equals("Success") ? new Color(46, 139, 87) : Color.RED);
        JOptionPane.showMessageDialog(this, label, title, title.equals("Success") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }
}
