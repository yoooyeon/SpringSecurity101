# SpringSecurity101

다음 블로그에 잘 정리되어 있어 참고했다. 

이 프로젝트에서는 Spring Security 6.x 버전을 사용했다.

[\[ Spring Security \] 스프링 시큐리티 로그인 5.7이상 버전 \( 6.x 버전 \) \( JPA , 로그인 기억하기 \)](https://dev-log.tistory.com/4)

## 기존 코드에서 수정한 내용
1. MemberPrincipalDetails를 record로 변경
2. Member 클래스에 불필요한 컬럼 제거
3. controller의 url
4. 화면 디자인

## 추가 할 기능
1. admin 권한을 만들어 역할 구분하기
2. jwt 토큰 관리하기
    - access token, refresh token 으로 나눈다.
    - redis에 refresh token을 저장한다.
3. 비밀번호 validation을 custom하여 체크한다.



## Spring Security를 활용하여 로그인을 구현 정리
1. SecurityConfig 파일 생성
2. SecurityFilterChain을 반환하는 빈 생성
   - login, logout 페이지 설정 
   - csrf 설정
   - request url에 따른 접근 권한 부여
3. UserDetails, UserDetailsService 구현체 생성
   - UserDetails는 로그인 id, password, 로그인 한 사용자의 정보를 담고있다.
   - UserDetailsService의 `loadUserByUsername()`를 구현하여 해당 id의 사용자 정보를 가져오는 역할이다.
4. AuthenticationProvider 구현체 생성
   - 이름처럼 인증을 제공하는 역할이다.
   - 화면에서 사용자가 입력한 id와 pw로 db의 pw를 비교하여 인증한다.
   - 인증이 성공하면 UsernamePasswordAuthenticationToken를 반환한다.
   - 이 토큰은 SecurityContextHolder.getContext()에 저장된다.
### Authority와 Role의 차이점은?

### Access Token과 Refresh Token을 모두 사용하는 이유는?
   
