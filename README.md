# E-Commerce Automation BDD Framework

A comprehensive BDD (Behavior-Driven Development) test automation framework for e-commerce applications, combining UI automation with Selenium WebDriver and API testing with Karate Framework.

## 🚀 Features

- **Dual Testing Approach**: Supports both UI and API test automation
- **BDD with Cucumber**: Human-readable test scenarios using Gherkin syntax
- **Page Object Model**: Maintainable and scalable UI test architecture
- **Karate Framework**: Powerful API testing with built-in assertions
- **Parallel Execution**: Run tests concurrently for faster feedback
- **Rich Reporting**: Multiple report formats including HTML and Extent Reports
- **WebDriver Management**: Automatic browser driver management with WebDriverManager
- **Cross-browser Testing**: Support for Chrome and other browsers
- **Data-Driven Testing**: XML-based locator management and data handling
- **Comprehensive Utilities**: Reusable utilities for common web interactions

## 🛠 Technology Stack

- **Java 21**: Latest LTS version of Java
- **Maven**: Build and dependency management
- **Selenium WebDriver 4.15.0**: UI automation
- **Cucumber 7.14.0**: BDD framework
- **JUnit 5**: Test execution and assertions
- **Karate 1.3.1**: API testing framework
- **Extent Reports 5.0.9**: Advanced test reporting
- **Log4j 2.21.1**: Logging framework
- **Apache POI**: Excel data handling
- **AssertJ**: Fluent assertions

## 📁 Project Structure

```
e-commerce-automation-BDD/
├── src/test/
│   ├── java/com/automation/ecommerceBDD/
│   │   ├── APIRunner/              # Karate API test runners
│   │   │   ├── KarateAPITestRunner.java
│   │   │   ├── KarateAuthenticationRunner.java
│   │   │   ├── KarateBrandsRunner.java
│   │   │   └── KarateProductsRunner.java
│   │   ├── UIRunner/                # Cucumber UI test runners
│   │   │   └── AuthRunner.java
│   │   ├── pages/                   # Page Object Model classes
│   │   │   ├── HomePage.java
│   │   │   └── authPages/
│   │   │       ├── SignupLoginPage.java
│   │   │       └── CreateAccountPage.java
│   │   ├── stepdefinitions/         # Cucumber step definitions
│   │   │   ├── Base.java
│   │   │   └── AuthenticationSteps.java
│   │   └── utilities/               # Reusable utility classes
│   │       ├── dataRelated/         # Data handling utilities
│   │       └── webActionRelated/    # Web interaction utilities
│   │           ├── WaitUtil.java
│   │           ├── AssertUtil.java
│   │           ├── ClickUtil.java
│   │           ├── ScrollUtil.java
│   │           └── SelectDropDownUtil.java
│   └── resources/
│       ├── features/
│       │   ├── api/                 # Karate API feature files
│       │   │   ├── authentication/
│       │   │   ├── brands/
│       │   │   └── products/
│       │   └── ui/                  # Cucumber UI feature files
│       │       ├── authentication/
│       │       ├── cart/
│       │       ├── checkout/
│       │       ├── products/
│       │       ├── subscription/
│       │       ├── navigation/
│       │       ├── contact/
│       │       └── testCases/
│       ├── locators/
│       │   └── locators.xml         # XML-based element locators
│       └── karate-config.js         # Karate configuration
├── pom.xml                          # Maven configuration
├── .gitignore                       # Git ignore rules
└── README.md                        # This file
```

## 📋 Prerequisites

Before running this project, ensure you have the following installed:

- **Java Development Kit (JDK) 21** or higher
  - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or use [OpenJDK](https://openjdk.org/)
  - Verify installation: `java -version`
  
- **Apache Maven 3.6+**
  - Download from [Apache Maven](https://maven.apache.org/download.cgi)
  - Verify installation: `mvn -version`
  
- **Git** (for version control)
  - Download from [git-scm.com](https://git-scm.com/)
  
- **Web Browser**
  - Chrome (recommended) - WebDriverManager will automatically download the appropriate driver
  - Other browsers supported by Selenium WebDriver

## 🔧 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/gulziraAbudula/e-commerce-automation-BDD.git
   cd e-commerce-automation-BDD
   ```

2. **Install dependencies**
   ```bash
   mvn clean install -DskipTests
   ```

3. **Verify setup**
   ```bash
   mvn clean compile
   ```

## ⚙️ Configuration

### Test Application
The framework is configured to test the e-commerce application at:
- **URL**: https://automationexercise.com/

### Browser Configuration
The default browser is Chrome. To change the browser, modify the `Base.java` file:
```java
String browser = "chrome"; // Change to "firefox", "edge", etc.
```

### Karate Configuration
API test configuration is managed in `src/test/resources/karate-config.js`:
- Environment settings (dev, stage, e2e)
- Base URLs for API endpoints
- Timeouts and other API configurations

### Test Tags
Tests are organized with Cucumber tags:
- `@RegressionUITest`: UI regression tests
- `@Smoke`: Smoke tests
- `@Skip`: Tests to skip

## 🧪 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run UI Tests Only
```bash
mvn test -Dtest=AuthRunner
```

### Run API Tests Only
```bash
# Run all API tests
mvn test -Dtest=KarateAPITestRunner

# Run specific API test suites
mvn test -Dtest=KarateAuthenticationRunner
mvn test -Dtest=KarateProductsRunner
mvn test -Dtest=KarateBrandsRunner
```

### Run Tests with Specific Tags
```bash
mvn test -Dcucumber.filter.tags="@Smoke"
mvn test -Dcucumber.filter.tags="@RegressionUITest and not @Skip"
```

### Run Tests in Parallel
The framework is configured for parallel execution in `pom.xml`:
```bash
mvn clean test -Dparallel=methods -DthreadCount=4
```

### Run Tests with Different Browsers
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

## 📊 Test Reports

After test execution, reports are generated in the following locations:

### Cucumber HTML Report
- **Location**: `target/cucumber-reports/index.html`
- **Description**: Standard Cucumber HTML report with test execution details

### Extent Reports
- **Location**: `target/extent-reports/`
- **Description**: Rich, interactive test reports with screenshots and detailed logs

### Surefire Reports
- **Location**: `target/surefire-reports/`
- **Description**: Maven Surefire test reports in XML format

### Karate Reports
- **Location**: `target/karate-reports/`
- **Description**: Karate API test reports with request/response details

To view reports, simply open the HTML files in your browser:
```bash
# On Linux/Mac
open target/cucumber-reports/index.html

# On Windows
start target/cucumber-reports/index.html
```

## 📝 Writing Tests

### UI Tests (Cucumber)

1. **Create a feature file** in `src/test/resources/features/ui/`
   ```gherkin
   Feature: User Login
     
     @Smoke
     Scenario: User successfully logs in
       Given User launches the browser
       And User navigates to 'http://automationexercise.com'
       When User clicks on Signup Login button
       Then User verifies that Login to your account is visible
   ```

2. **Create step definitions** in `src/test/java/com/automation/ecommerceBDD/stepdefinitions/`

3. **Create page objects** in `src/test/java/com/automation/ecommerceBDD/pages/`

### API Tests (Karate)

1. **Create a feature file** in `src/test/resources/features/api/`
   ```gherkin
   Feature: Get All Products API
     
     Scenario: Verify all products list
       Given url 'https://automationexercise.com/api/productsList'
       When method GET
       Then status 200
   ```

2. **Create a test runner** in `src/test/java/com/automation/ecommerceBDD/APIRunner/`

## 🏗️ Framework Components

### Utilities

The framework includes comprehensive utility classes for common operations:

- **WaitUtil**: Explicit and implicit waits
- **AssertUtil**: Custom assertions and validations
- **ClickUtil**: Enhanced click operations with retries
- **ScrollUtil**: Scroll operations for element visibility
- **SelectDropDownUtil**: Dropdown selection handling
- **BrowserUtil**: Browser initialization and management
- **XMLReaderUtil**: XML-based locator management
- **DatePickerUtil**: Date picker interactions
- **IframeHandlingUtil**: iFrame switching
- **FileDownloadUtil**: File download verification

### Design Patterns

- **Page Object Model (POM)**: UI page classes for better maintainability
- **Singleton Pattern**: Single WebDriver instance management
- **Factory Pattern**: Dynamic object creation for utilities
- **Builder Pattern**: Step definition builders for complex scenarios

## 🐛 Debugging

### Enable Debug Logging
Add to your test runner or command line:
```bash
mvn test -Dlog4j.configurationFile=log4j2.xml -Dlog4j.debug
```

### Take Screenshots on Failure
Screenshots are automatically captured on test failure and saved in:
- `target/screenshots/`

### View Browser Actions
Enable headed mode (visible browser) by modifying `BrowserUtil`:
```java
ChromeOptions options = new ChromeOptions();
// options.addArguments("--headless"); // Comment out for visible browser
```

## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make your changes**
   - Follow existing code style and patterns
   - Add tests for new functionality
   - Update documentation as needed
4. **Commit your changes**
   ```bash
   git commit -m "Add: description of your changes"
   ```
5. **Push to your fork**
   ```bash
   git push origin feature/your-feature-name
   ```
6. **Create a Pull Request**

### Code Style Guidelines
- Follow Java naming conventions
- Write clear, descriptive Gherkin scenarios
- Add comments for complex logic
- Keep methods small and focused
- Use meaningful variable and method names

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- **Automation Exercise** - Test application provider
- **Cucumber** - BDD framework
- **Karate** - API testing framework
- **Selenium** - Web automation framework

## 📧 Contact

For questions or support, please open an issue in the GitHub repository.

---

**Happy Testing! 🎉**
