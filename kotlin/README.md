# Kotlin Backend - Product Management System

This directory contains the Kotlin source files for the console-based Product Management System.

## 📁 Files

- **`Product.kt`** - Data class representing a product with properties like ID, name, description, price, category, and stock quantity
- **`ProductManager.kt`** - Core business logic class handling all product operations (view, search, create)
- **`Main.kt`** - Main application entry point with user interface and menu system

## 🚀 How to Compile and Run

From the project root directory:

```bash
# Using the build script
./run.sh

# Or compile manually
kotlinc kotlin/*.kt -include-runtime -d ProductManagementSystem.jar
java -jar ProductManagementSystem.jar

# Or run directly (if you have Kotlin script runner)
cd kotlin
kotlin MainKt
```

## 🛠 Technical Features

- **Data Class**: Clean product representation with auto-generated methods
- **Mutable Collections**: Efficient product storage and manipulation
- **Null Safety**: Kotlin's built-in null safety features
- **String Extensions**: Case-insensitive search functionality
- **Input Validation**: Comprehensive validation for all user inputs
- **Error Handling**: Proper exception handling throughout

## 📦 Sample Data

The system comes pre-loaded with sample products:
- iPhone 15 (Electronics) - $999.99, Stock: 25
- Samsung Galaxy S24 (Electronics) - $899.99, Stock: 30
- Dell XPS 13 (Computers) - $1199.99, Stock: 15
- Nike Air Max (Footwear) - $129.99, Stock: 50
- Coffee Maker (Appliances) - $79.99, Stock: 20

## 🔧 Future Enhancements

- Update/Edit existing products
- Delete products functionality
- File persistence (save/load)
- Advanced filtering and sorting
- Category management
- Inventory tracking features
