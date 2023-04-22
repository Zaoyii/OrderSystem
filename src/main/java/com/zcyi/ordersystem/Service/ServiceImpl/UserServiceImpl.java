package com.zcyi.ordersystem.Service.ServiceImpl;


import com.zcyi.ordersystem.Dao.UserDao;
import com.zcyi.ordersystem.Entity.User;
import com.zcyi.ordersystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Service
 *
 * @author ZaoYi
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User login(String username, String userPassword) {
        return userDao.selectByUserNamePassword(username, userPassword);
    }

    @Override
    public Map<String, Object> selectAllUserByPage(int page, int count) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("Users", userDao.selectAllUserByPage(page, count));
        map.put("AllUserCount", userDao.selectUserCount());
        return map;
    }

    @Override
    public int updateByUserId(Long userId, String userToken) {
        return userDao.updateByUserId(userId, userToken);
    }

    @Override
    public int updateAvatar(String imgUrl, int userId) {
        return userDao.updateAvatar(imgUrl, userId);
    }

    @Override
    public int updateUserInfo(User user) {
        return userDao.updateUserInfo(user);
    }

    @Override
    public User checkUsername(String username) {
        return userDao.selectByUserName(username);
    }

    @Override
    public int deleteUserInfo(int userId) {
        return userDao.deleteUserInfo(userId);
    }
}
