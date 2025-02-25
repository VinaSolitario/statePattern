# StatePattern

Problem:
A vending machine needs to manage different states, including "Idle", "ItemSelected", "Dispensing", and "OutOfOrder". Each state has specific rules and restrictions regarding allowed operations, and the vending machine has associated attributes like item inventory and balance.
# UML DIAGRAM
![image](https://github.com/VinaSolitario/statePattern/blob/main/UML%20DIAGRAM_STATEPATTERN.png)

# SOURCE CODE
```java
interface VendingMachineState {
    void selectItem(VendingMachine machine);
    void insertCoin(VendingMachine machine, int amount);
    void dispenseItem(VendingMachine machine);
    void setOutOfOrder(VendingMachine machine);
}

class IdleState implements VendingMachineState {
    public void selectItem(VendingMachine machine) {
        System.out.println("Item selected. Please insert coins.");
        machine.setState(new ItemSelectedState());
    }
    public void insertCoin(VendingMachine machine, int amount) {
        System.out.println("Please select an item first.");
    }
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Select an item and insert coins first.");
    }
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("Machine is now out of order.");
        machine.setState(new OutOfOrderState());
    }
}

class ItemSelectedState implements VendingMachineState {
    public void selectItem(VendingMachine machine) {
        System.out.println("Item already selected. Insert coins to proceed.");
    }
    public void insertCoin(VendingMachine machine, int amount) {
        machine.balance += amount;
        System.out.println("Coins inserted: " + amount);
        
        if (machine.inventory <= 0) {
            System.out.println("Out of stock. Refunding " + machine.balance + " coins.");
            machine.balance = 0;
            machine.setState(new IdleState());
        } else if (machine.balance < machine.itemPrice) {
            System.out.println("Not enough coins. Insert " + (machine.itemPrice - machine.balance) + " more.");
        } else {
            System.out.println("Dispensing item...");
            machine.setState(new DispensingState());
        }
    }
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Insert enough coins to dispense the item.");
    }
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("Machine is now out of order.");
        machine.setState(new OutOfOrderState());
    }
}

class DispensingState implements VendingMachineState {
    public void selectItem(VendingMachine machine) {
        System.out.println("Currently dispensing. Please wait.");
    }
    public void insertCoin(VendingMachine machine, int amount) {
        System.out.println("Currently dispensing. Please wait.");
    }
    public void dispenseItem(VendingMachine machine) {
        if (machine.inventory > 0) {
            machine.inventory--;
            machine.balance -= machine.itemPrice;
            System.out.println("Item dispensed. Remaining inventory: " + machine.inventory);
        } else {
            System.out.println("Out of stock.");
        }
        machine.setState(new IdleState());
    }
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("Machine is now out of order.");
        machine.setState(new OutOfOrderState());
    }
}

class OutOfOrderState implements VendingMachineState {
    public void selectItem(VendingMachine machine) {
        System.out.println("Machine is out of order.");
    }
    public void insertCoin(VendingMachine machine, int amount) {
        System.out.println("Machine is out of order.");
    }
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Machine is out of order.");
    }
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("Machine is already out of order.");
    }
}

class VendingMachine {
    private VendingMachineState state;
    int inventory;
    int balance;
    int itemPrice = 5;
    
    public VendingMachine(int inventory) {
        this.inventory = inventory;
        this.balance = 0;
        this.state = new IdleState();
    }
    
    public void setState(VendingMachineState state) {
        this.state = state;
    }
    
    public void selectItem() {
        state.selectItem(this);
    }
    
    public void insertCoin(int amount) {
        state.insertCoin(this, amount);
    }
    
    public void dispenseItem() {
        state.dispenseItem(this);
    }
    
    public void setOutOfOrder() {
        state.setOutOfOrder(this);
    }
}

public class StatePatternDemo {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(3);
        
        machine.selectItem();
        machine.insertCoin(2);
        machine.insertCoin(3);
        machine.dispenseItem();
        
        machine.setOutOfOrder();
        machine.selectItem();
    }
}
```

