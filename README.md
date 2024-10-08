## [같이 볼 문서](https://osoriandomori.github.io/posts/%EB%82%B4-%EB%A7%98%EB%8C%80%EB%A1%9C-%ED%8C%80-%EC%9E%85%EC%82%AC-%EA%B5%90%EC%9C%A1%EA%B3%BC%EC%A0%95/)

## Get-Started
- `docker-compose up -d` # 완전 초기화 희망하는 경우 container 멈추고 .db-data 삭제뒤 재실행

## spring 테스트 용 Multi Module Application
- application layer
- core layer : app layer 의 공통 core 모듈
- domain layer
    - application 에서 쓸 domain 별 기능 분리
    - 로직은 없이 설정 위주로 들어갈 예정이나 공통된 로직의 경우 구현 가능
- etc
    - yaml-importer : domain 의 설정을 읽어주는 yaml integration tool
        - application 에선 최상위 모듈에 `implementation project(':etc-yaml-importer')` 만 추가시 자동 설정 로드

## 직접 해보면 좋은 것
- application-context (spring container) 구현하기
- application-servlet 에 `@ComponentScan` 구현하기