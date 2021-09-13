package com.example.springboot2test.service;

import com.example.springboot2test.entity.Courses;
import com.example.springboot2test.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
//@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Courses saveCourse(Courses courseS) {
        return courseRepository.save(courseS);
    }

    @Override
    public void removeCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Courses updateCourse(Courses courseS) {
        return courseRepository.save(courseS);
    }

    @Override
    public Courses getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Courses> listCourses() {
        return courseRepository.findAll();
    }


//    @Override
//    public Page<Course> findPage(Integer pageNum, Integer pageSize, String name) {
//        Sort sort=Sort.by(Sort.Direction.DESC,"create_time");//排序
//        PageRequest request=PageRequest.of(pageNum -1,pageSize,sort);//分页
//        return courseRepository.findNameLike(name,request);
//    }


}
