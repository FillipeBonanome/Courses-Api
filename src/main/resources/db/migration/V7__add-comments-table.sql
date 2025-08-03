CREATE TABLE comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255) NOT NULL,
    user_id BINARY(16) NOT NULL,
    lesson_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,

    constraint fk_comments_user_id foreign key(user_id) references users(id),
    constraint fk_comments_lesson_id foreign key(lesson_id) references lessons(id)
);