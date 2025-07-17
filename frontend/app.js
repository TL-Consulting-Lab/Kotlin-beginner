// Product Management System - Frontend JavaScript
class ProductManagementSystem {
    constructor() {
        this.products = [];
        this.nextId = 1;
        this.currentSection = 'dashboard';
        
        this.initializeApp();
        this.loadSampleData();
        this.bindEvents();
        this.updateDashboard();
    }

    initializeApp() {
        console.log('🚀 Product Management System initialized');
    }

    loadSampleData() {
        // Load sample products (matching the Kotlin backend)
        const sampleProducts = [
            {
                id: this.nextId++,
                name: "iPhone 15",
                description: "Latest Apple smartphone with advanced features",
                price: 999.99,
                category: "Electronics",
                stockQuantity: 25
            },
            {
                id: this.nextId++,
                name: "Samsung Galaxy S24",
                description: "High-performance Android smartphone",
                price: 899.99,
                category: "Electronics",
                stockQuantity: 30
            },
            {
                id: this.nextId++,
                name: "Dell XPS 13",
                description: "Ultra-thin laptop for professionals",
                price: 1199.99,
                category: "Computers",
                stockQuantity: 15
            },
            {
                id: this.nextId++,
                name: "Nike Air Max",
                description: "Comfortable running shoes",
                price: 129.99,
                category: "Footwear",
                stockQuantity: 50
            },
            {
                id: this.nextId++,
                name: "Coffee Maker",
                description: "Automatic drip coffee maker",
                price: 79.99,
                category: "Appliances",
                stockQuantity: 20
            }
        ];

        this.products = sampleProducts;
        console.log('📦 Sample data loaded:', this.products.length, 'products');
    }

    bindEvents() {
        // Navigation events
        document.querySelectorAll('.nav-btn').forEach(btn => {
            btn.addEventListener('click', (e) => {
                const section = e.target.dataset.section;
                this.showSection(section);
            });
        });

        // Search events
        const searchBtn = document.getElementById('search-btn');
        const searchInput = document.getElementById('search-input');
        
        searchBtn.addEventListener('click', () => this.performSearch());
        searchInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                this.performSearch();
            }
        });

        // Form events
        const productForm = document.getElementById('product-form');
        const resetBtn = document.getElementById('reset-form');
        
        productForm.addEventListener('submit', (e) => this.handleCreateProduct(e));
        resetBtn.addEventListener('click', () => this.resetForm());

        // Filter events
        const categoryFilter = document.getElementById('category-filter');
        const sortFilter = document.getElementById('sort-filter');
        
        categoryFilter.addEventListener('change', () => this.applyFilters());
        sortFilter.addEventListener('change', () => this.applyFilters());
    }

    showSection(sectionName) {
        // Update navigation
        document.querySelectorAll('.nav-btn').forEach(btn => {
            btn.classList.remove('active');
        });
        document.querySelector(`[data-section="${sectionName}"]`).classList.add('active');

        // Update content
        document.querySelectorAll('.content-section').forEach(section => {
            section.classList.remove('active');
        });
        document.getElementById(sectionName).classList.add('active');

        this.currentSection = sectionName;

        // Load section-specific content
        switch (sectionName) {
            case 'dashboard':
                this.updateDashboard();
                break;
            case 'view-products':
                this.displayAllProducts();
                this.populateCategoryFilter();
                break;
            case 'search-products':
                this.displaySearchResults([]);
                break;
            case 'create-product':
                this.resetForm();
                break;
        }
    }

    updateDashboard() {
        // Update statistics
        const totalProducts = this.products.length;
        const categories = [...new Set(this.products.map(p => p.category))];
        const totalValue = this.products.reduce((sum, p) => sum + (p.price * p.stockQuantity), 0);
        const totalStock = this.products.reduce((sum, p) => sum + p.stockQuantity, 0);

        document.getElementById('total-products').textContent = totalProducts;
        document.getElementById('total-categories').textContent = categories.length;
        document.getElementById('total-value').textContent = `$${totalValue.toLocaleString()}`;
        document.getElementById('total-stock').textContent = totalStock.toLocaleString();

        // Display recent products (last 3)
        const recentProducts = this.products.slice(-3).reverse();
        this.displayProducts(recentProducts, 'recent-products-list');
    }

    displayAllProducts() {
        this.displayProducts(this.products, 'all-products-list');
    }

    displayProducts(products, containerId) {
        const container = document.getElementById(containerId);
        
        if (products.length === 0) {
            container.innerHTML = `
                <div class="empty-state">
                    <i class="fas fa-box-open"></i>
                    <h3>No products found</h3>
                    <p>There are no products to display at the moment.</p>
                </div>
            `;
            return;
        }

        container.innerHTML = products.map(product => `
            <div class="product-card">
                <div class="product-header">
                    <div class="product-id">ID: ${product.id}</div>
                </div>
                <div class="product-name">${product.name}</div>
                <div class="product-description">${product.description}</div>
                <div class="product-details">
                    <div class="detail-item">
                        <i class="fas fa-dollar-sign"></i>
                        <span class="product-price">$${product.price.toFixed(2)}</span>
                    </div>
                    <div class="detail-item">
                        <i class="fas fa-folder"></i>
                        <span class="product-category">${product.category}</span>
                    </div>
                    <div class="detail-item">
                        <i class="fas fa-warehouse"></i>
                        <span class="product-stock">${product.stockQuantity} units</span>
                    </div>
                </div>
            </div>
        `).join('');
    }

    populateCategoryFilter() {
        const categoryFilter = document.getElementById('category-filter');
        const categories = [...new Set(this.products.map(p => p.category))].sort();
        
        categoryFilter.innerHTML = '<option value="">All Categories</option>' +
            categories.map(cat => `<option value="${cat}">${cat}</option>`).join('');
    }

    applyFilters() {
        const categoryFilter = document.getElementById('category-filter').value;
        const sortFilter = document.getElementById('sort-filter').value;
        
        let filteredProducts = [...this.products];

        // Apply category filter
        if (categoryFilter) {
            filteredProducts = filteredProducts.filter(p => p.category === categoryFilter);
        }

        // Apply sorting
        filteredProducts.sort((a, b) => {
            switch (sortFilter) {
                case 'name':
                    return a.name.localeCompare(b.name);
                case 'price':
                    return a.price - b.price;
                case 'category':
                    return a.category.localeCompare(b.category);
                case 'stock':
                    return b.stockQuantity - a.stockQuantity;
                default:
                    return 0;
            }
        });

        this.displayProducts(filteredProducts, 'all-products-list');
    }

    performSearch() {
        const searchTerm = document.getElementById('search-input').value.trim().toLowerCase();
        
        if (!searchTerm) {
            this.showToast('Please enter a search term', 'warning');
            return;
        }

        const searchResults = this.products.filter(product => 
            product.name.toLowerCase().includes(searchTerm) ||
            product.category.toLowerCase().includes(searchTerm) ||
            product.description.toLowerCase().includes(searchTerm)
        );

        this.displaySearchResults(searchResults, searchTerm);
    }

    displaySearchResults(results, searchTerm = '') {
        const container = document.getElementById('search-results');
        
        if (searchTerm && results.length === 0) {
            container.innerHTML = `
                <div class="empty-state">
                    <i class="fas fa-search"></i>
                    <h3>No results found</h3>
                    <p>No products match your search term "${searchTerm}"</p>
                </div>
            `;
        } else if (searchTerm) {
            container.innerHTML = `
                <div style="margin-bottom: 20px; padding: 15px; background: #e6fffa; border-radius: 10px; border-left: 5px solid #38a169;">
                    <strong>Search Results for "${searchTerm}":</strong> ${results.length} product(s) found
                </div>
                ${results.map(product => `
                    <div class="product-card">
                        <div class="product-header">
                            <div class="product-id">ID: ${product.id}</div>
                        </div>
                        <div class="product-name">${product.name}</div>
                        <div class="product-description">${product.description}</div>
                        <div class="product-details">
                            <div class="detail-item">
                                <i class="fas fa-dollar-sign"></i>
                                <span class="product-price">$${product.price.toFixed(2)}</span>
                            </div>
                            <div class="detail-item">
                                <i class="fas fa-folder"></i>
                                <span class="product-category">${product.category}</span>
                            </div>
                            <div class="detail-item">
                                <i class="fas fa-warehouse"></i>
                                <span class="product-stock">${product.stockQuantity} units</span>
                            </div>
                        </div>
                    </div>
                `).join('')}
            `;
        } else {
            container.innerHTML = `
                <div class="empty-state">
                    <i class="fas fa-search"></i>
                    <h3>Enter a search term</h3>
                    <p>Type in the search box above to find products by name, category, or description.</p>
                </div>
            `;
        }
    }

    handleCreateProduct(e) {
        e.preventDefault();

        const formData = {
            name: document.getElementById('product-name').value.trim(),
            description: document.getElementById('product-description').value.trim(),
            price: parseFloat(document.getElementById('product-price').value),
            category: document.getElementById('product-category').value.trim(),
            stockQuantity: parseInt(document.getElementById('product-stock').value)
        };

        // Validation
        if (!this.validateProductData(formData)) {
            return;
        }

        // Create product
        const newProduct = {
            id: this.nextId++,
            ...formData
        };

        this.products.push(newProduct);
        
        this.showToast(`Product "${newProduct.name}" created successfully!`, 'success');
        this.resetForm();
        
        // Update dashboard if currently viewing it
        if (this.currentSection === 'dashboard') {
            this.updateDashboard();
        }

        console.log('✅ Product created:', newProduct);
    }

    validateProductData(data) {
        if (!data.name) {
            this.showToast('Product name is required', 'error');
            return false;
        }

        if (!data.description) {
            this.showToast('Product description is required', 'error');
            return false;
        }

        if (isNaN(data.price) || data.price < 0) {
            this.showToast('Please enter a valid positive price', 'error');
            return false;
        }

        if (!data.category) {
            this.showToast('Product category is required', 'error');
            return false;
        }

        if (isNaN(data.stockQuantity) || data.stockQuantity < 0) {
            this.showToast('Please enter a valid positive stock quantity', 'error');
            return false;
        }

        return true;
    }

    resetForm() {
        document.getElementById('product-form').reset();
        this.showToast('Form reset successfully', 'success');
    }

    showToast(message, type = 'success') {
        const toastContainer = document.getElementById('toast-container');
        const toastId = 'toast-' + Date.now();
        
        const iconMap = {
            success: 'fas fa-check-circle',
            error: 'fas fa-exclamation-circle',
            warning: 'fas fa-exclamation-triangle'
        };

        const toast = document.createElement('div');
        toast.className = `toast ${type}`;
        toast.id = toastId;
        toast.innerHTML = `
            <i class="${iconMap[type]}"></i>
            <span>${message}</span>
        `;

        toastContainer.appendChild(toast);

        // Auto remove after 3 seconds
        setTimeout(() => {
            const toastElement = document.getElementById(toastId);
            if (toastElement) {
                toastElement.style.animation = 'slideOut 0.3s ease forwards';
                setTimeout(() => {
                    toastElement.remove();
                }, 300);
            }
        }, 3000);
    }

    // API methods for potential backend integration
    getAllProducts() {
        return [...this.products];
    }

    getProductById(id) {
        return this.products.find(p => p.id === id);
    }

    searchProducts(term) {
        return this.products.filter(product => 
            product.name.toLowerCase().includes(term.toLowerCase()) ||
            product.category.toLowerCase().includes(term.toLowerCase()) ||
            product.description.toLowerCase().includes(term.toLowerCase())
        );
    }

    createProduct(productData) {
        const newProduct = {
            id: this.nextId++,
            ...productData
        };
        this.products.push(newProduct);
        return newProduct;
    }

    getTotalProducts() {
        return this.products.length;
    }

    getProductsByCategory(category) {
        return this.products.filter(p => p.category.toLowerCase() === category.toLowerCase());
    }

    getCategories() {
        return [...new Set(this.products.map(p => p.category))];
    }

    getTotalValue() {
        return this.products.reduce((sum, p) => sum + (p.price * p.stockQuantity), 0);
    }

    getTotalStock() {
        return this.products.reduce((sum, p) => sum + p.stockQuantity, 0);
    }
}

// Add slideOut animation for toasts
const style = document.createElement('style');
style.textContent = `
    @keyframes slideOut {
        from {
            transform: translateX(0);
            opacity: 1;
        }
        to {
            transform: translateX(100%);
            opacity: 0;
        }
    }
`;
document.head.appendChild(style);

// Initialize the application when DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    window.productSystem = new ProductManagementSystem();
    console.log('🎉 Product Management System ready!');
});
