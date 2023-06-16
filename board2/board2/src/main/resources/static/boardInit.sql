drop table board cascade constraints;
    
create table board(
    num number primary key,
    name varchar2(100),
    pwd varchar2(100),
    email varchar2(100),
    subject varchar2(255),
    content clob,
    ipAddr varchar2(100),
    hitCount number,
    created date
);

select * from board;

commit;
