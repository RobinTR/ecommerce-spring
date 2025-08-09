package org.robn.ecommerce.category.port;

import org.robn.ecommerce.category.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryReadPort {

    List<Category> findAll();

    Optional<Category> findById(Long id);

}
