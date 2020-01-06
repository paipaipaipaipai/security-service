package org.potholes.mapper;

import java.util.List;

import org.potholes.model.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDAO {
    int insert(UserRole record);

    UserRole selectByPrimaryKey(String id);

    int updateByPrimaryKey(UserRole record);

    int batchInsert(List<UserRole> roles);

    void deleteByUserId(String userId);

}