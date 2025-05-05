public class InventoryItem {
    private String id;
    private String name;
    private int quantity;
    private double price;

    public InventoryItem(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    // ✅ Added main method for testing
    public static void main(String[] args) {
        InventoryItem item = new InventoryItem("1", "Laptop", 5, 50000.0);
        System.out.println("Item Created: " + item.getName() + " | Quantity: " + item.getQuantity() + " | Price: " + item.getPrice());
    }
}