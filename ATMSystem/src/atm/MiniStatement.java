package atm;

import javax.swing.*;
import java.awt.*;

public class MiniStatement extends JFrame {

    public MiniStatement(User user) {
        setTitle("Mini Statement");
        setSize(450, 350);  // Increased size for responsiveness
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("📋 Mini Statement", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setOpaque(true);
        header.setBackground(new Color(46, 139, 87));
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(header, BorderLayout.NORTH);

        // Statement Area
        JTextArea statementArea = new JTextArea();
        statementArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        statementArea.setText(buildStatement(user));
        statementArea.setEditable(false);
        statementArea.setBackground(Color.WHITE);
        statementArea.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JScrollPane scrollPane = new JScrollPane(statementArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // Info dialog
        showInfoDialog("📄 Mini Statement loaded successfully");

        setResizable(false);
        setVisible(true);
    }

    private String buildStatement(User user) {
        return "----------------------------------------\n" +
                "           💳 MINI STATEMENT\n" +
                "----------------------------------------\n" +
                "👤 User       : " + user.getUsername() + "\n" +
                "💰 Balance    : $" + user.getBalance() + "\n" +
                "----------------------------------------\n" +
                "ℹ️  Note:\n" +
                "Transactions are not tracked\n" +
                "in this version.\n" +
                "----------------------------------------";
    }

    private void showInfoDialog(String message) {
        JLabel label = new JLabel(message);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(46, 139, 87));
        JOptionPane.showMessageDialog(this, label, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
