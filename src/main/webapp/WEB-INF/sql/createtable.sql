Microsoft Windows [Version 10.0.19045.6466]
(c) Microsoft Corporation. All rights reserved.

C:\Users\GG>sqlplus /nolog

SQL*Plus: Release 21.0.0.0.0 - Production on 수 4월 22 16:08:54 2026
Version 21.3.0.0.0

Copyright (c) 1982, 2021, Oracle.  All rights reserved.

SQL> conn /as sysdba
연결되었습니다.

SQL> alter session set "_ORACLE_SCRIPT"=true;
세션이 변경되었습니다.

SQL> create user spring identified by 1234;
사용자가 생성되었습니다.

SQL> grant connect, resource to spring;
권한이 부여되었습니다.

SQL> alter user spring default tablespace
  2  users quota unlimited on users;
사용자가 변경되었습니다.

SQL> conn spring/1234
연결되었습니다.

SQL> select * from tab;

선택된 레코드가 없습니다.

SQL>

---------------------------------------
-- 메뉴 목록
CREATE TABLE  MENUS (
    MENU_ID     VARCHAR2(6)    PRIMARY KEY
  , MENU_NAME   VARCHAR2(100)
  , MENU_SEQ    NUMBER(5)
);

INSERT INTO  MENUS VALUES ('MENU01','JAVA', 1);
COMMIT;

---------------------------------------
-- 회원정보
create table TUSER (
    USERID varchar2(12) primary key,
    PASSWD varchar2(12) not null,
    USERNAME varchar2(100) not null,
    EMAIL varchar2(320),
    UPOINT number(9) default 0,
    REGDATE date default sysdate
);

----------------------------------------
create table BOARD(
    IDX number(8,0) primary key,
    MENU_ID varchar2(6)
        references MENUS (MENU_ID)
    , TITLE varchar2(300) not null
    , CONTENT varchar2(4000)
    , WRITER varchar2(12) 
    , REGDATE DATE default sysdate
    , HIT number(9,0) default 0
);

INSERT INTO board (
    idx,
    menu_id,
    title,
    content,
    writer
) VALUES ( 
    (select nvl(max(IDX),0)+1 from BOARD),
    'MENU02',
    'SPRING Hello',
    '스프링 게시판에 오신것을 환영합니다',
    'spring'
);
commit;

SELECT
    idx,
    menu_id,
    title,
    writer,
    to_char(regdate,'YYYY-MM-DD') regdate,
    hit
FROM
    board
where MENU_ID = 'MENU01'
order by idx desc;











































