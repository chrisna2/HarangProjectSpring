<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<!-- 페이지 헤드 라인 : 제목 -->
<head>
<%@ include file="../include/header.jsp"%>
<title>대나무숲 리스트+컨텐츠 사용자페이지</title>

<style>
div#brcoment {
	word-wrap: break-word; /* Internet Explorer 5.5+ */
	white-space: pre-wrap; /* css-3 */
	white-space: -moz-pre-wrap; /* Mozilla, since 1999 */
	white-space: -pre-wrap; /* Opera 4-6 */
	white-space: -o-pre-wrap; /* Opera 7 */
	word-break: break-all;
}

div#bbcontent {
	word-wrap: break-word; /* Internet Explorer 5.5+ */
	white-space: pre-wrap; /* css-3 */
	white-space: -moz-pre-wrap; /* Mozilla, since 1999 */
	white-space: -pre-wrap; /* Opera 4-6 */
	white-space: -o-pre-wrap; /* Opera 7 */
	word-break: break-all;
}

span#bbtt {
	word-wrap: break-word; /* Internet Explorer 5.5+ */
	white-space: pre-wrap; /* css-3 */
	white-space: -moz-pre-wrap; /* Mozilla, since 1999 */
	white-space: -pre-wrap; /* Opera 4-6 */
	white-space: -o-pre-wrap; /* Opera 7 */
	word-break: break-all;
}

table {
	table-layout: fixed;
}

td {
	word-break: break-all;
	word-wrap: break-word;
}
</style>

<script>
	function fnbrdelete(br_num){
		
		if(confirm("댓글을 정말로 삭제 하시겠습니까?")){
			
		document.fnbrdelete.br_num.value=br_num;
		//document객체의 fnbrdelete form의 br_num 의 값에 br_num을 대입한다.
		
		document.fnbrdelete.submit();
		}
		
		
	}
	function fnbrpost(){
		
		if(document.bbreply.br_nickname.value ==""||document.bbreply.br_coment.value ==""){
			
			alert("빠짐없이 입력 해주세요");
			return;
			
		}
		else if(document.bbreply.br_nickname.value=="관리자"){
			
			
			alert("'관리자' 라는 닉네임은 사용할 수 없습니다.");
			return;
		}
		else if(document.bbreply.br_nickname.value.length>50){
			
			
			alert("닉네임을 너무 길게 입력하셨습니다. 50글자 이하로 입력 해 주세요.(공백 포함)");
			return;
		}
		else if(document.bbreply.br_coment.value.length>2000){
			
			
			alert("댓글을 너무 길게 입력하셨습니다. 2000글자 이하로 입력 해 주세요.(공백 포함)");
			return;
		}
		
		
		else {
			document.bbreply.submit();
						
		}
		
	}
	
	function fnBbconup(){
		
		document.bbconupdateform.submit();
	}
	
	function fnBbdel(){
		
		if(${brlist.size() > 0 }){
			alert("댓글이 등록된 글은 삭제 할 수 없습니다.");
			
		}
		else{
			
			if(confirm("정말로 삭제 하시겠습니까??")){
				
				document.bbcondel.submit();
			}
						
		}
		
		
	} 
	
	function fnBblike(){
		document.bblike.submit()
	}
	function fnBbdlike(){
		document.bbdlike.submit()
	}
	function fnBblikecancle(){
		document.bblikecancle.submit()
	}
	function fnBbdlikecancle(){
		document.bbdlikecancle.submit()
	}
	
	

	function fnBbnewlist(){
		document.bbnewlist.submit()
	}
	function fnBbhotlist(){
		document.bbhotlist.submit()
	}
	function fnBbpost(){
		document.bbpost.submit()
	}
	

	


///////////////// 페이지 관련 javascript function////////////////////
	function prevPage(){
	document.getElementById("prevPage").submit();
	}
	function nextPage(){
	document.getElementById("nextPage").submit();
	}
	function goPage(nowPage){
	document.getElementById("page").value = nowPage;
	document.getElementById("goPage").submit();
	}
	/////////////////////////////끝//////////////////////////////////
	
	
	
	
</script>


</head>

<div class="content-wrapper">
	<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
	<section class="content-header">
		<h1>
			<a href="/HarangProject/bamboo?cmd=BB_LIST" style="color: black">대나무숲
				리스트+컨텐츠 사용자페이지</a>
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


		<div class="row">
			<!-- 너비 사이즈 수정  : col-->
			<div class="col-md-9">
				<div class="box" id="bamcon">
					<div class="box-header" style= "background-color: #dceff4">
						<span id="bbtt"><font size="5">${bbcon.bb_title}</font></span> <span
							class="badge bg-green pull-right"> ${bbcon.bb_regdate}<br>닉네임
							: ${bbcon.bb_nickname}<br> 조회수 : ${bbcon.bb_count }<br>
							<span class="badge bg-blue"><i class="fa fa-thumbs-o-up"></i>
								${bblcnt.size()}</span> <span class="badge bg-red"><i
								class="fa fa-thumbs-o-down"></i> ${bbdlcnt.size()}</span>
						</span>

					</div>

					<div class="box-body" id="bbcontent">${bbcon.bb_content}
					</div>
					<!-- /.box-body -->
					<div class="box-footer" style= "background-color: #dceff4">

						<!-- 아이디 검사 해서 본인일 경우만 수정, 삭제 출력되도록 if문 처리 -->
						<c:if test="${bbcon.m_id eq member.m_id  }">
							<div class="pull-right">
								<a type="button" class="btn btn-success btn-xs"
									href="javascript:fnBbconup()">수정</a> <a type="button"
									class="btn btn-success btn-xs" href="javascript:fnBbdel()">삭제</a>

							</div>
						</c:if>

						<!--  수정을 위한 폼 시작 -->
						<!-- 수정 하려면.. 글번호를 가져가야함.  -->
						<form method="post" action="/bamboo/BB_UPDATE?bb_num=${bbcon.bb_num}"
							name="bbconupdateform">
							

						</form>
						<!--  수정을 위한 폼 끝 -->

						<!--  삭제를 위한 폼 시작 -->
						<!-- 삭제 하려면.. 글번호를 가져가야함.  -->
						<form method="post" action="/bamboo/BB_DEL" name="bbcondel">
							<input type="hidden" name="bb_num" value="${bbcon.bb_num}" /> 

						</form>
						<!--  수정을 위한 폼 끝 -->


						<div align="center">

							<c:if test="${islike == y}">
								<a type="button" class="btn btn-primary"
									href="javascript:fnBblike()">추천</a>
							</c:if>
							<c:if test="${islike != y}">
								<a type="button" class="btn btn-primary"
									href="javascript:fnBblikecancle()">추천취소</a>
							</c:if>
							<c:if test="${isdlike == y}">
								<a type="button" class="btn btn-danger"
									href="javascript:fnBbdlike()">비추천</a>
							</c:if>
							<c:if test="${isdlike != y}">
								<a type="button" class="btn btn-danger"
									href="javascript:fnBbdlikecancle()">비추천취소</a>
							</c:if>
						</div>

						<!-- 추천을 위한 폼 시작 -->
						<form method="post" action="/HarangProject/bamboo" name="bblike">
							<input type="hidden" name="bb_num" value="${bbcon.bb_num}" /> <input
								type="hidden" name="cmd" value="U_BB_LIKE" />

						</form>
						<!--  추천을 위한 폼 끝 -->

						<!-- 비추천을 위한 폼 시작 -->
						<form method="post" action="/HarangProject/bamboo" name="bbdlike">
							<input type="hidden" name="bb_num" value="${bbcon.bb_num}" /> <input
								type="hidden" name="cmd" value="U_BB_DLIKE" />

						</form>
						<!--  비추천을 위한 폼 끝 -->

						<!-- 추천취소를 위한 폼 시작 -->
						<form method="post" action="/HarangProject/bamboo"
							name="bblikecancle">
							<input type="hidden" name="bb_num" value="${bbcon.bb_num}" /> <input
								type="hidden" name="cmd" value="U_BB_LIKE_CANCLE" />

						</form>
						<!--  추천취소를 위한 폼 끝 -->

						<!-- 비추천취소를 위한 폼 시작 -->
						<form method="post" action="/HarangProject/bamboo"
							name="bbdlikecancle">
							<input type="hidden" name="bb_num" value="${bbcon.bb_num}" /> <input
								type="hidden" name="cmd" value="U_BB_DLIKE_CANCLE" />

						</form>
						<!--  비추천취소를 위한 폼 끝 -->






					</div>
					<!-- /.box-body -->
				</div>

				<br> <br>
			</div>

		</div>



		<div class="row">
			<!-- 너비 사이즈 수정  : col-->
			<div class="col-md-9">
				<div class="box" id="reply">

					<div class="box-body">
						<c:if test="${brlist.size() > 0 }">
							<c:forEach var="i" begin="0" end="${brlist.size()-1 }">
								<!--  댓글 들어가는 곳 -->
								<div class="box-header" >

									<c:if test="${brlist[i].br_nickname eq '관리자' }">
										<span class="badge bg-red">${brlist[i].br_nickname }</span>
									</c:if>
									<c:if test="${brlist[i].br_nickname != '관리자' }">
										<span class="badge bg-green">${brlist[i].br_nickname }</span>
									</c:if>

								</div>

								<div class="box-body" id="brcoment">${brlist[i].br_coment }</div>
								<!-- /.box-body -->
								<div class="box-footer " style= "background-color: #dceff4">

									<c:if test="${brlist[i].m_id eq sessionScope.member.m_id }">
										<p align="center">

											<a type="button" class="btn btn-danger btn-xs"
												href="javascript:fnbrdelete('${brlist[i].br_num }')">삭제</a>
										</p>
									</c:if>


								</div>

								<!-- 댓글 들어가는 곳 끝 -->
							</c:forEach>
						</c:if>




					</div>
					<!--  여기까지가 기존 댓글 들어가는 부분 -->

					<!--  댓글 삭제를 위한 폼 -->

					<form method="post" action="/HarangProject/bamboo"
						name="fnbrdelete">
						<input type="hidden" name="br_num" /> <input type="hidden"
							name="bb_num" value="${bbcon.bb_num}" /> <input type="hidden"
							name="cmd" value="U_BR_DEL" />

					</form>

					<!--  댓글 삭제를 위한 폼 끝-->





					<!-- 여기부터 새 댓글 작성창 -->
					<form action="/HarangProject/bamboo" name="bbreply" method="post">
						<input type="hidden" name="cmd" value="U_BB_REPLY" /> <input
							type="hidden" name="bb_num" value="${bbcon.bb_num }" />

						<div class="box-footer">

							<div class="box" id="reply">

								<div class="box-header">
									<div class="form-group">

										<textarea class="form-control" rows="1"
											placeholder="닉네임을 입력 해 주세요" name="br_nickname"></textarea>
									</div>

								</div>

								<div class="box-body">

									<div class="form-group">

										<textarea class="form-control" rows="3"
											placeholder="댓글을 입력 해 주세요" name="br_coment"></textarea>
									</div>



								</div>
								<!-- /.box-body -->
								<div class="box-footer ">
									<p align="right">
										<a type="button" class="btn btn-success btn-xs"
											href="javascript:fnbrpost()">등록</a>


									</p>

								</div>

							</div>




						</div>
					</form>
					<!-- 여기까지 새 댓글 작성창 -->
				</div>
			</div>
		</div>


		<!-- 컨텐츠 헤더 끝-->

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
							<!-- 인기글 보기를 위한 form 끝 -->





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
											
											<td><a href="/bamboo/BB_CON?page=${pageMaker.cri.page}&bb_num=${bbNList.bb_num }" style="color: black">${bbNList.bb_title}</a></td>
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
											<td><a href="/bamboo/BB_CON?page=${pageMaker.cri.page}&bb_num=${bblist.bb_num }" style="color: black">${bblist.bb_title}</a></td>
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
									href="/bamboo/BB_CON${pageMaker.makeQuery(pageMaker.startPage-1)}&bb_num=${bbcon.bb_num}">
									&laquo;</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage}"
								end="${pageMaker.endPage}" var="idx">
								<li value="${pageMaker.cri.page == idx ? 'class=active' : ''}">
									<a href="/bamboo/BB_CON?page=${idx}&bb_num=${bbcon.bb_num}" >
									 ${idx} </a>
								</li>
							</c:forEach>
							<c:if test="${pageMaker.next && pageMaker.endPage>0}">
								<li><a
									href="/bamboo/BB_CON${pageMaker.makeQuery(pageMaker.endPage+1)}&bb_num=${bbcon.bb_num}" >
									&raquo;</a></li>
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
	<input type="hidden" name="cmd" value="U_BB_CON" /> <input
		type="hidden" name="nowPage"
		value="${paging.pagePerBlock * (paging.nowBlock-1)}" /> <input
		type="hidden" name="nowBlock" value="${paging.nowBlock-1}" /> <input
		type="hidden" name="bb_num" value="${bbcon.bb_num }" />
</form>
<!-- 페이징 : 다음 블록으로 이동하는 폼 -->
<form id="nextPage" method="post" action="/HarangProject/bamboo">
	<input type="hidden" name="cmd" value="U_BB_CON" /> <input
		type="hidden" name="nowPage"
		value="${paging.pagePerBlock * (paging.nowBlock+1)}" /> <input
		type="hidden" name="nowBlock" value="${paging.nowBlock+1}" /> <input
		type="hidden" name="bb_num" value="${bbcon.bb_num }" />
</form>
<!-- 페이징 : 해당 페이지로 이동하는 폼 -->
<form id="goPage" method="post" action="/HarangProject/bamboo">
	<input type="hidden" name="cmd" value="U_BB_CON" /> <input
		type="hidden" name="bb_num" value="${bbcon.bb_num }" /> <input
		type="hidden" name="nowPage" value="" id="page" /> <input
		type="hidden" name="nowBlock" value="${paging.nowBlock}" />
</form> --%>
<!-- 페이징 관련 폼 여기까지입니다. ----------------------------------------------------------------------------------- -->



<!-- /.col -->

<!-- /.content-wrapper -->
<%@ include file="../include/footer.jsp"%>

