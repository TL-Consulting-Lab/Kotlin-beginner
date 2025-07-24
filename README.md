# Product Management System

[![CI/CD Pipeline](https://github.com/TL-Consulting-Lab/Kotlin-beginner/actions/workflows/ci.yml/badge.svg)](https://github.com/TL-Consulting-Lab/Kotlin-beginner/actions/workflows/ci.yml)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.21-blue.svg)](https://kotlinlang.org)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

A simple console-based Product Management System built with Kotlin that allows you to manage products with basic CRUD operations.

## Features

- 📋 **View All Products**: Display all products in the system with detailed information
- 🔍 **Search Product**: Search products by name, category, or description
- ➕ **Create Product**: Add new products to the system
- 💾 **Sample Data**: Comes pre-loaded with sample products for demonstration
- 🌐 **Web Interface**: Modern responsive web UI in the `frontend/` directory
- 📱 **Mobile Friendly**: Responsive design that works on all devices

## Project Structure

```
Kotlin-beginner/
├── kotlin/                # Kotlin source files
│   ├── Product.kt         # Product data class
│   ├── ProductManager.kt  # Product management logic
│   └── Main.kt           # Main application entry point
├── frontend/              # Web UI components
│   ├── index.html         # Main HTML interface
│   ├── styles.css         # Modern CSS styling
│   ├── app.js             # JavaScript application logic
│   └── README.md          # Frontend documentation
├── run.sh                 # Build and run script
├── demo-prompts.md        # Demo and testing prompts
└── README.md              # This file
```

## Product Properties

Each product has the following properties:
- **ID**: Unique identifier (auto-generated)
- **Name**: Product name
- **Description**: Product description
- **Price**: Product price in USD
- **Category**: Product category
- **Stock Quantity**: Available stock count

## How to Run

### Prerequisites
- Java 11, 17, or 21 (OpenJDK or Oracle JDK)
- Gradle 8.5+ (or use the included Gradle wrapper)

### Console Application (Kotlin)

1. **Using Gradle (Recommended)**:
   ```bash
   # Build and run with Gradle wrapper
   ./gradlew run
   
   # Or build the JAR and run it
   ./gradlew build
   java -jar build/libs/kotlin-product-management-1.0.0.jar
   ```

2. **Using the legacy build script**:
   ```bash
   # Using the provided script (requires kotlinc)
   ./run.sh
   ```

3. **Manual compilation**:
   ```bash
   # Compile manually (requires kotlinc)
   kotlinc kotlin/*.kt -include-runtime -d ProductManagementSystem.jar
   java -jar ProductManagementSystem.jar
   ```

### Development Commands

```bash
# Run tests
./gradlew test

# Generate test coverage report
./gradlew jacocoTestReport

# Check code style with ktlint
./gradlew ktlintCheck

# Auto-format code with ktlint
./gradlew ktlintFormat

# Build everything (compile, test, package)
./gradlew build

# Clean build artifacts
./gradlew clean
```

### Web Interface

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Open the web interface:
```bash
# Option 1: Open directly in browser
open index.html

# Option 2: Serve with HTTP server (recommended)
python3 -m http.server 8000
# Then visit http://localhost:8000
```

The web interface provides a modern, interactive UI with the same functionality as the console application.

## Usage

### Console Application
When you run the Kotlin application, you'll see a main menu with the following options:

1. **View All Products**: Shows all products currently in the system
2. **Search Product**: Enter a search term to find products by name, category, or description
3. **Create Product**: Add a new product by providing:
   - Product name
   - Product description
   - Price
   - Category
   - Stock quantity
4. **Exit**: Close the application

### Web Interface
The web interface provides four main sections:

1. **📊 Dashboard**: Overview with statistics and recent products
2. **📋 View Products**: Complete product listing with filtering and sorting
3. **🔍 Search Products**: Advanced search with real-time results
4. **➕ Create Product**: Interactive form with validation

#### Web Features:
- **Real-time Statistics**: Live dashboard with total products, categories, value, and stock
- **Advanced Filtering**: Filter by category and sort by multiple criteria
- **Toast Notifications**: User-friendly feedback for all operations
- **Responsive Design**: Works seamlessly on desktop, tablet, and mobile devices
- **Modern UI**: Beautiful gradient design with smooth animations

## Sample Products

The system comes with the following pre-loaded sample products:
- iPhone 15 (Electronics)
- Samsung Galaxy S24 (Electronics)
- Dell XPS 13 (Computers)
- Nike Air Max (Footwear)
- Coffee Maker (Appliances)

## Code Features

### Kotlin Backend
- **Data Class**: Uses Kotlin data class for clean product representation
- **Mutable List**: Products stored in a mutable list for easy manipulation
- **String Extensions**: Utilizes Kotlin's string extension functions for search
- **Null Safety**: Implements Kotlin's null safety features
- **Input Validation**: Comprehensive input validation for all user inputs
- **Error Handling**: Proper error handling with try-catch blocks
- **Clean UI**: Well-formatted console output with clear separators

### Frontend Web Interface
- **Modern JavaScript**: ES6+ class-based architecture with event-driven design
- **Responsive CSS**: Mobile-first design with CSS Grid and Flexbox
- **Glass Morphism**: Modern backdrop-filter effects and gradient backgrounds
- **Component Architecture**: Modular, reusable UI components
- **Real-time Updates**: Dynamic content updates without page refresh
- **Form Validation**: Client-side validation with immediate feedback
- **Toast Notifications**: Custom notification system for user feedback
- **Data Synchronization**: Same sample data as Kotlin backend for consistency

## CI/CD Pipeline

This project includes a comprehensive GitHub Actions CI/CD pipeline that ensures code quality and automated testing.

### Workflows

#### 🔄 CI/CD Pipeline (`.github/workflows/ci.yml`)
Triggered on pushes to `main`, `develop` branches and pull requests to `main`.

**Test Job** (Matrix: Java 11, 17, 21):
- Builds and tests the application on multiple JDK versions
- Generates test coverage reports with JaCoCo
- Uploads test results and coverage reports as artifacts

**Code Quality Job**:
- Runs ktlint for Kotlin code style checking
- Ensures consistent code formatting across the project
- Uploads ktlint reports as artifacts

**Build Job**:
- Creates distributable JAR files and archives
- Uploads build artifacts for deployment
- Only runs after tests and quality checks pass

#### 🚀 Release Workflow (`.github/workflows/release.yml`)
Triggered when tags matching `v*` are pushed.

- Builds the application for release
- Creates GitHub releases with automatic release notes
- Attaches JAR files and distribution archives
- Generates professional release documentation

### Build Features

- **Multi-JDK Support**: Tested on Java 11, 17, and 21
- **Gradle Build System**: Modern build automation with dependency management
- **Test Automation**: JUnit 5 with comprehensive test coverage
- **Code Coverage**: JaCoCo integration with HTML and XML reports
- **Code Quality**: ktlint integration for Kotlin style guidelines
- **Artifact Management**: Automatic build artifact generation and storage
- **Dependency Caching**: Optimized build times with Gradle caching

### Quality Gates

The CI pipeline enforces several quality gates:

1. **Compilation**: All code must compile successfully
2. **Tests**: All unit tests must pass on all supported JDK versions
3. **Code Style**: ktlint checks must pass with no violations
4. **Build**: Complete build process must succeed before merging

### Monitoring and Reports

Build status and quality metrics are tracked through:

- **Build Status Badges**: Visible in the README
- **Test Results**: Detailed test reports for each build
- **Coverage Reports**: Code coverage tracking with JaCoCo
- **Style Reports**: ktlint formatting and style check results

### Getting Started with Development

1. **Fork and Clone**: Fork the repository and clone it locally
2. **Build**: Run `./gradlew build` to ensure everything works
3. **Test**: Run `./gradlew test` to run the test suite
4. **Style Check**: Run `./gradlew ktlintCheck` to verify code style
5. **Develop**: Make your changes following the existing patterns
6. **Submit**: Create a pull request for review

The CI pipeline will automatically validate your changes across multiple Java versions and ensure code quality standards are met.

## Future Enhancements

### Backend Improvements
- Update/Edit existing products
- Delete products
- Save/Load products from file
- Product categories management
- Inventory management features
- Price filtering and sorting
- Export product data

### Frontend Enhancements
- Backend Integration with REST API
- Real-time updates with WebSocket connections
- Advanced filtering (price ranges, stock levels)
- Product image upload and display
- Bulk operations and batch actions
- CSV/PDF export functionality
- User authentication system
- Dark mode theme switching
- Offline support with local storage
