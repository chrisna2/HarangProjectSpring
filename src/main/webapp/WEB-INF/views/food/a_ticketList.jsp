<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/a_header.jsp" %>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->
<!-- 페이지 헤드 라인 : 제목 -->
<head>
     <title>식권 구매 내역</title>
     <!-- 오늘 날짜 출력 -->
     <jsp:useBean id="now" class="java.util.Date" />
     <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
     <!-- 오늘 날짜를 출력 하려면 ${today}를 선언  -->
</head>
	  <!-- 메인 페이지 구역 , 즉 작업 구역 -->
      <div class="content-wrapper">
<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
      <section class="content-header">
          <h1>
                         식권 판매 내역
            <small>현재 날짜 : ${today}</small>
          </h1>
          <ol class="breadcrumb">
          <!-- 페이지 기록 : 메인에서 부터 현재 페이지 까지의 경로 나열 -->
            <li><a href="#"><i class="fa fa-dashboard"></i> 메인</a></li>
            <li><a href="#"> 하랑 레스토랑</a></li>
            <li class="active"> 식권 구매 내역</li>
          </ol>
        </section>
<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
        <section class="content">
          <!-- 세로 길이 수정 -->
          <div class="row">
           <!-- 너비 사이즈 수정  : col-->
           <div class="col-md-9">
            <!-- 리스트 사용시  -->
             <div class="box">
                <div class="box-header">
                  <h3 class="box-title">식권 판매량 조회</h3>
                   <div class="box-tools">
                    <form action="/food/Aticket" method="get">
                     <div class="input-group">
                       <input type="text" name="keyword" value="${keyword}" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search"/>
	                      <select name="keyfield" class="form-control input-sm pull-right" style="width: 150px;">
	                        <option value="" ${keyfield eq null ? 'selected' : null }></option>
	                        <option value="f_selldate" ${keyfield eq 'f_selldate' ? 'selected' : null }>메뉴판메일</option>
	                        <option value="f_title" ${keyfield eq 'f_title' ? 'selected' : null }>메뉴</option>
	                        <option value="f_point" ${keyfield eq 'f_point' ? 'selected' : null }>판매포인트</option>
	                        <option value="fm_sellcount" ${keyfield eq 'fm_sellcount' ? 'selected' : null }>판매량</option>
	                      </select>
	                      <div class="input-group-btn">
                        <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                      </div>
                    </div>
                    </form>
                  </div>
                  
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table class="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>메뉴 판매일</th>
                        <th>메뉴</th>
                        <th>판매 포인트</th>
                        <th>판매량</th>
                      </tr>
                    </thead>
                    <tbody>
                     <c:choose>
                      <c:when test="${fn:length(requestScope.fmlist) eq 0}">
                      </c:when>
                      <c:otherwise>
                        <c:forEach items="${fmlist}" var="fm" 
                          varStatus="i">
                          <c:if test="${fm.f_selldate == today}">
	                      <tr class="text-green">
	                        <td>${fm.f_selldate}</td>
	                        <td>${fm.f_title}</td>
	                        <td>${fm.f_point}p</td>
	                        <td>${fm.fm_sellcount}</td>
	                      </tr>
	                      </c:if>
	                      <c:if test="${fm.f_selldate > today}">
	                      <tr class="text-blue">
                            <td>${fm.f_selldate}</td>
                            <td>${fm.f_title}</td>
                            <td>${fm.f_point}p</td>
                            <td>${fm.fm_sellcount}</td>
                          </tr>
                          </c:if>
	                      <c:if test="${fm.f_selldate < today}">
	                      <tr>
                            <td>${fm.f_selldate}</td>
                            <td>${fm.f_title}</td>
                            <td>${fm.f_point}p</td>
                            <td>${fm.fm_sellcount}</td>
                          </tr>
                          </c:if>
                        </c:forEach>
                     </c:otherwise>
                    </c:choose>
                  </table>
                </div><!-- /.box-body -->
                 <div class="box-footer clearfix">
                  <ul class="pagination pagination-sm no-margin pull-right">
					<c:if test="${pageMaker.prev}">
                            <li><a href="/food/Aticket${pageMaker.makeQuery(pageMaker.startPage-1)}">&laquo;</a></li>
                    </c:if>
                    <c:forEach begin="${pageMaker.startPage}" 
                    		   end="${pageMaker.endPage}" 
                               var="idx">
                            <li value="${pageMaker.cri.page == idx ? 'class=active' : ''}">
                          		<a href="/food/Aticket?page=${idx}">
                          			${idx}
                        	   	</a>
                             </li>
                   	</c:forEach>
                    <c:if test="${pageMaker.next && pageMaker.endPage>0}">
                      <li><a href="/food/Aticket${pageMaker.makeQuery(pageMaker.endPage+1)}">&raquo;</a></li>
                    </c:if>
                    </ul>
                </div>
              </div><!-- /.box -->
              </div><!-- /.col -->
           </div><!-- /.row -->
        </section><!-- /. 작업 공간 끝! -->
<!------------------------------------------------------------------------------------------------------------------->        
      </div><!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->
<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------> 
<%@ include file="../include/footer.jsp" %>
<script>
///////////////// 페이지 관련 javascript function////////////////////
</script>

