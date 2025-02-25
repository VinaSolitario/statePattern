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