package org.potholes.mapper;

import java.util.List;

import org.potholes.api.sys.RoleInfo;
import org.potholes.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO {
    int insert(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKey(Role record);

    List<Role> selectByMenuId(String menuId);

    List<RoleInfo> selectAllRoles();

    Role selectByRoleName(String roleName);

    void deleteByRoleId(String roleId);

}