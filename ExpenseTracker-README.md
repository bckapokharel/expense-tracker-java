# Expense Tracker — Java

A console-based personal expense tracker built in Java. Record expenses, manage custom categories, filter by date/amount/category, modify or delete entries, and generate monthly and category-wise spending reports.

---

## Features

- 📝 **Record expenses** with date, amount, category, and description
- 🏷️ **Manage categories** — add, view, and delete custom spending categories
- 🔍 **Filter expenses** by date range, category, or amount range
- ✏️ **Modify or delete** any recorded expense by ID
- 📊 **Generate reports** — monthly totals and category-wise breakdowns
- ✅ **Input validation** — handles invalid dates, amounts, and missing categories gracefully

---

## Tech stack

- **Java** (Java 8+ required for `LocalDate` and streams)
- **Java Collections** — `ArrayList`, `HashSet`, `HashMap`
- **Java Time API** — `LocalDate` for date handling
- **Java Streams** — for filtering and reporting

---

## How to run

### Prerequisites
- Java JDK 8 or higher installed
- Terminal / command prompt

### Steps

```bash
# 1. Clone the repo
git clone https://github.com/bckapokharel/expense-tracker-java.git
cd expense-tracker-java

# 2. Compile the file
javac ExpenseTracker.java

# 3. Run the program
java ExpenseTracker
```

---

## Usage walkthrough

When you run the program, you'll see a menu:

```
--- Expense Tracker Menu ---
1. Record Expense
2. Manage Categories
3. View Expenses
4. Filter Expenses
5. Modify/Delete Expense
6. Generate Reports
7. Exit
```

**Typical workflow:**
1. First, go to **option 2** → add a category (like `Food` or `Transport`)
2. Then **option 1** → record an expense under that category
3. Use **option 3** to view all expenses, or **option 4** to filter them
4. Generate spending reports with **option 6**

---

## Code structure

```
ExpenseTracker.java
├── class Expense       — model for a single expense (id, date, amount, category, description)
└── class ExpenseTracker — main class with menu loop and all logic
    ├── recordExpense()
    ├── manageCategories()
    ├── viewExpenses()
    ├── filterExpenses()
    ├── modifyOrDeleteExpense()
    └── generateReports()
```

---

## What I learned

- Using `LocalDate` and handling `DateTimeParseException`
- Designing a menu-driven CLI with proper input validation
- Working with Java Streams for filtering collections
- Separating data model (`Expense`) from program logic (`ExpenseTracker`)
- Managing application state with static collections

---

## Future improvements

- [ ] Persist data to a file (CSV or JSON) so expenses survive between sessions
- [ ] Add a budget limit with warnings when exceeded
- [ ] Export reports to a text/PDF file
- [ ] Build a simple GUI with JavaFX or Swing

---

## Author

**Bishika Pokharel** — Cybersecurity student at the University of North Texas
- 🌐 Portfolio: [bckapokharel.github.io](https://bckapokharel.github.io)
- 📧 bishikapokharel@my.unt.edu
