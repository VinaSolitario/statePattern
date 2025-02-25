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