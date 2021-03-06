package com.changhong.user.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.changhong.user.domain.DepartmentCategory;
import com.changhong.user.web.facade.dto.UserDTO;
import com.changhong.user.web.facade.dto.UserPasswordDTO;
import com.changhong.user.domain.User;
import com.changhong.user.repository.UserDao;
import com.changhong.user.web.facade.assember.UserWebAssember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Jack Wang
 * Date: 14-4-9
 * Time: 上午9:20
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        return userDao.findUserByName(username);
    }

    public UserDTO obtainUserById(int userId) {
        User user = (User) userDao.findById(userId, User.class);
        return UserWebAssember.toUserDTO(user);
    }


    public List<UserDTO> obtainAllUser(){
        List<User> users = userDao.loadAllUser();
        return UserWebAssember.toUserDTOList(users);
    }

    public List<UserDTO> obtainUsers(String name, int startPosition, int pageSize) {
        List<User> users = userDao.loadUsers(name, startPosition, pageSize);
        return UserWebAssember.toUserDTOList(users);
    }

    public void deleteUserById(int userId){
        User user = (User)userDao.findById(userId,User.class);
        userDao.delete(user);
    }

    public int obtainUserSize(String name) {
        return userDao.loadUserSize(name);
    }

    public boolean obtainUserExist(int id, String username) {
        return userDao.loadUserExist(id, username);
    }

    public void changeUserDetails(UserDTO userDTO) {
        User user = UserWebAssember.toUserDomain(userDTO);
        userDao.persist(user);
    }

    public void changeStatusForUser(int userId) {
        User user = (User) userDao.findById(userId, User.class);
        if("chappadmin".equals(user.getUsername())) {
            return;
        }

        if (user.isEnabled()) {
            user.setEnabled(false);
        } else {
            user.setEnabled(true);
        }
    }

    public UserPasswordDTO obtainUserPassword(int userId) {
        User user = (User) userDao.findById(userId, User.class);
        return UserWebAssember.toPasswordDTO(user);
    }

    public boolean obtainOldPasswordRight(int userId, String oldPassword) {
        User user = (User) userDao.findById(userId, User.class);
        return user.getPassword().equals(oldPassword) ? true : false;
    }

    public void changeUserPassword(int userId, String newPassword) {
        User user = (User) userDao.findById(userId, User.class);
        user.setPassword(newPassword);
    }


    public JSONArray obtainUserByDepartmentId(int departmentId){
        List<User> users = null;
        if(departmentId > 0){
             users = userDao.loadUserBydepartmentId(departmentId);
        }

        JSONArray array = new JSONArray();
        if(users != null){
            for (User user : users){
                JSONObject o = new JSONObject();
                o.put("id",user.getId());
                o.put("name",user.getUsername());
                o.put("employeeId",user.getEmployeeId());
                o.put("account",user.getAccount());
                o.put("position",user.getPosition().getName());
                o.put("email",user.getEmail());
                o.put("address",user.getAddress());
                o.put("parentID", user.getDepartment().getId());
                array.add(o);
            }
        }
        return array;
    }


}
