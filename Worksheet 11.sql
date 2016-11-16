
		SELECT c.no,
      		   c.user_no AS userNo,
     		   c.name,
       		   c.description,
               c.reg_date AS regDate,
	           p.num
 	  		FROM CATEGORY c,
      			 (  SELECT CATEGORY_NO, COUNT (*) as num
           		   		 FROM POST
        			GROUP BY CATEGORY_NO) p
 		WHERE p.CATEGORY_NO (+)= c.no
 		   	  AND c.USER_NO = 10;
			  
select * from CATEGORY;
	SELECT * FROM POST;		  
	select * from BLOG;
	select * from JUSERS;
 commit;
 
 UPDATE BLOG SET LOGO = '1', TITLE = '1' WHERE NO