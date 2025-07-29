# Product Management System

A simple console-based Product Management System built with Kotlin that allows you to manage products with basic CRUD operations.

## Features

- 📋 **View All Products**: Display all products in the system with detailed information
- 🔍 **Search Product**: Search products by name, category, or description
- ➕ **Create Product**: Add new products to the system
- 💾 **Sample Data**: Comes pre-loaded with sample products for demonstration
- 🖥️ **Console Interface**: Clean, user-friendly console-based interface
- ⚡ **Fast Performance**: Lightweight Kotlin application with instant startup

## 🚀 Quick Start

### **Console Application**
```powershell
cmd /c "gradlew.bat build -x test"
java -jar ".\build\libs\product-management-system-1.0.0.jar"
```

## Project Structure

```
Kotlin-beginner/
├── src/                   # Modern Gradle project structure
│   ├── main/kotlin/       # Main source code
│   │   ├── Product.kt     # Product data class
│   │   ├── ProductManager.kt # Product management logic
│   │   └── Main.kt        # Main application entry point
│   └── test/kotlin/       # Test source code
├── docs/                  # Project documentation
│   ├── GRADLE.md          # Build system guide
│   ├── challenges-prompts.md # Learning challenges
│   └── demo-prompts.md    # Demo scenarios
├── gradle/wrapper/        # Gradle wrapper files
├── build.gradle.kts       # Gradle build configuration
├── settings.gradle.kts    # Gradle settings
├── gradlew & gradlew.bat  # Gradle wrapper scripts
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

Before running the project, ensure you have the following installed:
- **Java 21** or higher
- **Kotlin 2.1.0** or higher  
- **Gradle** (included via wrapper)

### PowerShell Configuration (Windows Users)

If you encounter execution policy issues with PowerShell, check your current policy:

```powershell
Get-ExecutionPolicy
```

If it shows `Restricted`, you may need to use `cmd` commands instead of PowerShell, or set the policy to `RemoteSigned`:

```powershell
# Check current policy
Get-ExecutionPolicy

# If needed, set to RemoteSigned (run as Administrator)
Set-ExecutionPolicy RemoteSigned
```

### Console Application (Kotlin)

#### Method 1: Using Gradle (Recommended)
```powershell
# Build the project (Windows)
cmd /c "gradlew.bat build -x test"

# Run the console application
java -jar ".\build\libs\product-management-system-1.0.0.jar"
```

#### Method 2: Using Gradle Run Task
```powershell
# Run directly with Gradle (may have input issues in VS Code terminal)
cmd /c "gradlew.bat run --console=plain"
```

#### For Linux/macOS:
```bash
# Build the project
./gradlew build -x test

# Run the console application
java -jar build/libs/product-management-system-1.0.0.jar
```

### 🔧 Troubleshooting

#### Common Issues:

**Gradle Daemon Memory Issues:**
```powershell
# Stop all Gradle daemons and retry
cmd /c "gradlew.bat --stop"
cmd /c "gradlew.bat build -x test"
```

**Port Already in Use:**
```powershell
# Use a different port
npx http-server -p 8080 -o
```

**PowerShell Script Execution:**
```powershell
# Use cmd wrapper for Gradle commands
cmd /c "gradlew.bat [command]"
```

**Console Input Issues:**
- The console application works best in Windows Command Prompt or external terminal
- VS Code integrated terminal may have input handling limitations

### ✅ Verification Steps

1. **Check Java:** `java -version` (should show 21+)
2. **Check Kotlin:** `kotlin -version` (should show 2.1.0+)
3. **Build Project:** `cmd /c "gradlew.bat build -x test"`
4. **Run Console:** `java -jar ".\build\libs\product-management-system-1.0.0.jar"`

The console application provides a clean, interactive interface with full product management functionality.

## Usage

### Console Application
When you run the Kotlin application, you'll see a main menu with the following options:

1. **View All Products**: Shows all products currently in the system
2. **Search Products**: Enter a search term to find products by name, category, or description
3. **Create Product**: Add a new product by providing:
   - Product name
   - Product description
   - Price
   - Category
   - Stock quantity
4. **View Inventory Statistics**: See total products, categories, and stock levels
5. **View Products by Category**: Browse products organized by categories
6. **Exit**: Close the application

### Navigation
- Use number keys (1-6) to select menu options
- Follow the prompts for data entry
- Press Enter to continue after viewing results
- Input validation ensures data quality

## Sample Products

The system comes with the following pre-loaded sample products:
- iPhone 15 (Electronics)
- Samsung Galaxy S24 (Electronics)
- Dell XPS 13 (Computers)
- Nike Air Max (Footwear)
- Coffee Maker (Appliances)

## Code Features

## Code Features

### Kotlin Backend
- **Data Class**: Uses Kotlin data class for clean product representation
- **Mutable List**: Products stored in a mutable list for easy manipulation
- **String Extensions**: Utilizes Kotlin's string extension functions for search
- **Null Safety**: Implements Kotlin's null safety features
- **Input Validation**: Comprehensive input validation for all user inputs
- **Error Handling**: Proper error handling with try-catch blocks
- **Clean UI**: Well-formatted console output with clear separators
- **Service Architecture**: Separation of concerns with service and UI layers
- **Modern Gradle**: Uses Gradle with Kotlin DSL for build management

## 📚 Documentation

For detailed information, check out the [`docs/`](docs/) folder:

- **[Build & Setup Guide](docs/GRADLE.md)** - Complete Gradle configuration and build instructions
- **[Learning Challenges](docs/challenges-prompts.md)** - Programming exercises and practice problems
- **[Demo Scenarios](docs/demo-prompts.md)** - Example use cases and guided demonstrations
- **[Documentation Overview](docs/README.md)** - Complete docs folder navigation

## Future Enhancements

### Application Improvements
- Update/Edit existing products
- Delete products
- Save/Load products from file (JSON/CSV)
- Product categories management
- Inventory management features
- Price filtering and sorting
- Export product data
- Database integration (H2/SQLite)
- REST API development
- Product image support
- Barcode scanning integration
- Multi-user support
- Data backup and restore
