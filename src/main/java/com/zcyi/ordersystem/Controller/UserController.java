package com.zcyi.ordersystem.Controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.zcyi.ordersystem.Base.ApiResult;
import com.zcyi.ordersystem.Entity.User;
import com.zcyi.ordersystem.JWT.JwtUtils;
import com.zcyi.ordersystem.Service.ServiceImpl.UserServiceImpl;
import com.zcyi.ordersystem.Util.Constant;
import org.springframework.objenesis.ObjenesisHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ZaoYi
 */
@RestController
@ResponseBody
@RequestMapping("/User")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/Login")
    @ResponseBody
    public ApiResult<User> userLogin(@RequestBody User user) {
        System.out.println("用户名：" + user.getUserName() + "\b密码：" + user.getUserPassword());
        if (user.getUserName() == null || user.getUserPassword() == null) {
            return ApiResult.failed("参数有误");
        }
        if (!user.getUserName().isEmpty() || !user.getUserPassword().isEmpty()) {

            User login = userService.login(user.getUserName(), user.getUserPassword());
            if (login != null) {
                try {
                    if (login.getUserToken() == null ||
                            JwtUtils.verify(login.getUserToken()).getExpiresAt().before(Calendar.getInstance().getTime())) {
                        login.setUserToken(createTokenByUserNameAndUserId(login.getUserName(), login.getUserId()));
                        userService.updateByUserId(login.getUserId(), login.getUserToken());
                    }
                } catch (TokenExpiredException e) {
                    e.printStackTrace();
                    login.setUserToken(createTokenByUserNameAndUserId(login.getUserName(), login.getUserId()));
                    userService.updateByUserId(login.getUserId(), login.getUserToken());
                }
                return ApiResult.success(login);
            }
            return ApiResult.failed("用户名或密码错误");
        } else {
            return ApiResult.failed("用户名或密码为空");
        }
    }

    @PostMapping("/Register")
    @ResponseBody
    public ApiResult<User> userRegister(@RequestBody User user) {
        System.out.println(user.getUserName() + "--------" + user.getUserPassword() + "----------" + user.getUserPhone());
        if (user.getUserName() == null || user.getUserPassword() == null || user.getUserPhone() == null) {
            return ApiResult.failed("参数有误");
        }
        if (!user.getUserName().isEmpty() || !user.getUserPassword().isEmpty() | !user.getUserPhone().isEmpty()) {
            if (userService.checkUsername(user.getUserName()) == null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                user.setUserCreateTime(format.format(date));
                int i = userService.addUser(user);
                if (i == 1) {
                    return ApiResult.success("注册成功");
                } else {
                    return ApiResult.success("注册失败");
                }
            } else {
                return ApiResult.failed("用户名已存在");
            }

        } else {
            return ApiResult.failed("参数有误");
        }
    }

    private String createTokenByUserNameAndUserId(String userName, Long userId) {
        Map<String, String> tokenInfo = new HashMap<>(5);
        tokenInfo.put("userName", userName);
        tokenInfo.put("userId", userId + "");
        return JwtUtils.getToken(tokenInfo);
    }

    @GetMapping("/AllUserByPage")
    @ResponseBody
    public ApiResult<Map<String, Object>> allUser(int page, int count) {
        return ApiResult.success(userService.selectAllUserByPage(((page - 1) * count), count));
    }

    @RequestMapping("/AddUser")
    @ResponseBody
    public ApiResult<String> addUser(User user) {

        if (userService.addUser(user) != 0) {
            return ApiResult.success();
        } else {
            return ApiResult.failed();
        }

    }

    @RequestMapping("/UpdateUserInfo")
    @ResponseBody
    public ApiResult<String> updateUserInfo(User user) {
        userService.updateUserInfo(user);
        return ApiResult.success("更新成功");
    }

    @RequestMapping("/UpdateAvatar")
    @ResponseBody
    public ApiResult<String> updateAvatar(int userId, MultipartFile avatar) {
        if (avatar.isEmpty()) {
            return ApiResult.failed("参数有误");
        }
        String imgUrl = userId + "/" + avatar.getOriginalFilename();
        String path = Constant.USER_AVATAR_URL + imgUrl;
        System.out.println(imgUrl + "-----------" + path);
        File file1 = new File(path);
        if (!file1.exists()) {
            file1.getParentFile().mkdir();
            try {
                //创建文件
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return ApiResult.failed("上传失败");
            }
        }
        try {
            avatar.transferTo(file1);
            userService.updateAvatar(imgUrl, userId);
            return ApiResult.success("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResult.failed("上传失败");
        }
    }

    @RequestMapping("/DeleteUserInfo")
    @ResponseBody
    public ApiResult<String> deleteUserInfo(int userId) {
        if (userService.deleteUserInfo(userId) == 1) {
            return ApiResult.success("删除成功");
        } else {
            return ApiResult.success("删除失败");
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public ApiResult<String> test() {
            return ApiResult.success("test");
    }
}
