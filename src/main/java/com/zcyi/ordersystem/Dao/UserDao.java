package com.zcyi.ordersystem.Dao;


import com.zcyi.ordersystem.Entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ZaoYi
 * 用户Dao层
 */
@Mapper
@Repository
public interface UserDao {

    /**
     * 分页查询用户
     *
     * @param page 查询页数
     * @return 用户列表
     */

    @Select("select * from t_user LIMIT #{page}, #{count}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userCreateTime", column = "user_create_time"),
            @Result(property = "userAvatar", column = "user_avatar"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "userPhone", column = "user_phone"),
            @Result(property = "userSlogan", column = "user_slogan"),
            @Result(property = "userSlogan", column = "user_slogan"),
    })
    ArrayList<User> selectAllUserByPage(int page, int count);


    /**
     * 添加用户
     *
     * @param user 参数
     * @return 1成功 0失败
     */
    @Insert("insert into t_user (user_name,user_password,user_phone,user_create_time) " +
            "value (#{userName},#{userPassword},#{userPhone},#{userCreateTime}) ")
    int addUser(User user);

    /**
     * 登录实现
     *
     * @param username     用户名
     * @param userPassword 密码
     * @return 用户对象
     */
    @Select("select * from t_user where user_name = #{username} and user_password = #{userPassword}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPhone", column = "user_phone"),
            @Result(property = "userAvatar", column = "user_avatar"),
            @Result(property = "userSlogan", column = "user_slogan"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "userRole", column = "user_role"),
    })
    User selectByUserNamePassword(String username, String userPassword);

    /**
     * count
     * @return count
     */
    @Select("select count(user_id) from t_user")
    int selectUserCount();

    /**
     * 登录实现
     *
     * @param username 用户名
     * @return 用户对象
     */
    @Select("select * from t_user where user_name = #{username}")
    @Results({
            @Result(property = "userId", column = "user_id"),
    })
    User selectByUserName(String username);

    /**
     * 更新用户token
     *
     * @param userId    id
     * @param userToken token
     * @return 1/0
     */
    @Update("update t_user set user_token=#{userToken} where user_id= #{userId}")
    int updateByUserId(Long userId, String userToken);


    /**
     * 更新头像
     *
     * @param imgUrl url
     * @param userId id
     * @return 1/0
     */
    @Update("update t_user set user_avatar=#{imgUrl} where user_id= #{userId}")
    int updateAvatar(String imgUrl, int userId);

    /**
     * 更新用户信息
     *
     * @param user 参数
     * @return 1/0
     */
    @Update("update t_user set user_name= #{username},user_password=#{userPassword},user_email=#{userEmail},user_phone=#{userPhone} where user_id= #{userId}")
    int updateUserInfo(User user);

    /**
     * 删除用户信息
     *
     * @param userId
     * @return
     */
    @Delete("delete from t_user where user_id = #{userId}")
    int deleteUserInfo(int userId);
}
