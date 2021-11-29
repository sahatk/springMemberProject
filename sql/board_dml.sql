--- 페이징
SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 10 + 1) page  
      FROM (
             SELECT * FROM board
             ORDER BY num ASC
           ) m  
      )  
WHERE page = 1;

-- 마지막(총) 페이지
SELECT CEIL(COUNT(*)/10) FROM board;


-- 검색
SELECT * FROM board
WHERE name like '%순%'
ORDER BY num ASC;


--- 페이징 : 검색 (작성자, 글 제목, 내용)
--- 작성자
SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 10 + 1) page  
      FROM (
             SELECT * FROM board
             WHERE name like '%수%'
             ORDER BY num ASC
           ) m  
      )  
WHERE page = 1;

--- 제목
SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 10 + 1) page  
      FROM (
             SELECT * FROM board
             WHERE title like '%Spring%'
             ORDER BY num ASC
           ) m  
      )  
WHERE page = 1;

--- 내용
SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 10 + 1) page  
      FROM (
             SELECT * FROM board
             WHERE content like '%과목%'
             ORDER BY num ASC
           ) m  
      )  
WHERE page = 1;

--- 검색 결과 마지막 페이지
SELECT CEIL(COUNT(*)/10) FROM board
WHERE title like '%Spring%';

SELECT CEIL(COUNT(*)/10) FROM board
WHERE content like '%과목%';

----------------------------------------

-- 검색 테스트를 위한 샘플 데이터(더미 데이터) 재구성 
SELECT count(*) FROM board
WHERE name like '%이%'
ORDER BY num ASC;

SELECT count(*) FROM board
WHERE title like '%Spring%'
ORDER BY num ASC;

update board set
title = 'Spring을 공부하자'
where name like  '%순%';

update board set
content = 'JSP/Servlet 과목을 공부하자'
where name like  '%이%';

SELECT count(*) FROM board
WHERE content like '%과목을%'
ORDER BY num ASC;