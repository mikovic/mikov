package org.cassiopeya.dao.mysql;

import org.cassiopeya.dao.ConnectionDataBaseFactory;
import org.cassiopeya.dao.InvestDao;
import org.cassiopeya.dto.Invest;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class MysqlInvestDao implements InvestDao {
    @Override

        public Invest createInvest( int userId, int ideaId, int investment, Date investDate) {

            Invest invest = null;
            ResultSet rs = null;
            Statement s = null;
            Connection con = null;
        java.sql.Date sqlDate=new java.sql.Date(investDate.getTime());

            try {
                con = ConnectionDataBaseFactory.getConnection();
                s = con.createStatement();

                String sql = "INSERT INTO invests (user_id, idea_id, investment, invest_date) " +
                        "VALUES ('" +userId  + "','" + ideaId + "','" + investment+"','" + sqlDate +"')";
                int i = s.executeUpdate(sql);
                if (i==1){
                    sql = "SELECT invest_id FROM invests WHERE user_id='" + userId +
                            "' ORDER BY invest_id DESC LIMIT 0,1";
                    rs = s.executeQuery(sql);
                    if (rs.next()) {
                        int investId = rs.getInt(1);
                        invest = new Invest(investId, userId, ideaId,  investment, investDate );
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (rs!=null )rs.close();
                    if (s!=null)s.close();
                    if (con!=null)con.close();
                }catch (SQLException e){
                }
            }
        return invest;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Invest getInvest(int userId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ArrayList getInvestsInIdea(int ideaId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int sumInvest(int ideaId) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int sumInvest = 0;
        try {
            con = ConnectionDataBaseFactory.getConnection();
            String sql = "SELECT SUM(investment) FROM invests WHERE idea_Id ='" + ideaId +"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()){
                sumInvest = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs!=null )rs.close();
                if (pst!=null)pst.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }
        return sumInvest;
    }

    @Override
    public int mySumInvestInIdea(int userId, int ideaId) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int myInvestInIdea = 0;
        try {
            con = ConnectionDataBaseFactory.getConnection();
            String sql = "SELECT SUM(investment) FROM invests WHERE user_id ='" + userId+"'" +
                    " AND idea_id ='" + ideaId +"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()){
              myInvestInIdea = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs!=null )rs.close();
                if (pst!=null)pst.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }
        return myInvestInIdea;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
