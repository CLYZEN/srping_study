DROP TABLE DEPT CASCADE CONSTRAINTS;

create table dept (
deptno varchar2(100) primary key,
dept varchar2(100),
loc  varchar2(100)
);

insert into dept values('101', '���ߺ�', '��õ');
insert into dept values('102', 'ȫ����', '����');
insert into dept values('103', '������', '�λ�');

commit;

select * from dept;

