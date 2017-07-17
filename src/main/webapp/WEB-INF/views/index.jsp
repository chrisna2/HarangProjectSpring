<%@ page contentType="text/html; charset=UTF-8"%>
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
        <form name="login" action="/login/loginPost" method="post">
          <input type="hidden" name="cmd" value="login"/>
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
              <button type="submit" class="btn btn-primary btn-block btn-flat"><i class="fa fa-sign-in"></i> 로그인</button>
            </div><!-- /.col -->
          </div>
        </form>
        <a href="#">비밀번호 찾기</a><br>
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->
    <!-- 완성시 최종 삭제 -->
    <div class="login-box" >
      <div class="login-box-body">
           <h3>인덱스</h3>
           <h4>내 정보</h4>
               <ul>
                <li>user side</li>
                <li><a href="/myPage/myInfo">내 정보 조회</a></li>
                <li><a href="/myPage/specUp">스펙업!</a></li>
                <li><a href="/myPage/timeTable">수강 시간표!</a></li>
                <li><a href="/myPage/pointList">포인트 거래 내역</a></li>
                <li><a href="/myPage/pointZero">포인트 제로</a></li>
                <li><a href="/HarangProject/message?cmd=INBOX">쪽지함(관리자 개발자 공통) </a></li>
                <li>admin side</li>
                <li><a href="/myPage/AmemList">회원명단</a></li>
                <li><a href="/myPage/Achallenge">스펙 업 관리</a></li>
                <li><a href="/myPage/AspecList">자격증 관리</a></li>
                <li><a href="/myPage/Alesson">강의목록 관리</a></li>
                <li><a href="/myPage/ApointCheck">포인트 관리</a></li>
               </ul>
           <h4>하랑 레스토랑</h4>
               <ul>
                <li>user side</li>
                <li><a href="/food/menu">학식 메뉴 조회</a></li>
                <li><a href="/food/ticket">식권 구매 내역</a></li>
                <li>admin side</li>
                <li><a href="/food/Amenu">학식 메뉴 수정</a></li>
                <li><a href="/food/Aticket">식권 판매 내역</a></li>
               </ul>
           <h4>알바 하랑</h4> 
               <ul>
                 <li>user side</li>
                 <li><a href="/HarangProject/parttime?cmd=PMAIN">알바 모집</a></li>
                 <li><a href="/HarangProject/parttime?cmd=DMAIN">대타 모집</a></li>
                 <li><a href="/HarangProject/parttime?cmd=MYPAGE">내가 쓴 글</a></li>
                 <li>admin side</li>
                 <li><a href="/HarangProject/parttime?cmd=PMAIN">알바 모집 관리</a></li>
                 <li><a href="/HarangProject/parttime?cmd=DMAIN">대타 모집 관리</a></li>
               </ul>
            <h4>대나무 숲</h4>
            <ul>
                 <li>user side</li>
                 <li><a href="/HarangProject/bamboo?cmd=BB_LIST">메인 페이지</a></li>
                 <li>admin side</li>
                 <li><a href="/HarangProject/bamboo?cmd=A_BB_LIST">게시판 관리자 메인</a></li>
              </ul>
            <h4>학사일정</h4>
            <ul>
                 <li>user side</li>
                 <li><a href="/HarangProject/schedule?cmd=U_SCH_LIST">학사일정 메인 페이지</a></li>
                 <li>admin side</li>
                 <li><a href="/HarangProject/schedule?cmd=A_SCH_LIST">학사일정 관리자 메인</a></li>
              </ul>
                 <h4>강의평가</h4>
                <ul>
                 <li>user side</li>
                 <li><a href="/HarangProject/impage?cmd=main">강의평가 메인 페이지</a></li>
                 <li>admin side</li>
                 <li><a href="/HarangProject/impage?cmd=amain">강의평가 관리자 메인</a></li>
              </ul>
                 <h4>하랑딘24</h4>
            <ul>
                 <li>user side</li>
                 <li><a href="/HarangProject/harangdin?cmd=main">하랑딘 메인 페이지</a></li>
                 <li>admin side</li>
                 <li><a href="/HarangProject/harangdin?cmd=adminMain">하랑딘 관리자 메인</a></li>
              </ul>
            <h4>시설관리</h4>
            <ul>
                 <li>user side</li>
                 <li><a href="/HarangProject/facil?cmd=FacilMain">사용자 메인</a></li>
                 <li><a href="/HarangProject/facil?cmd=FacilPGreserv">사용자 운동장 예약</a></li>
                 <li><a href="/HarangProject/facil?cmd=FacilSRreserv">사용자 스터디룸 예약</a></li>
                 <li>admin side</li>
                 <li><a href="/HarangProject/facil?cmd=AFacilAddDel">관리자 시설 추가/삭제 </a></li>
                 <li><a href="/HarangProject/facil?cmd=AFacilPG">관리자 운동장 일정 추가 </a></li>
                 <li><a href="/HarangProject/facil?cmd=AFacilSR">관리자 스터디룸 일정 추가 </a></li>
                 <li><a href="/HarangProject/facil?cmd=AFacilManager">관리자 사용자 일정 취소 </a></li>
              </ul>
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
    <!-- jQuery 2.1.3 --> 
    <script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="resources/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
  </body>
</html>