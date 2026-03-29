/**
 *
 * @author Ayush
 */
package model;

public class TestModel {
    public static void main(String[] args) {
        
        BookStore store = BookStore.getInstance();
        
        // --- test adding books ---
        store.addBook("Java Programming", 50.0);
        store.addBook("Data Structures", 100.0);
        store.addBook("Algorithms", 200.0);
        store.addBook("Operating Systems", 500.0);
        System.out.println("Books added: " + store.getBooks().size()); // should print 4
        
        // --- test adding customers ---
        store.addCustomer("jane", "pass123");
        System.out.println("Customers added: " + store.getCustomers().size()); // should print 1
        
        // --- test authentication ---
        System.out.println(store.authenticate("admin", "admin")); // should not be null
        System.out.println(store.authenticate("jane", "pass123")); // should not be null
        System.out.println(store.authenticate("jane", "wrongpass")); // should print null
        
        // --- test points and status (from project example) ---
        Customer jane = (Customer) store.authenticate("jane", "pass123");
        System.out.println("Status: " + jane.getStatus().getStatusName()); // Silver
        System.out.println("Points: " + jane.getPoints()); // 0
        
        // buy books worth $700 (200 + 500)
        double cost1 = 700.0;
        jane.addPoints((int)(cost1 * jane.getStatus().getPointsMultiplier()));
        System.out.println("Points after $700 purchase: " + jane.getPoints()); // 7000
        System.out.println("Status: " + jane.getStatus().getStatusName()); // Gold
        
        // redeem points, buy $50 book
        double cost2 = jane.redeemPoints(50.0);
        System.out.println("Cost after redeem (should be 0): " + cost2); // 0
        System.out.println("Points remaining (should be 2000): " + jane.getPoints()); // 2000
        
        // redeem points, buy $100 book
        double cost3 = jane.redeemPoints(100.0);
        System.out.println("Cost after redeem (should be 80): " + cost3); // 80
        System.out.println("Points remaining (should be 800): " + jane.getPoints()); // 800
        System.out.println("Status (should be Silver): " + jane.getStatus().getStatusName()); // Silver
        
        // --- test save and load ---
        store.saveToFiles();
        System.out.println("Saved to files successfully");
        
        // reset and reload
        store.saveToFiles();
        System.out.println("Saved to files successfully");

        // clear and reload to simulate restart
        store.getBooks().clear();
        store.getCustomers().clear();
        store.loadFromFiles();
        System.out.println("Books loaded after reload: " + store.getBooks().size()); // should be 4
        System.out.println("Customers loaded after reload: " + store.getCustomers().size()); // should be 1

        System.out.println("All tests done!");
    }
}
