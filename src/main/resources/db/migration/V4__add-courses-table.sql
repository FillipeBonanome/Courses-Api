CREATE TABLE courses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    instructor_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    thumbnail VARCHAR(20) NOT NULL,
    published tinyint NOT NULL,
    slug varchar(100) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,

    constraint fk_courses_instructor_id foreign key(instructor_id) references users(id)
);