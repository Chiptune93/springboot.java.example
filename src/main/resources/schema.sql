
CREATE TABLE IF NOT EXISTS sample_user
(
    user_id         VARCHAR(50)     NOT NULL,
    user_name       VARCHAR(50)     NOT NULL,
    user_age        INT(10)         NOT NULL,    
    user_join_date  DATE            NOT NULL,
    PRIMARY KEY (user_id)
) AS SELECT * FROM CSVREAD('classpath:user.csv');
