package ad;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SwingApp {
    // JDBC connection settings
    static String DB_URL = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:5432/postgres?user=postgres.uzfqtmeirzyioxrfjniw&password=Hjxbc4hiHc8u9Jjq";
    static Connection conn;
    static int businesscustomer_id;

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        SwingUtilities.invokeLater(() -> createAndShowLoginFrame());
    }

    private static void createAndShowLoginFrame() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        // Create login panel with GridLayout
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField("wendys");
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField("test1111");
        JButton btnLogin = new JButton("Login");

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(new JLabel()); // Empty cell for layout spacing
        panel.add(btnLogin);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            String role = authenticateUser(username, password);

            if (role != null) {
                // If authentication succeeds, dispose of the login frame and show the
                // appropriate dashboard
                frame.dispose();
                showDashboard(role);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // This method connects to the database, executes the query, and returns the
    // user's role
    private static String authenticateUser(String username, String password) {
        String role = null;
        // SQL query joining the users and roles tables
        String query = "SELECT r.name, u.businesscustomer_id " +
                "FROM users u JOIN roles r ON u.role_id = r.id " +
                "WHERE u.username = ? AND u.password = crypt(?, u.password)";
        try {
            ResultSet rs = SQLHelper.executeQuery(conn, query, username, password);
            if (rs.next()) {
                role = rs.getString("name");
                businesscustomer_id = rs.getInt("businesscustomer_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return role;
    }

    // This method displays a new dashboard frame based on the user's role.
    private static void showDashboard(String role) {
        JFrame dashboardFrame = new JFrame("Dashboard - " + role);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setSize(600, 400);
        dashboardFrame.setLocationRelativeTo(null);

        JPanel dashboardPanel;
        // Show different dashboards based on role
        if ("ADMIN".equalsIgnoreCase(role)) {
            dashboardPanel = new AdminDashboardPanel();
        } else if ("SALES".equalsIgnoreCase(role)) {
            dashboardPanel = new SalesDashboardPanel();
        } else if ("BUSINESSCUSTOMER".equalsIgnoreCase(role)) {
            dashboardPanel = new BusinessCustomerDashboardPanel();
        } else {
            throw new Error("who are you and why are you using our system???");
        }
        dashboardFrame.getContentPane().add(dashboardPanel);
        dashboardFrame.setVisible(true);
    }

}

// Updated AdsPanel with forms and buttons for viewing, inserting, and updating
// ads
// Updated AdsPanel with double-click to edit ads (no update buttons)
// Updated AdsPanel to use correct ads table schema (id, title, adtype_id,
// businesscustomer_id)
// Updated AdsPanel to use correct ads table schema (id, title, adtype_id,
// businesscustomer_id)
// Updated AdsPanel to use correct ads table schema (id, title, adtype_id,
// businesscustomer_id)
class AdsPanel extends JPanel {
    private CardLayout adsLayout;
    private JPanel adsContent;
    private JList<String> currentAdsList, pastAdsList, upcomingAdsList, unassociatedAdsList;
    private DefaultListModel<String> currentModel, pastModel, upcomingModel, unassociatedModel;

    public AdsPanel() {
        setLayout(new BorderLayout());

        JPanel topNav = new JPanel(new GridLayout(2, 1));
        JPanel row1 = new JPanel(new FlowLayout());
        JPanel row2 = new JPanel(new FlowLayout());

        JButton btnCurrent = new JButton("Current Ads");
        JButton btnPast = new JButton("Past Ads");
        JButton btnUpcoming = new JButton("Upcoming Ads");
        JButton btnUnassociated = new JButton("Unassociated Ads");
        JButton btnAddAd = new JButton("Add New Ad");

        row1.add(btnCurrent);
        row1.add(btnPast);
        row1.add(btnUpcoming);
        row1.add(btnUnassociated);

        row2.add(btnAddAd);

        topNav.add(row1);
        topNav.add(row2);

        add(topNav, BorderLayout.NORTH);

        adsLayout = new CardLayout();
        adsContent = new JPanel(adsLayout);

        currentModel = new DefaultListModel<>();
        pastModel = new DefaultListModel<>();
        upcomingModel = new DefaultListModel<>();
        unassociatedModel = new DefaultListModel<>();

        currentAdsList = createAdList(currentModel);
        pastAdsList = createAdList(pastModel);
        upcomingAdsList = createAdList(upcomingModel);
        unassociatedAdsList = createAdList(unassociatedModel);

        adsContent.add(new JScrollPane(currentAdsList), "Current");
        adsContent.add(new JScrollPane(pastAdsList), "Past");
        adsContent.add(new JScrollPane(upcomingAdsList), "Upcoming");
        adsContent.add(new JScrollPane(unassociatedAdsList), "Unassociated");

        add(adsContent, BorderLayout.CENTER);

        btnCurrent.addActionListener(e -> loadAds("Current"));
        btnPast.addActionListener(e -> loadAds("Past"));
        btnUpcoming.addActionListener(e -> loadAds("Upcoming"));
        btnUnassociated.addActionListener(e -> loadAds("Unassociated"));

        btnAddAd.addActionListener(e -> showInsertAdDialog());

        loadAds("Current");
    }

    private JList<String> createAdList(DefaultListModel<String> model) {
        JList<String> list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    if (index != -1) {
                        String line = model.get(index);
                        String[] parts = line.split(":", 2);
                        if (parts.length == 2) {
                            int adId = Integer.parseInt(parts[0].trim());
                            showEditDialog(adId);
                        }
                    }
                }
            }
        });
        return list;
    }

    private void loadAds(String type) {
        DefaultListModel<String> model;
        String sql;
        switch (type) {
            case "Current" -> {
                model = currentModel;
                sql = "SELECT ads.* FROM ads JOIN adcampaigns ON ads.id = adcampaigns.ad_id WHERE ads.businesscustomer_id = ? AND adcampaigns.date_start <= now() AND (adcampaigns.date_stop IS NULL OR adcampaigns.date_stop >= now());";
            }
            case "Past" -> {
                model = pastModel;
                sql = "SELECT ads.* FROM ads JOIN adcampaigns ON ads.id = adcampaigns.ad_id WHERE ads.businesscustomer_id = ? AND adcampaigns.date_stop < now();";
            }
            case "Upcoming" -> {
                model = upcomingModel;
                sql = "SELECT ads.* FROM ads JOIN adcampaigns ON ads.id = adcampaigns.ad_id WHERE ads.businesscustomer_id = ? AND adcampaigns.date_start > now();";
            }
            case "Unassociated" -> {
                model = unassociatedModel;
                sql = "SELECT ads.* FROM ads LEFT JOIN adcampaigns ON ads.id = adcampaigns.ad_id WHERE ads.businesscustomer_id = ? AND adcampaigns.ad_id IS NULL;";
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        model.clear();
        try (ResultSet rs = SQLHelper.executeQuery(SwingApp.conn, sql, SwingApp.businesscustomer_id)) {
            while (rs != null && rs.next()) {
                int adtype = rs.getInt("adtype_id");
                model.addElement(rs.getInt("id") + ": " + rs.getString("title") + " (type " + adtype + ")");
            }
        } catch (SQLException ex) {
            model.addElement("Error loading ads: " + ex.getMessage());
        }
        adsLayout.show(adsContent, type);
    }

    private void showInsertAdDialog() {
        JTextField title = new JTextField();
        JTextField adtype = new JTextField();
        JTextField start = new JTextField();
        JTextField stop = new JTextField();

        Object[] fields = {
                "Title:", title,
                "Ad Type ID (1-6):", adtype,
                "Start Date (optional):", start,
                "End Date (optional):", stop
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "New Ad", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                if (start.getText().isEmpty()) {
                    String sql = "INSERT INTO ads (businesscustomer_id, title, adtype_id) VALUES (?, ?, ?);";
                    PreparedStatement ps = SwingApp.conn.prepareStatement(sql);
                    ps.setInt(1, SwingApp.businesscustomer_id);
                    ps.setString(2, title.getText());
                    ps.setInt(3, Integer.parseInt(adtype.getText()));
                    ps.executeUpdate();
                } else {
                    String sql = "WITH new_ad AS (INSERT INTO ads (businesscustomer_id, title, adtype_id) VALUES (?, ?, ?) RETURNING id) INSERT INTO adcampaigns (ad_id, date_start, date_stop) VALUES ((SELECT id FROM new_ad), ?, ?);";
                    PreparedStatement ps = SwingApp.conn.prepareStatement(sql);
                    ps.setInt(1, SwingApp.businesscustomer_id);
                    ps.setString(2, title.getText());
                    ps.setInt(3, Integer.parseInt(adtype.getText()));
                    ps.setDate(4, Date.valueOf(start.getText()));
                    ps.setDate(5, stop.getText().isEmpty() ? null : Date.valueOf(stop.getText()));
                    ps.executeUpdate();
                }
                loadAds("Current");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Insert error: " + ex.getMessage());
            }
        }
    }

    private void showEditDialog(int adId) {
        try {
            String sql = "SELECT a.title, a.adtype_id, c.id as campaign_id, c.date_start, c.date_stop " +
                    "FROM ads a LEFT JOIN adcampaigns c ON a.id = c.ad_id " +
                    "WHERE a.id = ? AND a.businesscustomer_id = ?;";
            PreparedStatement ps = SwingApp.conn.prepareStatement(sql);
            ps.setInt(1, adId);
            ps.setInt(2, SwingApp.businesscustomer_id);
            ResultSet rs = ps.executeQuery();

            String adTitle = null;
            java.util.List<String> campaigns = new ArrayList<>();
            java.util.List<Integer> campaignIds = new ArrayList<>();
            java.util.Map<Integer, Date> startDates = new HashMap<>();
            java.util.Map<Integer, Date> stopDates = new HashMap<>();

            while (rs.next()) {
                adTitle = rs.getString("title");
                int cid = rs.getInt("campaign_id");
                campaignIds.add(cid);
                campaigns.add("Campaign " + cid);
                startDates.put(cid, rs.getDate("date_start"));
                stopDates.put(cid, rs.getDate("date_stop"));
            }

            if (adTitle != null) {
                JComboBox<String> campaignSelector = new JComboBox<>(campaigns.toArray(new String[0]));
                JTextField title = new JTextField(adTitle);
                JTextField start = new JTextField();
                JTextField stop = new JTextField();

                if (!campaignIds.isEmpty()) {
                    int firstId = campaignIds.get(0);
                    Date s = startDates.get(firstId);
                    Date e = stopDates.get(firstId);
                    start.setText(s != null ? s.toString() : "");
                    stop.setText(e != null ? e.toString() : "");

                    campaignSelector.addActionListener(ef -> {
                        int selectedIdx = campaignSelector.getSelectedIndex();
                        int cid = campaignIds.get(selectedIdx);
                        Date sd = startDates.get(cid);
                        Date ed = stopDates.get(cid);
                        start.setText(sd != null ? sd.toString() : "");
                        stop.setText(ed != null ? ed.toString() : "");
                    });
                }

                Object[] fields = {
                        "New Title:", title,
                        "Select Campaign:", campaignSelector,
                        "New Start Date (YYYY-MM-DD):", start,
                        "New Stop Date (YYYY-MM-DD):", stop
                };

                int option = JOptionPane.showConfirmDialog(this, fields, "Edit Ad " + adId,
                        JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String updateTitleSql = "UPDATE ads SET title = ? WHERE id = ? AND businesscustomer_id = ?;";
                    PreparedStatement ps1 = SwingApp.conn.prepareStatement(updateTitleSql);
                    ps1.setString(1, title.getText());
                    ps1.setInt(2, adId);
                    ps1.setInt(3, SwingApp.businesscustomer_id);
                    ps1.executeUpdate();

                    if (!campaignIds.isEmpty()) {
                        int selectedCid = campaignIds.get(campaignSelector.getSelectedIndex());
                        String updateDatesSql = "UPDATE adcampaigns SET date_start = ?, date_stop = ? WHERE id = ?;";
                        PreparedStatement ps2 = SwingApp.conn.prepareStatement(updateDatesSql);
                        ps2.setDate(1, start.getText().trim().isEmpty() ? null : Date.valueOf(start.getText()));
                        ps2.setDate(2, stop.getText().trim().isEmpty() ? null : Date.valueOf(stop.getText()));
                        ps2.setInt(3, selectedCid);
                        ps2.executeUpdate();
                    }

                    loadAds("Current");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ad not found or you do not have permission to edit it.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Load/Edit error: " + ex.getMessage());
        }
    };

}

class BillingPanel extends JPanel {
    private CardLayout billingLayout;
    private JPanel billingContent;

    public BillingPanel() {
        setLayout(new BorderLayout());

        JPanel topNav = new JPanel(new FlowLayout());
        JButton btnInvoices = new JButton("Invoices");
        JButton btnHistory = new JButton("History");

        topNav.add(btnInvoices);
        topNav.add(btnHistory);
        add(topNav, BorderLayout.NORTH);

        billingLayout = new CardLayout();
        billingContent = new JPanel(billingLayout);

        JTextArea invoiceArea = new JTextArea("Invoice data here...", 10, 50);
        JTextArea historyArea = new JTextArea("Billing history here...", 10, 50);

        billingContent.add(new JScrollPane(invoiceArea), "Invoices");
        billingContent.add(new JScrollPane(historyArea), "History");

        add(billingContent, BorderLayout.CENTER);

        btnInvoices.addActionListener(e -> billingLayout.show(billingContent, "Invoices"));
        btnHistory.addActionListener(e -> billingLayout.show(billingContent, "History"));
    }
}

class BusinessCustomerDashboardPanel extends JPanel {
    private CardLayout sectionLayout;
    private JPanel sectionPanel;

    public BusinessCustomerDashboardPanel() {
        setLayout(new BorderLayout());
        JPanel topArea = new JPanel(new GridLayout(2, 1));
        topArea.add(new JLabel("Welcome, Business Customer!", SwingConstants.CENTER));

        // Navigation buttons
        JPanel navPanel = new JPanel(new FlowLayout());
        JButton btnAds = new JButton("Ads");
        JButton btnBilling = new JButton("Billing");
        JButton btnLogout = new JButton("Logout");

        navPanel.add(btnAds);
        navPanel.add(btnBilling);
        navPanel.add(btnLogout);
        topArea.add(navPanel);
        add(topArea, BorderLayout.NORTH);

        // Section content switching
        sectionLayout = new CardLayout();
        sectionPanel = new JPanel(sectionLayout);

        sectionPanel.add(new AdsPanel(), "Ads");
        sectionPanel.add(new BillingPanel(), "Billing");

        add(sectionPanel, BorderLayout.CENTER);

        btnAds.addActionListener(e -> sectionLayout.show(sectionPanel, "Ads"));
        btnBilling.addActionListener(e -> sectionLayout.show(sectionPanel, "Billing"));
        btnLogout.addActionListener(e -> System.exit(0)); // Placeholder logout
    }
}

// Dashboard panel for ADMIN role
class AdminDashboardPanel extends JPanel {
    public AdminDashboardPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Welcome, Admin!"), BorderLayout.NORTH);
        // Additional admin-specific components can be added here
    }
}

// Dashboard panel for USER role
class SalesDashboardPanel extends JPanel {
    public SalesDashboardPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Welcome, Sales Person!"), BorderLayout.NORTH);
        // Additional user-specific components can be added here
    }
}

class SQLHelper {
    static public ResultSet executeQuery(Connection conn, String query, Object... params) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
