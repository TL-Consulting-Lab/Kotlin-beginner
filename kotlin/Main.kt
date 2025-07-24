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
        print("Enter your choice (1-5): ")

        when (readLine()?.toIntOrNull()) {
            1 -> productManager.viewAllProducts()
            2 -> handleSearchProduct(productManager)
            3 -> handleCreateProduct(productManager)
            4 -> handleUpdateProduct(productManager)
            5 -> {
                running = false
                println("\n" + "=".repeat(60))
                println("Thank you for using Product Management System!")
                println("Goodbye!")
                println("=".repeat(60))
            }
            else -> {
                println("\n❌ Invalid choice! Please enter a number between 1-5.")
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
    println("4. ✏️ Update Product")
    println("5. 🚪 Exit")
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

/**
 * Handle update product functionality
 */
fun handleUpdateProduct(productManager: ProductManager) {
    println("\n" + "-".repeat(60))
    println("              UPDATE PRODUCT")
    println("-".repeat(60))

    // First, show available products for selection
    productManager.viewProductsForSelection()

    print("Enter the ID of the product to update: ")
    val idInput = readLine()
    val productId = idInput?.toIntOrNull()

    if (productId == null) {
        println("❌ Invalid product ID!")
        return
    }

    // Check if product exists
    val existingProduct = productManager.getProductById(productId)
    if (existingProduct == null) {
        println("❌ Product with ID $productId not found!")
        return
    }

    println("\nCurrent product details:")
    println(existingProduct)
    println("\nEnter new values (press Enter to keep current value):")

    try {
        // Collect update information
        print("New product name [${existingProduct.name}]: ")
        val nameInput = readLine()
        val newName = if (nameInput.isNullOrBlank()) null else nameInput.trim()

        print("New description [${existingProduct.description}]: ")
        val descriptionInput = readLine()
        val newDescription = if (descriptionInput.isNullOrBlank()) null else descriptionInput.trim()

        print("New price [$${String.format("%.2f", existingProduct.price)}]: $")
        val priceInput = readLine()
        val newPrice = if (priceInput.isNullOrBlank()) {
            null
        } else {
            val price = priceInput.toDoubleOrNull()
            if (price == null || price < 0) {
                println("⚠️ Invalid price format! Keeping current price.")
                null
            } else {
                price
            }
        }

        print("New category [${existingProduct.category}]: ")
        val categoryInput = readLine()
        val newCategory = if (categoryInput.isNullOrBlank()) null else categoryInput.trim()

        print("New stock quantity [${existingProduct.stockQuantity}]: ")
        val stockInput = readLine()
        val newStockQuantity = if (stockInput.isNullOrBlank()) {
            null
        } else {
            val stock = stockInput.toIntOrNull()
            if (stock == null || stock < 0) {
                println("⚠️ Invalid stock quantity! Keeping current stock.")
                null
            } else {
                stock
            }
        }

        // Perform the update
        val updatedProduct = productManager.updateProduct(
            id = productId,
            name = newName,
            description = newDescription,
            price = newPrice,
            category = newCategory,
            stockQuantity = newStockQuantity
        )

        if (updatedProduct != null) {
            println("✅ Product updated successfully!")
        }

    } catch (e: Exception) {
        println("❌ Error updating product: ${e.message}")
    }
}
