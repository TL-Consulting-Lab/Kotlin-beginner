package com.productmanagement.ui

import com.productmanagement.model.Product
import com.productmanagement.service.ProductService

/**
 * Console-based user interface for the Product Management System
 */
class ConsoleUI(private val productService: ProductService) {
    
    /**
     * Start the console application
     */
    fun start() {
        var running = true
        
        displayWelcome()
        
        while (running) {
            displayMenu()
            print("Enter your choice (1-6): ")
            
            when (readLine()?.toIntOrNull()) {
                1 -> viewAllProducts()
                2 -> searchProducts()
                3 -> createProduct()
                4 -> viewInventoryStats()
                5 -> viewProductsByCategory()
                6 -> {
                    running = false
                    displayGoodbye()
                }
                else -> displayError("Invalid choice! Please enter a number between 1-6.")
            }
            
            if (running) {
                waitForUser()
            }
        }
    }

    private fun displayWelcome() {
        println("*".repeat(70))
        println("          WELCOME TO PRODUCT MANAGEMENT SYSTEM")
        println("*".repeat(70))
    }

    private fun displayMenu() {
        println("\n" + "=".repeat(60))
        println("                    MAIN MENU")
        println("=".repeat(60))
        println("1. 📋 View All Products")
        println("2. 🔍 Search Products")
        println("3. ➕ Create Product")
        println("4. 📊 View Inventory Statistics")
        println("5. 📂 View Products by Category")
        println("6. 🚪 Exit")
        println("=".repeat(60))
    }

    private fun viewAllProducts() {
        val products = productService.getAllProducts()
        
        displaySectionHeader("ALL PRODUCTS")
        
        if (products.isEmpty()) {
            println("No products available in the system.")
            return
        }

        products.forEachIndexed { index, product ->
            println("${index + 1}. ${product.toCompactString()}")
        }
        
        println("\nTotal Products: ${products.size}")
        displaySectionFooter()
    }

    private fun searchProducts() {
        displaySectionHeader("SEARCH PRODUCTS")
        print("Enter search term (name, category, or description): ")
        
        val searchTerm = readLine()
        if (searchTerm.isNullOrBlank()) {
            displayError("Search term cannot be empty!")
            return
        }

        val results = productService.searchProducts(searchTerm.trim())
        
        println("\nSearch term: \"${searchTerm.trim()}\"")
        println("-".repeat(60))

        if (results.isEmpty()) {
            println("No products found matching \"${searchTerm.trim()}\"")
        } else {
            results.forEachIndexed { index, product ->
                println("${index + 1}. ${product.toCompactString()}")
            }
            println("\nFound ${results.size} product(s)")
        }
        displaySectionFooter()
    }

    private fun createProduct() {
        displaySectionHeader("CREATE NEW PRODUCT")
        
        try {
            print("Enter product name: ")
            val name = readLine()
            if (name.isNullOrBlank()) {
                displayError("Product name cannot be empty!")
                return
            }

            print("Enter product description: ")
            val description = readLine()
            if (description.isNullOrBlank()) {
                displayError("Product description cannot be empty!")
                return
            }

            print("Enter product price: $")
            val priceInput = readLine()
            val price = priceInput?.toDoubleOrNull()
            if (price == null || price < 0) {
                displayError("Please enter a valid positive price!")
                return
            }

            print("Enter product category: ")
            val category = readLine()
            if (category.isNullOrBlank()) {
                displayError("Product category cannot be empty!")
                return
            }

            print("Enter stock quantity: ")
            val stockInput = readLine()
            val stockQuantity = stockInput?.toIntOrNull()
            if (stockQuantity == null || stockQuantity < 0) {
                displayError("Please enter a valid positive stock quantity!")
                return
            }

            val result = productService.createProduct(name, description, price, category, stockQuantity)
            
            result.fold(
                onSuccess = { product ->
                    displaySuccess("Product created successfully!")
                    println(product)
                },
                onFailure = { error ->
                    displayError("Error creating product: ${error.message}")
                }
            )

        } catch (e: Exception) {
            displayError("Unexpected error: ${e.message}")
        }
        displaySectionFooter()
    }

    private fun viewInventoryStats() {
        val stats = productService.getInventoryStats()
        
        displaySectionHeader("INVENTORY STATISTICS")
        
        println("📦 Total Products: ${stats.totalProducts}")
        println("📂 Total Categories: ${stats.totalCategories}")
        println("💰 Total Inventory Value: $${String.format("%.2f", stats.totalValue)}")
        println("⚠️  Low Stock Products: ${stats.lowStockCount}")
        println("❌ Out of Stock Products: ${stats.outOfStockCount}")
        
        // Show low stock products if any
        val lowStockProducts = productService.getLowStockProducts()
        if (lowStockProducts.isNotEmpty()) {
            println("\n⚠️  LOW STOCK ALERT:")
            lowStockProducts.forEach { product ->
                println("   • ${product.name} (${product.stockQuantity} remaining)")
            }
        }
        
        displaySectionFooter()
    }

    private fun viewProductsByCategory() {
        val categories = productService.getAllCategories()
        
        if (categories.isEmpty()) {
            displayError("No categories available!")
            return
        }

        displaySectionHeader("PRODUCTS BY CATEGORY")
        
        categories.forEachIndexed { index, category ->
            println("${index + 1}. $category")
        }
        
        print("\nSelect category (1-${categories.size}): ")
        val choice = readLine()?.toIntOrNull()
        
        if (choice == null || choice < 1 || choice > categories.size) {
            displayError("Invalid category selection!")
            return
        }
        
        val selectedCategory = categories.elementAt(choice - 1)
        val products = productService.getProductsByCategory(selectedCategory)
        
        println("\n📂 Products in '$selectedCategory' category:")
        println("-".repeat(60))
        
        products.forEachIndexed { index, product ->
            println("${index + 1}. ${product.toCompactString()}")
        }
        
        println("\nTotal products in this category: ${products.size}")
        displaySectionFooter()
    }

    private fun displaySectionHeader(title: String) {
        println("\n" + "=".repeat(60))
        println("                    $title")
        println("=".repeat(60))
    }

    private fun displaySectionFooter() {
        println("=".repeat(60))
    }

    private fun displaySuccess(message: String) {
        println("✅ $message")
    }

    private fun displayError(message: String) {
        println("❌ $message")
    }

    private fun displayGoodbye() {
        println("\n" + "=".repeat(60))
        println("Thank you for using Product Management System!")
        println("Goodbye!")
        println("=".repeat(60))
    }

    private fun waitForUser() {
        println("\nPress Enter to continue...")
        readLine()
    }
}
