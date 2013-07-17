package org.cassiopeya.dao;

import org.cassiopeya.dao.mysql.MysqlImgDao;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 27.05.13
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
public class DaoImgFactory {
    public static ImgDao getImgDao() {
       return new MysqlImgDao();
        }
    }

