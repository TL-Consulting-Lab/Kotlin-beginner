package com.productmanagement.service

import com.productmanagement.model.Product
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ProductServiceTest {
    
    @Test
    fun `should initialize with sample products`() {
        // Given
        val productService = ProductService()
        
        // When
        val products = productService.getAllProducts()
        
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
        val productService = ProductService()
        
        // When
        val results = productService.searchProducts("iPhone")
        
        // Then
        assertTrue(results.isNotEmpty())
        assertTrue(results.any { it.name.contains("iPhone") })
    }
    
    @Test
    fun `should search products by category`() {
        // Given
        val productService = ProductService()
        
        // When
        val results = productService.searchProducts("Electronics")
        
        // Then
        assertTrue(results.isNotEmpty())
        assertTrue(results.all { it.category == "Electronics" })
    }
    
    @Test
    fun `should create new product successfully`() {
        // Given
        val productService = ProductService()
        val initialCount = productService.getAllProducts().size
        
        // When
        val result = productService.createProduct("New Product", "Description", 49.99, "New Category", 20)
        
        // Then
        assertTrue(result.isSuccess)
        
        val updatedProducts = productService.getAllProducts()
        assertEquals(initialCount + 1, updatedProducts.size)
        
        val createdProduct = result.getOrNull()
        assertNotNull(createdProduct)
        assertEquals("New Product", createdProduct.name)
        assertEquals(49.99, createdProduct.price)
    }

    @Test
    fun `should fail to create product with invalid data`() {
        // Given
        val productService = ProductService()
        
        // When
        val result = productService.createProduct("", "Description", 49.99, "Category", 20)
        
        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `should get product by ID`() {
        // Given
        val productService = ProductService()
        val products = productService.getAllProducts()
        val firstProduct = products.first()
        
        // When
        val foundProduct = productService.getProductById(firstProduct.id)
        
        // Then
        assertNotNull(foundProduct)
        assertEquals(firstProduct.id, foundProduct.id)
        assertEquals(firstProduct.name, foundProduct.name)
    }

    @Test
    fun `should return null for non-existent product ID`() {
        // Given
        val productService = ProductService()
        
        // When
        val foundProduct = productService.getProductById(999)
        
        // Then
        assertNull(foundProduct)
    }

    @Test
    fun `should update product stock`() {
        // Given
        val productService = ProductService()
        val products = productService.getAllProducts()
        val firstProduct = products.first()
        val newStock = 100
        
        // When
        val updated = productService.updateStock(firstProduct.id, newStock)
        
        // Then
        assertTrue(updated)
        val updatedProduct = productService.getProductById(firstProduct.id)
        assertNotNull(updatedProduct)
        assertEquals(newStock, updatedProduct.stockQuantity)
    }

    @Test
    fun `should delete product`() {
        // Given
        val productService = ProductService()
        val initialCount = productService.getAllProducts().size
        val products = productService.getAllProducts()
        val firstProduct = products.first()
        
        // When
        val deleted = productService.deleteProduct(firstProduct.id)
        
        // Then
        assertTrue(deleted)
        assertEquals(initialCount - 1, productService.getAllProducts().size)
        assertNull(productService.getProductById(firstProduct.id))
    }

    @Test
    fun `should get products by category`() {
        // Given
        val productService = ProductService()
        
        // When
        val electronicsProducts = productService.getProductsByCategory("Electronics")
        
        // Then
        assertTrue(electronicsProducts.isNotEmpty())
        assertTrue(electronicsProducts.all { it.category == "Electronics" })
    }

    @Test
    fun `should get inventory statistics`() {
        // Given
        val productService = ProductService()
        
        // When
        val stats = productService.getInventoryStats()
        
        // Then
        assertEquals(5, stats.totalProducts)
        assertTrue(stats.totalCategories > 0)
        assertTrue(stats.totalValue > 0.0)
    }

    @Test
    fun `should identify low stock products`() {
        // Given
        val productService = ProductService()
        
        // Create a low stock product
        productService.createProduct("Low Stock Item", "Description", 10.0, "Test", 5)
        
        // When
        val lowStockProducts = productService.getLowStockProducts()
        
        // Then
        assertTrue(lowStockProducts.isNotEmpty())
        assertTrue(lowStockProducts.all { it.isLowStock() })
    }

    @Test
    fun `should get all categories`() {
        // Given
        val productService = ProductService()
        
        // When
        val categories = productService.getAllCategories()
        
        // Then
        assertTrue(categories.contains("Electronics"))
        assertTrue(categories.contains("Computers"))
        assertTrue(categories.contains("Footwear"))
        assertTrue(categories.contains("Appliances"))
    }
}
