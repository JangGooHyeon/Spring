<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="/css/signin.css" rel="stylesheet">

  </head>

  <body>

<%--  	
		파라미터 전송을 위해 신경 쓸 부분 
		
		1. 어디로 보내는지 ?? : form tag action속성
			-->> 로그인 처리 요청 : LoginServlet doPost
			
		2. 어떻게 보낼지 ?? : form tag method
			-->> post :  사용자 비밀번호와 같이 보안 이슈가 발생할 수 있는 상황이므로 get방식으로 보내지 않는다.
			
		3. 뭘 보낼지 ?? : input, select, textarea의 name속성	
			-->> userId, password 
--%>
		
    <div class="container">
      <form class="form-signin" action="/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input name="userId" value="goo8455" type="text" id="userId" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="pass" value="qwer1234"	type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <label>아버지 날 보고 있다면 정-답을 알려줘...</label>
        <div class="checkbox">
          <label>
            <input type="checkbox" id="rememberme" value="remember-me"> 나를 잊지 말아요
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" id="signin">Sign in</button>
      </form>

    </div> <!-- /container -->

  </body>
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${cp }/js/cookieUtil.js" type="text/javascript"></script>
  <script src="${cp }/js/js.cookie.js" type="text/javascript"></script>
  <script type="text/javascript">
  	$(document).ready(function() {
  		
  		console.log("cp : ${cp}");
  		
  		//userId 쿠키 값이 있을경우 userId input에 설정
  		if(Cookies.get("userId")){
  			$("#userId").val(Cookies.get("userId"));
  			$("#rememberme").prop("checked",true);
  		}
  		
  		
		//sign in button 클릭 이밴트 핸들러
		$("#signin").click(function(){
			
			// 1. rememberme 체크박스가 체크 되었을 경우 
			//	사용자 아이디 input에 저장된 값을 cookie이름 : userId / cookieValue : 입력된 값 으로 쿠키를 생성
			// 2. rememberme 체크박스가 체크되어있지 않을 경우
			//	cookie이름 : userId --> cookie삭제
			
			//rememberme 체크박스가 체크 된 경우
			if($("#rememberme").prop("checked")){
				Cookies.set("userId", $("#userId").val(), {expries : 30});
				Cookies.set("rememberme", "y", {expries : 30});
			} else {
				Cookies.remove("userId");
				Cookies.remove("rememberme");
			}
			
			$("form").submit();
		});
	});
  </script>
  
</html>
