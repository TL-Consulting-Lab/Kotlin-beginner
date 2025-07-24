/**
 * Product Management System
 * Handles all product-related operations including viewing, searching, and creating products
 */
class ProductManager {
    private val products = mutableListOf<Product>()
    private var nextId = 1

    init {
        // Initialize with some sample products
        loadSampleProducts()
    }

    /**
     * Load sample products for demonstration
     */
    private fun loadSampleProducts() {
        products.addAll(
            listOf(
                Product(nextId++, "iPhone 15", "Latest Apple smartphone with advanced features", 999.99, "Electronics", 25),
                Product(nextId++, "Samsung Galaxy S24", "High-performance Android smartphone", 899.99, "Electronics", 30),
                Product(nextId++, "Dell XPS 13", "Ultra-thin laptop for professionals", 1199.99, "Computers", 15),
                Product(nextId++, "Nike Air Max", "Comfortable running shoes", 129.99, "Footwear", 50),
                Product(nextId++, "Coffee Maker", "Automatic drip coffee maker", 79.99, "Appliances", 20)
            )
        )
    }

    /**
     * View all products in the system
     */
    fun viewAllProducts() {
        println("\n" + "=".repeat(60))
        println("                    ALL PRODUCTS")
        println("=".repeat(60))
        
        if (products.isEmpty()) {
            println("No products available in the system.")
            return
        }

        products.forEachIndexed { index, product ->
            println("${index + 1}. $product")
        }
        
        println("Total Products: ${products.size}")
        println("=".repeat(60))
    }

    /**
     * Search for products by name or category
     */
    fun searchProduct(searchTerm: String) {
        println("\n" + "=".repeat(60))
        println("              SEARCH RESULTS")
        println("=".repeat(60))
        println("Search term: \"$searchTerm\"")
        println("-".repeat(60))

        val searchResults = products.filter { product ->
            product.name.contains(searchTerm, ignoreCase = true) ||
            product.category.contains(searchTerm, ignoreCase = true) ||
            product.description.contains(searchTerm, ignoreCase = true)
        }

        if (searchResults.isEmpty()) {
            println("No products found matching \"$searchTerm\"")
        } else {
            searchResults.forEachIndexed { index, product ->
                println("${index + 1}. $product")
            }
            println("Found ${searchResults.size} product(s)")
        }
        println("=".repeat(60))
    }

    /**
     * Create a new product
     */
    fun createProduct(name: String, description: String, price: Double, category: String, stockQuantity: Int): Product {
        val newProduct = Product(nextId++, name, description, price, category, stockQuantity)
        products.add(newProduct)
        
        println("\n" + "=".repeat(60))
        println("              PRODUCT CREATED")
        println("=".repeat(60))
        println("Product successfully created:")
        println(newProduct)
        println("=".repeat(60))
        
        return newProduct
    }

    /**
     * Get product by ID
     */
    fun getProductById(id: Int): Product? {
        return products.find { it.id == id }
    }

    /**
     * Get total number of products
     */
    fun getTotalProducts(): Int = products.size

    /**
     * Get products by category
     */
    fun getProductsByCategory(category: String): List<Product> {
        return products.filter { it.category.equals(category, ignoreCase = true) }
    }

    /**
     * Update an existing product with new details
     *
     * This method allows modification of any product field while maintaining data integrity.
     * It uses nullable parameters to allow selective updates - only non-null parameters
     * will be updated, keeping existing values for null parameters.
     *
     * @param id The unique product identifier to update (required)
     * @param name New product name (optional - null keeps existing value)
     * @param description New product description (optional - null keeps existing value)
     * @param price New product price (optional - null keeps existing value)
     * @param category New product category (optional - null keeps existing value)
     * @param stockQuantity New stock quantity (optional - null keeps existing value)
     *
     * @return Product? The updated product if successful, null if product not found
     *
     * @example
     * ```kotlin
     * // Update only price and stock
     * productManager.updateProduct(id = 1, price = 899.99, stockQuantity = 30)
     *
     * // Update all fields
     * productManager.updateProduct(
     *     id = 2,
     *     name = "Updated Phone",
     *     description = "New description",
     *     price = 799.99,
     *     category = "Electronics",
     *     stockQuantity = 25
     * )
     * ```
     */
    fun updateProduct(
        id: Int,
        name: String? = null,
        description: String? = null,
        price: Double? = null,
        category: String? = null,
        stockQuantity: Int? = null
    ): Product? {
        // Find the existing product by ID
        val existingProduct = getProductById(id)
        if (existingProduct == null) {
            println("\n" + "=".repeat(60))
            println("              PRODUCT NOT FOUND")
            println("=".repeat(60))
            println("❌ No product found with ID: $id")
            println("=".repeat(60))
            return null
        }

        // Create updated product with new values (or keep existing if null)
        val updatedProduct = Product(
            id = existingProduct.id,
            name = name ?: existingProduct.name,
            description = description ?: existingProduct.description,
            price = price ?: existingProduct.price,
            category = category ?: existingProduct.category,
            stockQuantity = stockQuantity ?: existingProduct.stockQuantity
        )

        // Find the index and replace the product in the list
        val productIndex = products.indexOfFirst { it.id == id }
        products[productIndex] = updatedProduct

        // Display success confirmation
        println("\n" + "=".repeat(60))
        println("              PRODUCT UPDATED")
        println("=".repeat(60))
        println("Product successfully updated:")
        println("Previous: ${existingProduct.name}")
        println("Updated:")
        println(updatedProduct)
        println("=".repeat(60))

        return updatedProduct
    }

    /**
     * Display all available products with their IDs for easy selection
     */
    fun viewProductsForSelection() {
        println("\n" + "=".repeat(60))
        println("              SELECT PRODUCT TO UPDATE")
        println("=".repeat(60))

        if (products.isEmpty()) {
            println("No products available in the system.")
            return
        }

        products.forEach { product ->
            println("ID: ${product.id} | ${product.name} | Category: ${product.category} | Price: $${String.format("%.2f", product.price)}")
        }

        println("-".repeat(60))
        println("Enter the ID of the product you want to update")
        println("=".repeat(60))
    }
}
