## LV 0 - 요금 폭탄 방지 AWS Budget 설정

## 월별 예산 금액 100달러로 지정
<img width="579" height="531" alt="Image" src="https://github.com/user-attachments/assets/481181bd-9978-4d0f-bbef-5186fb8a72d6" />

## 예산 80퍼 도달 시 이메일 알림 지정
<img width="555" height="661" alt="Image" src="https://github.com/user-attachments/assets/2b427103-9678-4bcc-9af9-1dd7d632ccc8" />

---

## VPC 설계
<img width="808" height="387" alt="Image" src="https://github.com/user-attachments/assets/be4e9819-ee62-4329-a396-15e4bed6fbae" />

## EC2 생성
<img width="1624" height="584" alt="Image" src="https://github.com/user-attachments/assets/bd707ccc-e988-4c45-9d73-c0216f03b07d" />

## EC2 배포 후 실제로 작동하는지 검증
<img width="396" height="340" alt="Image" src="https://github.com/user-attachments/assets/4da1c2ca-03f7-4df7-8761-26957f93cec1" />

로컬로 환경으로 H2 데이터베이스 상태에서 검증하였습니다.

EC2 퍼블릭 IP = 3.38.93.114

## DB 분리 및 보안 연결
<img width="503" height="275" alt="Image" src="https://github.com/user-attachments/assets/15e2d33b-f162-4f3b-b348-ea5c1de8731e" />

url = http://3.38.93.114:8080/actuator/info

<img width="1450" height="231" alt="Image" src="https://github.com/user-attachments/assets/1db9d318-4db4-4085-8e2c-5b8fbae8cb45" />

보안그룹을 추가하여 지정해주었습니다.

## 프로필 사진 기능 추가와 권한 관리
Presigned URL = [Presigned URL](https://cloud-work-seonghoon-uploads.s3.ap-northeast-2.amazonaws.com/uploads/be8a5bf9-fabc-4159-947f-ef6ae33d304e_GsK7VkbXYAAWM2Q.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEAgaDmFwLW5vcnRoZWFzdC0yIkcwRQIhAJWeWl7TITcqVXJXr1LTjpOgK9vbck%2FYZK9gifmoZ%2Bt1AiBTtkxpdvgzXOevUgBrfeITuUnmucBJOFGRFePypS8ZFirTBQjR%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDk3MTEyOTM0OTM1MSIMA%2FhfsruzPx8eqTdcKqcFk1UWzUKyCc7BQnAUa%2FHGI3nM2pH0aUoNoI49rVwKbOg7SpHHQ4m%2FGqUS0Zquk9do3l5RaPNgIl2yaxFTo09o0Z6damnvsrFwZHrzY%2Fx19AD0dV75fYNdH8iq%2BKAHh1zHZB4JcmhYcVZzihQNEtpFp2raLkDt6kEGCFmVx0Y07gS4rudsOaZfJCP1%2FuiOpEK0SRB48KRSadl5HxY%2FEnw91CgDaSSTOOQhH%2FELjqB9BcrQ%2FErcAlzyl5j4K4uN%2Fa7mPEriMUq5i%2BFUA0W36AY%2BfsvW9iyouR%2Fw1SCCFyYWVLCjV1Ga%2B3%2BK8%2FwQYJ%2FOwl9WrXabisp8tvFMl%2FiVykLjlUPybaRE3R4JBbb7FL58PWXMfPPyj9XqJSD%2BRoF3quxIh7XZWPtVJSrvK1cf5NpZUySq0GG06L9%2FGT5rwD3DRN9T3WNJHw1qJ0YMLPJBGj8o09hUHIWB%2FyemM0OIHNGw%2BDGmdr36zbdvo7a%2F3KnghxSP1XApaWikWx09do%2FOSUpjvezMyxCDaArar8bOIxTUHAa3dD1ZwjHyyYBa0n7SuFV%2BlSgCjSk2iJMlIvrt7%2Byj0O4OGsBWkAIkGPjPB%2BP55Ww1ssHSke8FEbVyaub3DaV8AkyleO22P7Ie5qJLffOxEc41Rm51cot6D9RL7%2F3CBYhS9IsHLvi8gLKknjYJJ5xyEyxVA3X3qvjCQAIhASY%2FpzGgZrZ6Bnri1UQ52lnRmaP5Z4tMBlMzxgF1M8REIvdLQmRzM93Ltm2ZbvTODQmAJpqMY5%2BOfTpTOOrw0RUNqfrLBbSYQ5YsW0l88d%2BA9R9oBNoc2dXhbHMI5ZLa1ZVxR5TMVVTnrj5v9R9%2FD673i59Usj4UR%2FWs8McLFnr1fyIK3G%2BhgZIKqbAV46czHHJYMszDOr8x7jDmsrDQBjqxAXM%2BurQ309BLRZUmrmvM%2FMJjHa8NZXzzPp%2FItbsQsSoetd1B5UBBgStY%2FkFSF0Meog%2FFMykCdQBM6ePGJ%2BheBCB4wtQNku49ZY8qlrv6upO1Rf9FO%2FFYv4NMlcNH5iIkvkTuT51y%2BcKdGAAwmbTm46CeoV14TRL%2F71jd4Qku4Xql2h346PZoqKRjttUCFnFFz4djzFQc4TrHd0HKIHEg5QRRmsO%2BJcn0WG16n9yHdebd9A%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20260519T080459Z&X-Amz-SignedHeaders=host&X-Amz-Credential=ASIA6EG6R4TTWBL4I7UA%2F20260519%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Expires=604800&X-Amz-Signature=2635efeff12446a8a7c11513239b9a47ba0fc0932967cbf0dfa861417c425329)

URL 만료기간 = 7일 (생성일 = "26.05.19" / 만료일 = "26.05.26")

## Docker & CI/CD 파이프라인 구축
<img width="730" height="422" alt="Image" src="https://github.com/user-attachments/assets/1f145c50-f7f9-47d7-b0b3-95a91a807b8f" />

Github Actions CI / CD 성공 이미지

<img width="1160" height="110" alt="Image" src="https://github.com/user-attachments/assets/6989f090-fc02-4e8a-973f-4ae580e43e11" />

EC2에서 확인한 도커 실행 화면

## 고가용성 아키텍처와 보안 도메인 연결
HTTPS 적용된 도메인 URL = http://api.seonghoon.click/actuator/health

<img width="1817" height="369" alt="Image" src="https://github.com/user-attachments/assets/50b24930-a6d8-4106-8fb4-6cb9c478e058" />

Target Group 이미지 