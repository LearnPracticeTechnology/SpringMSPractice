package com.ecommerce.userservice.service;

import com.ecommerce.userservice.controller.UserController;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    private static final Logger log
            = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    public User findByUserId(Long userId){
        return userRepository.findByUserId(userId);
    }
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public UserDeptObj getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        UserDeptObj vo = new UserDeptObj();
        User user = userRepository.findByUserId(userId);

        Department department =
                restTemplate.getForObject("http://dept-service/departments/" + user.getDepartmentId()
                        ,Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return  vo;
    }
}
