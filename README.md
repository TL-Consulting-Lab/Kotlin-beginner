# Product Management System

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

### Console Application (Kotlin)

1. Make sure you have Kotlin installed on your system
2. Navigate to the project directory
3. Compile and run the application:

```bash
# Using the provided script
./run.sh

# Or compile manually
kotlinc kotlin/*.kt -include-runtime -d ProductManagementSystem.jar
java -jar ProductManagementSystem.jar
```

Alternatively, if you have Kotlin script runner:

```bash
# Run directly with Kotlin
kotlin MainKt
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
