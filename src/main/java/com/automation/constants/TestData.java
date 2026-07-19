package com.automation.constants;

public final class TestData {

    private TestData() {}

    //Valid Users
    public static final String STANDARD_USER = "standard_user";

    public static final String LOCKED_USER = "locked_out_user";

    public static final String PROBLEM_USER = "problem_user";

    public static final String PERFORMANCE_USER = "performance_glitch_user";

    //Password
    public static final String PASSWORD = "secret_sauce";

    //Invalid Users/Credentials
    public static final String INVALID_USERNAME = "invalid_user";

    public static final String INVALID_PASSWORD = "invalid_password";

    //Error Messages
    public static final String INVALID_LOGIN_ERROR =
            "Epic sadface: Username and password do not match any user in this service";

    public static final String LOCKED_USER_ERROR =
            "Epic sadface: Sorry, this user has been locked out.";

    //Inventory Data
    public static final String BACKPACK = "Sauce Labs Backpack";

    public static final String BIKE_LIGHT = "Sauce Labs Bike Light";

    public static final String FLEECE_JACKET = "Sauce Labs Fleece Jacket";


    // Product Prices
    public static final String BACKPACK_PRICE = "$29.99";

    public static final String BIKE_LIGHT_PRICE = "$9.99";

    public static final String FLEECE_JACKET_PRICE = "$49.99";

    //Checkout Page Data
    public static final String FIRST_NAME = "Mohit";
    public static final String LAST_NAME = "Singh";
    public static final String POSTAL_CODE = "110001";

    public static final String EMPTY_STRING = "";

    //checkout overiview test data

    public static final String PAYMENT_INFORMATION =
            "SauceCard #31337";

    public static final String SHIPPING_INFORMATION =
            "Free Pony Express Delivery!";

    //complet finish test data
    public static final String COMPLETE_HEADER =
            "Thank you for your order!";

    public static final String COMPLETE_MESSAGE =
            "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

}