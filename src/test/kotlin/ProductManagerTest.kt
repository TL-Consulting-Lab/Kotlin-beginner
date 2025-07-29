import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertNotNull

class ProductManagerTest {
    
    @Test
    fun `should initialize with sample products`() {
        // Given
        val productManager = ProductManager()
        
        // When
        val products = productManager.getAllProducts()
        
        // Then
        assertEquals(5, products.size)
        
        // Verify some sample products
        val iPhone = products.find { it.name == "iPhone 15" }
        assertNotNull(iPhone)
        assertEquals("Electronics", iPhone.category)
        assertEquals(999.99, iPhone.price)
    }
    
    @Test
    fun `should search products by name`() {
        // Given
        val productManager = ProductManager()
        
        // When
        val results = productManager.searchProducts("iPhone")
        
        // Then
        assertTrue(results.isNotEmpty())
        assertTrue(results.any { it.name.contains("iPhone") })
    }
    
    @Test
    fun `should search products by category`() {
        // Given
        val productManager = ProductManager()
        
        // When
        val results = productManager.searchProducts("Electronics")
        
        // Then
        assertTrue(results.isNotEmpty())
        assertTrue(results.all { it.category == "Electronics" })
    }
    
    @Test
    fun `should create new product`() {
        // Given
        val productManager = ProductManager()
        val initialCount = productManager.getAllProducts().size
        
        // When
        productManager.createProduct("New Product", "Description", 49.99, "New Category", 20)
        
        // Then
        val updatedProducts = productManager.getAllProducts()
        assertEquals(initialCount + 1, updatedProducts.size)
        
        val newProduct = updatedProducts.last()
        assertEquals("New Product", newProduct.name)
        assertEquals(49.99, newProduct.price)
    }
}
