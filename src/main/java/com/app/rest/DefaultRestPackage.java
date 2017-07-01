package com.app.rest;

public final class DefaultRestPackage {
    private DefaultRestPackage() {
    }
    
    public static final String name = DefaultRestPackage.class.getPackage().getName();
}
