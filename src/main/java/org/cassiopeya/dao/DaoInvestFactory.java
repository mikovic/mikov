package org.cassiopeya.dao;

import org.cassiopeya.dao.mysql.MysqlCommentDao;
import org.cassiopeya.dao.mysql.MysqlInvestDao;


public class DaoInvestFactory {
    public static InvestDao getInvestDao(){
        return new MysqlInvestDao();
    }
}
