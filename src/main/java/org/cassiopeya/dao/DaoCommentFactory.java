package org.cassiopeya.dao;

import org.cassiopeya.dao.mysql.MysqlCommentDao;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 27.05.13
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
public class DaoCommentFactory {
    public static CommentDao getCommentDao(){
       return new MysqlCommentDao();
    }
}
