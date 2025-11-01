import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BusTicketBooking extends JFrame {

    private JTextField txtName, txtPhone, txtDate, txtPassengers, txtFare;
    private JComboBox<String> sourceBox, destinationBox, seatTypeBox, timeBox, paymentBox;
    private JButton btnCalculate, btnBook;
    private JLabel lblFare;

    public BusTicketBooking() {
        setTitle("🚌 Bus Ticket Booking System");
        setSize(950, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        // Background panel with gradient
        JPanel bgPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(63, 81, 181),
                        getWidth(), getHeight(),
                        new Color(156, 39, 176));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Subtle decorative glow
                g2.setColor(new Color(255, 255, 255, 30));
                for (int i = 0; i < 40; i++) {
                    int x = (int) (Math.random() * getWidth());
                    int y = (int) (Math.random() * getHeight());
                    g2.fillOval(x, y, 4, 4);
                }
            }
        };
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        // Header panel
        JPanel header = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(106, 27, 154),
                        getWidth(), getHeight(), new Color(63, 81, 181));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        header.setLayout(new BorderLayout());
        header.setBounds(0, 0, getWidth(), 90);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        bgPanel.add(header);

        JLabel title = new JLabel("🚌 Bus Ticket Booking System", JLabel.CENTER);
        title.setFont(new Font("Segoe UI Black", Font.BOLD, 34));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        title.setOpaque(false);
        header.add(title);

        // Card Panel (Main form)
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(new Color(255, 255, 255, 245));
        card.setBounds(60, 120, 830, 550);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 255), 2),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        bgPanel.add(card);

        int labelX = 60, fieldX = 260, y = 40, gap = 70;

        // Passenger Name
        JLabel lblName = createStyledLabel("👤 Passenger Name:");
        lblName.setBounds(labelX, y, 180, 25);
        card.add(lblName);
        txtName = createRoundedTextField();
        txtName.setBounds(fieldX, y, 480, 40);
        card.add(txtName);

        y += gap;
        // Phone Number
        JLabel lblPhone = createStyledLabel("📱 Phone Number:");
        lblPhone.setBounds(labelX, y, 180, 25);
        card.add(lblPhone);
        txtPhone = createRoundedTextField();
        txtPhone.setBounds(fieldX, y, 480, 40);
        card.add(txtPhone);

        y += gap;
        // Source and Destination
        JLabel lblSource = createStyledLabel("📍 Source:");
        lblSource.setBounds(labelX, y, 180, 25);
        card.add(lblSource);

        String[] cities = {"Pune", "Mumbai", "Nagpur", "Nashik", "Kolhapur", "Solapur",
                "Aurangabad", "Thane", "Satara", "Goa"};
        sourceBox = createRoundedComboBox(cities);
        sourceBox.setBounds(fieldX, y, 200, 40);
        card.add(sourceBox);

        JLabel lblDest = createStyledLabel("🎯 Destination:");
        lblDest.setBounds(fieldX + 220, y, 120, 25);
        card.add(lblDest);
        destinationBox = createRoundedComboBox(cities);
        destinationBox.setBounds(fieldX + 340, y, 200, 40);
        card.add(destinationBox);

        y += gap;
        // Date & Time
        JLabel lblDate = createStyledLabel("📅 Journey Date:");
        lblDate.setBounds(labelX, y, 180, 25);
        card.add(lblDate);
        txtDate = createRoundedTextField();
        txtDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        txtDate.setBounds(fieldX, y, 200, 40);
        card.add(txtDate);

        JLabel lblTime = createStyledLabel("⏰ Departure Time:");
        lblTime.setBounds(fieldX + 220, y, 150, 25);
        card.add(lblTime);
        String[] times = {"06:00 AM", "09:00 AM", "12:00 PM", "03:00 PM", "06:00 PM", "09:00 PM"};
        timeBox = createRoundedComboBox(times);
        timeBox.setBounds(fieldX + 370, y, 150, 40);
        card.add(timeBox);

        y += gap;
        // Passengers & Seat Type
        JLabel lblPassengers = createStyledLabel("👥 No. of Passengers:");
        lblPassengers.setBounds(labelX, y, 180, 25);
        card.add(lblPassengers);
        txtPassengers = createRoundedTextField();
        txtPassengers.setText("1");
        txtPassengers.setBounds(fieldX, y, 80, 40);
        card.add(txtPassengers);

        JLabel lblSeat = createStyledLabel("💺 Seat Type:");
        lblSeat.setBounds(fieldX + 100, y, 100, 25);
        card.add(lblSeat);
        String[] seatTypes = {"Regular", "AC", "Sleeper"};
        seatTypeBox = createRoundedComboBox(seatTypes);
        seatTypeBox.setBounds(fieldX + 190, y, 120, 40);
        card.add(seatTypeBox);

        lblFare = createStyledLabel("💰 Total Fare (₹):");
        lblFare.setBounds(fieldX + 330, y, 120, 25);
        card.add(lblFare);
        txtFare = createRoundedTextField();
        txtFare.setEditable(false);
        txtFare.setBackground(new Color(240, 255, 240));
        txtFare.setBounds(fieldX + 450, y, 130, 40);
        card.add(txtFare);

        // Buttons and Payment
        y += 100;
        JLabel lblPayment = createStyledLabel("💳 Payment Mode:");
        lblPayment.setBounds(100, y, 180, 25);
        card.add(lblPayment);
        paymentBox = createRoundedComboBox(new String[]{"Cash", "UPI", "Card"});
        paymentBox.setBounds(250, y - 5, 150, 40);
        card.add(paymentBox);

        btnCalculate = createRoundedButton("💰 Calculate Fare", new Color(255, 152, 0), new Color(255, 87, 34));
        btnCalculate.setBounds(420, y - 5, 170, 40);
        card.add(btnCalculate);

        btnBook = createRoundedButton("✅ Book Ticket", new Color(76, 175, 80), new Color(56, 142, 60));
        btnBook.setBounds(610, y - 5, 170, 40);
        card.add(btnBook);

        JLabel footer = new JLabel("🎫 Safe Travels with Our Bus Service! 🎫", JLabel.CENTER);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        footer.setForeground(Color.WHITE);
        footer.setBounds(0, 700, getWidth(), 30);
        bgPanel.add(footer);

        // Action listeners
        btnCalculate.addActionListener(e -> calculateFare());
        btnBook.addActionListener(e -> bookTicket());
    }

    // --- UI Components Helpers ---
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(40, 40, 70));
        return label;
    }

    private JTextField createRoundedTextField() {
        JTextField field = new JTextField() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false);
            }

            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(200, 200, 220));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            }
        };
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        field.setBackground(Color.WHITE);
        return field;
    }

    private JComboBox<String> createRoundedComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return combo;
    }

    private JButton createRoundedButton(String text, Color normal, Color hover) {
        JButton btn = new JButton(text) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? hover : normal);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setForeground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // --- Functionality ---
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
                JOptionPane.showMessageDialog(this, "Enter valid number of passengers (1–10)!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String seatType = (String) seatTypeBox.getSelectedItem();

            int baseFare = 200;
            if (seatType.equals("AC")) baseFare += 100;
            else if (seatType.equals("Sleeper")) baseFare += 200;

            int distanceFare = Math.abs(sourceBox.getSelectedIndex() - destinationBox.getSelectedIndex()) * 100;
            int total = (baseFare + distanceFare) * passengers;
            txtFare.setText("₹" + total);
        } catch (Exception e) {
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
                ╔════════════════════════════════════════════════════╗
                ║                  🚌 BUS TICKET RECEIPT             ║
                ╠════════════════════════════════════════════════════╣
                ║  Ticket ID: %-40s║
                ║  Passenger: %-40s║
                ║  Phone: %-44s║
                ║  Route: %-10s ➜ %-26s║
                ║  Date: %-44s║
                ║  Time: %-44s║
                ║  Seat: %-10s (%s)                           ║
                ║  Payment: %-40s║
                ║  Total Fare: %-38s║
                ╠════════════════════════════════════════════════════╣
                ║         Thank You & Safe Journey! 🌟              ║
                ╚════════════════════════════════════════════════════╝
                """, ticketID, name, phone, source, dest, date, time, seat, seatNum, pay, fare);

        try {
            File folder = new File("Tickets");
            if (!folder.exists()) folder.mkdir();
            String fileName = "Tickets/" + ticketID + ".txt";
            FileWriter fw = new FileWriter(fileName);
            fw.write(ticket);
            fw.close();

            showTicketPopup(ticket);
            JOptionPane.showMessageDialog(this, "🎟 Ticket booked successfully!\nSaved as: " + fileName,
                    "Success", JOptionPane.INFORMATION_MESSAGE);
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
        area.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(550, 420));
        JOptionPane.showMessageDialog(this, scroll, "🎟 Booking Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BusTicketBooking().setVisible(true));
    }
}
