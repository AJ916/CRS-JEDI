
CREATE TABLE User (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(100),
    role ENUM('Student', 'Professor', 'Admin') NOT NULL
);

CREATE TABLE Student (
    student_id INT PRIMARY KEY,
    department VARCHAR(50),
    FOREIGN KEY (student_id) REFERENCES User(user_id)
);

CREATE TABLE Professor (
    professor_id INT PRIMARY KEY,
    department VARCHAR(50),
    designation VARCHAR(50),
    FOREIGN KEY (professor_id) REFERENCES User(user_id)
);

CREATE TABLE Admin (
    admin_id INT PRIMARY KEY,
    date_of_joining DATE,
    FOREIGN KEY (admin_id) REFERENCES User(user_id)
);

ALTER TABLE professor 
RENAME COLUMN instructor_id TO professor_id;


CREATE TABLE Course (
    course_id VARCHAR(10) PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    instructor_id INT,
    total_seats INT,
    available_seats INT,
    is_offered BOOLEAN,
    FOREIGN KEY (instructor_id) REFERENCES Professor(professor_id)
);

CREATE TABLE Grade (
    grade_id INT PRIMARY KEY AUTO_INCREMENT,
    grade VARCHAR(2) NOT NULL
);

CREATE TABLE GradeCard (
    gradecard_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id VARCHAR(10),
    grade_id INT,
    FOREIGN KEY (student_id) REFERENCES Student(student_id),
    FOREIGN KEY (course_id) REFERENCES Course(course_id),
    FOREIGN KEY (grade_id) REFERENCES Grade(grade_id)
);

CREATE TABLE Payment (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    amount_due DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Student(student_id)
);

CREATE TABLE CourseEnrollment (
    student_id INT,
    course_id VARCHAR(20),
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (course_id) REFERENCES course(course_id)
);

INSERT INTO CourseEnrollment (student_id, course_id) VALUES (1, 'C101');
INSERT INTO CourseEnrollment (student_id, course_id) VALUES (1, 'C102');
INSERT INTO CourseEnrollment (student_id, course_id) VALUES (2, 'C103');
INSERT INTO CourseEnrollment (student_id, course_id) VALUES (8, 'C101');
INSERT INTO CourseEnrollment (student_id, course_id) VALUES (9, 'C104');

CREATE TABLE SystemSettings (
    id INT PRIMARY KEY,
    is_add_drop_window_open BOOLEAN
);
-- Initialize with default value
INSERT INTO SystemSettings (id, is_add_drop_window_open) VALUES (1, false);

SET SQL_SAFE_UPDATES = 1;

DELETE FROM CourseEnrollment;

select * from student;
select * from course;
select * from CourseEnrollment;
select * from user;
select * from systemsettings;

