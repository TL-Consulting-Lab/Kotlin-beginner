package com.productmanagement

import com.productmanagement.service.ProductService
import com.productmanagement.ui.ConsoleUI

/**
 * Main entry point for the Product Management System
 * 
 * This application provides a console-based interface for managing products,
 * including viewing, searching, creating, and organizing products by categories.
 * 
 * @author Product Management Team
 * @version 1.0.0
 */
fun main() {
    try {
        // Initialize the service layer
        val productService = ProductService()
        
        // Initialize the user interface
        val consoleUI = ConsoleUI(productService)
        
        // Start the application
        consoleUI.start()
        
    } catch (e: Exception) {
        println("❌ Fatal error: ${e.message}")
        println("The application will now exit.")
        e.printStackTrace()
    }
}
