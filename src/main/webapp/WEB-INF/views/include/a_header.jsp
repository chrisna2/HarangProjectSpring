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
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
      <c:if test="${admin==null}">
        <script type="text/javascript">
	        alert("관리자 전용 페이지 입니다. 관리자로 접속해 주십시요.");
	        location.href="/";
        </script>
    </c:if>
  </head>
  <!-- 스킨 색상 변경 가능 : white, yellow, red, purple -->
   <body class="skin-blue">
    <div class="wrapper">
      <header class="main-header">
        <!-- Logo -->
        <a href="/HarangProject/login?cmd=refresh" class="logo"><img src="../resources/dist/img/Logo.png" class="img-circle" alt="User Image" width="40" height="40"/>&nbsp;하랑 대학교</a>
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
                <a href="/HarangProject/login?cmd=logout">
                    <i class="fa fa-sign-out"></i>
                </a>
              </li>
              <li class="dropdown messages-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-money"></i>&nbsp;&nbsp;
                    ${admin.m_point} 포인트
                </a>
                <ul class="dropdown-menu">
                  <li class="header">거래 기록</li>
                  <li>
                    <!-- 내부 메뉴 : 실제 메세지 데이터가 포함 -->
                    <ul class="menu">
                      <!-- 메세지 반복문 지점 시작 -->
                      <c:forEach items="${PLH}" var="ap">
                           <c:if test="${admin.m_id eq ap.m_giver}">
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
                           <c:if test="${admin.m_id eq ap.m_haver}">
                              <li>
		                        <a href="#">
		                          <div class="pull-left">
		                            <i class="fa fa-arrow-up text-green"></i>
		                          </div>
		                          <h4>
		                            ${ap.r_content}
		                            <small><i class="fa fa-clock-o"></i>${p.r_regdate}</small>
		                          </h4>
		                           <p style="color: green">${ap.r_point} 포인트</p>
		                        </a>
		                      </li>
                           </c:if>
                      </c:forEach>
                      <!-- 메세지 반복문 지점 끝-->
                    </ul>
                  </li>
                  <li class="footer"><a href="#">모든 거래 기록 보기</a></li>
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
                        <a href="/HarangProject/message?cmd=READ&t_num=${msg.t_num}&tab=INBOX">
                          <div class="pull-left">
                            <img src="${msg.s_photo}" class="img-circle" alt="User Image"/>
                          </div>
                          <h4>
                             ${msg.m_sender_name}[${msg.m_sender}]                               
                            <small><i class="fa fa-clock-o"></i>${msg.t_send_date}</small>
                          </h4>
                          <p>${msg.t_title}</p>
                        </a>
                      </li><!-- end message -->
                      </c:forEach>
                      <!-- 메세지 반복문 지점 끝-->
                    </ul>
                  </li>
                  <li class="footer"><a href="/HarangProject/message?cmd=INBOX">모든 메세지 보기</a></li>
                </ul>
              </li>
              <!--회원 개인정보 요약 형태 -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image"/>
                  <span class="hidden-xs">${admin.m_name } - ${admin.m_dept}</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- 회원 사진 -->
                  <li class="user-header">
                    <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />
                    <p>
                      ${admin.m_name } - ${admin.m_dept}
                      <small>Member since  ${admin.m_regdate}</small>
                    </p>
                  </li>
                  <!-- 개인정보 창 -->
                  <li class="user-body">
                    <div class="col-xs-4 text-center">
                      <a href="#">팔로워</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">판매</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">친구</a>
                    </div>
                  </li>
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat">내 정보 조회</a>
                    </div>
                    <div class="pull-right">
                      <a href="/HarangProject/login?cmd=logout" class="btn btn-default btn-flat">로그아웃</a>
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
              <!-- 상위 메뉴 시작 -->
            <li class="treeview">
              <a href="/HarangProject/message?cmd=INBOX">
                <i class="fa fa-envelope-o"></i>
                <span>메세지 함</span>
              </a>
              <!-- 하위 메뉴 끝 -->
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-eye"></i>
                <span>회원 및 포인트 관리</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <!-- 하위 메뉴 시작 -->
              <ul class="treeview-menu">
                <li><a href="/myPage/AmemList"><i class="fa fa-circle-o"></i> 회원 정보 조회</a></li>
                <li><a href="/myPage/Achallenge"><i class="fa fa-circle-o"></i> 스팩 업 관리</a></li>
                <li><a href="/myPage/AspecList"><i class="fa fa-circle-o"></i> 스팩 목록 관리</a></li>
                <li><a href="/myPage/Alesson"><i class="fa fa-circle-o"></i> 강의 목록 관리</a></li>
                <li><a href="/myPage/ApointCheck"><i class="fa fa-circle-o"></i> 포인트 관리 시스템</a></li>
              </ul>
              <!-- 하위 메뉴 끝 -->
            </li>
           <!-- 상위 메뉴 끝 -->
            <li class="treeview">
              <a href="#">
                <i class="fa fa-share-square-o"></i>
                <span>학교 시설물 등록 관리</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="/HarangProject/facil?cmd=AFacilAddDel"><i class="fa fa-circle-o"></i> 관리자 시설 추가/삭제</a></li>
                <li><a href="/HarangProject/facil?cmd=AFacilPG"><i class="fa fa-circle-o"></i> 관리자 운동장 일정 추가</a></li>
                <li><a href="/HarangProject/facil?cmd=AFacilSR"><i class="fa fa-circle-o"></i> 관리자 스터디룸 일정 추가</a></li>
                <li><a href="/HarangProject/facil?cmd=AFacilManager"><i class="fa fa-circle-o"></i> 관리자 사용자 일정 취소</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-cutlery"></i>
                <span>하랑 레스토랑 관리</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="/food/Amenu"><i class="fa fa-circle-o"></i> 메뉴 등록/수정</a></li>
                <li><a href="/food/Aticket"><i class="fa fa-circle-o"></i> 식권 판매 내역</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-briefcase"></i> <span>알바 하랑 관리</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="/HarangProject/parttime?cmd=PMAIN"><i class="fa fa-circle-o"></i> 알바 모집 관리</a></li>
                <li><a href="/HarangProject/parttime?cmd=DMAIN"><i class="fa fa-circle-o"></i> 대타 모집 관리</a></li>
              </ul>
            </li>
            <li>
              <a href="/HarangProject/impage?cmd=amain">
                <i class="fa fa-check-square-o"></i><span>강의 평가 관리</span>
              </a>
            </li>
            <li>
              <a href="/HarangProject/schedule?cmd=A_SCH_LIST">
                <i class="fa fa-calendar"></i>
                <span>학사 일정 관리</span>
              </a>
            </li>
            <li>
              <a href="/HarangProject/bamboo?cmd=A_BB_LIST">
                <i class="fa fa-group"></i><span>하랑 대나무 숲 관리</span>
              </a>
            </li>
            <li>
              <a href="/HarangProject/harangdin?cmd=adminMain">
                <i class="fa fa-book"></i> <span>하랑딘 24 관리</span>
              </a>
            </li>
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>
