DROP TABLE board;

CREATE TABLE board (
    num NUMBER(4) CONSTRAINT board_num_pk PRIMARY KEY,
    author VARCHAR2(20),
    title VARCHAR2(50),
    content VARCHAR2(100),
    writeday DATE DEFAULT CURRENT_DATE,
    readcnt NUMBER(4) DEFAULT 0,
    repRoot NUMBER(4),
    repStep NUMBER(4),
    repIndent NUMBER(4)
);
DESC board;

drop sequence board_seq;

create sequence board_seq;

insert into board(num, author, title, content, repRoot, repStep, repIndent)
values (board_seq.NEXTVAL, '홍길동', '테스트', '테스트입니다', board_seq.CURRVAL, 0, 0);

SELECT * FROM board;

commit;
rollback;
