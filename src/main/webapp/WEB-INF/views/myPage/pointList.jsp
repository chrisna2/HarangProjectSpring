<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/header.jsp" %>
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
                         포인트 거래 내역
            <small>당신이 사용한 포인트의 흔적</small>
          </h1>
          <ol class="breadcrumb">
          <!-- 페이지 기록 : 메인에서 부터 현재 페이지 까지의 경로 나열 -->
            <li><a href="#"><i class="fa fa-dashboard"></i> 메인</a></li>
            <li><a href="#"> 마이페이지</a></li>
            <li class="active"> 포인트 거래 내역</li>
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
                  <h3 class="box-title">${member.m_name} [학번 : ${member.m_id} ] 포인트 거래 목록</h3>
                   <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table class="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>거래 날짜</th>
                        <th>거래 내용</th>
                        <th>포인트</th>
                        <th>출금 대상</th>
                        <th>입금 대상</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                      <c:when test="${fn:length(pList) eq 0}">
                      </c:when>
                      <c:otherwise>
                      <c:forEach items="${requestScope.pList}" 
                       var="p" varStatus="i">
                        <c:if test="${member.m_id eq p.m_giver}">
                           <tr class="text-red">
                            <td>${p.r_regdate}</td>
                            <td>${p.r_content}</td>
                            <td>- ${p.r_point}</td>
                            <td>${p.m_givername} | ${p.m_giver}</td>
                            <td>${p.m_havername} | ${p.m_haver}</td>
                          </tr>
                         </c:if>
                         <c:if test="${member.m_id eq p.m_haver}">
                           <tr class="text-green">
                            <td>${p.r_regdate}</td>
                            <td>${p.r_content}</td>
                            <td>+ ${p.r_point}</td>
                            <td>${p.m_givername} | ${p.m_giver}</td>
                            <td>${p.m_havername} | ${p.m_haver}</td>
                          </tr>
                         </c:if>
                      </c:forEach>
                    </c:otherwise>
                    </c:choose>
                    </tbody>
                  </table>
                </div><!-- /.box-body -->
                 <div class="box-footer clearfix">
                       <ul class="pagination pagination-sm no-margin pull-right">
					<c:if test="${pageMaker.prev}">
                            <li><a href="/myPage/pointList${pageMaker.makeQuery(pageMaker.startPage-1)}">&laquo;</a></li>
                    </c:if>
                    <c:forEach begin="${pageMaker.startPage}" 
                    		   end="${pageMaker.endPage}" 
                               var="idx">
                            <li value="${pageMaker.cri.page == idx ? 'class=active' : ''}">
                          		<a href="/myPage/pointList?page=${idx}">
                          			${idx}
                        	   	</a>
                             </li>
                   	</c:forEach>
                    <c:if test="${pageMaker.next && pageMaker.endPage>0}">
                      <li><a href="/myPage/pointList${pageMaker.makeQuery(pageMaker.endPage+1)}">&raquo;</a></li>
                    </c:if>
                    </ul>
                    <!-- 검색 폼 -->    
                    <form action="/myPage/pointList" name="search" method="post">
                      <div class="input-group">
                          <select name="keyfield" class="form-control input-sm pull-left" style="width: 150px;">
                            <option value="r_regdate" ${keyfield eq 'r_regdate' ? 'selected' : null }>거래 날짜</option>
                            <option value="r_content" ${keyfield eq 'r_content' ? 'selected' : null }>거래 내용</option>
                            <option value="r_point" ${keyfield eq 'r_point' ? 'selected' : null }>포인트</option>
                            <option value="m_givername" ${keyfield eq 'm_givername' ? 'selected' : null }>출금 대상</option>
                            <option value="m_havername" ${keyfield eq 'm_havername' ? 'selected' : null }>입금 대상</option>
                          </select>
                          <input type="text" name="keyword" class="form-control input-sm  pull-left" style="width: 150px;" placeholder="Search"/>
                         <div class="input-group-btn  pull-left">
                            <button class="btn btn-sm btn-primary"> 검색 <i class="fa fa-search"></i></button>
                         </div>
                      </div>
                    </form>
                </div>
              </div><!-- /.box -->
              </div><!-- /.col -->
           </div><!-- /.row -->
        </section><!-- /. 작업 공간 끝! -->
<!------------------------------------------------------------------------------------------------------------------->        
      </div><!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->
<!-- 페이징 관련 폼 여기까지입니다. ----------------------------------------------------------------------------------- -->
<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------> 
<%@ include file="../include/footer.jsp" %>
