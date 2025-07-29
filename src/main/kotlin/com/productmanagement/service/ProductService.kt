package com.productmanagement.service

import com.productmanagement.model.Product

/**
 * Service class for managing products
 * Handles business logic for product operations
 */
class ProductService {
    private val products = mutableListOf<Product>()
    private var nextId = 1

    init {
        loadSampleProducts()
    }

    /**
     * Load sample products for demonstration
     */
    private fun loadSampleProducts() {
        val sampleProducts = listOf(
            createProductInternal("iPhone 15", "Latest Apple smartphone with advanced features", 999.99, "Electronics", 25),
            createProductInternal("Samsung Galaxy S24", "High-performance Android smartphone", 899.99, "Electronics", 30),
            createProductInternal("Dell XPS 13", "Ultra-thin laptop for professionals", 1199.99, "Computers", 15),
            createProductInternal("Nike Air Max", "Comfortable running shoes", 129.99, "Footwear", 50),
            createProductInternal("Coffee Maker", "Automatic drip coffee maker", 79.99, "Appliances", 20)
        )
        products.addAll(sampleProducts)
    }

    /**
     * Internal method to create products without validation messages
     */
    private fun createProductInternal(name: String, description: String, price: Double, category: String, stockQuantity: Int): Product {
        return Product(nextId++, name, description, price, category, stockQuantity)
    }

    /**
     * Get all products
     */
    fun getAllProducts(): List<Product> = products.toList()

    /**
     * Search for products by term
     */
    fun searchProducts(searchTerm: String): List<Product> {
        if (searchTerm.isBlank()) return emptyList()
        
        return products.filter { it.matchesSearchTerm(searchTerm) }
    }

    /**
     * Create a new product
     */
    fun createProduct(name: String, description: String, price: Double, category: String, stockQuantity: Int): Result<Product> {
        return try {
            val product = Product(nextId++, name.trim(), description.trim(), price, category.trim(), stockQuantity)
            products.add(product)
            Result.success(product)
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        }
    }

    /**
     * Get product by ID
     */
    fun getProductById(id: Int): Product? = products.find { it.id == id }

    /**
     * Update product stock
     */
    fun updateStock(productId: Int, newStock: Int): Boolean {
        val product = getProductById(productId) ?: return false
        val index = products.indexOf(product)
        if (index == -1) return false
        
        val updatedProduct = product.copy(stockQuantity = newStock)
        products[index] = updatedProduct
        return true
    }

    /**
     * Delete product by ID
     */
    fun deleteProduct(id: Int): Boolean {
        return products.removeIf { it.id == id }
    }

    /**
     * Get products by category
     */
    fun getProductsByCategory(category: String): List<Product> {
        return products.filter { it.category.equals(category, ignoreCase = true) }
    }

    /**
     * Get total number of products
     */
    fun getTotalProducts(): Int = products.size

    /**
     * Get products that are low in stock
     */
    fun getLowStockProducts(): List<Product> = products.filter { it.isLowStock() }

    /**
     * Get products that are out of stock
     */
    fun getOutOfStockProducts(): List<Product> = products.filter { !it.isInStock() }

    /**
     * Get all categories
     */
    fun getAllCategories(): Set<String> = products.map { it.category }.toSet()

    /**
     * Get total inventory value
     */
    fun getTotalInventoryValue(): Double = products.sumOf { it.price * it.stockQuantity }

    /**
     * Get statistics about the inventory
     */
    fun getInventoryStats(): InventoryStats {
        return InventoryStats(
            totalProducts = products.size,
            totalCategories = getAllCategories().size,
            totalValue = getTotalInventoryValue(),
            lowStockCount = getLowStockProducts().size,
            outOfStockCount = getOutOfStockProducts().size
        )
    }
}

/**
 * Data class for inventory statistics
 */
data class InventoryStats(
    val totalProducts: Int,
    val totalCategories: Int,
    val totalValue: Double,
    val lowStockCount: Int,
    val outOfStockCount: Int
)
