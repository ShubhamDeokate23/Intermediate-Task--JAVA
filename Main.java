import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class Main {
    private static InventoryController controller = new InventoryController();
    private static DefaultTableModel tableModel;
    private static JTable inventoryTable;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inventory Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        // Input fields
        JLabel nameLabel = new JLabel("Product Name:");
        JTextField nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
        JButton addButton = new JButton("Add Product");
        JButton deleteButton = new JButton("Delete Product");
        JButton updateButton = new JButton("Update Product");

        // Table setup
        String[] columnNames = {"ID", "Name", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        inventoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);

        // Add components to panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);

        // Add Product
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                InventoryItem item = new InventoryItem(String.valueOf(System.currentTimeMillis()), name, quantity, 100.0);
                controller.addItem(item);
                updateTable();
            }
        });

        // Update Product
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = inventoryTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String id = (String) tableModel.getValueAt(selectedRow, 0);
                    int newQuantity = Integer.parseInt(JOptionPane.showInputDialog("Enter new quantity:"));
                    double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new price:"));
                    if (controller.updateItem(id, newQuantity, newPrice)) {
                        updateTable();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error updating item.");
                    }
                }
            }
        });

        // Delete Product
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = inventoryTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String id = (String) tableModel.getValueAt(selectedRow, 0);
                    if (controller.deleteItem(id)) {
                        updateTable();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error deleting item.");
                    }
                }
            }
        });
    }

    // Method to update table data
    private static void updateTable() {
        tableModel.setRowCount(0);
        for (InventoryItem item : controller.getInventory()) {
            tableModel.addRow(new Object[]{item.getId(), item.getName(), item.getQuantity(), item.getPrice()});
        }
    }
}