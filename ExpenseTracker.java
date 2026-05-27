import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

class Expense {
    private int id;
    private LocalDate date;
    private double amount;
    private String category;
    private String description;

    public Expense(int id, LocalDate date, double amount, String category, String description) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Date: " + date + ", Amount: $" + amount + ", Category: " + category + ", Description: " + description;
    }
}

public class ExpenseTracker {
    private static List<Expense> expenses = new ArrayList<>();
    private static Set<String> categories = new HashSet<>();
    private static int expenseIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Expense Tracker Menu ---");
            System.out.println("1. Record Expense");
            System.out.println("2. Manage Categories");
            System.out.println("3. View Expenses");
            System.out.println("4. Filter Expenses");
            System.out.println("5. Modify/Delete Expense");
            System.out.println("6. Generate Reports");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    recordExpense(scanner);
                    break;
                case 2:
                    manageCategories(scanner);
                    break;
                case 3:
                    viewExpenses();
                    break;
                case 4:
                    filterExpenses(scanner);
                    break;
                case 5:
                    modifyOrDeleteExpense(scanner);
                    break;
                case 6:
                    generateReports();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void recordExpense(Scanner scanner) {
        try {
            System.out.print("Enter date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            if (!categories.contains(category)) {
                System.out.println("Category not found. Please add it first.");
                return;
            }

            expenses.add(new Expense(expenseIdCounter++, date, amount, category, description));
            System.out.println("Expense recorded successfully!");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Amount should be a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    private static void manageCategories(Scanner scanner) {
        System.out.println("\n--- Manage Categories ---");
        System.out.println("1. Add Category");
        System.out.println("2. View Categories");
        System.out.println("3. Delete Category");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter category name: ");
                String category = scanner.nextLine();
                if (categories.add(category)) {
                    System.out.println("Category added successfully!");
                } else {
                    System.out.println("Category already exists.");
                }
                break;
            case 2:
                System.out.println("Existing Categories: " + categories);
                break;
            case 3:
                System.out.print("Enter category name to delete: ");
                category = scanner.nextLine();
                if (categories.remove(category)) {
                    System.out.println("Category deleted successfully!");
                } else {
                    System.out.println("Category not found.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("\n--- Recorded Expenses ---");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    private static void filterExpenses(Scanner scanner) {
        System.out.println("\n--- Filter Expenses ---");
        System.out.println("1. By Date Range");
        System.out.println("2. By Category");
        System.out.println("3. By Amount Range");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                try {
                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter end date (YYYY-MM-DD): ");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());
                    expenses.stream()
                            .filter(e -> !e.getDate().isBefore(startDate) && !e.getDate().isAfter(endDate))
                            .forEach(System.out::println);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                }
                break;
            case 2:
                System.out.print("Enter category: ");
                String category = scanner.nextLine();
                expenses.stream()
                        .filter(e -> e.getCategory().equalsIgnoreCase(category))
                        .forEach(System.out::println);
                break;
            case 3:
                System.out.print("Enter minimum amount: ");
                double minAmount = scanner.nextDouble();
                System.out.print("Enter maximum amount: ");
                double maxAmount = scanner.nextDouble();
                expenses.stream()
                        .filter(e -> e.getAmount() >= minAmount && e.getAmount() <= maxAmount)
                        .forEach(System.out::println);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void modifyOrDeleteExpense(Scanner scanner) {
        System.out.print("Enter expense ID to modify/delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Expense expense = expenses.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (expense == null) {
            System.out.println("Expense not found.");
            return;
        }

        System.out.println("1. Modify Expense");
        System.out.println("2. Delete Expense");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                modifyExpense(scanner, expense);
                break;
            case 2:
                expenses.remove(expense);
                System.out.println("Expense deleted successfully!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void modifyExpense(Scanner scanner, Expense expense) {
        try {
            System.out.print("Enter new date (YYYY-MM-DD) or press Enter to keep [" + expense.getDate() + "]: ");
            String dateInput = scanner.nextLine();
            if (!dateInput.isEmpty()) {
                expense.setDate(LocalDate.parse(dateInput));
            }

            System.out.print("Enter new amount or press Enter to keep [" + expense.getAmount() + "]: ");
            String amountInput = scanner.nextLine();
            if (!amountInput.isEmpty()) {
                expense.setAmount(Double.parseDouble(amountInput));
            }

            System.out.print("Enter new category or press Enter to keep [" + expense.getCategory() + "]: ");
            String categoryInput = scanner.nextLine();
            if (!categoryInput.isEmpty()) {
                expense.setCategory(categoryInput);
            }

            System.out.print("Enter new description or press Enter to keep [" + expense.getDescription() + "]: ");
            String descriptionInput = scanner.nextLine();
            if (!descriptionInput.isEmpty()) {
                expense.setDescription(descriptionInput);
            }

            System.out.println("Expense modified successfully!");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format. Please enter a valid number.");
        }
    }

    private static void generateReports() {
        System.out.println("\n--- Expense Reports ---");
        System.out.println("1. Monthly Report");
        System.out.println("2. Category-wise Report");
        System.out.print("Choose an option: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                Map<String, Double> monthlyTotals = new HashMap<>();
                for (Expense expense : expenses) {
                    String month = expense.getDate().getMonth().toString();
                    monthlyTotals.put(month, monthlyTotals.getOrDefault(month, 0.0) + expense.getAmount());
                }
                System.out.println("Monthly Expense Report:");
                monthlyTotals.forEach((month, total) -> System.out.println(month + ": $" + total));
                break;
            case 2:
                Map<String, Double> categoryTotals = new HashMap<>();
                for (Expense expense : expenses) {
                    String category = expense.getCategory();
                    categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + expense.getAmount());
                }
                System.out.println("Category-wise Expense Report:");
                categoryTotals.forEach((category, total) -> System.out.println(category + ": $" + total));
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
