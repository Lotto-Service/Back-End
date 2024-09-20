SET foreign_key_checks = 0;

DROP TABLE IF EXISTS USERS;

-- 사용자 정보
CREATE TABLE USERS (
                       USER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                       USERNAME VARCHAR(100) NOT NULL UNIQUE,
                       PASSWORD VARCHAR(100) NOT NULL,
                       EMAIL VARCHAR(100) NOT NULL UNIQUE,
                       BIRTH DATE,
                       PHONE_NUMBER VARCHAR(100) NOT NULL UNIQUE,
                       CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       UPDATED_AT TIMESTAMP,
                       DELETED_AT TIMESTAMP
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

DROP TABLE IF EXISTS LOTTO_TICKETS;

-- 사용자 로또 티켓 정보
CREATE TABLE LOTTO_TICKETS (
                               TICKET_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                               USER_ID BIGINT NOT NULL,
                               DRAW_NO BIGINT,
                               NUM_LIST VARCHAR(200),
                               IS_AUTO VARCHAR(100) NOT NULL, -- 수동(PASSIVITY), 반자동(SEMI_AUTO), 자동(AUTO)
                               CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID),
                               FOREIGN KEY (DRAW_NO) REFERENCES ROUNDS(DRAW_NO)
);


DROP TABLE IF EXISTS PRIZES;

-- 회차별 누적 금액 정보
CREATE TABLE PRIZES (
                        PRIZE_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        DRAW_NO BIGINT,
                        TOT_SELLAMNT BIGINT, -- 누적 당첨금
                        FIRST_ACCUMAMNT BIGINT, -- 1등 당첨금 총액
                        FIRST_PRZWNER_CO INT, -- 1등 당첨 인원
                        FIRST_WINAMNT BIGINT, -- 1등 당첨금
                        FOREIGN KEY (DRAW_NO) REFERENCES ROUNDS(DRAW_NO)
);

DROP TABLE IF EXISTS AUTH_CODES;

-- 인증코드 정보
CREATE TABLE AUTH_CODES (
						AUTH_CODE_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
						USER_ID BIGINT,
						PHONE_NUMBER VARCHAR(100),
						CODE VARCHAR(200),
						EXPIRATION_TIME TIMESTAMP,
						CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
						COMPLETED_AT TIMESTAMP
);