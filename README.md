# league of legend 전적 검색 back-end


## 사용한 기술 스택
- Spring boot (API Server)
- Spring Security (Security)
- Mysql (RDB)
- JPA,Query_DSL (ORM)
- JWT (Login)
- AWS (Infra)
- jenkins (CI/CD)
- S3 (file upload)

## 배포

![image](https://user-images.githubusercontent.com/66015002/128163954-cea47773-5f7b-40bf-988f-da6ab36c5bf5.png)


(참고용)

8080 포트 확인 :  sudo netstat -lnp | grep 8080

포트 죽이기 : sudo kill (아이디)


## 로그인 처리(JWT , oauth2(추가예정))


![image](https://user-images.githubusercontent.com/66015002/127429984-6dd46f53-4fb2-4976-aaa4-f78dff8a65a3.png)

## DB(RDS, 챔피언 상세정보 저장)

![image](https://user-images.githubusercontent.com/66015002/127766860-db041f47-bf34-4cde-aa70-cd786e6ce75b.png)

## file upload

S3 bucket 이용, multipart form-data로 파일 업로드
![image](https://user-images.githubusercontent.com/66015002/128161006-f5ee5f32-1fd8-41b9-9093-aa9a8906ed7a.png)


## RIOT API 서버단 호출
