package org.cassiopeya.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionDataBaseFactory {
    private   static DataSource pool = null;

    static {
        try {
            Context initContext = new InitialContext();
            Context xmlContext = (Context) initContext.lookup("java:comp/env");
            pool = (DataSource) xmlContext.lookup("jdbc/pool");
            if (pool==null) throw new NamingException("'pool' is an unknown DataSource");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
        public static Connection getConnection() throws SQLException {
            Connection conn = pool.getConnection();
            return conn;
        }


    }


