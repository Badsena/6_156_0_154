package com.example.demo;

import com.example.demo.SimpleConsoleTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Test Case 2: Verify Function/Method Names Exist
 * This test verifies that all required methods exist in the classes
 */
@Listeners(SimpleConsoleTestListener.class)
public class FunctionNameVerificationTest {

    @Test(priority = 1, description = "Verify ProductController methods exist")
    public void testProductControllerMethodsExist() {
        try {
            Class<?> clazz = Class.forName("com.example.demo.controller.ProductController");
            Method[] methods = clazz.getDeclaredMethods();
            List<String> methodNames = Arrays.stream(methods)
                    .map(Method::getName)
                    .collect(Collectors.toList());

            // Verify required methods exist
            Assert.assertTrue(methodNames.contains("createProduct"), 
                "createProduct method should exist");
            Assert.assertTrue(methodNames.contains("getAllProducts"), 
                "getAllProducts method should exist");

            // System.out.println("✓ ProductController methods verified:");
            methodNames.forEach(name -> System.out.println("  - " + name));
        } catch (ClassNotFoundException e) {
            Assert.fail("ProductController class not found: " + e.getMessage());
        }
    }
}

