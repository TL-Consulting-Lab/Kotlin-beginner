import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ProductTest {
    @Test
    fun testProductCreation() {
        val product =
            Product(
                id = 1,
                name = "Test Product",
                description = "Test Description",
                price = 99.99,
                category = "Test Category",
                stockQuantity = 10,
            )

        assertEquals(1, product.id)
        assertEquals("Test Product", product.name)
        assertEquals("Test Description", product.description)
        assertEquals(99.99, product.price)
        assertEquals("Test Category", product.category)
        assertEquals(10, product.stockQuantity)
    }

    @Test
    fun testProductToString() {
        val product =
            Product(
                id = 1,
                name = "Test Product",
                description = "Test Description",
                price = 99.99,
                category = "Test Category",
                stockQuantity = 10,
            )

        val toString = product.toString()
        assertTrue(toString.contains("Product ID: 1"))
        assertTrue(toString.contains("Name: Test Product"))
        assertTrue(toString.contains("Price: $99.99"))
        assertTrue(toString.contains("Category: Test Category"))
        assertTrue(toString.contains("Stock Quantity: 10"))
    }
}
