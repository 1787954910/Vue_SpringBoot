package com.example.springboot2test.config;

import com.example.springboot2test.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//注册拦截器
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                //其他接口需token验证
                .addPathPatterns("/course/**")
                .addPathPatterns("/questions/**")
                .addPathPatterns("/grades/**")

                .addPathPatterns("/student/saveStudent/**")
                .addPathPatterns("/student/deleteStudent/**")
                .addPathPatterns("/student/findStudentById/**")
                .addPathPatterns("/student/findAllStudent")

                .addPathPatterns("/teacher/saveTeacher/**")
                .addPathPatterns("/teacher/deleteTeacher/**")
                .addPathPatterns("/teacher/findTeacherById/**")
                .addPathPatterns("/teacher/findAllTeacher")

                .addPathPatterns("/admin/saveAdmin/**")
                .addPathPatterns("/admin/deleteAdmin/**")
                .addPathPatterns("/admin/findAdminById/**")
                .addPathPatterns("/admin/findAllAdmin")

                .excludePathPatterns("/login");  //登录接口放行
    }
}
