package org.potholes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.potholes.api.sys.RoleInfo;
import org.potholes.api.sys.SysUser;
import org.potholes.api.sys.UserInfo;
import org.potholes.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    int insert(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKey(User record);

    SysUser selectByUserName(String userName);

    Integer countUserByKeywords(@Param("keywords") String keywords, @Param("adminUserId") String adminUserId);

    List<UserInfo> selectUserByKeywords(@Param("keywords") String keywords, @Param("adminUserId") String adminUserId,
            RowBounds rowBounds);

    void deleteUserById(String userId);

    List<RoleInfo> selectRolesByUserId(String userId);

    User selectUserByUserName(String userName);

}