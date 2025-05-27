package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginBtn, signUpBtn;

    public Login() {
        setTitle("ATM Login");
        setSize(420, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 250, 255));

        JLabel title = new JLabel("Welcome to Smart ATM", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
        panel.setOpaque(false);

        userField = new JTextField("Enter Username");
        styleTextField(userField);

        passField = new JPasswordField("Enter PIN");
        stylePasswordField(passField);

        panel.add(userField);
        panel.add(passField);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setOpaque(false);
        loginBtn = new JButton("Login");
        signUpBtn = new JButton("Sign Up");

        styleButton(loginBtn);
        styleButton(signUpBtn);

        btnPanel.add(loginBtn);
        btnPanel.add(signUpBtn);

        panel.add(btnPanel);
        add(panel, BorderLayout.CENTER);

        loginBtn.addActionListener(e -> login());
        signUpBtn.addActionListener(e -> {
            dispose();
            new SignUp();
        });

        setVisible(true);
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setForeground(Color.GRAY);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));

        String placeholder = textField.getText();
        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void stylePasswordField(JPasswordField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.GRAY);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));

        String placeholder = "Enter PIN";
        field.setEchoChar((char) 0);
        field.setText(placeholder);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (new String(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('●');
                }
            }

            public void focusLost(FocusEvent e) {
                if (new String(field.getPassword()).isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                    field.setEchoChar((char) 0);
                }
            }
        });
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(30, 144, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void login() {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        if (username.equals("Enter Username") || password.equals("Enter PIN")) {
            showMessage("Please enter valid credentials.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = UserStorage.getUser(username);
        if (user != null && user.getPin().equals(password)) {
            showMessage("Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new DashboardPanel(user);
        } else {
            showMessage("Invalid username or PIN.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMessage(String message, String title, int type) {
        JLabel label = new JLabel(message);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(this, label, title, type);
    }
}
