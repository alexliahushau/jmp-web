CREATE TABLE USERS (
    USER_ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY, 
    FIRST_NAME VARCHAR(100),
    LAST_NAME VARCHAR(100),
    EMAIL VARCHAR(100),
    BIRTHDAY DATE,
    ROLES VARCHAR(100),
    PASSWORD VARCHAR(60),
    PRIMARY KEY(USER_ID)
);

CREATE TABLE USER_ACCOUNT (
    ACCOUNT_ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
	USER_ID BIGINT,
	AMOUNT BIGINT,
	PRIMARY KEY(ACCOUNT_ID, USER_ID)
);

CREATE TABLE PERSISTENT_LOGINS (
    USERNAME VARCHAR(64) NOT NULL,
    SERIES VARCHAR(64) NOT NULL,
    TOKEN VARCHAR(64) NOT NULL,
    LAST_USED TIMESTAMP NOT NULL,
    PRIMARY KEY (SERIES)
);

CREATE TABLE TICKETS (
    TICKET_ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    EVENT_ID BIGINT,
    USER_ID BIGINT,
    DATE_TIME TIMESTAMP,
    SEAT INTEGER,
    PURCHASED BOOLEAN,
    PRIMARY KEY(TICKET_ID)
);

CREATE TABLE EVENTS (
    EVENT_ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(30),
    BASE_PRICE DOUBLE,
    RATING VARCHAR(30),
    PRIMARY KEY(EVENT_ID)
);

CREATE TABLE AUDITORIUMS (
    AUDITORIUM_NAME VARCHAR(30) NOT NULL,
    SEATS BIGINT,
    PRIMARY KEY(AUDITORIUM_NAME)
);

CREATE TABLE EVENT_AIR_DATES (
    EVENT_ID BIGINT,
    AIR_DATE TIMESTAMP
);

CREATE TABLE AUDITORIUM_VIP_SEATS (
    AUDITORIUM_NAME VARCHAR(30),
    VIP_SEAT BIGINT
);

CREATE TABLE EVENT_AUDITORIUM_DATE (
    EVENT_ID BIGINT,
    AUDITORIUM_NAME VARCHAR(30),
    DATE TIMESTAMP
);

CREATE TABLE COUNT_GIVEN_DISCOUNT (
    USER_ID BIGINT,
    CLASS_NAME VARCHAR(100),
    COUNT BIGINT,
    PRIMARY KEY(USER_ID, CLASS_NAME)
);

CREATE TABLE EVENT_STATISTIC_COUNT (
    EVENT_ID BIGINT,
    METHOD_NAME VARCHAR(100),
    COUNT BIGINT,
    PRIMARY KEY(EVENT_ID, METHOD_NAME)
);

CREATE TABLE USER_SYSTEM_MESSAGE (
    ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    USER_ID BIGINT,
    MESSAGE VARCHAR(100),
    TIME TIMESTAMP,
    PRIMARY KEY(ID)
);

ALTER TABLE TICKETS ADD FOREIGN KEY (EVENT_ID) REFERENCES EVENTS(EVENT_ID);
ALTER TABLE TICKETS ADD FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID);
ALTER TABLE EVENT_AIR_DATES ADD FOREIGN KEY (EVENT_ID) REFERENCES EVENTS(EVENT_ID);
ALTER TABLE EVENT_AUDITORIUM_DATE ADD FOREIGN KEY (EVENT_ID) REFERENCES EVENTS(EVENT_ID);
ALTER TABLE EVENT_AUDITORIUM_DATE ADD FOREIGN KEY (AUDITORIUM_NAME) REFERENCES AUDITORIUMS(AUDITORIUM_NAME);
ALTER TABLE AUDITORIUM_VIP_SEATS ADD FOREIGN KEY (AUDITORIUM_NAME) REFERENCES AUDITORIUMS(AUDITORIUM_NAME);
ALTER TABLE COUNT_GIVEN_DISCOUNT ADD FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID);
ALTER TABLE EVENT_STATISTIC_COUNT ADD FOREIGN KEY (EVENT_ID) REFERENCES EVENTS(EVENT_ID);
ALTER TABLE USER_SYSTEM_MESSAGE ADD FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID);
ALTER TABLE USER_ACCOUNT ADD FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID);