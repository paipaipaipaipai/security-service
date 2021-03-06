package org.potholes.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.potholes.api.Pagination;
import org.potholes.api.ResponseData;
import org.potholes.api.sys.UserInfo;
import org.potholes.api.sys.UserInfoReq;
import org.potholes.api.sys.UserSearchReq;
import org.potholes.service.UserService;
import org.potholes.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 用户管理
 */
@RequestMapping("system/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("allUsers")
    public ResponseData<Pagination<List<UserInfo>>> allUsers(HttpServletRequest request, HttpServletResponse response,
            @RequestBody UserSearchReq req) throws Exception {
        return ResponseUtils.buildSuccessResponse(userService.getUsers(req));
    }

    @RequestMapping("saveUser")
    public ResponseData<String> saveUser(HttpServletRequest request, HttpServletResponse response,
            @RequestBody UserInfoReq req) throws Exception {
        userService.saveUser(req);
        return ResponseUtils.buildSuccessResponse(null);
    }

    @RequestMapping("deleteUser")
    public ResponseData<String> deleteUser(HttpServletRequest request, HttpServletResponse response,
            @RequestBody UserInfoReq req) throws Exception {
        userService.deleteUser(req);
        return ResponseUtils.buildSuccessResponse(null);
    }

    @RequestMapping("resetPassword")
    public ResponseData<String> resetPassword(HttpServletRequest request, HttpServletResponse response,
            @RequestBody UserInfoReq req) throws Exception {
        userService.resetPassword(req);
        return ResponseUtils.buildSuccessResponse(null);
    }

    @RequestMapping("getUser")
    public ResponseData<UserInfo> getUser(HttpServletRequest request, HttpServletResponse response,
            @RequestBody UserInfoReq req) throws Exception {
        return ResponseUtils.buildSuccessResponse(userService.getUser(req));
    }

}
