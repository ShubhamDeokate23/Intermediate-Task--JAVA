import java.util.ArrayList;
import java.util.List;

public class InventoryController {
    public List<InventoryItem> getInventory() {
        return inventory;
    }
    private List<InventoryItem> inventory = new ArrayList<>();

    public void addItem(InventoryItem item) {
        inventory.add(item);
    }

    public boolean updateItem(String id, int newQuantity, double newPrice) {
        for (InventoryItem item : inventory) {
            if (item.getId().equals(id)) {
                item.setQuantity(newQuantity);
                item.setPrice(newPrice);
                return true;
            }
        }
        return false;
    }

    public boolean deleteItem(String id) {
        return inventory.removeIf(item -> item.getId().equals(id));
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        for (InventoryItem item : inventory) {
            System.out.println(item);
        }
    }
}