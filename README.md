# SauceDemo Selenium Automation Framework

A Java-based Selenium automation framework for testing the SauceDemo web application using Selenium WebDriver, TestNG, Maven, and the Page Object Model (POM).

The framework is designed with a focus on maintainability, reusability, thread-safe parallel execution, and automated test reporting.

---

## Tech Stack

- Java 21
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager
- Log4j2
- ExtentReports
- Git & GitHub

---

## Framework Features

- Page Object Model (POM)
- Reusable BasePage and BaseTest
- Thread-safe WebDriver management using `ThreadLocal`
- Parallel test execution with TestNG
- Data-driven testing using TestNG DataProviders
- Automatic screenshots on test failure
- TestNG Listeners
- Retry mechanism for failed tests
- ExtentReports HTML reporting
- Log4j2 execution logging
- Externalized configuration using properties files

---

## Automated Modules

### Login

- Valid login
- Invalid credentials
- Locked user validation
- Data-driven login testing

### Inventory

- Verify inventory page
- Verify products and prices
- Add products to cart

### Cart

- Verify added products
- Verify product prices
- Remove products
- Continue Shopping navigation
- Checkout navigation

### Checkout

- Checkout information
- Checkout overview
- Payment information
- Shipping information
- Order completion

### End-to-End Flow

```text
Login
  ↓
Inventory
  ↓
Add Product
  ↓
Cart
  ↓
Checkout
  ↓
Order Completion
```

---

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.automation
│   │       ├── base
│   │       ├── constants
│   │       ├── driver
│   │       ├── exceptions
│   │       ├── pages
│   │       └── utils
│   │
│   └── resources
│
└── test
    ├── java
    │   └── com.automation
    │       ├── base
    │       ├── dataproviders
    │       ├── listeners
    │       ├── tests
    │       └── utils
    │
    └── resources
```

---

## Reporting and Logs

The framework generates:

- ExtentReports HTML reports
- Screenshots for failed tests
- Log4j2 execution logs

Generated artifacts:

```text
reports/
screenshots/
logs/
```

---

## Running the Tests

### Using Maven

```bash
mvn clean test
```

### Using TestNG

Tests can also be executed using the `testng.xml` suite file.

---

## Configuration

Framework configuration is managed through:

```text
src/test/resources/config.properties
```

Example:

```properties
browser=chrome
headless=false
maximize=true
url=https://www.saucedemo.com/
```

---

## Author

**Mohit Singh**

Java | Selenium | TestNG | Maven | SDET Automation