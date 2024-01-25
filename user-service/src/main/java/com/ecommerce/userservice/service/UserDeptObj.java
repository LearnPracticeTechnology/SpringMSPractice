package com.ecommerce.userservice.service;

import com.ecommerce.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeptObj {
    private User user;
    private Department department;
}
