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
select * from emp;
select * from commute;

select * from emp where empno = 2 and to_char(hiredate,'YYYY-MM-DD') = to_char(sysdate, 'YYYY-MM-DD');

select * from commute where empno = 1 and to_char(come,'YYYY-MM-DD') = to_char(sysdate, 'YYYY-MM-DD');


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


UPDATE commute
SET out = SYSDATE
WHERE empno = 1
AND commuteno = (
    SELECT MAX(commuteno)
    FROM commute
    WHERE empno = 1
);

select * from emp
where empno = 3;

select *
from commute;

select * from commute where empno = 3 and to_char(come,'YYYY-MM-DD') = to_char(sysdate, 'YYYY-MM-DD');

select e.empno, e.name, c.come, c.out
from emp e, commute c
where e.empno = c.empno and e.empno = 1 -- ���� ����
order by come desc;

select nvl(count(*), 0) 
from emp e, commute c 
where e.empno = c.empno and e.empno = 1;

select * from (
    select rownum rnum, data.* from (
	        	select e.empno, e.name, c.come, c.out
	        	from emp e, commute c
	        	where e.empno = c.empno and e.empno = 1
	        	order by c.come desc
    		) data
		)
    where rnum >= 1 and rnum <= 5;


select * from commute;

commit;                    
rollback;