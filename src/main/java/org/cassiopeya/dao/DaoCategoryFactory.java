package org.cassiopeya.dao;

import org.cassiopeya.dao.mysql.MysqlCategoryDao;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 18.02.13
 * Time: 16:10
 * To change this template use File | Settings | File Templates.
 */
public class DaoCategoryFactory {
    public static CategoryDao getCategoryDao() {
        return new MysqlCategoryDao();
 }
}
