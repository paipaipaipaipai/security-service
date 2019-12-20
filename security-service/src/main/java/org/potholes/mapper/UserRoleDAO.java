package org.potholes.mapper;

import org.potholes.model.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDAO {
    int insert(UserRole record);

    UserRole selectByPrimaryKey(String id);

    int updateByPrimaryKey(UserRole record);

}