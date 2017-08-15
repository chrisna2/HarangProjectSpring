<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<!-- 페이지 헤드 라인 : 제목 -->
<head>
<%@ include file="../include/header.jsp"%>
<title>대나무숲 리스트 사용자페이지</title>
<style>
table {
	table-layout: fixed;
}

td {
	word-break: break-all;
	word-wrap: break-word;
}
</style>


<script>
	function fnBbnewlist() {
		document.bbnewlist.submit()
	}
	function fnBbhotlist() {
		document.bbhotlist.submit()
	}
	function fnBbpost() {
		document.bbpost.submit()
	}
	///////////////// 페이지 관련 javascript function////////////////////
	/* function prevPage() {
		document.getElementById("prevPage").submit();
	}
	function nextPage() {
		document.getElementById("nextPage").submit();
	}
	function goPage(nowPage) {
		document.getElementById("page").value = nowPage;
		document.getElementById("goPage").submit();
	} */
	/////////////////////////////끝//////////////////////////////////
</script>


</head>

<div class="content-wrapper">
	<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
	<section class="content-header">
		<h1>
			<a href="/bamboo/BB_LIST" style="color: black">대나무숲
				</a>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> 메인</a></li>
			<li class="active">대나무숲</li>
		</ol>
	</section>
	<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
	<br> <br>
	<section class="content">

		<!-- 컨텐츠 헤더부분. 제목. 작성일. 작성자. 추천/비추천 수 표시 -->



		<!--  여기부터 본문 -->

		<!-- 세로 길이 수정 -->
		<div class="row">
			<!-- 너비 사이즈 수정  : col-->
			<div class="col-md-9">
				<div class="box">
					<div class="box-header" style="background-color: #dceff4">
						<h1 class="box-title">
							<a href="/bamboo/BB_LIST" style="color: black">대나무숲</a>
						</h1>
						<div class="box-tools">

							<p align="right">
								<a type="button" class="btn btn-default btn-sm"
									href="javascript:fnBbnewlist()"
									title="최신글 보기 : 최근 3일 이내의 게시글을 게시일 순서로 검색합니다. "
									data-placement="top">최신글 보기</a> <a type="button"
									class="btn btn-default btn-sm" href="javascript:fnBbhotlist()"
									title="인기글 보기 : 최근 30일 이내의 게시글을 추천수가 많은 순서로 검색합니다. "
									data-placement="top">인기글 보기</a> <a type="button"
									class="btn btn-primary btn-sm" href="javascript:fnBbpost()">글쓰기</a>
							</p>



							<!-- 최신글 보기를 위한 form 시작 -->

							<form action="/bamboo/BB_LIST" name="bbnewlist"
								method="post">
								<input type="hidden" name="keyword" value="bbnewlist">

							</form>
							<!-- 최신글 보기를 위한 form 끝 -->

							<!-- 인기글 보기를 위한 form 시작 -->
							<form action="/bamboo/BB_LIST" name="bbhotlist"
								method="post">
								<input type="hidden" name="keyword" value="bbhotlist">

							</form>
							<!-- 인기글 보기를 위한 form 끝 -->

							<!-- 글쓰기를 위한 form 시작 -->
							<form action="/bamboo/BB_POST" name="bbpost" method="get">
								

							</form>
							<!-- 글쓰기를 위한 form 끝 -->





						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive no-padding">
						<table class="table table-hover">


							<tr>
								<th width="100">작성일</th>
								<th width="100">닉네임</th>
								<th>제목</th>
								<th width="70">조회수</th>
								<th width="70">추천수</th>

							</tr>

							


							<c:choose>
								<c:when test="${fn:length(bbNList) eq 0}">
								</c:when>
								<c:otherwise>
									<c:forEach items="${requestScope.bbNList}" var="bbNList">
										<tr bgcolor="pink">
											<td><fmt:formatDate value="${bbNList.bb_regdate}"
													pattern="yyyy-MM-dd" /></td>
											<td>${bbNList.bb_nickname}</td>
											
											<td><a href="/bamboo/BB_CON?page=${pageMaker.cri.page}&bb_num=${bbNList.bb_num }" 
											style="color: black">[공지]${bbNList.bb_title} [${bbNList.reply_cnt}]</a></td>
											<td>${bbNList.bb_count}</td>
											<td>${bbNList.like_cnt}</td>


										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>


							<!--  일반글 목록 가져오기 시작 -->
							<c:choose>
								<c:when test="${fn:length(bblist) eq 0}">
								</c:when>
								<c:otherwise>
									<c:forEach items="${requestScope.bblist}" var="bblist">
										<tr>
											<td><fmt:formatDate value="${bblist.bb_regdate}"
													pattern="yyyy-MM-dd" /></td>
											<td>${bblist.bb_nickname}</td>
											<td><a href="/bamboo/BB_CON?page=${pageMaker.cri.page}&bb_num=${bblist.bb_num }"
											 style="color: black">${bblist.bb_title} [${bblist.reply_cnt}]</a></td>
											<td>${bblist.bb_count}</td>
											<td>${bblist.like_cnt}</td>


										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							<!--  일반글 목록 가져오기 끝 -->





							
						</table>
					</div>
					<!-- /.box-body -->





					<!-- 페이징 버튼 -->
					<div class="box-footer clearfix" style="background-color: #dceff4">
						
						<ul class="pagination pagination-sm no-margin pull-right">
							<c:if test="${pageMaker.prev}">
								<li><a
									href="/bamboo/BB_LIST${pageMaker.makeQuery(pageMaker.startPage-1)}">&laquo;</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage}"
								end="${pageMaker.endPage}" var="idx">
								<li value="${pageMaker.cri.page == idx ? 'class=active' : ''}">
									<a href="/bamboo/BB_LIST?page=${idx}"> ${idx} </a>
								</li>
							</c:forEach>
							<c:if test="${pageMaker.next && pageMaker.endPage>0}">
								<li><a
									href="/bamboo/BB_LIST${pageMaker.makeQuery(pageMaker.endPage+1)}">&raquo;</a></li>
							</c:if>
						</ul>


						<form action="/bamboo/BB_LIST" name="search" method="post">

							
							<div class="input-group">

								<select name="keyfield" class="form-control input-sm"
									style="width: 150px;">

									<option value="bb_title">제목</option>
									<option value="bb_content">내용</option>

								</select> <input type="text" name="keyword" class="form-control input-sm"
									style="width: 150px;" placeholder="Search" />

								<button class="btn btn-sm btn-default pull-left">
									<i class="fa fa-search"></i>
								</button>

							</div>
						</form>
					</div>
					<!-- 페이징 버튼 -->

				</div>
			</div>
			<!-- /.box -->
		</div>

	</section>
</div>

<!-- 페이징 관련 폼 ----------------------------------------------------------------------->
<!-- 페이징 : 이전 블록으로 이동하는 폼 -->
<%-- <form id="prevPage" method="post" action="/HarangProject/bamboo">
	<input type="hidden" name="cmd" value="BB_LIST" /> <input
		type="hidden" name="nowPage"
		value="${paging.pagePerBlock * (paging.nowBlock-1)}" /> <input
		type="hidden" name="nowBlock" value="${paging.nowBlock-1}" />
</form>
<!-- 페이징 : 다음 블록으로 이동하는 폼 -->
<form id="nextPage" method="post" action="/HarangProject/bamboo">
	<input type="hidden" name="cmd" value="BB_LIST" /> <input
		type="hidden" name="nowPage"
		value="${paging.pagePerBlock * (paging.nowBlock+1)}" /> <input
		type="hidden" name="nowBlock" value="${paging.nowBlock+1}" />
</form>
<!-- 페이징 : 해당 페이지로 이동하는 폼 -->
<form id="goPage" method="post" action="/HarangProject/bamboo">
	<input type="hidden" name="cmd" value="BB_LIST" /> <input
		type="hidden" name="nowPage" value="" id="page" /> <input
		type="hidden" name="nowBlock" value="${paging.nowBlock}" />
</form> --%>
<!-- 페이징 관련 폼 여기까지입니다. ----------------------------------------------------------------------------------- -->
<!-- /.col -->

<!-- /.content-wrapper -->
<%@ include file="../include/footer.jsp"%>


