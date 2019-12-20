package org.potholes.mapper;

import org.potholes.model.RoleMenu;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuDAO {
    int insert(RoleMenu record);

    RoleMenu selectByPrimaryKey(String id);

    int updateByPrimaryKey(RoleMenu record);

}