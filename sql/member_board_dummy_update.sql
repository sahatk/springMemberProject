-- 백업(backup)
create table board2 as select * from board;

-- 게시판 필드 추가(userid)
alter table board add userid varchar2(10 char);

-- 기존 데이터 삭제
delete board;

-------------------------------------------

update board
set name = m2.name,
    userid = m2.userid
where email in (
select b.email 
from board b, member2 m2
where b.email = m2.email);

select b.email 
from board b, member2 m2
where b.email = m2.email

-------------------------------------------

CREATE OR REPLACE PROCEDURE dummy_board_gen_proc
IS
BEGIN

    FOR i IN 1..100 LOOP
    
        INSERT INTO board VALUES
        (
         board_seq.nextval,
         '12345678',
         '김' || (100+i),
         'ezen_' || i || '@abcd.com',
         '게시글 제목' || i,
         '게시글 내용' || i,
         0,
         sysdate,
         '');
         
     END LOOP;
 
    COMMIT;    
END;
/

EXECUTE dummy_board_gen_proc;

-- 회원 목록에 있는 회원아이디/이름로 게시판 데이터(userid, 이름) 수정 

SET VERIFY OFF
SET SERVEROUTPUT ON

CREATE OR REPLACE PROCEDURE board_id_name_update_proc  
IS  
    msg  varchar2(100 char);
BEGIN  
       
      FOR i IN 1..100 LOOP  
      
        UPDATE board
        SET userid = (SELECT userid
                      FROM member2  
                      WHERE email = 'ezen_' || i || '@abcd.com'),
            name = (SELECT name
                      FROM member2   
                      WHERE email = 'ezen_' || i || '@abcd.com')          
        WHERE email = 'ezen_' || i || '@abcd.com';
        
        SELECT email INTO msg 
        FROM board
        WHERE email = 'ezen_' || i || '@abcd.com';
        
        DBMS_OUTPUT.PUT_LINE('msg : ' || msg);
 
    END LOOP;  

    COMMIT;         
  END;  
/  
 
EXECUTE board_id_name_update_proc;

SET VERIFY ON
SET SERVEROUTPUT OFF
 