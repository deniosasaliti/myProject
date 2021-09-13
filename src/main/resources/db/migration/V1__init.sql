CREATE TABLE my_scheme."user"
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name     VARCHAR(255),
    email    VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT pk_user PRIMARY KEY (id)
);


CREATE TABLE my_scheme.post
(
    post_id      BIGINT NOT NULL,
    post_name    VARCHAR(255),
    url          VARCHAR(255),
    description  TEXT,
    vote_count   INTEGER,
    user_id      BIGINT,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    image        VARCHAR(255),
    categories   VARCHAR(255),
    CONSTRAINT pk_post PRIMARY KEY (post_id)
);

ALTER TABLE my_scheme.post
    ADD CONSTRAINT FK_POST_ON_USER FOREIGN KEY (user_id) REFERENCES my_scheme."user" (id);

CREATE TABLE my_scheme.comment
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    text         VARCHAR(255),
    post_id      BIGINT,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    user_id      BIGINT,
    CONSTRAINT pk_comment PRIMARY KEY (id)
);

ALTER TABLE my_scheme.comment
    ADD CONSTRAINT FK_COMMENT_ON_POST FOREIGN KEY (post_id) REFERENCES my_scheme.post (post_id);

ALTER TABLE my_scheme.comment
    ADD CONSTRAINT FK_COMMENT_ON_USER FOREIGN KEY (user_id) REFERENCES my_scheme."user" (id);


CREATE TABLE my_scheme.token
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    token   VARCHAR(255),
    user_id BIGINT,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

ALTER TABLE my_scheme.token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES my_scheme."user" (id);



CREATE TABLE my_scheme.vote
(
    vote_id   BIGINT NOT NULL,
    vote_type INTEGER,
    post_id   BIGINT,
    user_id   BIGINT,
    CONSTRAINT pk_vote PRIMARY KEY (vote_id)
);

ALTER TABLE my_scheme.vote
    ADD CONSTRAINT FK_VOTE_ON_POST FOREIGN KEY (post_id) REFERENCES my_scheme.post (post_id);

ALTER TABLE my_scheme.vote
    ADD CONSTRAINT FK_VOTE_ON_USER FOREIGN KEY (user_id) REFERENCES my_scheme."user" (id);