package org.cassiopeya.dao;

import org.cassiopeya.dto.User;
import sun.font.CreatedFontTracker;

public interface UserDao {
    boolean isUserByLogin (String login);
    User createUser (String login, String password, String email);
    User regUser (String login, String password);

}
