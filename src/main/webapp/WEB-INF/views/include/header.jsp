<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.2 -->
    <link href="../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- fullCalendar 2.2.5-->
    <link href="../resources/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css" />
    <link href="../resources/plugins/fullcalendar/fullcalendar.print.css" rel="stylesheet" type="text/css" media='print' />
    <!-- Theme style -->
    <link href="../resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="../resources/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="../resources/plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />
     <!-- Morris chart -->
    <link href="../resources/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
     <!-- jvectormap -->
    <link href="../resources/plugins/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
    <!-- Date Picker -->
    <link href="../resources/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
    <!-- Daterange picker -->
    <link href="../resources/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
    <!-- bootstrap wysihtml5 - text editor -->
    <link href="../resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
     <!-- DATA TABLES -->
    <link href="../resources/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
    <!-- 아이콘 설정 -->
    <link rel="apple-touch-icon" sizes="57x57" href="../resources/dist/ico/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="../resources/dist/ico/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="../resources/dist/ico/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="../resources/dist/ico/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="../resources/dist/ico/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="../resources/dist/ico/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="../resources/dist/ico/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="../resources/dist/ico/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="../resources/dist/ico/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="../resources/dist/ico/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="../resources/dist/ico/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="../resources/dist/ico/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="../resources/dist/ico/favicon-16x16.png">
	<link rel="manifest" href="../resources/dist/ico/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="../resources/dist/ico/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">
	<!-- 
	<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
	<script>
	  (adsbygoogle = window.adsbygoogle || []).push({
	    google_ad_client: "ca-pub-3013019925957573",
	    enable_page_level_ads: true
	  });
	</script>
	-->	
	<c:if test="${member==null}">
        <script type="text/javascript">
	        alert("잘못된 접근 방식 입니다. 로그인을 해 주십시요");
	        location.href="/";
        </script>
    </c:if> 
  </head>
  <!-- 스킨 색상 변경 가능 : white, yellow, red, purple -->
   <body class="skin-black">
    <div class="wrapper">
      <header class="main-header">
        <!-- Logo -->
        <a href="/base/main" class="logo"><img src="../resources/dist/img/Logo.png" class="img-circle" alt="User Image" width="40" height="40"/>&nbsp;하랑 대학교</a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
            <!-- 포인트 확인 : 현제 보유 포인트와 상세 정보 확인 -->
            <!-- 포인트 확인 : 현제 보유 포인트와 상세 정보 확인 -->
              <li>
              	<!-- 스프링 시큐리티를 통해 로그아웃 하기 위해서는 반드시 post방식으로 전달해야 한다. -->
                <a href="#" onclick="document.getElementById('logoutFrm').submit();">
                    <i class="fa fa-sign-out"></i>
                </a>
                <form id="logoutFrm" action="/login/logout" method="post">
                	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> 
                </form>
              </li>
              <li class="dropdown messages-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-money"></i>&nbsp;&nbsp;
                    ${member.m_point } 포인트
                </a>
                <ul class="dropdown-menu">
                  <li class="header">최근 거래 기록</li>
                  <li>
                    <!-- 내부 메뉴 : 실제 메세지 데이터가 포함 -->
                    <ul class="menu">
                       <c:forEach items="${PLH}" var="ap">
                           <c:if test="${member.m_id eq ap.m_giver}">
                             <li>
                                <a href="#">
                                  <div class="pull-left">
                                    <i class="fa fa-arrow-down text-red"></i>
                                  </div>
                                  <h4>
                                    ${ap.r_content}
                                    <small><i class="fa fa-clock-o"></i>${p.r_regdate}</small>
                                  </h4>
                                   <p style="color: red">${ap.r_point} 포인트</p>
                                </a>
                              </li>
                           </c:if>
                           <c:if test="${member.m_id eq ap.m_haver}">
                              <li>
                                <a href="#">
                                  <div class="pull-left">
                                    <i class="fa fa-arrow-up text-green"></i>
                                  </div>
                                  <h4>
                                    ${ap.r_content}
                                  </h4>
                                   <p style="color: green">${ap.r_point} 포인트</p>
                                </a>
                              </li>
                           </c:if>
                      </c:forEach>
                    </ul>
                  </li>
                  <li class="footer"><a href="/myPage/pointList">모든 거래 기록 보기</a></li>
                </ul>
              </li>
             
              <!-- 메세지 : 드롭 다운으로 메세지 출력 가능-->
              <li class="dropdown messages-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-envelope-o"></i>
                  <span class="label label-success">${NRM}</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">최근 들어온 메세지</li>
                  <li>
                    <!-- 내부 메뉴 : 실제 메세지 데이터가 포함 -->
                    <ul class="menu">
                      <!-- 메세지 반복문 지점 시작 -->
                      <c:forEach items="${head_msg}" var="msg">
                        <li><!-- 메세지 시작 -->
                        <a href="/message/READ?t_num=${msg.t_num}&tab=INBOX">
                          <div class="pull-left">
                          	<c:if test="${'' eq  msg.s_photo}">
                          		<img src="../resources/dist/img/no-user-image.gif" class="img-circle" alt="User Image"/>
                          	</c:if>
                          	<c:if test="${'' ne  msg.s_photo}">
                            	<img src="/displayFile?fileName=${msg.s_photo}" class="img-circle" alt="User Image"/>
                            </c:if>
                          </div>
                          <h4>
                             ${msg.m_sender_name}[${msg.m_sender}]                             
                          </h4>
                          <p>${msg.t_title}</p>
                        </a>
                      </li><!-- end message -->
                      </c:forEach>
                      <!-- 메세지 반복문 지점 끝-->
                    </ul>
                  </li>
                  <li class="footer"><a href="/message/INBOX">모든 메세지 보기</a></li>
                </ul>
              </li>
              <!--개 인 정 보 요약 형태 -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <c:if test="${'' eq member.m_photo}">
                  	<img src="../resources/dist/img/no-user-image.gif" class="user-image" alt="User Image"/>
                  </c:if>
                  <c:if test="${'' ne member.m_photo}">
                  	<img src="/displayFile?fileName=${member.m_photo}" class="user-image" alt="User Image"/>
                  </c:if>
                  <span class="hidden-xs">${member.m_name } -  ${member.m_dept }</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- 회원 사진 -->
                  <li class="user-header">
					<c:if test="${'' eq member.m_photo}">
                    	<img src="../resources/dist/img/no-user-image.gif" class="img-circle" alt="User Image"/>
                    </c:if>
                    <c:if test="${'' ne member.m_photo}">
                    	<img src="/displayFile?fileName=${member.m_photo}" class="img-circle" alt="User Image" />
                    </c:if>
                    <p>
                        ${member.m_name } - ${member.m_dept }
                      <small>Member since ${member.m_regdate }</small>
                    </p>
                  </li>
                  <!-- 개인정보 창 -->
                 <!--  <li class="user-body">
                    <div class="col-xs-4 text-center">
                      <a href="#">팔로워</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">판매</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">친구</a>
                    </div>
                  </li> -->
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="/myPage/myinfo" class="btn btn-default btn-flat">내 정보 조회</a>
                    </div>
                    <div class="pull-right">
                      <a href="/login/logout" class="btn btn-default btn-flat">로그아웃</a>
                    </div>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <!-- GNB 부분 공통적으로 메뉴바 적용 가능-->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
         <!-- 사이드 바 메뉴 항목 -->
          <ul class="sidebar-menu">
            <!-- 상위 메뉴 시작 -->
            <li class="treeview">
              <a href="/message/INBOX">
                <i class="fa fa-envelope-o"></i>
                <span>메세지 함</span>
              </a>
              <!-- 하위 메뉴 끝 -->
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-eye"></i>
                <span>마이페이지</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <!-- 하위 메뉴 시작 -->
              <ul class="treeview-menu">
                <li><a href="/myPage/myInfo"><i class="fa fa-circle-o"></i> 개인정보조회</a></li>
                <li><a href="/myPage/specUp"><i class="fa fa-circle-o"></i> 스팩 업!</a></li>
                <li><a href="/myPage/timeTable"><i class="fa fa-circle-o"></i> 수강 시간표 v1</a></li>
                <li><a href="/myPage/timeTable2"><i class="fa fa-circle-o"></i> 수강 시간표 v2</a></li>
                <li><a href="/myPage/pointList"><i class="fa fa-circle-o"></i> 포인트 거래 내역</a></li>
                <li><a href="/myPage/pointZero"><i class="fa fa-circle-o"></i> 학비 포인트 제로!</a></li>
              </ul>
              <!-- 하위 메뉴 끝 -->
            </li>
           <!-- 상위 메뉴 끝 -->
            <li class="treeview">
              <a href="#">
                <i class="fa fa-share-square-o"></i>
                <span>학교시설물</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="/facil/FacilMain"><i class="fa fa-circle-o"></i> 사용자 메인</a></li>
                <li><a href="/facil/FacilPGreserv"><i class="fa fa-circle-o"></i> 운동장 예약</a></li>
                <li><a href="/facil/FacilSRreserv"><i class="fa fa-circle-o"></i> 스터디룸 예약</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-cutlery"></i>
                <span>하랑 레스토랑</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="/food/menu"><i class="fa fa-circle-o"></i> 학식 메뉴 조회</a></li>
                <li><a href="/food/ticket"><i class="fa fa-circle-o"></i> 식권 구매 내역</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-briefcase"></i> <span>알바 하랑</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="/parttime/PMAIN"><i class="fa fa-circle-o"></i> 알바 모집</a></li>
                <li><a href="/parttime/DMAIN"><i class="fa fa-circle-o"></i> 대타 모집</a></li>
                <li><a href="/parttime/MYPAGE"><i class="fa fa-circle-o"></i> 내가 쓴 글</a></li>
              </ul>
            </li>
            <li>
              <a href="#">
                <i class="fa fa-book"></i> <span>하랑딘 24</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="/harangdin/main"><i class="fa fa-circle-o"></i> 하랑딘 메인</a></li>
                <li><a href="/harangdin/sellList"><i class="fa fa-circle-o"></i> 도서 판매 내역</a></li>
                <li><a href="/harangdin/buyList"><i class="fa fa-circle-o"></i> 도서 구매 내역</a></li>
                <li><a href="/harangdin/donationList"><i class="fa fa-circle-o"></i> 도서 기부 내역</a></li>
              </ul>
            </li>
            <li>
              <a href="/impage/main">
                <i class="fa fa-check-square-o"></i><span>강의 평가</span>
              </a>
            </li>
            <li>
              <a href="/schedule/SCH_LIST">
                <i class="fa fa-calendar"></i><span>학사일정</span>
              </a>
            </li>
            <li>
              <a href="/bamboo/BB_LIST">
                <i class="fa fa-group"></i><span>하랑 대나무 숲</span>
              </a>
            </li>
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>
      
