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
                Product(nextId++, "Coffee Maker", "Automatic drip coffee maker", 79.99, "Appliances", 20),
            ),
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

        val searchResults =
            products.filter { product ->
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
    fun createProduct(
        name: String,
        description: String,
        price: Double,
        category: String,
        stockQuantity: Int,
    ): Product {
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
}
