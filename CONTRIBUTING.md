# Contributing to Product Management System

Thank you for your interest in contributing to the Product Management System! This document provides guidelines and information for contributors.

## Development Workflow

### Prerequisites

- Java 11, 17, or 21 (OpenJDK or Oracle JDK)
- Git

### Setting Up the Development Environment

1. **Fork the Repository**
   ```bash
   # Fork the repository on GitHub, then clone your fork
   git clone https://github.com/YOUR_USERNAME/Kotlin-beginner.git
   cd Kotlin-beginner
   ```

2. **Build the Project**
   ```bash
   # Build and test the project
   ./gradlew build
   ```

3. **Run Tests**
   ```bash
   # Run the full test suite
   ./gradlew test
   ```

4. **Check Code Style**
   ```bash
   # Verify code formatting
   ./gradlew ktlintCheck
   ```

### Development Commands

| Command | Purpose |
|---------|---------|
| `./gradlew build` | Complete build (compile, test, package) |
| `./gradlew test` | Run unit tests |
| `./gradlew run` | Run the application |
| `./gradlew ktlintCheck` | Check code style |
| `./gradlew ktlintFormat` | Auto-format code |
| `./gradlew jacocoTestReport` | Generate coverage report |
| `./gradlew clean` | Clean build artifacts |

### Code Style Guidelines

This project uses [ktlint](https://ktlint.github.io/) for Kotlin code formatting and style:

- **Indentation**: 4 spaces, no tabs
- **Line Length**: Maximum 120 characters
- **Import Organization**: No wildcard imports (`import kotlin.test.*` ❌, use specific imports ✅)
- **Function Naming**: camelCase
- **Class Naming**: PascalCase
- **Constant Naming**: UPPER_SNAKE_CASE

**Auto-formatting**: Run `./gradlew ktlintFormat` to automatically fix most style issues.

### Writing Tests

- Place tests in `src/test/kotlin/`
- Use descriptive test names that explain what is being tested
- Follow the AAA pattern: Arrange, Act, Assert
- Aim for high test coverage (check with `./gradlew jacocoTestReport`)

Example test structure:
```kotlin
@Test
fun testProductCreation() {
    // Arrange
    val expectedName = "Test Product"
    
    // Act
    val product = Product(1, expectedName, "Description", 99.99, "Category", 10)
    
    // Assert
    assertEquals(expectedName, product.name)
}
```

### Submitting Changes

1. **Create a Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make Your Changes**
   - Follow the code style guidelines
   - Add tests for new functionality
   - Update documentation if needed

3. **Verify Your Changes**
   ```bash
   # Run all checks before committing
   ./gradlew build ktlintCheck
   ```

4. **Commit Your Changes**
   ```bash
   git add .
   git commit -m "Add feature: brief description of changes"
   ```

5. **Push and Create Pull Request**
   ```bash
   git push origin feature/your-feature-name
   ```
   Then create a pull request on GitHub.

### Pull Request Guidelines

- **Title**: Use a clear, descriptive title
- **Description**: Explain what changes were made and why
- **Tests**: Ensure all tests pass and add new tests for new features
- **Code Style**: Verify ktlint checks pass
- **Documentation**: Update relevant documentation

### CI/CD Pipeline

Your pull request will be automatically tested through our CI/CD pipeline:

#### ✅ What Gets Checked
- **Multi-JDK Testing**: Your changes are tested on Java 11, 17, and 21
- **Unit Tests**: All existing and new tests must pass
- **Code Style**: ktlint formatting rules must be followed
- **Build**: Complete build process must succeed

#### 📊 Quality Reports
- **Test Coverage**: JaCoCo generates coverage reports
- **Style Report**: ktlint provides detailed formatting feedback
- **Build Artifacts**: Distributable JARs are created and stored

#### 🚫 Common CI Failures and Fixes
- **Test Failures**: Run `./gradlew test` locally to debug
- **Style Violations**: Run `./gradlew ktlintFormat` to auto-fix
- **Build Errors**: Run `./gradlew build` to identify compilation issues

### Project Structure

```
Kotlin-beginner/
├── .github/workflows/          # CI/CD pipeline definitions
│   ├── ci.yml                 # Main CI/CD workflow
│   └── release.yml            # Release automation
├── src/
│   ├── main/kotlin/           # Application source code
│   │   ├── Main.kt           # Application entry point
│   │   ├── Product.kt        # Product data class
│   │   └── ProductManager.kt # Business logic
│   └── test/kotlin/           # Test source code
│       ├── ProductTest.kt     # Product unit tests
│       └── ProductManagerTest.kt # ProductManager tests
├── frontend/                   # Web interface (HTML/CSS/JS)
├── build.gradle.kts           # Gradle build configuration
├── gradle.properties          # Gradle properties
├── settings.gradle.kts        # Gradle settings
└── README.md                  # Project documentation
```

### Getting Help

- **Issues**: Check existing [GitHub Issues](https://github.com/TL-Consulting-Lab/Kotlin-beginner/issues)
- **Discussions**: Start a [GitHub Discussion](https://github.com/TL-Consulting-Lab/Kotlin-beginner/discussions)
- **Documentation**: Refer to the [README.md](README.md) for detailed information

### Code of Conduct

- Be respectful and inclusive in all communications
- Focus on constructive feedback and collaboration
- Help others learn and grow in their development journey
- Follow the GitHub Community Guidelines

## Recognition

Contributors will be recognized in:
- Git commit history
- Release notes for significant contributions
- Project documentation (when appropriate)

Thank you for contributing to the Product Management System! 🚀