insert into courses(id, course,teacher_id)values (1,"计算机基础",1);
insert into courses(id, course,teacher_id)values (2,"数据结构",2);
insert into courses(id, course,teacher_id)values (3,"计算机网络",3);
insert into courses(id, course,teacher_id)values (4,"操作系统",4);
insert into courses(id, course,teacher_id)values (5,"Web前端开发",5);

insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (1,"完整的计算机系统由（）组成","硬件系统和软件系统","运算器、存储器", "输入设备和输出设备", "主机和外部设备","硬件系统和软件系统",1);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (2,"以下软件中，（）不是操作系统软件","microsoft office","Windowsxp", "unix", "linux", "microsoft office",1);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (3,"用一个字节最多能编出（）不同的码","256","8", "16", "128", "256",1);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (4,"在数据结构中，从逻辑上可以把数据结构分为（）","线性结构和非线性结构","动态结构和静态结构", "紧凑结构和非紧凑结构", "线性结构和非线性结构", "内部结构和外部结构",2);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (5,"数据结构在内存中的表示是指（）","数据的存储结构","数据的存储结构", "数据结构", "数据的逻辑结构", "数据元素之间的关系",2);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (6,"需要分配较大空间，插入和删除不需要移动元素的线性表，其存储结构是（）","静态链表","单链表", "静态链表", "线性链表", "顺序存储结构",2);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (7,"在TCP报头中，（）字段指定下一个字节的期望值。","ACK","SYN" ,"ACK" ,"PSH" ,"FIN",3);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (8,"（）层的主要任务是提供跨物理链路的可靠信息传输","传输层","物理层" ,"数据链路层", "网络层","传输层",3);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (9,"数据链路层使用 （ ）进行错误检测。","CRC","CRC", "汉明码" ,"曼彻斯特编码", "差分曼彻斯特编码",3);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (10,"（）不是分时系统的基本特征","实时性","同时性" ,"独立性", "实时性", "交互性",4);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (11,"计算机系统的组成包括（）","计算机硬件和计算机软件","程序和数据", "计算机硬件和计算机软件" ,"处理器和内存", "处理器和存储器和外围设备",4);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (12,"实时操作系统追求的目标是（）","快速响应","高吞吐率", "充分利用内存", "快速响应", "减少系统开销",4);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (13," HTML5 不支持的视频格式是（ ）","flv","ogg" ,"mp4", "flv", "WebM",5);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (14,"为标识一个 HTML 文件，应该使用标签（ ）","<html></html>","<style></style>", "<body></body>" ,"<head></head>", "<html></html>",5);
insert into questions(id,question, correct,pick1,pick2,pick3,pick4,course_id) VALUES (15,"以下哪个选项是对对象进行定位的（ ）","position","padding", "margin" ,"position" ,"display",5);

insert into student_course(student_id, course_id) VALUES (1,1);
insert into student_course(student_id, course_id) VALUES (1,2);
insert into student_course(student_id, course_id) VALUES (1,3);
insert into student_course(student_id, course_id) VALUES (1,4);
insert into student_course(student_id, course_id) VALUES (1,5);
insert into student_course(student_id, course_id) VALUES (2,1);
insert into student_course(student_id, course_id) VALUES (2,2);
insert into student_course(student_id, course_id) VALUES (2,3);
insert into student_course(student_id, course_id) VALUES (2,4);
insert into student_course(student_id, course_id) VALUES (2,5);
insert into student_course(student_id, course_id) VALUES (3,1);
insert into student_course(student_id, course_id) VALUES (3,2);
insert into student_course(student_id, course_id) VALUES (3,3);
insert into student_course(student_id, course_id) VALUES (3,4);
insert into student_course(student_id, course_id) VALUES (3,5);

insert into grades(id,Grades,student_id,course_id,update_time) VALUES (1,"80",1,1,'2019-01-21 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (2,"75",1,1,'2020-02-02 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (3,"55",1,2,'2020-03-03 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (4,"80",1,2,'2018-01-21 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (5,"75",2,3,'2021-02-02 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (6,"55",3,3,'2020-04-03 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (7,"80",1,4,'2019-02-21 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (8,"75",2,4,'2020-03-02 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (9,"55",3,5,'2020-02-03 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (10,"80",1,5,'2019-01-22 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (11,"75",2,1,'2020-05-02 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (12,"55",3,2,'2020-05-03 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (13,"80",1,3,'2019-01-28 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (14,"75",2,1,'2020-02-09 21:28:18');
insert into grades(id,Grades,student_id,course_id,update_time) VALUES (15,"55",3,3,'2020-03-04 21:28:18');

insert into student(id, name, gender,password,username, phone,authority_id)VALUES (1,"张一","女","123456","stu1","19989102551",3);
insert into student(id, name, gender,password,username, phone,authority_id)VALUES (2,"张二","女","123456","stu2","19989102552",3);
insert into student(id, name, gender,password,username,phone,authority_id)VALUES (3,"张四","男","123456","stu3","19989102553",3);

insert into teacher(id, name, gender,password,username, phone,authority_id)VALUES (1,"李老师","男","654321","tea1","19989102554",2);
insert into teacher(id, name, gender,password,username,phone,authority_id)VALUES (2,"张老师","男","654321","tea2","19989102555",2);
insert into teacher(id, name, gender,password,username,phone,authority_id)VALUES (3,"王老师","女","654321","tea3","19989102556",2);
insert into teacher(id, name, gender,password,username,phone,authority_id)VALUES (4,"梁老师","男","654321","tea4","19989102553",2);
insert into teacher(id, name, gender,password,username,phone,authority_id)VALUES (5,"刘老师","女","654321","tea5","19989102556",2);
insert into teacher(id, name, gender,password,username,phone,authority_id)VALUES (6,"杨老师","女","654321","tea6","19989102558",2);

insert into admin(id, name, gender,password,username, phone,authority_id)VALUES (1,"李管","男","654321","adm1","19989102557",1);


insert into authority (id, name) VALUES (1, 'ROLE_ADMIN');
insert into authority (id, name) VALUES (2, 'ROLE_TEACHER');
insert into authority (id, name) VALUES (3, 'ROLE_STUDENT');







