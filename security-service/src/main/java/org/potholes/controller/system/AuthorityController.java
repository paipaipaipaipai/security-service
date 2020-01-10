package org.potholes.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.potholes.api.ResponseData;
import org.potholes.api.sys.AuthorityReq;
import org.potholes.api.sys.MenuTree;
import org.potholes.api.sys.RoleInfo;
import org.potholes.service.MenuService;
import org.potholes.service.RoleService;
import org.potholes.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 角色权限管理
 */
@RequestMapping("system/authority")
@RestController
public class AuthorityController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("roles")
    public ResponseData<List<RoleInfo>> getAllRoles(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return ResponseUtils.buildSuccessResponse(roleService.getAllRoles());
    }

    @RequestMapping("menuTree")
    public ResponseData<List<MenuTree>> menuTree(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return ResponseUtils.buildSuccessResponse(menuService.menuTree());
    }

    @RequestMapping("roleMenus")
    public ResponseData<List<String>> roleMenus(HttpServletRequest request, HttpServletResponse response,
            @RequestBody AuthorityReq req) throws Exception {
        return ResponseUtils.buildSuccessResponse(menuService.roleMenus(req));
    }

    @RequestMapping("saveAuthority")
    public ResponseData<String> saveAuthority(HttpServletRequest request, HttpServletResponse response,
            @RequestBody AuthorityReq req) throws Exception {
        menuService.saveAuthority(req);
        return ResponseUtils.buildSuccessResponse(null);
    }

}
