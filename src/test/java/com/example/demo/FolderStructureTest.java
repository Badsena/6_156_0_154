package com.example.demo;

import com.example.demo.SimpleConsoleTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Test Case 4: Verify Folder Structure
 * This test verifies that the project has the correct folder structure
 */
@Listeners(SimpleConsoleTestListener.class)
public class FolderStructureTest {

    private static final String PROJECT_ROOT = "src/main/java/com/example/demo";
    private static final String TEST_ROOT = "src/test/java/com/example/demo";
    private static final String RESOURCES_ROOT = "src/main/resources";

    @Test(priority = 1, description = "Verify controller folder structure exists")
    public void testControllerFolderExists() {
        Path controllerPath = Paths.get(PROJECT_ROOT, "controller");
        File controllerDir = controllerPath.toFile();
        
        Assert.assertTrue(controllerDir.exists(), 
            "Controller folder should exist at: " + controllerPath);
        Assert.assertTrue(controllerDir.isDirectory(), 
            "Controller should be a directory");
        
        // System.out.println("✓ Controller folder structure verified: " + controllerPath);
    }

    @Test(priority = 2, description = "Verify model folder structure exists")
    public void testModelFolderExists() {
        Path modelPath = Paths.get(PROJECT_ROOT, "model");
        File modelDir = modelPath.toFile();
        
        Assert.assertTrue(modelDir.exists(), 
            "Model folder should exist at: " + modelPath);
        Assert.assertTrue(modelDir.isDirectory(), 
            "Model should be a directory");
        
        // System.out.println("✓ Model folder structure verified: " + modelPath);
    }

}

