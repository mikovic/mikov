package org.cassiopeya.dao;

import org.cassiopeya.dao.mysql.MysqlIdeaDao;

public class DaoIdeaFactory {
    public static IdeaDao getIdeaDao() {
        return new MysqlIdeaDao();
  }
}
