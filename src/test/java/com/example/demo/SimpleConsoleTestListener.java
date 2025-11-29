package com.example.demo;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Simple console logger that prints each TestNG method name with its status.
 */
public class SimpleConsoleTestListener implements ITestListener {

    private void log(String status, ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        System.out.println(String.format("%s: %s", methodName, status));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log("PASS", result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log("FAIL", result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log("SKIPPED", result);
    }

    // Unused hooks retained for completeness
    @Override
    public void onTestStart(ITestResult result) { }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    @Override
    public void onStart(ITestContext context) { }

    @Override
    public void onFinish(ITestContext context) { }
}

