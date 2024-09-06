SET foreign_key_checks = 0;

DROP TABLE IF EXISTS USERS;

-- 사용자 정보
CREATE TABLE USERS (
                       USER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                       USERNAME VARCHAR(100) NOT NULL UNIQUE,
                       PASSWORD VARCHAR(100) NOT NULL,
                       EMAIL VARCHAR(100) NOT NULL UNIQUE,
                       BIRTH DATE,
                       PHONE_NUMBER VARCHAR(100),
                       CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       UPDATED_AT TIMESTAMP,
                       DELETED_AT TIMESTAMP
);

DROP TABLE IF EXISTS LOTTO_TICKETS;

-- 사용자 로또 티켓 정보
CREATE TABLE LOTTO_TICKETS (
                               TICKET_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                               USER_ID BIGINT NOT NULL,
                               ROUND_ID BIGINT NOT NULL,
                               NUM1 INT NOT NULL,
                               NUM2 INT NOT NULL,
                               NUM3 INT NOT NULL,
                               NUM4 INT NOT NULL,
                               NUM5 INT NOT NULL,
                               NUM6 INT NOT NULL,
                               IS_AUTO BOOLEAN NOT NULL DEFAULT FALSE, -- 수동(0), 자동(1)
                               CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID),
                               FOREIGN KEY (ROUND_ID) REFERENCES ROUNDS(ROUND_ID)
);

DROP TABLE IF EXISTS ROUNDS;

-- 회차 정보
CREATE TABLE ROUNDS (
                        ROUND_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        DRAW_NO BIGINT UNIQUE,
                        DRAW_DATE DATE,
                        WINNING_NUM_1 INT,
                        WINNING_NUM_2 INT,
                        WINNING_NUM_3 INT,
                        WINNING_NUM_4 INT,
                        WINNING_NUM_5 INT,
                        WINNING_NUM_6 INT,
                        BONUS_NUMBER INT,
                        CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS PRIZES;

-- 회차별 누적 금액 정보
CREATE TABLE PRIZES (
                        PRIZE_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        DRAW_NO BIGINT,
                        TOT_SELLAMNT DECIMAL, -- 누적 당첨금
                        FIRST_ACCUMAMNT DECIMAL, -- 1등 당첨금 총액
                        FIRST_PRZWNER_CO INT, -- 1등 당첨 인원
                        FIRST_WINAMNT DECIMAL, -- 1등 당첨금
                        FOREIGN KEY (DRAW_NO) REFERENCES ROUNDS(DRAW_NO)
);