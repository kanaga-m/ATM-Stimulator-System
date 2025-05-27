package atm;

import javax.swing.*;
import java.awt.*;

public class WithdrawPanel extends JFrame {

    public WithdrawPanel(User user) {
        setTitle("Withdraw Money");
        setSize(400, 220);
        setMinimumSize(new Dimension(350, 220));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout(15, 15));

        // Header label
        JLabel header = new JLabel("💸 Withdraw Money", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setOpaque(true);
        header.setBackground(new Color(178, 34, 34)); // dark red
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(header, BorderLayout.NORTH);

        // Center panel for form controls
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(245, 250, 255));
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel amountLabel = new JLabel("Enter amount to withdraw:");
        amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        centerPanel.add(amountLabel, gbc);

        JTextField amountField = new JTextField();
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        amountField.setPreferredSize(new Dimension(200, 35)); // Taller input box
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;        // Let the textfield expand horizontally
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(amountField, gbc);

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        withdrawBtn.setBackground(new Color(178, 34, 34));
        withdrawBtn.setForeground(Color.WHITE);
        withdrawBtn.setFocusPainted(false);
        withdrawBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        centerPanel.add(withdrawBtn, gbc);

        add(centerPanel, BorderLayout.CENTER);

        withdrawBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText().trim());
                if (amount <= 0) {
                    showMessage("Enter a valid positive amount", JOptionPane.WARNING_MESSAGE);
                } else if (amount > user.getBalance()) {
                    showMessage("Insufficient balance", JOptionPane.WARNING_MESSAGE);
                } else {
                    user.setBalance(user.getBalance() - amount);
                    showMessage("Withdrawn $" + amount + "\nNew Balance: $" + user.getBalance(), JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (NumberFormatException ex) {
                showMessage("Enter a valid number", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    private void showMessage(String message, int messageType) {
        JLabel label = new JLabel("<html><body style='font-family:Segoe UI; font-size:14px;'>" + message.replace("\n", "<br>") + "</body></html>");
        label.setForeground(messageType == JOptionPane.ERROR_MESSAGE ? Color.RED : new Color(178, 34, 34));
        JOptionPane.showMessageDialog(this, label, "Withdraw Money", messageType);
    }
}
