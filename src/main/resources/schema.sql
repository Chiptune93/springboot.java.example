/* 1. 일반 테이블 생성 방법 */
-- CREATE TABLE IF NOT EXISTS sample_user
-- (
--     user_id         VARCHAR(50)     NOT NULL,
--     user_name       VARCHAR(50)     NOT NULL,
--     user_age        INTEGER         NOT NULL,    
--     user_join_date  DATE            NOT NULL,
--     PRIMARY KEY (user_id)
-- );

/* 2. CSV 데이터 가져오면서 테이블 생성 */
CREATE TABLE IF NOT EXISTS sample_user
(
    user_id         VARCHAR(50)     NOT NULL,
    user_name       VARCHAR(50)     NOT NULL,
    user_age        INT(10)         NOT NULL,    
    user_join_date  DATE            NOT NULL,
    PRIMARY KEY (user_id)
) AS SELECT * FROM CSVREAD('classpath:sample_table.csv');

/* 3. CSV 데이터 구조 그대로 테이블 생성 및 데이터 입력 */
-- CREATE TABLE IF NOT EXISTS sample_user AS SELECT * FROM CSVREAD('classpath:sample_table.csv');