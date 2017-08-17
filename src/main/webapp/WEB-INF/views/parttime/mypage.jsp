<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../include/header.jsp"%>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->
<!-- 페이지 헤드 라인 : 제목 -->
<head>
<title>기본 값 페이지</title>
</head>
<!-- 메인 페이지 구역 , 즉 작업 구역 -->
<div class="content-wrapper">
	<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
	<section class="content-header">
		<h1>
			${member.m_name}님의 글 <small>내가 쓴 글과 지원한 항목을 확인하세요.</small>
		</h1>
		<ol class="breadcrumb">
			<!-- 페이지 기록 메인에서 부터 현재 페이지 까지의 경로 나열 -->
			<li><a href="#"><i class="fa fa-dashboard"></i> Home > 알바 하랑</a></li>
			<li class="active">내가 쓴 글</li>
		</ol>
	</section>
	<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
	<section class="content">
	<div class="row">
		<div class="col-md-10">
			
			
			<!-- 알바 모집 collapse -->
			<div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">알바 모집</h3>
                  <small>내가 작성한 알바 모집 공고입니다.</small>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table class="table table-bordered table-striped">
							<tr>
								<th style="width: 10px">#</th>
								<th style="width: 60%">제목</th>
								<th>작성일</th>
								<th>조회수</th>
								<th>지원자 수</th>
							</tr>
							<c:choose>
								<c:when test="${fn:length(plist) eq 0}">
									<tr>
										<td></td>
										<td>게시물이 없습니다.<td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${plist}" var="list">
										<tr>
											<td>${list.list_num}</td>
											<td>${list.p_header}&nbsp;&nbsp; 
												<a href="#" onclick="fnP_Read('${list.p_num}')">${list.p_title}</a>
											</td>
											<td>${list.p_regdate}</td>
											<td>${list.p_cnt}</td>
											<td>${list.cnt_apply}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</table>
                </div><!-- /.box-body -->
                
                <!-- 페이징 버튼 -->
					<div class="box-footer clearfix">
						<ul class="pagination pagination-sm no-margin pull-right">
							<c:if test="${pageMaker1.prev}">
		                            <li><a href="/parttime/MYPAGE${pageMaker1.makeQuery(pageMaker1.startPage-1)}&n=1">&laquo;</a></li>
		                    </c:if>
		                    <c:forEach begin="${pageMaker1.startPage}" 
		                    		   end="${pageMaker1.endPage}" 
		                               var="idx">
		                            <li value="${pageMaker1.cri.page == idx ? 'class=active' : ''}">
		                          		<a href="/parttime/MYPAGE?page=${idx}&n=1">
		                          			${idx}
		                        	   	</a>
		                             </li>
		                   	</c:forEach>
		                    <c:if test="${pageMaker1.next && pageMaker1.endPage>0}">
		                      <li><a href="/parttime/MYPAGE${pageMaker1.makeQuery(pageMaker1.endPage+1)}&n=1">&raquo;</a></li>
		                    </c:if>
	                    </ul>
					</div><!-- 페이징 버튼 -->
              </div><!-- /.box -->
              
           	<!-- 대타 모집 collapse -->
			<div class="box box-warning">
                <div class="box-header with-border">
                  <h3 class="box-title">대타 모집</h3>
                  <small>내가 작성한 대타 모집 공고입니다.</small>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table class="table table-bordered table-striped">
							<tr>
								<th style="width: 10px">#</th>
								<th style="width: 60%">제목</th>
								<th>작성일</th>
								<th>조회수</th>
								<th>지원자 수</th>
							</tr>
							<c:choose>
								<c:when test="${fn:length(dlist) eq 0}">
								<tr>
									<td></td>
									<td>게시물이 없습니다.<td>
									<td></td>
									<td></td>
									<td></td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${dlist}" var="list">
										<tr>
											<td>${list.list_num}</td>
											<td>${list.d_header}&nbsp;&nbsp; 
												<a href="javascript:fnD_Read('${list.d_num}')">${list.d_title}</a>
											</td>
											<td>${list.d_regdate}</td>
											<td>${list.d_cnt}</td>
											<td>${list.cnt_apply}</td>
										</tr>
									</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
                </div><!-- /.box-body -->
                <!-- 페이징 버튼 -->
					<div class="box-footer clearfix">
						<ul class="pagination pagination-sm no-margin pull-right">
							<c:if test="${pageMaker2.prev}">
		                            <li><a href="/parttime/MYPAGE${pageMaker2.makeQuery(pageMaker2.startPage-1)}&n=2">&laquo;</a></li>
		                    </c:if>
		                    <c:forEach begin="${pageMaker2.startPage}" 
		                    		   end="${pageMaker2.endPage}" 
		                               var="idx">
		                            <li value="${pageMaker2.cri.page == idx ? 'class=active' : ''}">
		                          		<a href="/parttime/MYPAGE?page=${idx}&n=2">
		                          			${idx}
		                        	   	</a>
		                             </li>
		                   	</c:forEach>
		                    <c:if test="${pageMaker2.next && pageMaker2.endPage>0}">
		                      <li><a href="/parttime/MYPAGE${pageMaker2.makeQuery(pageMaker2.endPage+1)}&n=2">&raquo;</a></li>
		                    </c:if>
	                    </ul>
					</div><!-- 페이징 버튼 -->
			</div><!-- /.box -->
                
            <!-- 알바 지원 collapse -->
			<div class="box box-success">
                <div class="box-header with-border">
                  <h3 class="box-title">알바 지원</h3>
                  <small>내가 지원한 알바 목록입니다.</small>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table class="table table-bordered table-striped">
							<tr>
								<th style="width: 10px">#</th>
								<th style="width: 40%">제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>이력서</th>
								<th>지원자 수</th>
								<th>채용 여부</th>
							</tr>
							<c:choose>
								<c:when test="${fn:length(p_resume) eq 0}">
								<tr>
									<td></td>
									<td>게시물이 없습니다.<td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${p_resume}" var="list" >
										<tr>
											<td>${list.list_num}</td>
											<td>${list.p_header}&nbsp;&nbsp; 
												<a href="javascript:fnP_A_Read('${list.p_num}')" >${list.p_title}</a>
											</td>
											<td>${list.m_name} </td>
											<td>${list.p_regdate} </td>
											<td><button class="btn btn-xs btn-success" onclick="fnP_Resume('${list.p_num}')">이력서</button></td>
											<td>${list.cnt_apply}</td>
											<td>
												<c:if test="${list.pm_choice eq 'Y'}"><span class="btn btn-success btn-xs">채용성공</span></c:if>
												<c:if test="${list.pm_choice eq 'N' and list.checkDeadline eq true}"><span class="btn btn-default btn-xs">채용실패</span></c:if>
												<c:if test="${list.pm_choice eq 'N' and list.checkDeadline eq false}"><span class="btn btn-default btn-xs">채용중</span></c:if>
											</td>
										</tr>
									</c:forEach>
							</c:otherwise>
							</c:choose>
						</table>
                </div><!-- /.box-body -->
                <!-- 페이징 버튼 -->
					<div class="box-footer clearfix">
						<ul class="pagination pagination-sm no-margin pull-right">
							<c:if test="${pageMaker3.prev}">
		                            <li><a href="/parttime/MYPAGE${pageMaker3.makeQuery(pageMaker3.startPage-1)}&n=3">&laquo;</a></li>
		                    </c:if>
		                    <c:forEach begin="${pageMaker3.startPage}" 
		                    		   end="${pageMaker3.endPage}" 
		                               var="idx">
		                            <li value="${pageMaker3.cri.page == idx ? 'class=active' : ''}&n=3">
		                          		<a href="/parttime/MYPAGE?page=${idx}">
		                          			${idx}
		                        	   	</a>
		                             </li>
		                   	</c:forEach>
		                    <c:if test="${pageMaker3.next && pageMaker3.endPage>0}">
		                      <li><a href="/parttime/MYPAGE${pageMaker3.makeQuery(pageMaker3.endPage+1)}&n=3">&raquo;</a></li>
		                    </c:if>
	                    </ul>
					</div><!-- 페이징 버튼 -->
              </div><!-- /.box -->
                
            <!-- 대타 지원 collapse -->
			<div class="box box-danger">
                <div class="box-header with-border">
                  <h3 class="box-title">대타 지원</h3>
                  <small>내가 지원한 대타 목록입니다.</small>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table class="table table-bordered table-striped">
							<tr>
								<th style="width: 10px">#</th>
								<th style="width: 35%">제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>이력서</th>
								<th>지원자 수</th>
								<th>채용 여부</th>
								<th>포인트</th>
							</tr>
							<c:choose>
								<c:when test="${fn:length(d_resume) eq 0}">
								<tr>
									<td></td>
									<td>게시물이 없습니다.<td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${d_resume}" var="list">
										<tr>
											<td>${list.list_num}</td>
											<td>${list.d_header}&nbsp;&nbsp; 
												<a href="javascript:fnD_A_Read('${list.d_num}')">${list.d_title}</a>
											</td>
											<td>${list.m_name} </td>
											<td>${list.d_regdate} </td>
											<td><button class="btn btn-xs btn-danger" onclick="fnD_Resume('${list.d_num}')">이력서</button></td>
											<td>${list.cnt_apply}</td>
											<td>
												<c:if test="${list.dm_choice eq 'Y'}"><span class="btn btn-danger btn-xs">채용성공</span></c:if>
												<c:if test="${list.dm_choice eq 'N' and list.checkDeadline eq true}"><span class="btn btn-default btn-xs">채용실패</span></c:if>
												<c:if test="${list.dm_choice eq 'N' and list.checkDeadline eq false}"><span class="btn btn-default btn-xs">채용중</span></c:if>
											</td>
											<td>
												<c:choose>
												<c:when test="${list.dm_iscomplete eq 'N'}"><span class="btn btn-default btn-xs">지급거부</span></c:when>
												<c:when test="${list.dm_iscomplete eq 'Y'}"><span class="btn btn-danger btn-xs">지급성공</span></c:when>
												<c:otherwise><span class="btn btn-default btn-xs">대기중</span></c:otherwise>
												</c:choose>
											</td>
										</tr>
									</c:forEach>
							</c:otherwise>
							</c:choose>
						</table>
                </div><!-- /.box-body -->
                <!-- 페이징 버튼 -->
					<div class="box-footer clearfix">
						<ul class="pagination pagination-sm no-margin pull-right">
							<c:if test="${pageMaker4.prev}">
		                            <li><a href="/parttime/MYPAGE${pageMaker4.makeQuery(pageMaker4.startPage-1)}&n=4">&laquo;</a></li>
		                    </c:if>
		                    <c:forEach begin="${pageMaker4.startPage}" 
		                    		   end="${pageMaker4.endPage}" 
		                               var="idx">
		                            <li value="${pageMaker4.cri.page == idx ? 'class=active' : ''}">
		                          		<a href="/parttime/MYPAGE?page=${idx}&n=4">
		                          			${idx}
		                        	   	</a>
		                             </li>
		                   	</c:forEach>
		                    <c:if test="${pageMaker4.next && pageMaker4.endPage>0}">
		                      <li><a href="/parttime/MYPAGE${pageMaker4.makeQuery(pageMaker4.endPage+1)}&n=4">&raquo;</a></li>
		                    </c:if>
	                    </ul>
					</div><!-- 페이징 버튼 -->
              </div><!-- /.box -->  
            </div><!-- /.col -->
		</div><!-- /.row -->
	</section><!-- /. 작업 공간 끝! -->
<!------------------------------------------------------------------------------------------------------------------->
</div><!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->
<form name="pRead" method="post" action="/parttime/PREAD">
	<input type="hidden" name="p_num" value="" id="p_num"/>
	<input type="hidden" name="tab" value="MYPAGE"/>
</form>
<form name="dRead" method="post" action="/parttime/DREAD">
	<input type="hidden" name="d_num" value="" id="d_num"/>
	<input type="hidden" name="tab" value="MYPAGE"/>
</form>
<form name="paRead" method="post" action="/parttime/PREAD">
	<input type="hidden" name="p_num" value="" id="pa_num"/>
	<input type="hidden" name="tab" value="MYPAGE"/>
</form>
<form name="daRead" method="post" action="/parttimeDREAD">
	<input type="hidden" name="d_num" value="" id="da_num"/>
	<input type="hidden" name="tab" value="MYPAGE"/>
</form>
<form name="pResume" method="post" action="/parttime/PRESUME">
	<input type="hidden" name="p_num" value="" id="p_resume"/>
	<input type="hidden" name="m_id" value="${pageMaker1.cri.m_id}"/>
	<input type="hidden" name="tab" value="MYPAGE"/>
</form>
<form name="dResume" method="post" action="/parttime/DRESUME">
	<input type="hidden" name="d_num" value="" id="d_resume"/>
	<input type="hidden" name="m_id" value="${pageMaker1.cri.m_id}"/>
	<input type="hidden" name="tab" value="MYPAGE"/>
</form>
<!--페이징 관련 폼 ---------------------------------------------------------------- -->

<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------>
<%@ include file="../include/footer.jsp"%>
<!-- --------------------------------------------------------------------------------------------------- -->
<script>
function fnP_Read(p_num){
	document.getElementById("p_num").value = p_num;
	pRead.submit();
}	
function fnD_Read(d_num){
	document.getElementById("d_num").value = d_num;
	dRead.submit();
}
function fnP_A_Read(p_num){
	document.getElementById("pa_num").value = p_num;
	paRead.submit();
}
function fnD_A_Read(d_num){
	document.getElementById("da_num").value = d_num;
	daRead.submit();
}
function fnP_Resume(p_num){
	document.getElementById("p_resume").value = p_num;
	pResume.submit();
}
function fnD_Resume(d_num){
	document.getElementById("d_resume").value = d_num;
	dResume.submit();
}

</script>


 
