# AI 기반 처방전/시술 안내문 분석 
NaverClovaOCR, ClaudeAPI

## 데모

https://github.com/user-attachments/assets/9b9ff623-0a6b-4291-83b1-e511f6951d8e

> 갤러리에서 시술 안내문 선택 → AI 분석 → 결과 확인

## 기능 흐름

- 갤러리에서 사진 선택
- AI 문서 분석 (OCR + Claude)
- 결과 정리해서 표시

## 기술 스택

### Android
- Kotlin
- Jetpack Compose
- Retrofit2
- Hilt

### Backend
- FastAPI (Python)
- Naver Clova OCR
- Claude AI (Anthropic)
- AWS EC2
