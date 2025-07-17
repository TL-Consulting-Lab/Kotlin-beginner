/**
 * Data class representing a Product in the management system
 */
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val stockQuantity: Int
) {
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
}
