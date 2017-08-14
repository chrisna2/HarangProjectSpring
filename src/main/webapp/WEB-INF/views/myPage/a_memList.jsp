<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../include/a_header.jsp" %>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->
<!-- 페이지 헤드 라인 : 제목 -->
<head>
<script type="text/javascript">
/* window.onload =  function () {
	
    var usermail = "${read3.m_mail}";
    var mailArray = usermail.split('@');
    input.m_mail1.value = mailArray[0];
    input.m_mail2.value = mailArray[1];

    var usertel = "${read3.m_tel}";
    var telArray = usertel.split('-');
    input.m_tel1.value = telArray[0];
    input.m_tel2.value = telArray[1];
    input.m_tel3.value = telArray[2];
    
    var useraddr = "${read3.m_addr}"
    var mailArray = useraddr.split('/');
    input.m_addr1.value = mailArray[0];
    input.m_addr2.value = mailArray[1];
    input.m_addr3.value = mailArray[2];
} */


function mailcheck(){ 
     i = input.m_mail3.selectedIndex; // 선택항목의 인덱스 번호
     var mail= input.m_mail3.options[i].value; // 선택항목 value
     input.m_mail2.value = mail;
}


function checkform(){
    
    var m_mail1 = input.m_mail1.value;
    var m_mail2 = input.m_mail2.value;
    var m_mail = m_mail1 + "@" + m_mail2;
    input.m_mail.value = m_mail;

    var m_tel1 = input.m_tel1.value;
    var m_tel2 = input.m_tel2.value;
    var m_tel3 = input.m_tel3.value;
    var m_tel = m_tel1 + "-" + m_tel2 + "-" + m_tel3;
    input.m_tel.value = m_tel;

    var m_addr1 = input.m_addr1.value;
    var m_addr2 = input.m_addr2.value;
    var m_addr3 = input.m_addr3.value;
    var m_addr = m_addr1 + "/" + m_addr2 + "/" + m_addr3;
    input.m_addr.value = m_addr;

    return true;
    
}
</script>


     <title>회원 관리</title>
</head>
	  <!-- 메인 페이지 구역 , 즉 작업 구역 -->
      <div class="content-wrapper">
<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
        <section class="content-header">
          <h1>
             	회원 관리
          </h1>
          <br>
          <form action="/myPage/Anewmem" method="post">
          <input type="submit" class="btn btn-sm btn-primary col-md-9" value="신입생/편입생 등록">
          </form>
          <br>
          <br>
          <ol class="breadcrumb">
          <!-- 페이지 기록 : 메인에서 부터 현재 페이지 까지의 경로 나열 -->
            <li><a href="#"><i class="fa fa-dashboard"></i> 관리자 메인</a></li>
            <li><a href="#"> 사이트 관리</a></li>
            <li class="active"> 회원 관리</li>
          </ol>
        </section>
<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
        <section class="content">
          <!-- 세로 길이 수정 -->
          <div class="row">
           <!-- 너비 사이즈 수정  : col-->
           <div class="col-md-9">
           
             <!-- Input addon -->
              <div class="box box-black" id="userData" hidden="hidden">
                <div class="box-header">
                  <h3 class="box-title">개인 정보 수정</h3>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                  </div>
                </div>
                
                <!-- form 시작 -->
                <form role="form" name="input" onsubmit="return checkform()"  method="post" enctype="multipart/form-data">
                <div class="box-body">
                  <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-picture-o"></i>이미지</span>
                    <span class="input-group-addon  bg-gray">
   
                        <img src="" id="memberImg" class="img-rounded" height="90" width="90" alt="User Image"/>
                        
                    </span>
                  </div>
                  <br>
                  <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-smile-o"></i> 이름</span>
                    <input type="text" name="m_name" class="form-control" value="" readonly="readonly">
                  </div>
                  <br>
                  <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-money"></i> 보유 포인트</span>
                    <input type="text" name="m_point" class="form-control" value="" readonly="readonly">
                  </div>
                  <br>
                  <!-- 폼 태그 따로 줌 -->
                  
                  <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-sort-numeric-desc"></i> 학번</span>
                    <input type="text" name="m_id" class="form-control" value="" required="required">
                    <span class="input-group-btn">
                      <button class="btn btn-success btn-flat" type="button">학번 수정</button>
                    </span>
                  </div>
                 
                  <br>
                  <!-- 폼 태그 따로 줌 -->
                  
                  <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-sitemap"></i> 학과</span>
                    <input type="text" name="m_dept" class="form-control" value="" required="required">
                    <span class="input-group-btn">
                      <button class="btn btn-success btn-flat" type="button">학과 수정</button>
                    </span>
                  </div>
                 
                  <br>
                  <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-star"></i> 생년월일</span>
                    <input type="text" name="m_birth" class="form-control" value="" readonly="readonly">
                  </div>
                  <br>
                  <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-envelope"></i> 이메일</span>
                    <input type="text" name="m_mail1" class="form-control"  readonly="readonly">
                    <span class="input-group-addon bg-gray"> @ </span>
                    <input type="text" name="m_mail2" class="form-control" value="" readonly="readonly">
                  </div>
                  <br>
                  <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-phone-square"></i> 연락처</span>
                   <input type="text" name="m_tel1" class="form-control"  readonly="readonly">
                    <span class="input-group-addon bg-gray"> - </span>
                    <input type="text" name="m_tel2" class="form-control"  readonly="readonly">
                    <span class="input-group-addon bg-gray"> - </span>
                    <input type="text" name="m_tel3" class="form-control"  readonly="readonly">
                  </div>
                  <br>
                  <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-location-arrow"></i> 우편번호</span>
                    <input type="text" name="m_addr1" class="form-control"  readonly="readonly">
                  </div>
                  <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-location-arrow"></i> 우편번호 주소</span>
                    <input type="text" name="m_addr2" class="form-control"  readonly="readonly">
                  </div>
                  <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-location-arrow"></i> 상세 주소</span>
                    <input type="text" name="m_addr3" class="form-control"  readonly="readonly">
                  </div>
                  <br>
                </div><!-- /.box-body -->
              
                 <div class="box-footer" align="right">
                    <input type="button" class="btn" id="btn-close" value="닫기">
                    <input type="submit" class="btn btn-danger" value="강퇴">
                </div>
                  </form>
              </div><!-- /.box -->
           
           <!-- 리스트 사용시  -->
            <div class="box">
                <div class="box-header">
                  <h3 class="box-title">회원 목록</h3>
                   <div class="box-tools">
                    <div class="input-group">
                    <form action="/myPage/AmemList" name="search" method="post">
                      <input type="text"  name="keyword" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search"/>
                      <select class="form-control input-sm pull-right" name="keyword" style="width: 150px;">
                         <option></option>
                         <option value="m_id" ${keyfield eq 'm_id' ? 'selected' : null}>학번 / 관리자 번호</option>
                         <option value="m_name" ${keyfield eq 'm_name' ? 'selected' : null}>이름</option>
                         <option value="m_dept" ${keyfield eq 'm_dept' ? 'selected' : null}>학과</option>
                         <option value="m_point" ${keyfield eq 'm_dept' ? 'selected' : null}>포인트</option>
                      </select>
                      <div class="input-group-btn" >
                        <button type="submit" class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                      </div>
                       </form>
                    </div>
                    
                  </div>
                  
                </div><!-- /.box-header -->
                
                <div class="box-body">
                  <table class="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>학번 / 관리자 번호</th>
                        <th>이름</th>
                        <th>학과</th>
                        <th>학년</th>
                        <th>보유 포인트</th>
                        <th>회원 등록 날짜</th>
                      </tr>
                    </thead>
                    <tbody>
                    
                    <c:forEach var="all" 
                      items="${memList}">
                       <tr>
                        <td><a href="javascript:memberData('${all.m_id}')"> ${all.m_id} </a></td>
                        <td>${all.m_name }</td>
                        <td>${all.m_dept}</td>
                        <td>${all.m_grade}</td>
                        <td>${all.m_point}</td>
                        <td>${all.m_regdate}</td>
                      </tr>
                     </c:forEach>
                  
                    
                   
                    </tbody>
                  </table>
                     <div class="box-footer clearfix">
             			<ul class="pagination pagination-sm no-margin pull-right">
						<c:if test="${pageMaker.prev}">
	                            <li><a href="/myPage/AmemList${pageMaker.makeQuery(pageMaker.startPage-1)}">&laquo;</a></li>
	                    </c:if>
	                    <c:forEach begin="${pageMaker.startPage}" 
	                    		   end="${pageMaker.endPage}" 
	                               var="idx">
	                            <li value="${pageMaker.cri.page == idx ? 'class=active' : ''}">
	                          		<a href="/myPage/AmemList?page=${idx}">
	                          			${idx}
	                        	   	</a>
	                             </li>
	                   	</c:forEach>
	                    <c:if test="${pageMaker.next && pageMaker.endPage>0}">
	                      <li><a href="/myPage/AmemList${pageMaker.makeQuery(pageMaker.endPage+1)}">&raquo;</a></li>
	                    </c:if>
                    </ul>
                </div><!-- /.box-body -->
                  
                </div><!-- /.box-body -->
                
              </div><!-- /.box -->
              
              </div><!-- /.col -->
           </div><!-- /.row -->
        </section><!-- /. 작업 공간 끝! -->
        
                   <!-- 모달 : 뒷 페이지 배경을 눌러도 꺼지지 않음 -->
                <div class="modal fade" id="theModal" data-backdrop="static">
                    <div class="modal-dialog" style="width:80%">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3>원본 이미지 확인</h3>
                            </div>
                            <div class="modal-body" align="center">
                            	<img id="memberBigImg" width="100%">
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary" data-dismiss="modal">닫기</button>
                            </div>
                        </div>
                    </div>
                </div><!-- 모달 끝 -->
<!------------------------------------------------------------------------------------------------------------------->        
      </div><!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->
        <!-- 페이징 관련 폼 ----------------------------------------------------------------------->
<!-- 페이징 : 이전 블록으로 이동하는 폼 -->
<form name="frmRead" method="post" action="/HarangProject/myPage?cmd=AmemList">
		<input type="hidden" name="m_id" />
</form>	
<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------> 
<%@ include file="../include/footer.jsp" %>
<script>
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

//로컬 업로드 사진 불러오기
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#local').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}
$("#imgInp").change(function(){
    readURL(this);
});

//[image] 원본(큰이미지)의 이미지 이름 불러오기
function getImageLink(fileName){
	
	var front = fileName.substr(0,12);
	var end = fileName.substr(14);
	return front + end;
	
}

function memberData(m_id){

	$.getJSON("/myPage/userData",
			{m_id:m_id},
			function(data){
				
			    $("#userData").slideUp();
				
				var usermail = data.m_mail;
				
				if(usermail == null || usermail == ""){
					alert("아직 회원이 회원으로 등록 하지 않았습니다.");
				}
				else{
					var mailArray = usermail.split('@');
					    input.m_mail1.value = mailArray[0];
					    input.m_mail2.value = mailArray[1];

					var usertel = data.m_tel;
					var telArray = usertel.split('-');
					    input.m_tel1.value = telArray[0];
					    input.m_tel2.value = telArray[1];
					    input.m_tel3.value = telArray[2];
					    
					var useraddr = data.m_addr;
					var mailArray = useraddr.split('/');
					    input.m_addr1.value = mailArray[0];
					    input.m_addr2.value = mailArray[1];
					    input.m_addr3.value = mailArray[2];
				}
			    
			    input.m_id.value = data.m_id;
			    
			    input.m_name.value = data.m_name;
			    
			    input.m_birth.value = data.m_birth;
			    
			    input.m_dept.value = data.m_dept;

			    input.m_point.value = data.m_point;
				
			    var $image = data.m_photo;
			    
			    if($image == null || $image == ""){
				    $("#memberImg").attr("src", "../resources/dist/img/no-user-image.gif");
			    }
			    else{
				    $("#memberImg").attr("src", "/displayFile?fileName="+$image);
				    $("#memberBigImg").attr("src", "/displayFile?fileName="+getImageLink($image));
			    }
			    
			    
			    $("#userData").slideDown();
			});
}

$("#memberImg").click(
	      function(){
	         $("#theModal").modal('toggle');
	    });

$("#btn-close").click(function(){
	 $("#userData").slideUp();
});


</script>
