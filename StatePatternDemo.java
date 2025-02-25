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