package com.productmanagement.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertFailsWith

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

    @Test
    fun `should format compact string correctly`() {
        // Given
        val product = Product(1, "iPhone", "Smartphone", 999.99, "Electronics", 25)
        
        // When
        val compact = product.toCompactString()
        
        // Then
        assertTrue(compact.contains("iPhone"))
        assertTrue(compact.contains("Electronics"))
        assertTrue(compact.contains("999.99"))
        assertTrue(compact.contains("25"))
    }

    @Test
    fun `should match search terms correctly`() {
        // Given
        val product = Product(1, "iPhone 15", "Latest Apple smartphone", 999.99, "Electronics", 25)
        
        // Then
        assertTrue(product.matchesSearchTerm("iPhone"))
        assertTrue(product.matchesSearchTerm("apple"))
        assertTrue(product.matchesSearchTerm("Electronics"))
        assertTrue(product.matchesSearchTerm("smartphone"))
        assertFalse(product.matchesSearchTerm("Samsung"))
    }

    @Test
    fun `should detect stock status correctly`() {
        // Given
        val inStockProduct = Product(1, "Product A", "Description", 10.0, "Category", 15)
        val lowStockProduct = Product(2, "Product B", "Description", 10.0, "Category", 5)
        val outOfStockProduct = Product(3, "Product C", "Description", 10.0, "Category", 0)
        
        // Then
        assertTrue(inStockProduct.isInStock())
        assertFalse(inStockProduct.isLowStock())
        
        assertTrue(lowStockProduct.isInStock())
        assertTrue(lowStockProduct.isLowStock())
        
        assertFalse(outOfStockProduct.isInStock())
        assertFalse(outOfStockProduct.isLowStock()) // 0 is not considered low stock, it's out of stock
    }

    @Test
    fun `should validate product creation`() {
        // Test invalid ID
        assertFailsWith<IllegalArgumentException> {
            Product(0, "Product", "Description", 10.0, "Category", 5)
        }
        
        // Test blank name
        assertFailsWith<IllegalArgumentException> {
            Product(1, "", "Description", 10.0, "Category", 5)
        }
        
        // Test negative price
        assertFailsWith<IllegalArgumentException> {
            Product(1, "Product", "Description", -10.0, "Category", 5)
        }
        
        // Test negative stock
        assertFailsWith<IllegalArgumentException> {
            Product(1, "Product", "Description", 10.0, "Category", -1)
        }
    }
}
