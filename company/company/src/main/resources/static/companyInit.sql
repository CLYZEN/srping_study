drop table emp;
drop table commute;

create table emp(
    empno number primary key,
    name varchar2(50) not null,
    hiredate date not null,
    adminchk varchar2(20) default 'N' not null,
    constraint checkadmin check(adminchk in('Y','N'))
);

create table commute(
    commuteno number primary key,
    empno number not null,
    come date,
    out date,
    constraint fk_empno FOREIGN key(empno) REFERENCES emp(empno)
);
INSERT INTO emp (empno, name, hiredate, adminchk) VALUES
(1, 'John Doe', TO_DATE('2022-01-01', 'YYYY-MM-DD'), 'N');

INSERT INTO emp (empno, name, hiredate, adminchk) VALUES
(2, 'John Doe', TO_DATE('2023-06-21', 'YYYY-MM-DD'), 'N');

INSERT INTO emp (empno, name, hiredate, adminchk) VALUES
(3, 'John Doe', TO_DATE('2023-06-21', 'YYYY-MM-DD'), 'N');



insert into commute (commuteno,empno,come) values(commuteseq.nextval,1,sysdate);

insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-01', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-02', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-03', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-04', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-05', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-06', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-07', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-08', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-09', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-10', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-11', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-12', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-13', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-14', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-15', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-16', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-17', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-18', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-19', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));
insert into commute (commuteno,empno,come,out) values(commuteseq.nextval,1, TO_DATE('2023-06-20', 'YYYY-MM-DD'),TO_DATE('2023-06-01', 'YYYY-MM-DD'));


insert into emp values(EMP_SEQ.nextval, '¹Ú½ÂÃ¶', sysdate, 'N');
insert into emp values(EMP_SEQ.nextval, '¹Ú½ÂÃ¶', sysdate, 'N');
insert into emp values(EMP_SEQ.nextval, '¹Ú½ÂÃ¶', sysdate, 'N');
insert into emp values(EMP_SEQ.nextval, '¹Ú½ÂÃ¶', sysdate, 'N');
insert into emp values(EMP_SEQ.nextval, '¹Ú½ÂÃ¶', sysdate, 'N');

