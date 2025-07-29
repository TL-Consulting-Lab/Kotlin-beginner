import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ProductTest {
    
    @Test
    fun `should create product with correct properties`() {
        // Given
        val product = Product(
            id = 1,
            name = "Test Product",
            description = "A test product",
            price = 29.99,
            category = "Test Category",
            stockQuantity = 10
        )
        
        // Then
        assertEquals(1, product.id)
        assertEquals("Test Product", product.name)
        assertEquals("A test product", product.description)
        assertEquals(29.99, product.price)
        assertEquals("Test Category", product.category)
        assertEquals(10, product.stockQuantity)
    }
    
    @Test
    fun `should format product display correctly`() {
        // Given
        val product = Product(1, "Test Product", "Description", 29.99, "Category", 5)
        
        // When
        val display = product.toString()
        
        // Then
        assertTrue(display.contains("Test Product"))
        assertTrue(display.contains("$29.99"))
        assertTrue(display.contains("Category"))
    }
}
