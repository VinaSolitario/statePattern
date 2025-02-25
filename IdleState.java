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