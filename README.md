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
Presigned URL = [Presigned URL](https://cloud-work-seonghoon-uploads.s3.ap-northeast-2.amazonaws.com/uploads/be8a5bf9-fabc-4159-947f-ef6ae33d304e_GsK7VkbXYAAWM2Q.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEB4aDmFwLW5vcnRoZWFzdC0yIkgwRgIhALrSW3a7kUAQXYjXeJ0WxiD3rKrhcKpX6K7RdGAh3ZavAiEAkEPietk8MkGGhKXq3Yfp3QC01MG7V8K1tUkOWCah2Noq1AUI5%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgw5NzExMjkzNDkzNTEiDN2hrz2jCiCRCH8RiiqoBc0D1Cz8hLPef8jilvKKBR37%2BjgTphUPS%2F0R6g9GSpywSjydPsRibaOryz2BsylkcZDcZb5AZHRH9JrL3Or57NZFUbe%2F%2BfpahAwBuvmkbSp13WU5NoBPoAFdrZipcQ8%2F%2Bk6xzTYFEaHjtT4dQ2Nyu138TF%2FPAyBKnwyyPsC0hzkxLCV8qTiADTegc095SqyjrpBAxQ50amOnifljxePmyMRU7uW5UB152zhThZS611MhvTyafHrkAYM7MYw6S4EgwNhPknXnKd1Yu7AJCvojcZjgsAh1%2FefrzX%2B9b0reTTlQnr2PvGGDDzeTJDrB6RNl8jKP0R17jjXtokibujDI%2Boc65aHRbkbyilrDhmo%2FyByw4tlGcNgDJ4jf1n%2BSrRbKGJD%2FSJ65niph%2FrzxB6uwQgHYOvlPMD5txATrFUnpW%2BI6PZuhNKJLHw4S8Nyrd6mwG2Xe4UJeEZo5PLF76BG2mA%2Btq6IgbSl37%2BZUvhqtAFi6zcdH1R3wTvWac9KeBexYSe0xIfqAJchm19rZIrhdg03blDm1dAVLW6WPhU1xJJkU1asfwOqaj8SZjhFWtL%2BD8JWgsgx2BHPuDupxuZVAoU5V5MlsfHegi5TMOMB5JU7gSREYErI6GpLemGx3JdoCjigtx55MI7Pq0r38rvxVhCqJQJyn9UZWxoDDHmWzwPBNz3NPnvhETs036o1gUZ1ng7%2FhLibVRwRyik8bOizSjcxerZypDfSC%2BlGlqWKMivWe8LYhoVkjJpZjb8oDG3s78XqFhoEpO4%2FBPxCG%2FhNEX7o%2FlNfYxcXlxH8nY9Z6bEJS%2BQNVYq6zjYNrj1WxVs%2FpftzbucoxSUx3qg7Rn%2BFmqnfe45hGVYH5VyDsh9JhPqDAFaUUm1sfD9oBDrJzlWTDBnyaUqtrtFu1MPqitdAGOrABRVZqN1spOc2kQvn4247gyXMMVQ%2FIKYwn5n1Sbd%2FNgXeJkk7lpxQyIZ5XsilGoRHGtHJJ2uwV9dqoi04AscuJfojF0lop%2FtUwojYFX7qJTmjmapaEV4zwDJ6Zw8WHAbtWIZ%2FtEli60DoqROiJNqVpbMBqXG%2FLq4EEA5ALJKM075TLlhngFLJ4zlHwSTBabNqaYpub5pMy2Hvah3W1afzBAfkXTTe0XEXI2Q8kv1GX4Lc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20260520T070038Z&X-Amz-SignedHeaders=host&X-Amz-Credential=ASIA6EG6R4TT3IXL6MWG%2F20260520%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Expires=604800&X-Amz-Signature=c14026e3291c075fca0d6385d34f578195895650b4bdd41dbcf793db70335d99)

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