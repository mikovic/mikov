package org.cassiopeya.dao;

import org.cassiopeya.dao.mysql.MysqlUserDao;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 13.02.13
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */
public class DaoUserFactory {
    public static UserDao getUserDao() {
     return new MysqlUserDao();
    }
}
