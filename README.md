## Get-Started
- `docker-compose up -d` # 완전 초기화 희망하는 경우 container 멈추고 .db-data 삭제뒤 재실행

## spring 테스트 용 Multi Module Application
- application layer
    - 실제 Application 담당할 layer, 현 mvc, reative, batch + cloud 까지 추가 예정
- domain layer
    - application 에서 쓸 domain 별 기능 분리
    - 로직은 없이 설정 위주로 들어갈 예정이나 공통된 로직의 경우 구현 가능
- etc
    - yaml-importer : domain 의 설정을 읽어주는 yaml integration tool
        - application 에선 최상위 모듈에 `implementation project(':etc-yaml-importer')` 만 추가시 자동 설정 로드

## Spec
- domain-mysql : RDBMS