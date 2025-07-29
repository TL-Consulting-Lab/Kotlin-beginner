package com.productmanagement.model

/**
 * Data class representing a Product in the management system
 * 
 * @property id Unique identifier for the product
 * @property name Name of the product
 * @property description Detailed description of the product
 * @property price Price of the product in USD
 * @property category Category the product belongs to
 * @property stockQuantity Available quantity in stock
 */
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val stockQuantity: Int
) {
    init {
        require(id > 0) { "Product ID must be positive" }
        require(name.isNotBlank()) { "Product name cannot be blank" }
        require(description.isNotBlank()) { "Product description cannot be blank" }
        require(price >= 0.0) { "Product price cannot be negative" }
        require(category.isNotBlank()) { "Product category cannot be blank" }
        require(stockQuantity >= 0) { "Stock quantity cannot be negative" }
    }

    /**
     * Formats the product information for display
     */
    override fun toString(): String {
        return """
            Product ID: $id
            Name: $name
            Description: $description
            Price: $${String.format("%.2f", price)}
            Category: $category
            Stock Quantity: $stockQuantity
            ${"-".repeat(50)}
        """.trimIndent()
    }

    /**
     * Returns a compact string representation for lists
     */
    fun toCompactString(): String {
        return "$name ($category) - $${String.format("%.2f", price)} (Stock: $stockQuantity)"
    }

    /**
     * Checks if the product matches a search term
     */
    fun matchesSearchTerm(searchTerm: String): Boolean {
        val term = searchTerm.lowercase()
        return name.lowercase().contains(term) ||
                description.lowercase().contains(term) ||
                category.lowercase().contains(term)
    }

    /**
     * Checks if the product is in stock
     */
    fun isInStock(): Boolean = stockQuantity > 0

    /**
     * Checks if the product is low in stock (less than 10 items)
     */
    fun isLowStock(): Boolean = stockQuantity < 10
}
