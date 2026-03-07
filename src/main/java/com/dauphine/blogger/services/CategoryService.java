package com.dauphine.blogger.services;

import ch.qos.logback.core.status.StatusListenerAsList;
import com.dauphine.blogger.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> getAll();

    Category getById(UUID id);

    Category create(String name);

    Category updateName(UUID id, String name);

    boolean deletedById(UUID id);
}
