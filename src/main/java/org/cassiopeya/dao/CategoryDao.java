package org.cassiopeya.dao;

import org.cassiopeya.dto.Category;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 16.02.13
 * Time: 20:39
 * To change this template use File | Settings | File Templates.
 */
public interface CategoryDao {
    Map categories();
    Category createCategory(String category);
}
