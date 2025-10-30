import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BusTicketBooking extends JFrame {

    // Declare text fields
    private JTextField txtName, txtPhone, txtDate, txtPassengers, txtFare;
    private JComboBox<String> sourceBox, destinationBox, seatTypeBox, timeBox, paymentBox;
    private JButton btnCalculate, btnBook;
    private JLabel lblFare;

    public BusTicketBooking() {
        setTitle("🚌 Bus Ticket Booking System");
        setSize(900, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        // Modern gradient background
        JPanel bgPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(32, 0, 77),
                        getWidth(), getHeight(), new Color(93, 33, 170));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Decorative elements
                g2d.setColor(new Color(255, 255, 255, 20));
                for (int i = 0; i < 50; i++) {
                    int x = (int) (Math.random() * getWidth());
                    int y = (int) (Math.random() * getHeight());
                    int size = (int) (Math.random() * 3) + 1;
                    g2d.fillOval(x, y, size, size);
                }
            }
        };
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBackground(new Color(103, 58, 183, 200));
        headerPanel.setBounds(0, 0, getWidth(), 80);
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 215, 0)));
        bgPanel.add(headerPanel);

        JLabel title = new JLabel("🚌 Bus Ticket Booking System", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 10, getWidth(), 60);
        headerPanel.add(title);

        // Card panel
        JPanel card = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(103, 58, 183, 5));
                for (int i = 0; i < getWidth(); i += 20) {
                    for (int j = 0; j < getHeight(); j += 20) {
                        g2d.fillRect(i, j, 1, 1);
                    }
                }
            }
        };
        card.setLayout(null);
        card.setBackground(new Color(255, 255, 255, 250));
        card.setBounds(50, 100, 800, 580);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 255), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        bgPanel.add(card);

        int labelX = 60, fieldX = 250, y = 40, gap = 70;

        // Passenger Name
        JLabel lblName = createStyledLabel("👤 Passenger Name:");
        lblName.setBounds(labelX, y, 180, 25);
        card.add(lblName);
        txtName = createStyledTextField();
        txtName.setBounds(fieldX, y, 450, 35);
        card.add(txtName);

        y += gap;
        // Phone
        JLabel lblPhone = createStyledLabel("📱 Phone Number:");
        lblPhone.setBounds(labelX, y, 180, 25);
        card.add(lblPhone);
        txtPhone = createStyledTextField();
        txtPhone.setBounds(fieldX, y, 450, 35);
        card.add(txtPhone);

        y += gap;
        // Source and Destination
        JLabel lblSource = createStyledLabel("📍 Source:");
        lblSource.setBounds(labelX, y, 180, 25);
        card.add(lblSource);

        String[] cities = {"Pune", "Mumbai", "Nagpur", "Nashik", "Kolhapur", "Solapur",
                "Aurangabad", "Thane", "Satara", "Goa"};
        sourceBox = createStyledComboBox(cities);
        sourceBox.setBounds(fieldX, y, 200, 35);
        card.add(sourceBox);

        JLabel lblDest = createStyledLabel("🎯 Destination:");
        lblDest.setBounds(fieldX + 220, y, 120, 25);
        card.add(lblDest);
        destinationBox = createStyledComboBox(cities);
        destinationBox.setBounds(fieldX + 340, y, 200, 35);
        card.add(destinationBox);

        y += gap;
        // Date and Time
        JLabel lblDate = createStyledLabel("📅 Journey Date:");
        lblDate.setBounds(labelX, y, 180, 25);
        card.add(lblDate);
        txtDate = createStyledTextField();
        txtDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        txtDate.setBounds(fieldX, y, 200, 35);
        card.add(txtDate);

        JLabel lblTime = createStyledLabel("⏰ Departure Time:");
        lblTime.setBounds(fieldX + 220, y, 150, 25);
        card.add(lblTime);
        String[] times = {"06:00 AM", "09:00 AM", "12:00 PM", "03:00 PM", "06:00 PM", "09:00 PM"};
        timeBox = createStyledComboBox(times);
        timeBox.setBounds(fieldX + 370, y, 150, 35);
        card.add(timeBox);

        y += gap;
        // Passengers and Seat Type
        JLabel lblPassengers = createStyledLabel("👥 No. of Passengers:");
        lblPassengers.setBounds(labelX, y, 180, 25);
        card.add(lblPassengers);
        txtPassengers = createStyledTextField();
        txtPassengers.setText("1");
        txtPassengers.setBounds(fieldX, y, 80, 35);
        card.add(txtPassengers);

        JLabel lblSeat = createStyledLabel("💺 Seat Type:");
        lblSeat.setBounds(fieldX + 100, y, 100, 25);
        card.add(lblSeat);
        String[] seatTypes = {"Regular", "AC", "Sleeper"};
        seatTypeBox = createStyledComboBox(seatTypes);
        seatTypeBox.setBounds(fieldX + 190, y, 120, 35);
        card.add(seatTypeBox);

        lblFare = createStyledLabel("💰 Total Fare (₹):");
        lblFare.setBounds(fieldX + 330, y, 120, 25);
        card.add(lblFare);
        txtFare = createStyledTextField();
        txtFare.setEditable(false);
        txtFare.setBackground(new Color(240, 255, 240));
        txtFare.setBounds(fieldX + 450, y, 130, 35);
        card.add(txtFare);

        // Buttons section
        y += 100;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonPanel.setBounds(50, y, 700, 60);
        buttonPanel.setOpaque(false);
        card.add(buttonPanel);

        btnCalculate = createStyledButton("💰 Calculate Fare", new Color(255, 152, 0), new Color(255, 87, 34));
        btnCalculate.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(btnCalculate);

        btnBook = createStyledButton("✅ Book Ticket", new Color(76, 175, 80), new Color(56, 142, 60));
        btnBook.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(btnBook);

        JLabel paymentLabel = createStyledLabel("💳 Payment:");
        paymentLabel.setBounds(580, y + 15, 80, 25);
        card.add(paymentLabel);

        paymentBox = createStyledComboBox(new String[]{"Cash", "UPI", "Card"});
        paymentBox.setBounds(660, y + 10, 100, 35);
        card.add(paymentBox);

        // Footer
        JLabel footer = new JLabel("🎫 Safe Travels with Our Bus Service! 🎫", JLabel.CENTER);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        footer.setForeground(Color.WHITE);
        footer.setBounds(0, 700, getWidth(), 30);
        bgPanel.add(footer);

        // Actions
        btnCalculate.addActionListener(e -> calculateFare());
        btnBook.addActionListener(e -> bookTicket());
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(60, 60, 60));
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 220)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        textField.setBackground(Color.WHITE);
        return textField;
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBackground(Color.WHITE);
        comboBox.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return label;
            }
        });
        return comboBox;
    }

    private JButton createStyledButton(String text, Color normalColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(normalColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(normalColor);
            }
        });
        return button;
    }

    private void calculateFare() {
        try {
            String source = (String) sourceBox.getSelectedItem();
            String dest = (String) destinationBox.getSelectedItem();
            if (source.equals(dest)) {
                JOptionPane.showMessageDialog(this, "Source and Destination cannot be same!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int passengers = Integer.parseInt(txtPassengers.getText());
            if (passengers <= 0 || passengers > 10) {
                JOptionPane.showMessageDialog(this, "Enter valid number of passengers (1-10)!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String seatType = (String) seatTypeBox.getSelectedItem();

            int baseFare = 200;
            if (seatType.equals("AC")) baseFare += 100;
            else if (seatType.equals("Sleeper")) baseFare += 200;

            int distanceFare = Math.abs(sourceBox.getSelectedIndex() - destinationBox.getSelectedIndex()) * 100;
            int total = (baseFare + distanceFare) * passengers;
            txtFare.setText("₹" + total);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void bookTicket() {
        if (txtFare.getText().isEmpty() || txtFare.getText().equals("₹")) {
            JOptionPane.showMessageDialog(this, "Please calculate fare first!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (txtName.getText().trim().isEmpty() || txtPhone.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = txtName.getText();
        String phone = txtPhone.getText();
        String source = (String) sourceBox.getSelectedItem();
        String dest = (String) destinationBox.getSelectedItem();
        String date = txtDate.getText();
        String time = (String) timeBox.getSelectedItem();
        String seat = (String) seatTypeBox.getSelectedItem();
        String pay = (String) paymentBox.getSelectedItem();
        String fare = txtFare.getText();

        String ticketID = "TCK-" + (100000 + new Random().nextInt(900000));
        String seatNum = (1 + new Random().nextInt(40)) + Character.toString('A' + new Random().nextInt(5));

        String ticket = String.format("""
                ╔══════════════════════════════════════╗
                ║           BUS TICKET RECEIPT         ║
                ╠══════════════════════════════════════╣
                ║  Ticket ID: %s                ║
                ║  Passenger: %-25s ║
                ║  Phone: %-28s ║
                ║  Route: %s to %-18s ║
                ║  Date: %-28s ║
                ║  Time: %-28s ║
                ║  Seat: %s (%s)              ║
                ║  Payment: %-25s ║
                ║  Total Fare: %-22s ║
                ╠══════════════════════════════════════╣
                ║        Thank You & Safe Journey!     ║
                ╚══════════════════════════════════════╝
                """, ticketID, name, phone, source, dest, date, time, seat, seatNum, pay, fare);

        try {
            File folder = new File("Tickets");
            if (!folder.exists()) folder.mkdir();
            String fileName = "Tickets/" + ticketID + ".txt";
            FileWriter fw = new FileWriter(fileName);
            fw.write(ticket);
            fw.close();

            JOptionPane.showMessageDialog(this, "🎟 Ticket Booked Successfully!\nSaved as: " + fileName,
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            showTicketPopup(ticket);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving ticket: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showTicketPopup(String text) {
        JTextArea area = new JTextArea(text);
        area.setEditable(false);
        area.setFont(new Font("Consolas", Font.BOLD, 13));
        area.setBackground(new Color(255, 255, 240));
        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        JOptionPane.showMessageDialog(this, scrollPane, "Booking Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BusTicketBooking().setVisible(true));
    }
}
