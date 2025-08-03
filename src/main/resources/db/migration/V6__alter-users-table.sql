ALTER TABLE courses DROP CONSTRAINT fk_courses_instructor_id;
ALTER TABLE users MODIFY id BINARY(16);
ALTER TABLE courses MODIFY instructor_id BINARY(16) NOT NULL;

ALTER TABLE courses ADD CONSTRAINT fk_courses_instructor_id FOREIGN KEY (instructor_id) REFERENCES users(id)