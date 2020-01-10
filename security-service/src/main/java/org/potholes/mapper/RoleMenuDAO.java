package org.potholes.mapper;

import java.util.List;

import org.potholes.model.RoleMenu;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuDAO {
    int insert(RoleMenu record);

    RoleMenu selectByPrimaryKey(String id);

    int updateByPrimaryKey(RoleMenu record);

    void deleteByRoleId(String roleId);

    int batchInsert(List<RoleMenu> roleMenus);

}