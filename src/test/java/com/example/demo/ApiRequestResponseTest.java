package com.example.demo;

import com.example.demo.controller.ProductController;
import com.example.demo.SimpleConsoleTestListener;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Test Case 3: Verify API Request and Response Codes
 * This test verifies that API endpoints return correct response codes
 */
@Listeners(SimpleConsoleTestListener.class)
public class ApiRequestResponseTest {

    private ProductController productController;
    private ProductRepository productRepository;

    @BeforeMethod
    public void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productController = new ProductController(productRepository);
    }

    @Test(priority = 1, description = "Test POST API - Create Product returns 201 CREATED")
    public void testCreateProductReturns201() {
        // Arrange
        Product product = new Product("Test Product", 99.99);
        Product savedProduct = new Product("Test Product", 99.99);
        savedProduct.setId(1L);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // Act
        ResponseEntity<Product> response = productController.createProduct(product);

        // Assert
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED, 
            "Response status should be 201 CREATED");
        Assert.assertNotNull(response.getBody(), "Response body should not be null");
        Assert.assertEquals(response.getBody().getName(), "Test Product", 
            "Product name should match");
        Assert.assertEquals(response.getBody().getPrice(), 99.99, 
            "Product price should match");

        // System.out.println("✓ POST API returns 201 CREATED status code");
    }

    @Test(priority = 2, description = "Test GET API - Get All Products returns 200 OK")
    public void testGetAllProductsReturns200() {
        // Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", 10.0));
        products.add(new Product("Product 2", 20.0));

        when(productRepository.findAll()).thenReturn(products);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Assert
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK, 
            "Response status should be 200 OK");
        Assert.assertNotNull(response.getBody(), "Response body should not be null");
        Assert.assertEquals(response.getBody().size(), 2, 
            "Should return 2 products");

        // System.out.println("✓ GET API returns 200 OK status code");
    }

    @Test(priority = 3, description = "Test GET API - Empty list returns 200 OK")
    public void testGetAllProductsEmptyListReturns200() {
        // Arrange
        List<Product> emptyList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(emptyList);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Assert
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK, 
            "Response status should be 200 OK even for empty list");
        Assert.assertNotNull(response.getBody(), "Response body should not be null");
        Assert.assertEquals(response.getBody().size(), 0, 
            "Should return empty list");

        // System.out.println("✓ GET API returns 200 OK for empty list");
    }

    @Test(priority = 4, description = "Test API Request Body Validation")
    public void testApiRequestBodyValidation() {
        // Arrange
        Product product = new Product("Valid Product", 50.0);
        Product savedProduct = new Product("Valid Product", 50.0);
        savedProduct.setId(1L);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // Act
        ResponseEntity<Product> response = productController.createProduct(product);

        // Assert
        Assert.assertNotNull(response.getBody(), "Response body should not be null");
        Assert.assertNotNull(response.getBody().getName(), "Product name should not be null");
        Assert.assertTrue(response.getBody().getPrice() >= 0, 
            "Product price should be non-negative");

        // System.out.println("✓ API request body validation passed");
    }
}

