<%@page import="java.util.ArrayList"%>
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
			알바 채용 정보 <small>교내의 알바 채용 정보를 확인하세요!</small>
		</h1>
		<ol class="breadcrumb">
			<!-- 페이지 기록 메인에서 부터 현재 페이지 까지의 경로 나열 -->
			<li><a href="#"><i class="fa fa-dashboard"></i> Home > 알바 하랑</a></li>
			<li class="active">알바 모집</li>
		</ol>
	</section>
	<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
	<section class="content">
		<div class="row">
			<div class="col-md-10">
				<div class="box box-info">
					<div class="box-header">
						<h3 class="box-title"></h3>
					</div>
					<div class="box-body">
						<div class="row">
						<div class="col-md-2">
							<button class="btn btn-sm btn-info" onclick="location.href='/parttime/PPOST'">글쓰기</button>
						</div>
						<div class="col-md-9"></div>
						<div class="col-md-1">
							<button class="btn btn-default btn-sm pull-right" onclick="fnRefresh()"><i class="fa fa-refresh"></i></button>
						</div>					
                  	</div>
						<table class="table table-bordered table-striped">
							<tr>
								<th style="width: 10px">#</th>
								<th style="width: 40%">제목</th>
								<th>시급</th>
								<th>글쓴이</th>
								<th>작성일</th>
								<th>조회수</th>
								<th>지원자 수</th>
							</tr>
							
							<!-- 목록 삽입 -->
							<!-- 맨위에  taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" 추가해주세요!! -->
							<c:choose>
								<c:when test="${fn:length(list) eq 0}">
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
									<c:forEach items="${list}" var="list"> 
										<tr>
											<td>${list.list_num}</td>
											<td>${list.p_header}&nbsp;&nbsp; 
												<a href="javascript:fnRead('${list.p_num}')" id="read">${list.p_title}</a>
											</td>
											<td>${list.p_wage}원 </td>
											<td>${list.m_name}</td>
											<td>${list.p_regdate}</td>
											<td>${list.p_cnt}</td>
											<td>${list.cnt_apply}</td>
										</tr>
									</c:forEach>
							</c:otherwise>
							</c:choose>
						<!-- 목록삽입  끝 -->	
							
						</table>
					</div>
					<!-- /.box-body -->
					
					
					<!-- 페이징 버튼 -->
					<div class="box-footer clearfix">
	                    <ul class="pagination pagination-sm no-margin pull-right">
							<c:if test="${pageMaker.prev}">
		                            <li><a href="/parttime/PMAIN${pageMaker.makeQuery(pageMaker.startPage-1)}">&laquo;</a></li>
		                    </c:if>
		                    <c:forEach begin="${pageMaker.startPage}" 
		                    		   end="${pageMaker.endPage}" 
		                               var="idx">
		                            <li value="${pageMaker.cri.page == idx ? 'class=active' : ''}">
		                          		<a href="/parttime/PMAIN?page=${idx}">
		                          			${idx}
		                        	   	</a>
		                             </li>
		                   	</c:forEach>
		                    <c:if test="${pageMaker.next && pageMaker.endPage>0}">
		                      <li><a href="/parttime/PMAIN${pageMaker.makeQuery(pageMaker.endPage+1)}">&raquo;</a></li>
		                    </c:if>
	                    </ul>
					
					<form name="search" method="post" action="/parttime/PMAIN">
					<div class="row">
						<div class="col-md-3"></div>
						<div class="col-md-2 form-group">
							<select class="form-control" name="keyfield">
	                        <c:choose>
	                        <c:when test="${keyfield eq '제목' or keyfield eq null}">
		                        <option selected="selected">제목</option>
		                        <option>시급</option>
	                        </c:when>
	                        <c:when test="${keyfield eq '시급'}">
		                        <option>제목</option>
		                        <option selected="selected">시급</option>
	                        </c:when>
	                        </c:choose>
	                      </select>
						</div>
						<div class="col-md-4 input-group input-group-sm">
	                    <input type="text" name="keyword" value="${keyword}" class="form-control">
	                    <span class="input-group-btn">
	                      <button class="btn btn-info btn-flat" type="submit">Go!</button>
	                    </span>
	                  </div><!-- /input-group -->
                  </div>
                  </form>
				</div>
			</div>
		</div><!-- row -->


	</section><!-- /. 작업 공간 끝! -->
<!------------------------------------------------------------------------------------------------------------------->
</div><!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->
<!-- 글 읽기 -->
<form name="read" method="post" action="/parttime/PREAD">
	<input type="hidden" name="p_num" value="" id="p_num"/>
	<input type="hidden" name="tab" value="PMAIN"/>
</form>
<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------>
<%@ include file="../include/footer.jsp"%>

<!-- --------------------------------------------------------------------------------------------------- -->
<script>

function fnRead(p_num){
	document.getElementById("p_num").value = p_num;
	document.read.submit();
}

function fnRefresh(){
	location.href="/parttime/PMAIN";
}
</script>
 
