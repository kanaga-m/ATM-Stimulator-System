package atm;

import javax.swing.*;

public class ATMApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ATM App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            // Prompt user for input
            String username = JOptionPane.showInputDialog(frame, "Enter Username:");
            String pin = JOptionPane.showInputDialog(frame, "Enter PIN:");
            String accountNumber = JOptionPane.showInputDialog(frame, "Enter Account Number:");
            String email = JOptionPane.showInputDialog(frame, "Enter Email:");
            String accountType = JOptionPane.showInputDialog(frame, "Enter Account Type (e.g., Savings):");
            String ifscCode = JOptionPane.showInputDialog(frame, "Enter IFSC Code:");
            String branch = JOptionPane.showInputDialog(frame, "Enter Branch Name:");
            String balanceStr = JOptionPane.showInputDialog(frame, "Enter Balance:");
            String createdDate = JOptionPane.showInputDialog(frame, "Enter Account Created Date:");
            String lastLogin = JOptionPane.showInputDialog(frame, "Enter Last Login Date & Time:");

            double balance = 0.0;
            try {
                balance = Double.parseDouble(balanceStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid balance. Setting to 0.0", "Warning", JOptionPane.WARNING_MESSAGE);
            }

            // Create User object
            User user = new User(username, pin, balance, accountNumber, email, accountType, ifscCode, branch, createdDate, lastLogin);

            // Show account info
            AccountInfoPanel.showAccountInfoDialog(frame, user);
        });
    }
}
