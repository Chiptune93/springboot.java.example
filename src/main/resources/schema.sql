CREATE TABLE IF NOT EXISTS members
(
    user_id         VARCHAR(50)     NOT NULL,
    user_name       VARCHAR(50)     NOT NULL,
    user_email      VARCHAR(50)     NOT NULL,    
    user_age        VARCHAR(50)     NOT NULL,    
    user_address    VARCHAR(50)     NOT NULL,    
    user_enter_date VARCHAR(50)     NOT NULL,
    PRIMARY KEY (user_id)
);
