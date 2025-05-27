package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDateTime;

public class SignUp extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField pinField;
    private JComboBox<String> accountTypeCombo;
    private JButton createBtn;

    public SignUp() {
        setTitle("ATM Sign Up");
        setSize(420, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 250, 255));

        JLabel title = new JLabel("Create Your Account", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
        panel.setOpaque(false);

        nameField = new JTextField("Enter Username");
        pinField = new JPasswordField("Enter 4-digit PIN");
        emailField = new JTextField("Enter Email");

        styleTextField(nameField);
        styleTextField(emailField);
        stylePasswordField(pinField);

        accountTypeCombo = new JComboBox<>(new String[]{"Savings", "Current"});
        accountTypeCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panel.add(nameField);
        panel.add(pinField);
        panel.add(emailField);
        panel.add(accountTypeCombo);

        createBtn = new JButton("Create Account");
        styleButton(createBtn);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setOpaque(false);
        btnPanel.add(createBtn);
        panel.add(btnPanel);

        add(panel, BorderLayout.CENTER);

        createBtn.addActionListener(e -> createAccount());

        setVisible(true);
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.GRAY);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));

        String placeholder = field.getText();
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
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

        String placeholder = "Enter 4-digit PIN";
        field.setEchoChar((char) 0);
        field.setText(placeholder);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                String text = new String(field.getPassword());
                if (text.equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('●');
                }
            }

            public void focusLost(FocusEvent e) {
                String text = new String(field.getPassword());
                if (text.isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                    field.setEchoChar((char) 0);
                }
            }
        });
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(46, 139, 87));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void createAccount() {
        String username = nameField.getText();
        String pin = new String(pinField.getPassword());
        String email = emailField.getText();
        String selectedAccountType = accountTypeCombo.getSelectedItem().toString();

        if (username.equals("Enter Username") || pin.equals("Enter 4-digit PIN") || email.equals("Enter Email")) {
            showMessage("Please fill all fields.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (pin.length() == 4 && pin.matches("\\d+")) {
            // Provide default values for missing fields
            String ifscCode = "DEFAULT_IFSC";
            String branch = "DEFAULT_BRANCH";
            String createdDate = getCurrentDateTime();
            String lastLogin = getCurrentDateTime();

            User user = new User(
                    username,
                    pin,
                    0.0,
                    generateAccountNumber(),
                    email,
                    selectedAccountType,
                    ifscCode,
                    branch,
                    createdDate,
                    lastLogin
            );

            UserStorage.addUser(user);
            showMessage("Account Created Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new Login();
        } else {
            showMessage("PIN must be 4 numeric digits.", "Invalid PIN", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateAccountNumber() {
        return "AC" + System.currentTimeMillis();
    }

    private String getCurrentDateTime() {
        return LocalDateTime.now().toString();
    }

    private void showMessage(String message, String title, int type) {
        JLabel label = new JLabel(message);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(this, label, title, type);
    }
}
