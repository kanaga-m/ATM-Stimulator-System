package atm;

import javax.swing.*;
import java.awt.*;

public class PinChange extends JFrame {

    public PinChange(User user) {
        setTitle("Change PIN");
        setSize(450, 300);  // Increased size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("🔒 Change Your PIN", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setOpaque(true);
        header.setBackground(new Color(46, 139, 87));
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(header, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 250, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel currentPinLabel = new JLabel("Current PIN:");
        JPasswordField currentPinField = new JPasswordField();
        styleField(currentPinField);

        JLabel newPinLabel = new JLabel("New PIN (4 digits):");
        JPasswordField newPinField = new JPasswordField();
        styleField(newPinField);

        // Adding components with layout positions
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(currentPinLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        centerPanel.add(currentPinField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(newPinLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(newPinField, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Change Button
        JButton changeBtn = new JButton("Change PIN");
        styleButton(changeBtn);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 250, 255));
        buttonPanel.add(changeBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listener
        changeBtn.addActionListener(e -> {
            String currentPin = new String(currentPinField.getPassword());
            String newPin = new String(newPinField.getPassword());

            if (!currentPin.equals(user.getPin())) {
                showMessage("❌ Incorrect current PIN", Color.RED);
            } else if (!newPin.matches("\\d{4}")) {
                showMessage("⚠️ New PIN must be exactly 4 digits", Color.ORANGE);
            } else {
                user.setPin(newPin);
                showMessage("✅ PIN changed successfully!", new Color(46, 139, 87));
                dispose();
            }
        });

        setResizable(false);
        setVisible(true);
    }

    private void styleField(JPasswordField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setPreferredSize(new Dimension(200, 35));  // Bigger text field
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(new Color(46, 139, 87));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void showMessage(String message, Color color) {
        JLabel label = new JLabel(message);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(color);
        JOptionPane.showMessageDialog(this, label, "PIN Change Status", JOptionPane.INFORMATION_MESSAGE);
    }
}
