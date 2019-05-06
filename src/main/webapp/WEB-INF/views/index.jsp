<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>하랑대학교 | 로그인</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.2 -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="resources/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />
    <style>
        html,
        body {
            margin:0;
            padding:0;
            height:100%;
        }
        .wrapper {
            position:relative;
            min-height:100%;
            background-image:url("resources/dist/img/index_back.jpg");
            background-position: center;
            background-size: 100%;
        }
        header {
            height:60px;
            background:#ffffff;
        }
        footer {
            position:absolute;
            bottom:0;
            width:100%;
            height:70px;   
            background:#ffffff;
        }
        .login-box{
            margin-top: 200px;
            margin-bottom: 300px;
        }
    </style>
  </head>
  <body class="login-page">
  <div class="wrapper">
   <header class="main-header">
        <!-- Logo -->
        <a href="#" class="logo">
        <img src="resources/dist/img/Logo.png" class="img-circle" alt="User Image" width="40" height="40"/>
        &nbsp;<b>하랑</b> 대학교</a>
        <!-- Header Navbar: style can be found in header.less -->
   </header>
    <div class="login-box">
      <div class="login-logo">
         <img src="resources/dist/img/Logo.png" class="img-circle" alt="User Image" width="75" height="75"/><br>
        <a href="#"><font color="#FFFFFF"><b>하랑</b>대학교</font></a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">커뮤니티에 접속하시려면 로그인을 하세요</p>
        <form method="post" action="/login/login">
          <div class="form-group has-feedback">
            <input type="text" class="form-control" name="m_id" placeholder="학번" required="required"/>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" name="m_pw" placeholder="비밀번호" required="required"/>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">    
            </div><!-- /.col -->
            <div class="col-xs-4">
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> 
              <input type="submit" value="로그인" class="btn btn-primary btn-block btn-flat">
            </div><!-- /.col -->
          </div>
        </form>
        <a href="#" id="btnPopup">포트폴리오 사이트 안내</a><br>
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->
    
     <footer>
        <div class="pull-right hidden-xs">
          <b>Version</b> 2.0
        </div>
        <strong>Web Copyright &copy; 2017 The Center Team.</strong> All rights reserved.<br>
        <Strong>팀장 : 나현기, 부팀장 : 양혜민, 팀원 : 김성지, 김민준, 서지윤, 박주선 </Strong><br>
        <strong>Template Copyright &copy; 2014-2015 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights reserved.
     </footer>
    </div><!-- .\ wrapper --> 
    
                    <!-- 모달 : 뒷 페이지 배경을 눌러도 꺼지지 않음 -->
                <div class="modal fade" id="theModal" data-backdrop="static">
                    <div class="modal-dialog">
                        <form name="zip" action="">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3>하랑 대학교 커뮤니티 안내</h3>
                            </div>
                            <div class="modal-body">
                          		<h4><strong>저희 하랑 대학교 커뮤니티에 방문해 주셔서 감사합니다.</strong></h4>
                          		<p>이 사이트는 가상의 '하랑대학교'를 선정하고 포인트 중심으로 학교와 사이트 사이에 가상의 경제 시스템을 구축한 학생 커뮤니티 사이트입니다.</p>
                          		<ul>
                          			<li><strong>Web Copyright &copy; 2017 The Center Team.</strong> All rights reserved.</li>
                          			<li><strong>Template Copyright  &copy; 2014-2015 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights reserved. </li>
                          			<li>팀장 : 나현기, 부팀장 : 양혜민, 팀원 : 김성지, 김민준, 서지윤, 박주선 </li>
                          			<li>이전 버전 (JSP-Servlet) : <a href="http://netsong7.synology.me:8080/HarangProject/index.jsp">http://netsong7.synology.me:8080/HarangProject/index.jsp</a></li>
                          			<li>개발 환경  : Spring, Mybatis, bootstrap3, HTML5, Jquery, Javascript</li>
                         			<li>스프링 리펙토링 기간 : 2017-08-01 ~ 2017-08-16</li>
                          		</ul>
                          		<br>
                          		<h4><strong>접속 방법</strong></h4>
                          		<p>1. 학생(회원)으로 접속시</p>
                          		<ul>
                          			<li>회원아이디, <i>'2017<strong>01</strong>001'</i> 부터 <i>'2017<strong>01</strong>020'</i> 까지 신규 등록 회원 입니다. 해당회원으로 접속시 '신규 회원 등록 창'으로 이동합니다.</li>
                          			<li>회원아이디, <i>'2017<strong>05</strong>001'</i> 부터 <i>'2017<strong>05</strong>020'</i> 까지 등록된 회원 입니다. 해당회원으로 접속시 '회원 메인 창'으로 이동합니다.</li>
                          			<li>해당 회원 아이디의 비밀번호는 모두 <strong>'1111'</strong>로 동일 합니다.</li>
                          		</ul>
                          		<p>2. 관리자로 접속시</p>
                          		<ul>
                          			<li>관리자아이디는 <strong>'admin01'</strong> 부터 <strong>'admin06'</strong> 까지 존재 합니다.</li>
                          			<li>관리자아이디로 접속시 '관리자 메인 창'으로 이동합니다.</li>
                          			<li>해당 관리자의 비밀번호는 모두 <strong>'1111'</strong>로 동일 합니다.</li>
                          		</ul>
                          		
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary" data-dismiss="modal">닫기</button>
                            </div>
                        </div>
                        </form>
                    </div>
                </div><!-- 모달 끝 -->
    <!-- jQuery 2.1.3 --> 
    <script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="resources/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <script type="text/javascript">
	    //모달 접근 바로 하기
	    $("#btnPopup").click(
	        function(){
	            //alert("버튼 눌림");
	            $("#theModal").modal('toggle');
	        });
    </script>
  </body>
</html>