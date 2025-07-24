import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ProductManagerTest {
    private lateinit var productManager: ProductManager

    @BeforeTest
    fun setUp() {
        productManager = ProductManager()
    }

    @Test
    fun testSampleProductsLoaded() {
        assertTrue(productManager.getTotalProducts() > 0, "Sample products should be loaded")
    }

    @Test
    fun testCreateProduct() {
        val initialCount = productManager.getTotalProducts()

        val product =
            productManager.createProduct(
                name = "Test Product",
                description = "Test Description",
                price = 99.99,
                category = "Test Category",
                stockQuantity = 10,
            )

        assertNotNull(product)
        assertEquals("Test Product", product.name)
        assertEquals(initialCount + 1, productManager.getTotalProducts())
    }

    @Test
    fun testGetProductById() {
        val product =
            productManager.createProduct(
                name = "Test Product",
                description = "Test Description",
                price = 99.99,
                category = "Test Category",
                stockQuantity = 10,
            )

        val foundProduct = productManager.getProductById(product.id)
        assertNotNull(foundProduct)
        assertEquals(product.name, foundProduct.name)
    }

    @Test
    fun testGetProductByIdNotFound() {
        val product = productManager.getProductById(999)
        assertNull(product)
    }

    @Test
    fun testGetProductsByCategory() {
        productManager.createProduct("Test1", "Desc1", 10.0, "Electronics", 5)
        productManager.createProduct("Test2", "Desc2", 20.0, "Electronics", 3)
        productManager.createProduct("Test3", "Desc3", 30.0, "Books", 2)

        val electronicsProducts = productManager.getProductsByCategory("Electronics")
        assertTrue(electronicsProducts.size >= 2, "Should find at least 2 Electronics products")

        val booksProducts = productManager.getProductsByCategory("Books")
        assertTrue(booksProducts.size >= 1, "Should find at least 1 Books product")
    }
}
