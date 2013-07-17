package org.cassiopeya.dao;

import org.cassiopeya.dao.mysql.MysqlCategoryDao;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class DaoCategoryFactory {
    public static CategoryDao getCategoryDao() {
                return new MysqlCategoryDao();
 }
}
