INSERT INTO region_board(member_id, title, content, views, like_cnt, hate_cnt, report_cnt, writing_area)
SELECT
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    ),
	'TITLE_'||level,
	'CONTENT_'||level,
	level,
	level,
	level,
	level,
    level
FROM dual
CONNECT BY level <= 100000;

COMMIT;


INSERT INTO sale_board(member_id, title, content, views, like_cnt, hate_cnt, report_cnt, writing_area)
SELECT
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    ),
	'TITLE_'||level,
	'CONTENT_'||level,
	level,
	level,
	level,
	level,
    level
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO region_board_comment(post_no, member_id, secret_yn, content, like_cnt, high_comment_no)
SELECT
	(
        SELECT post_no
        FROM region_board
        WHERE rownum = 1
    ),
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    ),
	'Y',
	'CONTENT_'||level,
	level,
	level
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO sale_board_comment(post_no, member_id, content, like_cnt, high_comment_no)
SELECT
	(
        SELECT post_no
        FROM sale_board
        WHERE rownum = 1
    ),
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    ),
	'CONTENT_'||level,
	level,
	level
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO region_board_file(file_id, post_no, file_name, file_path, file_size)
SELECT
	'FILE_'||level,
	(
        SELECT post_no
        FROM region_board
        WHERE rownum = 1
    ),
	'FILENAME_'||level,
	'FILEPATH_'||level,
	level
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO sale_board_file(file_id, post_no, file_name, file_path, file_size)
SELECT
	'FILE_'||level,
	(
        SELECT post_no
        FROM sale_board
        WHERE rownum = 1
    ),
	'FILENAME_'||level,
	'FILEPATH_'||level,
	level
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO member_keyword
SELECT
	'KEYWORD'||level,
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    )
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO region_board_keyword_not(not_content, not_dt, not_read_dt, post_no, keyword, member_id)
SELECT
	'CONTENT_'||level,
	CURRENT_TIMESTAMP,
	CURRENT_TIMESTAMP,
	(
        SELECT post_no
        FROM region_board
        WHERE rownum = 1
    ),
	'KEYWORD'||level,
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    )
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO sale_board_keyword_not(not_content, not_dt, not_read_dt, post_no, keyword, member_id)
SELECT
	'CONTENT_'||level,
	CURRENT_TIMESTAMP,
	CURRENT_TIMESTAMP,
	(
        SELECT post_no
        FROM sale_board
        WHERE rownum = 1
    ),
	'KEYWORD'||level,
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    )
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO region_board_act_not(member_id, post_no, not_target_member_id, not_content, not_type, not_dt, not_read_dt)
SELECT
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    ),
	(
        SELECT post_no
        FROM region_board
        WHERE rownum = 1
    ),
	level,
	'CONTENT_'||level,
	'comment',
	CURRENT_TIMESTAMP,
	CURRENT_TIMESTAMP
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO sale_board_act_not(member_id, post_no, not_target_member_id, not_content, not_type, not_dt, not_read_dt)
SELECT
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    ),
	(
        SELECT post_no
        FROM sale_board
        WHERE rownum = 1
    ),
	level,
	'CONTENT_'||level,
	'comment',
	CURRENT_TIMESTAMP,
	CURRENT_TIMESTAMP
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO region_board_bookmark(member_id, post_no)
SELECT
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    ),
	(
        SELECT post_no
        FROM region_board
        WHERE rownum = 1
    )
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO sale_board_bookmark(member_id, post_no)
SELECT
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    ),
	(
        SELECT post_no
        FROM sale_board
        WHERE rownum = 1
    )
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO used_board(member_id, title, content, views, write_dt, report_cnt, sale_status, writing_area_id, price)
SELECT
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    ),
	'TITLE_'||level,
	'CONTENT_'||level,
	level,
	CURRENT_TIMESTAMP,
	level,
	0,
	level,
	level
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO used_board_bookmark(member_id, post_no)
SELECT
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    ),
	(
        SELECT post_no
        FROM used_board
        WHERE rownum = 1
    )
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO used_board_act_not(member_id, post_no, not_target_member_id, not_content, not_type, not_dt, not_read_dt)
SELECT
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    ),
	(
        SELECT post_no
        FROM used_board
        WHERE rownum = 1
    ),
	level,
	'CONTENT_'||level,
	'comment',
	CURRENT_TIMESTAMP,
	CURRENT_TIMESTAMP
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO used_board_keyword_not(not_content, not_dt, not_read_dt, post_no, keyword, member_id)
SELECT
	'CONTENT_'||level,
	CURRENT_TIMESTAMP,
	CURRENT_TIMESTAMP,
	(
        SELECT post_no
        FROM used_board
        WHERE rownum = 1
    ),
	'KEYWORD'||level,
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    )
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO used_board_comment(post_no, member_id, secret_yn, content, like_cnt, high_comment_no)
SELECT
	(
        SELECT post_no
        FROM used_board
        WHERE rownum = 1
    ),
	(
        SELECT member_id
        FROM member
        WHERE rownum = 1
    ),
	'Y',
	'CONTENT_'||level,
	level,
	level
FROM dual
CONNECT BY level <= 100000;

COMMIT;

INSERT INTO used_board_file(file_id, post_no, file_name, file_path, file_size)
SELECT
	'FILE_'||level,
	(
        SELECT post_no
        FROM used_board
        WHERE rownum = 1
    ),
	'FILENAME_'||level,
	'FILEPATH_'||level,
	level
FROM dual
CONNECT BY level <= 100000;

COMMIT;
rollback;