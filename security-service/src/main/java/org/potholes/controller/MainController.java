package org.potholes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.potholes.api.ResponseData;
import org.potholes.api.ResponseStatus;
import org.potholes.api.sys.RoleInfo;
import org.potholes.api.sys.SysMenu;
import org.potholes.service.MenuService;
import org.potholes.service.RoleService;
import org.potholes.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 登录即可用,不需要权限的接口
 */
@RestController
public class MainController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("checkLogin")
    public ResponseData<String> checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ResponseUtils.buildResponse(ResponseStatus.TOKEN_INVALID);
    }

    @RequestMapping("config/sysmenu")
    public ResponseData<List<SysMenu>> getSysmenu(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return ResponseUtils.buildSuccessResponse(menuService.getMenusByUser());
    }

    @RequestMapping("config/roles")
    public ResponseData<List<RoleInfo>> getAllRoles(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return ResponseUtils.buildSuccessResponse(roleService.getAllRoles());
    }

}
