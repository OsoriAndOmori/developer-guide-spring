<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Script Example</title>
</head>
<body>
<h1>JSP Script Example</h1>
<p>
    <!-- Java 스크립틀릿을 사용하여 랜덤 숫자 생성 -->
    <%
        int randomNumber = (int) (Math.random() * 100); // 0부터 99 사이의 랜덤 숫자
        System.out.println("Generated Random Number: " + randomNumber); // 랜덤 숫자 출력
    %>
</p>
<!-- 서버에서 전달된 데이터를 JavaScript 변수에 삽입 -->
<script>
    var message = "${message}";
    console.log(message);
</script>
</body>
</html>