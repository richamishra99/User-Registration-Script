# User-Registration-Script
User Registration flow
# QA Automation Assignment – Registration Flow

## Objective
Automate the registration flow of a publicly available website and validate:
1. Successful user registration
2. No persistent database entry is created after registration

## Website Used
https://automationexercise.com  
(Chosen because it allows UI registration without real email verification)

---

## Tech Stack
- Language: Java
- Automation: Selenium WebDriver
- Test Framework: TestNG
- Build Tool: Maven
- Design Pattern: Page Object Model (POM)

---

## Test Scenarios Covered
- Successful user registration
- Validation when existing email is used
- UI confirmation after account creation

---

## Database Validation Approach
Direct database access is not available for public websites.

**Approach Used:**
- Validation is done via UI error messages
- Re-registration with same email confirms persistence behavior
- Assumption documented as per assignment guidelines

---

## How to Run the Project

### Prerequisites
- Java 8+
- Maven
- Chrome Browser

### Steps
```bash
git clone https://github.com/your-username/qa-registration-automation.git
cd qa-registration-automation
mvn clean test
