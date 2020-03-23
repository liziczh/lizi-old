package com.liziczh.erp.service.impl;

import com.liziczh.erp.dto.request.EmailDTO;
import com.liziczh.erp.dto.request.ManagedUserDTO;
import com.liziczh.erp.dto.request.PasswordDTO;
import com.liziczh.erp.dto.request.UserDTO;
import com.liziczh.erp.entity.User;
import com.liziczh.erp.entity.UserRole;
import com.liziczh.erp.repository.UserRepository;
import com.liziczh.erp.repository.UserRoleRepository;
import com.liziczh.erp.service.MailService;
import com.liziczh.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Lizi
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private MailService mailService;

    @Override
    public User loginByUsername(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        System.out.println(user);
        return user;
    }

    @Override
    public Page<User> pagingQueryUserList(Integer roleId, String search, Integer currentPage, Integer limit) {
        // 按创建时间逆序
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(currentPage - 1, limit, sort);
        Page<User> userPage = null;
        if (roleId.equals(0)) {
            userPage = userRepository.findByNameContaining(search, pageable);
        } else {
            userPage = userRepository.findByRole_IdAndNameContaining(roleId, search, pageable);
        }
        return userPage;
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<UserRole> findAllUserRole() {
        return userRoleRepository.findAll();
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findOneByEmailIgnoreCase(email);
    }

    @Override
    public User addUser(ManagedUserDTO managedUserDTO) {
        User user = new User();
        user.setUsername(managedUserDTO.getUsername());
        user.setPassword(managedUserDTO.getPassword());
        user.setName(managedUserDTO.getName());
        user.setEmail(managedUserDTO.getEmail());
        user.setPhone(managedUserDTO.getPhone());
        user.setRole(managedUserDTO.getRole());
        user.setCreateTime(new Date());
        User addUser = userRepository.save(user);
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setUsername(managedUserDTO.getUsername());
        emailDTO.setPassword(managedUserDTO.getPassword());
        emailDTO.setEmail(managedUserDTO.getEmail());
        mailService.sendRegister(emailDTO);
        return addUser;
    }

    @Override
    public User modifyUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).get();
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setRole(userDTO.getRole());
        user.setUpdateTime(new Date());
        User modifyUser = userRepository.save(user);
        return modifyUser;
    }

    @Override
    public User modifyUserPassword(PasswordDTO passwordDTO) {
        User user = userRepository.findOneByUsername(passwordDTO.getUsername()).get();
        user.setPassword(passwordDTO.getPassword());
        userRepository.save(user);
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setUsername(user.getUsername());
        emailDTO.setPassword(user.getPassword());
        emailDTO.setEmail(user.getEmail());
        mailService.sendResetPassword(emailDTO);
        return user;
    }

    @Override
    public void removeUser(long userId) {
        userRepository.deleteById(userId);
    }

}
