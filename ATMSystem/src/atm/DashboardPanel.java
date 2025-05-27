package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardPanel extends JFrame {

    public DashboardPanel(User user) {
        // Theme toggle: Set true for dark theme
        boolean darkTheme = false;

        // Window setup
        setTitle("ATM Dashboard");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 15, 15));
        setResizable(false);
        setUndecorated(false);

        // Theme colors
        Color bgColor = darkTheme ? new Color(40, 40, 40) : Color.WHITE;
        Color fgColor = darkTheme ? Color.WHITE : Color.BLACK;
        Color hoverColor = darkTheme ? new Color(70, 70, 70) : new Color(200, 230, 255);

        Font btnFont = new Font("Segoe UI", Font.BOLD, 14);

        // Buttons with icons and tooltips
        JButton infoBtn = createButton("Account Info", "assets/info.png", "View account info", hoverColor, bgColor);
        JButton withdrawBtn = createButton("Withdraw", "assets/withdraw.png", "Withdraw cash", hoverColor, bgColor);
        JButton depositBtn = createButton("Deposit", "assets/deposit.png", "Deposit funds", hoverColor, bgColor);
        JButton statementBtn = createButton("Mini Statement", "assets/statement.png", "View mini statement", hoverColor, bgColor);
        JButton pinChangeBtn = createButton("Change PIN", "assets/pin.png", "Change ATM PIN", hoverColor, bgColor);
        JButton logoutBtn = createButton("Logout", "assets/logout.png", "Logout", hoverColor, bgColor);

        JButton[] buttons = {infoBtn, withdrawBtn, depositBtn, statementBtn, pinChangeBtn, logoutBtn};

        for (JButton btn : buttons) {
            btn.setFont(btnFont);
            btn.setForeground(fgColor);
            btn.setBackground(bgColor);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Animation: hover zoom effect
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(hoverColor);
                    btn.setSize(btn.getWidth() + 4, btn.getHeight() + 4);
                }

                public void mouseExited(MouseEvent e) {
                    btn.setBackground(bgColor);
                    btn.setSize(btn.getWidth() - 4, btn.getHeight() - 4);
                }
            });

            add(btn);
        }

        // Button actions
        infoBtn.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "User: " + user.getUsername() + "\nBalance: $" + user.getBalance()));
        withdrawBtn.addActionListener(e -> new WithdrawPanel(user));
        depositBtn.addActionListener(e -> new DepositPanel(user));
        statementBtn.addActionListener(e -> new MiniStatement(user));
        pinChangeBtn.addActionListener(e -> new PinChange(user));
        logoutBtn.addActionListener(e -> {
            dispose();
            new Login();
        });

        getContentPane().setBackground(bgColor);
        setVisible(true);
    }

    // Create a button with icon, tooltip, and hover color
    private JButton createButton(String text, String iconPath, String tooltip, Color hoverColor, Color bgColor) {
        JButton button = new JButton(text, resizeIcon(iconPath, 40, 40));
        button.setToolTipText(tooltip);
        return button;
    }

    // Resize icons utility
    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
