create table member2 (
	name varchar2(10 char),
	userid varchar2(10 char),
	pwd varchar2(10 char),
	email varchar2(20 char),
	phone char(13 char),
	admin number(1) default 0,
	primary key(userid)
);

drop table member2;

insert into member2 
values ('이소미', 'somi', '1234', 'gmd@naver.com', '010-2362-5157', 0);
insert into member2
values ('하상오', 'sang12', '1234', 'ha12@naver.com', '010-5629-8888', 1);
insert into member2 
values ('김윤승', 'light', '1234', 'youn1004@naver.com', '010-9999-8282', 0);

select * from member2;

select * from member2 where userid='sang12';

update member2 
set 
pwd ='#Abcd1234',
email='spring11@abcd.com',
phone='010-4545-9898'
where userid='Jsp1234567';

delete member2 where userid='somi';

--- 페이징
SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 10 + 1) page  
      FROM (
             SELECT * FROM member2  
             ORDER BY userid ASC
           ) m  
      )  
WHERE page = 2;

-- 검색
SELECT count(*) FROM member2 
WHERE name like '%수%'
ORDER BY userid ASC;


--- 검색 : 페이징
SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 10 + 1) page  
      FROM (
             SELECT * FROM member2  
             WHERE name like '%수%'
             ORDER BY userid ASC
           ) m  
      )  
WHERE page = 1;


SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 10 + 1) page  
      FROM (
             SELECT * FROM member2  
             WHERE phone like '%1234%'
             ORDER BY userid ASC
           ) m  
      )  
WHERE page = 1;

-- 마지막(총) 페이지 : 전체 회원
SELECT CEIL(COUNT(*)/10) FROM member2;

-- 마지막(총) 페이지 : 검색
SELECT CEIL(COUNT(*)/10) FROM member2  
WHERE name like '%수%';

-- 회원 존재 여부 점검
SELECT count(*) FROM member2
WHERE userid='abcdabcdabcd';

-- 로그인 인증
SELECT * FROM member2
WHERE userid='sang12' AND pwd='1234';

SELECT * FROM member2
WHERE userid='sang12' AND pwd='zazaza'; 

SELECT * FROM member2
WHERE userid='abcdabcd' AND pwd='1234'; 