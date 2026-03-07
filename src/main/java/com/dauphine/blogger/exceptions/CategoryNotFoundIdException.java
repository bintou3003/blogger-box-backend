package com.dauphine.blogger.exceptions;

public class CategoryNotFoundIdException extends Exception {
    public CategoryNotFoundIdException(String id) {
        super("Category with id '" + id + "' not found.");
    }
}