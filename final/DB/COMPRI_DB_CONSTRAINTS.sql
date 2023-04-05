ALTER TABLE region_board ADD
CONSTRAINT region_board_post_no_pk PRIMARY KEY(post_no);

ALTER TABLE region_board ADD
CONSTRAINT region_board_member_id_fk FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE;

ALTER TABLE sale_board ADD
CONSTRAINT sale_board_post_no_pk PRIMARY KEY(post_no);

ALTER TABLE sale_board ADD
CONSTRAINT sale_board_member_id_fk FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE;

ALTER TABLE region_board_comment ADD
CONSTRAINT region_board_comment_post_no_fk FOREIGN KEY(post_no) REFERENCES region_board(post_no) ON DELETE CASCADE;

ALTER TABLE region_board_comment ADD
CONSTRAINT region_board_comment_member_id_fk FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE;

ALTER TABLE region_board_comment ADD
CONSTRAINT region_board_comment_comment_no_post_no_pk PRIMARY KEY(comment_no, post_no);

ALTER TABLE sale_board_comment ADD
CONSTRAINT sale_board_comment_post_no_fk FOREIGN KEY(post_no) REFERENCES sale_board(post_no) ON DELETE CASCADE;

ALTER TABLE sale_board_comment ADD
CONSTRAINT sale_board_comment_member_id_fk FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE;

ALTER TABLE sale_board_comment ADD
CONSTRAINT sale_board_comment_comment_no_post_no_pk PRIMARY KEY(comment_no, post_no);

ALTER TABLE region_board_file ADD
CONSTRAINT region_board_file_post_no_fk FOREIGN KEY(post_no) REFERENCES region_board(post_no) ON DELETE CASCADE;

ALTER TABLE region_board_file ADD
CONSTRAINT region_board_file_file_id_post_no_pk PRIMARY KEY(file_id, post_no);
    
ALTER TABLE sale_board_file ADD
CONSTRAINT sale_board_file_post_no_fk FOREIGN KEY(post_no) REFERENCES sale_board(post_no) ON DELETE CASCADE;

ALTER TABLE sale_board_file ADD
CONSTRAINT sale_board_file_file_id_post_no_pk PRIMARY KEY(file_id, post_no);

ALTER TABLE member_keyword ADD
CONSTRAINT member_keyword_member_id_fk FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE;

ALTER TABLE member_keyword ADD
CONSTRAINT member_keyword_keyword_member_id_pk PRIMARY KEY(keyword, member_id);

ALTER TABLE region_board_keyword_not ADD
CONSTRAINT region_board_keyword_not_not_no_pk PRIMARY KEY(not_no);

ALTER TABLE region_board_keyword_not ADD
CONSTRAINT region_board_keyword_not_post_no_fk FOREIGN KEY(post_no) REFERENCES region_board(post_no) ON DELETE SET NULL;

ALTER TABLE region_board_keyword_not ADD
CONSTRAINT region_board_keyword_not_keyword_member_id_fk 
FOREIGN KEY(keyword, member_id) REFERENCES member_keyword(keyword, member_id);

ALTER TABLE sale_board_keyword_not ADD
CONSTRAINT sale_board_keyword_not_not_no_pk PRIMARY KEY(not_no);

ALTER TABLE sale_board_keyword_not ADD
CONSTRAINT sale_board_keyword_not_post_no_fk FOREIGN KEY(post_no) REFERENCES sale_board(post_no) ON DELETE SET NULL;

ALTER TABLE sale_board_keyword_not ADD
CONSTRAINT sale_board_keyword_not_keyword_member_id_fk 
FOREIGN KEY(keyword, member_id) REFERENCES member_keyword(keyword, member_id);

ALTER TABLE region_board_act_not ADD
CONSTRAINT region_board_act_not_not_no_pk PRIMARY KEY(not_no);

ALTER TABLE region_board_act_not ADD
CONSTRAINT region_board_act_not_member_id_fk FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE;

ALTER TABLE region_board_act_not ADD
CONSTRAINT region_board_act_not_post_no_fk FOREIGN KEY(post_no) REFERENCES region_board(post_no) ON DELETE SET NULL;

ALTER TABLE sale_board_act_not ADD
CONSTRAINT sale_board_act_not_not_no_pk PRIMARY KEY(not_no);

ALTER TABLE sale_board_act_not ADD
CONSTRAINT sale_board_act_not_member_id_fk FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE;

ALTER TABLE sale_board_act_not ADD
CONSTRAINT sale_board_act_not_post_no_fk FOREIGN KEY(post_no) REFERENCES sale_board(post_no) ON DELETE SET NULL;

ALTER TABLE region_board_bookmark ADD
CONSTRAINT region_board_bookmark_bookmark_no_pk PRIMARY KEY(bookmark_no);

ALTER TABLE region_board_bookmark ADD
CONSTRAINT region_board_bookmark_member_id_fk FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE;

ALTER TABLE region_board_bookmark ADD
CONSTRAINT region_board_bookmark_post_no_fk FOREIGN KEY(post_no) REFERENCES region_board(post_no) ON DELETE SET NULL;

ALTER TABLE sale_board_bookmark ADD
CONSTRAINT sale_board_bookmark_bookmark_no_pk PRIMARY KEY(bookmark_no);

ALTER TABLE sale_board_bookmark ADD
CONSTRAINT sale_board_bookmark_member_id_fk FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE;

ALTER TABLE sale_board_bookmark ADD
CONSTRAINT sale_board_bookmark_post_no_fk FOREIGN KEY(post_no) REFERENCES sale_board(post_no) ON DELETE SET NULL;


