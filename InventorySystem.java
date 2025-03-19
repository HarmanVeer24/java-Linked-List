public class InventorySystem {
    public static void main(String[] args) {
        InventorySystem inventory = new InventorySystem();
        inventory.addItemBeginning("Laptop", "#101", 5, 55000);
        inventory.addItemEnd("Mouse", "#102", 8, 800);
        inventory.addItemEnd("Refrigerator", "#103", 2, 75000);
        inventory.addItemEnd("Headphones", "#104", 12, 3000);

        inventory.displayItems();

        inventory.removeItem("#103");

        inventory.searchItem("Mouse");

        System.out.print("Total Inventory Value: ");
        inventory.totalValue();

        inventory.sortByPrice();
        System.out.println("Sorted by price: ");
        inventory.displayItems();

        inventory.sortByName();
        System.out.println("Sorted by name: ");
        inventory.displayItems();
    }
    class Item {
        String itemName;
        String itemId;
        int quantity;
        double price;
        Item next;

        public Item(String itemName, String itemId, int quantity, double price) {
            this.itemName = itemName;
            this.itemId = itemId;
            this.quantity = quantity;
            this.price = price;
            this.next = null;
        }
    }

    public Item head = null;
    public Item tail = null;

    //Method to add items in beginning
    public void addItemBeginning(String itemName, String itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        if(head == null) {
            head = newItem;
            tail = newItem;
        } else {
            newItem.next = head;
            head = newItem;
        }
    }

    //Method to add item at the last
    public void addItemEnd(String itemName, String itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        if(head == null) {
            head = newItem;
            tail = newItem;
        } else {
            tail.next = newItem;
            tail = newItem;
        }
    }

    //Method to add item at a specific position
    public void addItemPosition(int position, String itemName, String itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);

        if(position == 1){
            newItem.next = head;
            head = newItem;
        }

        Item temp = head;
        for(int i = 1; i < position - 1; i++) {
            temp = temp.next;
        }
        newItem.next = temp.next;
        temp.next = newItem;
    }

    //Method to remove item based on item id
    public void removeItem(String itemId) {
        Item temp = head, prev = null;

        if(temp != null && temp.itemId.equals(itemId)) {
            head = temp.next;
        }
        while(temp != null && temp.itemId != itemId) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = temp.next;
    }

    //Method to update quantity based on item id
    public void updateQuantity(String id, int newQuantity) {
        Item current = head;

        while(current != null) {
            if(current.itemId.equals(id)) {
                current.quantity = newQuantity;
                System.out.println("Quantity updated for item number " + id);
            }
            current = current.next;
        }
        System.out.println("Item not found");
    }

    public void searchItem(String key) {
        Item current = head;

        System.out.println("Item found:");
        while(current != null){
            if(key.equals(current.itemId) || key.equals(current.itemName)){
                System.out.println("Item Name: " + current.itemName);
                System.out.println("Item ID: " + current.itemId);
                System.out.println("Quantity: " + current.quantity);
                System.out.println("Price: " + current.price);
            }
            current = current.next;
        }
        System.out.println();
    }

    public void totalValue(){
        Item current = head;
        double total = 0;
        while(current != null) {
            total += current.price * current.quantity;
            current = current.next;
        }
        System.out.println(total);
    }

    public void sortByPrice() {
        if (head == null || head.next == null) return;
        for (Item i = head; i != null; i = i.next) {
            for (Item j = head; j.next != null; j = j.next) {
                if (j.price > j.next.price) {
                    swap(j, j.next);
                }
            }
        }
    }

    public void sortByName() {
        if (head == null || head.next == null) return;
        for (Item i = head; i != null; i = i.next) {
            for (Item j = head; j.next != null; j = j.next) {
                if (j.itemName.compareToIgnoreCase(j.next.itemName) > 0) {
                    swap(j, j.next);
                }
            }
        }
    }

    private void swap(Item a, Item b) {
        String tempName = a.itemName;
        String itemId = a.itemId;
        int tempQuantity = a.quantity;
        double tempPrice = a.price;

        a.itemName = b.itemName;
        a.itemId = b.itemId;
        a.quantity = b.quantity;
        a.price = b.price;

        b.itemName = tempName;
        b.itemId = itemId;
        b.quantity = tempQuantity;
        b.price = tempPrice;
    }

    public void displayItems() {
        Item temp = head;
        while (temp != null) {
            System.out.println("Item ID: " + temp.itemId + ", Name: " + temp.itemName + ", Quantity: " + temp.quantity + ", Price: " + temp.price);
            temp = temp.next;
        }
        System.out.println();
    }
}
//output
// Item ID: #101, Name: Laptop, Quantity: 5, Price: 55000.0
//Item ID: #102, Name: Mouse, Quantity: 8, Price: 800.0
//Item ID: #103, Name: Refrigerator, Quantity: 2, Price: 75000.0
//Item ID: #104, Name: Headphones, Quantity: 12, Price: 3000.0
//
//Item found:
//Item Name: Mouse
//Item ID: #102
//Quantity: 8
//Price: 800.0
//
//Total Inventory Value: 317400.0
//Sorted by price:
//Item ID: #102, Name: Mouse, Quantity: 8, Price: 800.0
//Item ID: #104, Name: Headphones, Quantity: 12, Price: 3000.0
//Item ID: #101, Name: Laptop, Quantity: 5, Price: 55000.0
//
//Sorted by name:
//Item ID: #104, Name: Headphones, Quantity: 12, Price: 3000.0
//Item ID: #101, Name: Laptop, Quantity: 5, Price: 55000.0
//Item ID: #102, Name: Mouse, Quantity: 8, Price: 800.0