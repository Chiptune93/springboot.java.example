# File Upload

## Feature

- 파일업로드 4가지 패턴 예시
  - 단순 파일 서버 경로(프로젝트 경로) 업로드
  - 서버에 업로드 후, 파일 정보 DB 저장
  - 서버에 업로드 후, 파일 정보 DB 저장 및 예외 처리 등
  - 다중 업로드 지원
- http://localhost:8080/ 에서 테스트 가능

## Table Create SQL

- Execute this SQL before test

```sql
1. file

CREATE TABLE public.file (
	seq numeric NULL,
	master_seq numeric NULL,
	file_name varchar NULL,
	file_path varchar NULL,
	file_type varchar NULL,
	file_size varchar NULL,
	file_ext varchar NULL
);
COMMENT ON TABLE public.file IS 'file upload test';

CREATE SEQUENCE public.seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

2. file_master

CREATE TABLE public.file_master (
	master_seq numeric NULL,
	reg_id varchar NULL,
	reg_dttm timestamp NULL
);
COMMENT ON TABLE public.file_master IS 'file upload master';

CREATE SEQUENCE public.master_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

```
