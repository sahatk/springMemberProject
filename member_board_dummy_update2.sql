create table board2 as select * from board;

alter table board2 add userid varchar2(10 char);

delete board2;

-------------------------------------------

update board2
set name = m2.name,
    userid = m2.userid
where email in (
select b2.email 
from board2 b2, member2 m2
where b2.email = m2.email);

select b2.email 
from board2 b2, member2 m2
where b2.email = m2.email

-------------------------------------------

CREATE OR REPLACE PROCEDURE dummy_board2_gen_proc
IS
BEGIN

    FOR i IN 1..100 LOOP
    
        INSERT INTO board2 VALUES
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

EXECUTE dummy_board2_gen_proc;

-- 회원 목록에 있는 회원아이디/이름로 게시판 데이터(userid, 이름) 수정 

SET VERIFY OFF
SET SERVEROUTPUT ON

CREATE OR REPLACE PROCEDURE board2_id_name_update_proc  
IS  
    msg  varchar2(100 char);
BEGIN  
       
      FOR i IN 1..100 LOOP  
      
        UPDATE board2
        SET userid = (SELECT userid
                      FROM member2  
                      WHERE email = 'ezen_' || i || '@abcd.com'),
            name = (SELECT name
                      FROM member2   
                      WHERE email = 'ezen_' || i || '@abcd.com')          
        WHERE email = 'ezen_' || i || '@abcd.com';
        
        SELECT email INTO msg 
        FROM board2
        WHERE email = 'ezen_' || i || '@abcd.com';
        
        DBMS_OUTPUT.PUT_LINE('msg : ' || msg);
 
    END LOOP;  

    COMMIT;         
  END;  
/  
 
EXECUTE board2_id_name_update_proc;

SET VERIFY ON
SET SERVEROUTPUT OFF
 