package atm;

import javax.swing.*;

public class AccountInfoPanel {

    public static void showAccountInfoDialog(JFrame parent, User user) {
        if (user == null) {
            JOptionPane.showMessageDialog(parent, "User data not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder info = new StringBuilder();
        info.append("<html><body style='font-family: Segoe UI; font-size: 12px;'>");
        info.append("<h2 style='color: #2E8B57;'>Account Information</h2>");
        info.append("<b>Username:</b> ").append(nullCheck(user.getUsername())).append("<br>");
        info.append("<b>Account Number:</b> ").append(nullCheck(user.getAccountNumber())).append("<br>");
        info.append("<b>Email:</b> ").append(nullCheck(user.getEmail())).append("<br>");
        info.append("<b>Account Type:</b> ").append(nullCheck(user.getAccountType())).append("<br>");
        info.append("<b>IFSC Code:</b> ").append(nullCheck(user.getIfscCode())).append("<br>");
        info.append("<b>Branch:</b> ").append(nullCheck(user.getBranch())).append("<br>");
        info.append("<b>Balance:</b> $").append(String.format("%.2f", user.getBalance())).append("<br>");
        info.append("<b>Account Created Date:</b> ").append(nullCheck(user.getCreatedDate())).append("<br>");
        info.append("<b>Last Login:</b> ").append(nullCheck(user.getLastLogin())).append("<br>");
        info.append("</body></html>");

        JOptionPane.showMessageDialog(parent, info.toString(), "Account Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String nullCheck(String value) {
        return (value == null || value.trim().isEmpty()) ? "N/A" : value;
    }
}
