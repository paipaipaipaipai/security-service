package org.potholes.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.potholes.api.ResponseData;
import org.potholes.api.sys.RoleInfoReq;
import org.potholes.service.RoleService;
import org.potholes.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 角色管理
 */
@RequestMapping("system/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("createRole")
    public ResponseData<String> createRole(HttpServletRequest request, HttpServletResponse response,
            @RequestBody RoleInfoReq req) throws Exception {
        roleService.createRole(req);
        return ResponseUtils.buildSuccessResponse(null);
    }

    @RequestMapping("saveRole")
    public ResponseData<String> saveRole(HttpServletRequest request, HttpServletResponse response,
            @RequestBody RoleInfoReq req) throws Exception {
        roleService.saveRole(req);
        return ResponseUtils.buildSuccessResponse(null);
    }

    @RequestMapping("deleteRole")
    public ResponseData<String> deleteRole(HttpServletRequest request, HttpServletResponse response,
            @RequestBody RoleInfoReq req) throws Exception {
        roleService.deleteRole(req);
        return ResponseUtils.buildSuccessResponse(null);
    }

}
