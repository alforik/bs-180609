insert into user ( id, user_id, password, name, email) values (1,  'a', 'a', 'a_name', 'a@naver.com');
insert into user ( id, user_id, password, name, email) values (2,  'b', 'b', 'b_name', 'b@naver.com');

insert into qna ( id, writer_id, title, contents, create_date, count_of_answer) values (1, '1', 'a1_title', 'a1aa_contents', CURRENT_TIMESTAMP(), 0);
insert into qna ( id, writer_id, title, contents, create_date, count_of_answer) values (2, '1', 'a2_title', 'a2aa_contents', CURRENT_TIMESTAMP(), 2);
insert into qna ( id, writer_id, title, contents, create_date, count_of_answer) values (3, '2', 'b1_title', 'b1bb_contents', CURRENT_TIMESTAMP(), 3);

INSERT INTO ANSWER (id, writer_id, qna_id, contents, create_date) VALUES(1, 2, 2, '댓글 2-1', CURRENT_TIMESTAMP());
INSERT INTO ANSWER (id, writer_id, qna_id, contents, create_date) VALUES(2, 2, 2, '댓글 2-2', CURRENT_TIMESTAMP());
INSERT INTO ANSWER (id, writer_id, qna_id, contents, create_date) VALUES(3, 1, 3, '댓글 3-1', CURRENT_TIMESTAMP());
INSERT INTO ANSWER (id, writer_id, qna_id, contents, create_date) VALUES(4, 2, 3, '댓글 3-2', CURRENT_TIMESTAMP());
INSERT INTO ANSWER (id, writer_id, qna_id, contents, create_date) VALUES(5, 2, 3, '댓글 3-3', CURRENT_TIMESTAMP());