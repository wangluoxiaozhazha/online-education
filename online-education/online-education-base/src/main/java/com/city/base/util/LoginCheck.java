package com.city.base.util;


import com.city.base.entity.Admin;
import com.city.base.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
public class LoginCheck {

    @Autowired
    AdminMapper adminMapper;
    @Bean
    public LoginCheck getLoginCheck(){
        return new LoginCheck();
    }

    public boolean loginCheck(HttpServletRequest request, Admin admin){

   /*     if (CodeUtil.checkVerifyCode(request)) {
            if(admin.getPassword().equals(adminMapper.selectAdmin(admin.getAdminName()).getPassword())){
                return true;
            }
        }*/
            return true;
    }
}
