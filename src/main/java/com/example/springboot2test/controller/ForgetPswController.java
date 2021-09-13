package com.example.springboot2test.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.springboot2test.entity.*;
import com.example.springboot2test.service.AdminServiceImpl;
import com.example.springboot2test.service.StudentServiceImpl;
import com.example.springboot2test.service.TeacherServiceImpl;
import com.example.springboot2test.util.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping
public class ForgetPswController {

    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/forgetPsw")
    public Map<String, Object> forgetPsw(@RequestBody ForgetPsw forgetPsw, HttpSession session) {

        System.out.println(forgetPsw.getUsername());
        System.out.println(forgetPsw.getPhone());
        System.out.println(forgetPsw.getCode());

        Map<String, Object> map = new HashMap<>();
        Admin admin = (Admin) adminService.loadUserByUsername(forgetPsw.getUsername());
        Teacher teacher = (Teacher) teacherService.loadUserByUsername(forgetPsw.getUsername());
        Student student = (Student) studentService.loadUserByUsername(forgetPsw.getUsername());
        if (admin != null) {
            if (!forgetPsw.getPhone().equals(admin.getPhone()) || !forgetPsw.getCode().equals(session.getAttribute("verify_code"))) {
                map.put("state",false);
                map.put("msg", "手机号或验证码输入错误");
            } else {
                map.put("state",true);
                map.put("msg", "用户信息填写正确");
                map.put("user1",admin);
            }
        } else if (teacher != null) {
            if (!forgetPsw.getPhone().equals(teacher.getPhone()) || !forgetPsw.getCode().equals(session.getAttribute("verify_code"))) {
                map.put("state",false);
                map.put("msg", "手机号或验证码输入错误");
            } else {
                map.put("state",true);
                map.put("msg", "用户信息填写正确");
                map.put("user1",teacher);
            }
        } else if (student != null) {
            if (!forgetPsw.getPhone().equals(student.getPhone()) || !forgetPsw.getCode().equals(session.getAttribute("verify_code"))) {
                map.put("state",false);
                map.put("msg", "手机号或验证码输入错误");
            } else {
                map.put("state",true);
                map.put("msg", "用户信息填写正确");
                map.put("user1",student);
            }
        } else {
            map.put("state",false);
            map.put("msg", "用户不存在");
        }
        return map;

        //        // 验证码（指定长度的随机数）
//        String code = CodeUtil.generateVerifyCode(6);
//        String TemplateParam = "{\"code\":\""+code+"\"}";
//        // 短信模板id
//        String TemplateCode = "SMS_215352126";
//        SendSmsResponse response = SmsTool.sendSms(phone,TemplateParam,TemplateCode);
    }

    @GetMapping("/verifyCode")
    public void verifyCode(HttpSession session, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        resp.setContentType("image/jpeg");
        BufferedImage image = code.getImage();
        String text = code.getText();
        session.setAttribute("verify_code",text);
        VerificationCode.output(image,resp.getOutputStream());
        System.out.println(text);
    }
}
