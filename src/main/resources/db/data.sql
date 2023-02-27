insert into user_tb(username, password, email, address, detail_address, tel, real_name, career, resume_title, resume_content, created_at) values('ssar', '1234', 'ssar@nate.com', '부산시 야호구 야동','11길','01023452344','김살',1,'이력서제목1','이력서내용1', now());
insert into user_tb(username, password, email, address, detail_address, tel, real_name, career, resume_title, resume_content, created_at) values('love', '1234', 'love@nate.com', '부산시 야호구 상동','12길','01011111111','박사랑',0,'이력서제목2','이력서내용2', now());
insert into user_tb(username, password, email, address, detail_address, tel, real_name, career, resume_title, resume_content, created_at) values('google', '1234', 'google@nate.com', '부산시 야구 김동','13길','01012331233','제갈구글',3,'이력서제목3','이력서내용3', now());
insert into user_tb(username, password, email, address, detail_address, tel, real_name, career, resume_title, resume_content, created_at) values('naver', '1234', 'naver@nate.com', '부산시 박박구 쿠동','14길','01034566666','독고네이버',5,'이력서제목4','이력서내용4', now());
insert into user_tb(username, password, email, address, detail_address, tel, real_name, career, resume_title, resume_content, created_at) values('noew', '1234', 'noew@nate.com', '부산시 상상구 모동','15길','01066666666','상냥이',6,'이력서제목5','이력서내용5', now());

insert into company_tb(username, password, email, address, detail_address, tel, company_name, company_scale, company_numb, company_field, created_at) values('cos', '1234','cos@nate.com', '부산시 빡구 동동', '71길', '01012341234', '(주)미친회사', '대기업', 333322221111,' IT업', now());
insert into company_tb(username, password, email, address, detail_address, tel, company_name, company_scale, company_numb, company_field, created_at) values('kit', '1234','kit@nate.com', '부산시 봉구 원동', '72길', '01032113211', '(주)친친회사', '중견기업', 122322421111,' IT업', now());

insert into board_tb(company_id, title, content, career, created_at) values(1, '공고제목1','공고내용1', 1, now());
insert into board_tb(company_id, title, content, career, created_at) values(1, '공고제목2','공고내용2', 3, now());
insert into board_tb(company_id, title, content, career, created_at) values(2, '공고제목3','공고내용3', 6, now());
insert into board_tb(company_id, title, content, career, created_at) values(2, '공고제목4','공고내용4', -1, now());

insert into apply_tb(user_id, board_id, created_at) values(1, 1, now());
insert into apply_tb(user_id, board_id, created_at) values(2, 1, now());
insert into apply_tb(user_id, board_id, created_at) values(1, 2, now());
insert into apply_tb(user_id, board_id, created_at) values(2, 2, now());

insert into tech_tb(user_id, java, c_lang, python, php, jsc, ruby, assembly_lang, sql_lang, created_at) values(1, 'on', null, null, null, 'on', null, null, 'on', now());
insert into tech_tb(user_id, java, c_lang, python, php, jsc, ruby, assembly_lang, sql_lang, created_at) values(2, 'on', null, null, 'on', null, null, 'on', null, now());
insert into tech_tb(user_id, java, c_lang, python, php, jsc, ruby, assembly_lang, sql_lang, created_at) values(3, 'on', null, 'on', null, null, 'on', null, null, now());
insert into tech_tb(user_id, java, c_lang, python, php, jsc, ruby, assembly_lang, sql_lang, created_at) values(4, null, 'on', null, 'on', null, null, 'on', 'on', now());

insert into tech_tb(board_id, java, c_lang, python, php, jsc, ruby, assembly_lang, sql_lang, created_at) values(1, 'on', 'on', null, 'on', null, 'on', null, null, now());
insert into tech_tb(board_id, java, c_lang, python, php, jsc, ruby, assembly_lang, sql_lang, created_at) values(2, 'on', null, null, 'on', 'on', null, 'on', 'on', now());
insert into tech_tb(board_id, java, c_lang, python, php, jsc, ruby, assembly_lang, sql_lang, created_at) values(3, null, null, 'on', null, 'on', 'on', 'on', null, now());
insert into tech_tb(board_id, java, c_lang, python, php, jsc, ruby, assembly_lang, sql_lang, created_at) values(4, null, 'on', 'on', null, null, 'on', null, 'on', now());

commit; 