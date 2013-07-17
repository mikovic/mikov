package org.cassiopeya.dao;

import org.cassiopeya.dto.Invest;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 06.06.13
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */
public interface InvestDao {
    Invest createInvest ( int userId, int ideaId, int investment, Date investDate );
    Invest getInvest(int userId);
    ArrayList getInvestsInIdea (int ideaId);
    int sumInvest (int ideaId);
    int mySumInvestInIdea (int  userId, int ideaId);
}
