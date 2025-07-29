/**
 * Product Management System - Main Application
 * This is the main entry point for the product management system
 */

fun main() {
    val productManager = ProductManager()
    var running = true

    println("*".repeat(70))
    println("          WELCOME TO PRODUCT MANAGEMENT SYSTEM")
    println("*".repeat(70))

    while (running) {
        displayMenu()
        print("Enter your choice (1-4): ")
        
        when (readLine()?.toIntOrNull()) {
            1 -> productManager.viewAllProducts()
            2 -> handleSearchProduct(productManager)
            3 -> handleCreateProduct(productManager)
            4 -> {
                running = false
                println("\n" + "=".repeat(60))
                println("Thank you for using Product Management System!")
                println("Goodbye!")
                println("=".repeat(60))
            }
            else -> {
                println("\n❌ Invalid choice! Please enter a number between 1-4.")
            }
        }
        
        if (running) {
            println("\nPress Enter to continue...")
            readLine()
        }
    }
}

/**
 * Display the main menu
 */
fun displayMenu() {
    println("\n" + "=".repeat(60))
    println("                    MAIN MENU")
    println("=".repeat(60))
    println("1. 📋 View All Products")
    println("2. 🔍 Search Product")
    println("3. ➕ Create Product")
    println("4. 🚪 Exit")
    println("=".repeat(60))
}

/**
 * Handle search product functionality
 */
fun handleSearchProduct(productManager: ProductManager) {
    println("\n" + "-".repeat(60))
    println("              SEARCH PRODUCT")
    println("-".repeat(60))
    print("Enter search term (name, category, or description): ")
    
    val searchTerm = readLine()
    if (searchTerm.isNullOrBlank()) {
        println("❌ Search term cannot be empty!")
        return
    }
    
    productManager.searchProduct(searchTerm.trim())
}

/**
 * Handle create product functionality
 */
fun handleCreateProduct(productManager: ProductManager) {
    println("\n" + "-".repeat(60))
    println("              CREATE NEW PRODUCT")
    println("-".repeat(60))
    
    try {
        print("Enter product name: ")
        val name = readLine()
        if (name.isNullOrBlank()) {
            println("❌ Product name cannot be empty!")
            return
        }

        print("Enter product description: ")
        val description = readLine()
        if (description.isNullOrBlank()) {
            println("❌ Product description cannot be empty!")
            return
        }

        print("Enter product price: $")
        val priceInput = readLine()
        val price = priceInput?.toDoubleOrNull()
        if (price == null || price < 0) {
            println("❌ Please enter a valid positive price!")
            return
        }

        print("Enter product category: ")
        val category = readLine()
        if (category.isNullOrBlank()) {
            println("❌ Product category cannot be empty!")
            return
        }

        print("Enter stock quantity: ")
        val stockInput = readLine()
        val stockQuantity = stockInput?.toIntOrNull()
        if (stockQuantity == null || stockQuantity < 0) {
            println("❌ Please enter a valid positive stock quantity!")
            return
        }

        productManager.createProduct(
            name = name.trim(),
            description = description.trim(),
            price = price,
            category = category.trim(),
            stockQuantity = stockQuantity
        )

    } catch (e: Exception) {
        println("❌ Error creating product: ${e.message}")
    }
}