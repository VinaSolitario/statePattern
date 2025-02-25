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