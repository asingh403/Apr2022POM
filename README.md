# Project Readme - Apr2022POM

This repository contains the source code and related files for the project "Apr2022POM." The project focuses on implementing a Page Object Model (POM) framework for test automation using Selenium WebDriver and Java.

## Table of Contents
- [Introduction](#introduction)
- [Getting Started](#getting-started)
- [Folder Structure](#folder-structure)
- [Dependencies](#dependencies)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction
The Apr2022POM project aims to provide a robust and scalable framework for automating web application testing using the Page Object Model design pattern. The POM design pattern enhances code reusability and maintainability by separating the test logic from the page elements and actions.

## Getting Started
To get started with the Apr2022POM project, follow these steps:

1. Clone the repository using the following command:
   ```
   git clone https://github.com/asingh403/Apr2022POM.git
   ```

2. Ensure you have the necessary dependencies installed (see the [Dependencies](#dependencies) section for more details).

3. Set up your development environment, preferably an IDE such as IntelliJ IDEA or Eclipse, to work with Java and Selenium WebDriver.

4. Build and run the project.

## Folder Structure
The folder structure of the project is as follows:

```
Apr2022POM/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── project/
│   │               ├── pages/
│   │               │   └── ... (Page classes)
│   │               ├── tests/
│   │               │   └── ... (Test classes)
│   │               ├── utilities/
│   │               │   └── ... (Utility classes)
│   │               └── webdriver/
│   │                   └── ... (WebDriver setup and configuration)
│   └── test/
│       └── java/
│           └── com/
│               └── project/
│                   ├── pages/
│                   │   └── ... (Page test classes)
│                   └── utilities/
│                       └── ... (Utility test classes)
├── .gitignore
├── pom.xml
└── README.md
```

The main source code resides in the `src/main/java/com/project` directory, where you can find the page classes, test classes, utility classes, and WebDriver setup.

The test source code resides in the `src/test/java/com/project` directory, where you can find the page test classes and utility test classes.

## Dependencies
The Apr2022POM project has the following dependencies:

- Java 8 or above
- Selenium WebDriver
- TestNG
- Apache Maven

Ensure that you have these dependencies installed and configured properly in your development environment before running the project.

## Usage
The Apr2022POM project provides a framework for creating and executing automated tests using the POM design pattern. To use the framework, follow these steps:

1. Create page classes for the web pages you want to test. These page classes should extend the `BasePage` class and contain methods to interact with the page elements.

2. Create test classes for your test scenarios. These test classes should extend the `BaseTest` class and use the page classes to perform the desired actions and assertions.

3. Run the test classes using your preferred test runner or build tool (e.g., TestNG, Maven). The tests will execute the actions defined in the page
