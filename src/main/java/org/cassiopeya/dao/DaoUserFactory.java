package org.cassiopeya.dao;

import org.cassiopeya.dao.mysql.MysqlUserDao;


public class DaoUserFactory {
    public static UserDao getUserDao() {
     return new MysqlUserDao();
    }
}
