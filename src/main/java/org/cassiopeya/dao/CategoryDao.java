package org.cassiopeya.dao;

import org.cassiopeya.dto.Category;
import java.util.Map;

public interface CategoryDao {
    Map getCategories();
    Category createCategory(String categoryName);
    Category getCategory(int categoryId);
}
