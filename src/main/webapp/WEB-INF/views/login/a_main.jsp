<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/a_header.jsp" %>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->
<!-- 페이지 헤드 라인 : 제목 -->
<head>
     <title>하랑대학교|관리자 메인</title>
</head>
	  <!-- 메인 페이지 구역 , 즉 작업 구역 -->
      <div class="content-wrapper">
<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
        <section class="content-header">
          <h1>
             	관리자 메인
            <small>관리자 페이지 입니다.</small>
          </h1>
          <ol class="breadcrumb">
          <!-- 페이지 기록 : 메인에서 부터 현재 페이지 까지의 경로 나열 -->
            <li class="active"><i class="fa fa-dashboard"></i>관리자 메인</a></li>
          </ol>
        </section>
<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
        <section class="content">
          <!-- 세로 길이 수정 -->
          <div class="row">
           <!-- 너비 사이즈 수정  : col-->
           <div class="col-md-12">
				<!-- 포인트 거래 내역 -->
				<div class="col-md-5">
					<div class="box box-solid">
						<div class="box-header with-border">
			              <font size='3px'><b>최근 포인트 거래 내역</b></font>
			              <a href="/myPage/Applist?check_id=${admin.m_id}" class="btn btn-primary pull-right">상세 확인</a>
			              <a href="/myPage/ApointCheck" class="btn btn-info pull-right">포인트 관리</a>
			            </div>
	            		<div class="box-body">
	            			<table class="table table-bordered table-striped">
			                    <thead>
			                      <tr>
			                        <th>거래 날짜</th>
			                        <th>거래 내용</th>
			                        <th width="20%">포인트</th>
			                      </tr>
			                    </thead>
			                    <tbody>
			                    <c:choose>
			                      <c:when test="${fn:length(pList) eq 0}">
			                      	<tr>
			                            <td></td>
			                            <td>최근 거래가 없습니다.</td>
			                            <td></td>
			                          </tr>
			                      </c:when>
			                      <c:otherwise>
			                      <c:forEach items="${requestScope.pList}" begin="0" end="17" var="p" varStatus="i">
			                        <c:if test="${admin.m_id eq p.m_giver}">
			                           <tr class="text-red">
			                            <td>${p.r_regdate}</td>
			                            <td>${p.r_content}</td>
			                            <td>- ${p.r_point}</td>
			                          </tr>
			                         </c:if>
			                         <c:if test="${admin.m_id eq p.m_haver}">
			                           <tr class="text-green">
			                            <td>${p.r_regdate}</td>
			                            <td>${p.r_content}</td>
			                            <td>+ ${p.r_point}</td>
			                          </tr>
			                         </c:if>
			                      </c:forEach>
			                    </c:otherwise>
			                    </c:choose>
			                    </tbody>
			                  </table>
	            		</div>
					</div>
				</div>
				<div class="col-md-6">
			    	<!-- 최신알바정보 -->	
		        		<div class="nav-tabs-custom">
				            <ul class="nav nav-tabs pull-right">
		        			  <li class="pull-left header"><small><b>최신 정보</b></small></li>
				              <li class="active"><a href="#tab_1" data-toggle="tab" style="color:black">알바</a></li>
				              <li><a href="#tab_2" data-toggle="tab" style="color:black">대타</a></li>
				              <li><a href="#tab_3" data-toggle="tab" style="color:black">대숲</a></li>
				            </ul>
				            <div class="tab-content">
				              <div class="tab-pane active" id="tab_1">
				              	<c:forEach items="${p_list}" var="list" begin="0" end="4" step="1" >
									<a href="javascript:fnReadP('${list.p_num}')" id="read" style="color:black">${list.p_header}&nbsp;&nbsp;${list.p_title}</a><br>
								</c:forEach>
				              </div>
				              <div class="tab-pane" id="tab_2">
				              	<c:forEach items="${d_list}" var="list" begin="0" end="4" step="1" >
									<a href="javascript:fnReadD('${list.d_num}')" id="read" style="color:black">${list.d_header}&nbsp;&nbsp;${list.d_title}</a><br>
								</c:forEach>
				              </div>
				              <div class="tab-pane" id="tab_3">
				              	<c:forEach items="${blist}" var="list" begin="0" end="4" step="1" >
									<a href="javascript:fnReadB('${list.bb_num}')" id="read" style="color:black">${list.bb_title}</a><br>
								</c:forEach>
				              </div>
				            </div>
				          </div>
				  </div>				
				
				<div class="col-md-6">
					<!-- 3-1.학사일정 List / box-->
					<div class="box box-primary">
						<!-- 3-1. box-header -->
						<div class="box-header">
							<h3 class="box-title">학사일정</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
							</div>
						</div>
						<!-- 3-1. box-body -->
						<div class="box-body">
							<!-- 3-1. 시작 -->
							<!--검색 결과 테이블 / 기본은 전체 행사 출력 -->
							<hr />
							<div class="row">
								<div class="col-md-12">
									<!-- 검색 결과 테이블 -->
									<div class="nav-tabs-custom">
						            <ul class="nav nav-tabs pull-right">
				        			  <li class="pull-left header"><small><b>일정확인</b></small></li>
						              <li class="active"><a href="#schedule_1" data-toggle="tab" style="color:black">운동장</a></li>
						              <li><a href="#schedule_2" data-toggle="tab" style="color:black">스터디룸</a></li>
						            </ul>
						            <div class="tab-content">
						              <div class="tab-pane active" id="schedule_1">
										<table id="scPglist" class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>시작날짜</th>
													<th>종료날짜</th>
													<th>행사명</th>
													<th>장소</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${requestScope.scPglist}" var="s"
													varStatus="i">
													<tr class="text-blue">
														<td>${s.s_dstart}</td>
														<td>${s.s_dend}</td>
														<td>${s.s_title}</td>
														<td>${s.s_location}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
					              	<div class="tab-pane" id="schedule_2">
										<!-- 검색 결과 테이블 -->
										<table id="scSrlist" class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>시작날짜</th>
													<th>종료날짜</th>
													<th>행사명</th>
													<th>장소</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${requestScope.scSrlist}" var="s"
													varStatus="i">
													<tr class="text-blue">
														<td>${s.s_dstart}</td>
														<td>${s.s_dend}</td>
														<td>${s.s_title}</td>
														<td>${s.s_location}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									  </div>
						            </div>
						          </div>
								</div>
							</div>
							<!-- 3-1. 종료  -->
						</div>
						<!-- 3-1. box-footer -->
						<div class="box-footer"></div>
					</div>
				<!-- /. 3-1 box -->
			    </div>
				<!-- 포인트 거래 내역 끝! -->	 
              </div><!-- /.col -->
           </div><!-- /.row -->
        </section><!-- /. 작업 공간 끝! -->
<!------------------------------------------------------------------------------------------------------------------->        
      </div><!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->
      
<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------> 
<%@ include file="../include/footer.jsp" %>