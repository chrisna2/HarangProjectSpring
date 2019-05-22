<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/header.jsp" %>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->
<!-- 페이지 헤드 라인 : 제목 -->
<head>
     <title>수강 시간표 작성</title>
     <style type="text/css">
        td{
            text-align:center;
            height:77px;
        }
        th{
            text-align:center;
        }
     </style>
</head>
	  <!-- 메인 페이지 구역 , 즉 작업 구역 -->
      <div class="content-wrapper">
<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
        <section class="content-header">
          <h1>
             	수강 시간표
            <small>당신의 수강 시간표를 미리 작성해 보세요</small>
          </h1>
          <ol class="breadcrumb">
          <!-- 페이지 기록 : 메인에서 부터 현재 페이지 까지의 경로 나열 -->
            <li><a href="#"><i class="fa fa-dashboard"></i> 메인</a></li>
            <li><a href="#"> 마이페이지</a></li>
            <li class="active"> 수강 시간표</li>
          </ol>
        </section>
<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
        <section class="content">
          <!-- 세로 길이 수정 -->
          <div class="row">
           <!-- 너비 사이즈 수정  : col-->
           <div class="col-md-9">
             
           <!-- 수업 시간표 -->
             <div class="box">
                <div class="box-header">
                  <h3 class="box-title">${ttname} 당신의 시간표</h3>
                   <div class="box-tools">
                    <form action="/myPage/timeTable2" method="post" name="timetable">
                     <div class="input-group">
                          <select name="tt_term" class="form-control input-sm pull-right" style="width: 15%;">
                            <option value="1" ${tt_term eq '1' ? 'selected' : null }>1학기</option>
                            <option value="2" ${tt_term eq '2' ? 'selected' : null }>2학기</option>
                          </select>
                          <select name="tt_grade" class="form-control input-sm pull-right" style="width: 15%;">
                            <option value="1" ${tt_grade eq '1' ? 'selected' : null }>1학년</option>
                            <option value="2" ${tt_grade eq '2' ? 'selected' : null }>2학년</option>
                            <option value="3" ${tt_grade eq '3' ? 'selected' : null }>3학년</option>
                            <option value="4" ${tt_grade eq '4' ? 'selected' : null }>4학년</option>
                          </select>
                          <div class="input-group-btn">
                          <input type="hidden" name='check' value="findtt">
                        <button type="submit" class="btn btn-sm btn-primary">&nbsp;&nbsp;&nbsp; 시간표 조회  &nbsp;&nbsp;&nbsp;<i class="fa fa-search">&nbsp;</i></button>
                      </div>
                    </div>
                    </form>
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table class="table table-bordered table-striped" style="border-color: #9E9E9E">
                  <colgroup>
                    <col class="col-md-1 col-xs-1">
                    <col class="col-md-2 col-xs-2"> 
                    <col class="col-md-2 col-xs-2"> 
                    <col class="col-md-2 col-xs-2"> 
                    <col class="col-md-2 col-xs-2"> 
                    <col class="col-md-2 col-xs-2"> 
                  </colgroup>
                  <thead>
                    <tr class="bg-gray">
                      <th></th>
                      <th>월요일</th>
                      <th>화요일</th>
                      <th>수요일</th>
                      <th>목요일</th>
                      <th>금요일</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr id="t1">
                      <td class="bg-gray"><b>1교시</b>
                      <td id="d1"></td>
                      <td id="d2"></td>
                      <td id="d3"></td>
                      <td id="d4"></td>
                      <td id="d5"></td>
                    </tr>
                    <tr id="t2">
                     <td class="bg-gray"><b>2교시</b>
                      <td id="d1"></td>
                      <td id="d2"></td>
                      <td id="d3"></td>
                      <td id="d4"></td>
                      <td id="d5"></td>
                    </tr>
                    <tr id="t3">
                       <td class="bg-gray"><b>3교시</b>
                      <td id="d1"></td>
                      <td id="d2"></td>
                      <td id="d3"></td>
                      <td id="d4"></td>
                      <td id="d5"></td>
                    </tr>
                    <tr id="t4">
                       <td class="bg-gray"><b>4교시</b>
                      <td id="d1"></td>
                      <td id="d2"></td>
                      <td id="d3"></td>
                      <td id="d4"></td>
                      <td id="d5"></td>
                    </tr>
                    <tr id="t5">
                       <td class="bg-gray"><b>5교시</b>
                      <td id="d1"></td>
                      <td id="d2"></td>
                      <td id="d3"></td>
                      <td id="d4"></td>
                      <td id="d5"></td>
                    </tr>
                    <tr id="t6">
                      <td class="bg-gray"><b>6교시</b>
                      <td id="d1"></td>
                      <td id="d2"></td>
                      <td id="d3"></td>
                      <td id="d4"></td>
                      <td id="d5"></td>
                    </tr>
                    <tr id="t7">
                      <td class="bg-gray"><b>7교시</b>
                      <td id="d1"></td>
                      <td id="d2"></td>
                      <td id="d3"></td>
                      <td id="d4"></td>
                      <td id="d5"></td>
                    </tr>
                    <tr id="t8">
                       <td class="bg-gray"><b>8교시</b>
                      <td id="d1"></td>
                      <td id="d2"></td>
                      <td id="d3"></td>
                      <td id="d4"></td>
                      <td id="d5"></td>
                    </tr>
                    <tr id="t9">
                       <td class="bg-gray"><b>9교시</b>
                      <td id="d1"></td>
                      <td id="d2"></td>
                      <td id="d3"></td>
                      <td id="d4"></td>
                      <td id="d5"></td>
                    </tr>
                   </tbody>
                  </table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->

 <!-- 리스트 사용시  -->
             <div class="box">
                <div class="box-header">
                  <h3 class="box-title">${ttname} 시간표 출력 목록</h3>
                   <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table class="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>수업 번호</th>
                        <th>주요 학과</th>
                        <th>필수 여부</th>
                        <th>수업 명</th>
                        <th>대상 학년</th>
                        <th>학기</th>
                        <th>수업 요일</th>
                        <th>수업 시간</th>
                        <th>교수님</th>
                        <th>강의실</th>
                        <th>이수 학점</th>
                        <th>시간표 등록</th>
                      </tr>
                    </thead>
                    <tbody id="ttList">
                    </tbody>
                  </table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
              
			<!--
				Ajax는 기본적으로 비동기식호출이기 때문에 서버에 요청을 하고 그 결과값을 받더라도 화면의 전환이 일어나지 않는다. 
				따라서 결과값을 받은 후, 데이터의 갱신 등을 따로 해줘야한다. 이것은 submit을 할때와 다른 점으로, 
				화면 갱신이 일어나지 않기 때문에 JSTL등을 이용하여 목록 등을 만들수가 없다. 
				
				출처: https://addio3305.tistory.com/91 [흔한 개발자의 개발 노트]
				
				따라서 현재 화면을 비동기 AJAX로 전환 하려면 JSTL및 EL을 제거해야 한다...
				
			-->
            <!-- 리스트 사용시  -->
             <div class="box">
                <div class="box-header">
                  <h3 class="box-title">${ttname} 수업 목록</h3>
                   <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table class="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>수업 번호</th>
                        <th>주요 학과</th>
                        <th>필수 여부</th>
                        <th>수업 명</th>
                        <th>대상 학년</th>
                        <th>학기</th>
                        <th>수업 요일</th>
                        <th>수업 시간</th>
                        <th>교수님</th>
                        <th>강의실</th>
                        <th>이수 학점</th>
                        <th>시간표 등록</th>
                      </tr>
                    </thead>
                    <tbody id="lessonList">
                    </tbody>
                  </table>
                </div><!-- /.box-body -->
                 <div class="box-footer clearfix">
                     <ul class="pagination pagination-sm no-margin pull-right" id="lessonPage">
                     </ul>
                    <!-- 검색 폼 -->    
                    <form name="search" method="post" action="/myPage/timeTable">
                      <div class="input-group">
                          <input type="hidden" name="tt_grade" value="${tt_grade}"/>
                          <input type="hidden" name="tt_term" value="${tt_term}"/>
                          <input type="hidden" name='check' value="search">
                          <select name="keyfield" class="form-control input-sm pull-left" style="width: 150px;">
                            <option value="l_dept" ${keyfield eq 'l_dept' ? 'selected' : null }>주요 학과</option>
                            <option value="l_ismust" ${keyfield eq 'l_ismust' ? 'selected' : null }>필수 여부</option>
                            <option value="l_name" ${keyfield eq 'l_name' ? 'selected' : null }>수업명</option>
                            <option value="l_grade" ${keyfield eq 'l_grade' ? 'selected' : null }>대상 학년</option>
                            <option value="l_day" ${keyfield eq 'l_day' ? 'selected' : null }>수업 요일</option>
                            <option value="l_time" ${keyfield eq 'l_time' ? 'selected' : null }>수업 시간</option>
                            <option value="l_teacher" ${keyfield eq 'l_teacher' ? 'selected' : null }>교수님</option>
                            <option value="l_room" ${keyfield eq 'l_room' ? 'selected' : null }>강의실</option>
                            <option value="l_credit" ${keyfield eq 'l_credit' ? 'selected' : null }>이수 학점</option>
                          </select>
                          <input type="text" name="keyword" class="form-control input-sm  pull-left" style="width: 150px;" value="${keyword}"/>
                         <div class="input-group-btn  pull-left">
                            <button type="submit" class="btn btn-sm btn-primary"> 검색 <i class="fa fa-search"></i></button>
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
		<form name="reset" method="post" action="/myPage/timeTable">
		    <input type="hidden" name='check' value="findtt">
		    <input type="hidden" name="tt_grade" value="${tt_grade}"/>
		    <input type="hidden" name="tt_term" value="${tt_term}"/>
		    <input type="hidden" name="keyword" value="${keyword}"/>
		    <input type="hidden" name="keyfield" value="${keyfield}"/>
		</form> 
<!-- 푸터(footer) 삽입 [지우지 마세여] --------------------------------------------------------------------------------------> 
<%@ include file="../include/footer.jsp" %>
<script>
//초기화함수
$(document).ready(function(){
	refershLesson();
});

//이제 이 의미를 되살려본다. 20190522 ㅋㅋㅋ
function refershLesson(){
	
	var keyword = "${keyword}";
	var keyfield = "${keyfield}";
	var tt_term = "${tt_term}";
	var tt_grade = "${tt_grade}";
	var m_id = "${member.m_id}";
	var page = 0;
	
	if(null == "${param.page}" || "" == "${param.page}"){
		page = 1;
	}
	else{
		page = "${param.page}";
	}
	
	$.getJSON("/myPage/refreshLesson",
			{
				tt_term:tt_term,
				tt_grade:tt_grade,
				m_id:m_id,
				keyfield:keyfield,
				keyword:encodeURIComponent(keyword),
				page:page
			},
			function(data) {
				
				console.log(data);
				
				//시간표 초기화!
				resetTime();
				$("#ttList tr").remove();
				//FOR문이 돌고 있는 상황 ;;
				$(data.timetableList).each(function(index, tt){
					$("#ttList").append(
						 "<tr>"
                            +"<td>"+tt.l_num+"</td>"
                            +"<td>"+tt.l_dept+"</td>"
                            +"<td>"+tt.l_ismust+"</td>"
                            +"<td>"+tt.l_name+"</td>"
                            +"<td>"+tt.l_grade+"</td>"
                            +"<td>"+tt.l_term+"</td>"
                            +"<td id='day"+tt.l_num+"'>"+tt.l_day+"</td>"
                            +"<td id='time"+tt.l_num+"'>"+tt.l_time+"</td>"
                            +"<td>"+tt.l_teacher+"</td>"
                            +"<td>"+tt.l_room+"</td>"
                            +"<td>"+tt.l_credit+"</td>"
                            +"<td><input type='button' onclick=deletelesson('"+tt.l_num+"')  class='btn btn-danger' value='수강 취소'></td>"
                          +"</tr>");
					
					  // 요일  -> 날짜 숫자로 변환
				       var day = tt.l_day;
				       var daynum = 0;
				       
				        if(day=="월"){
				        	daynum = 1;
				            }
				        else if(day=="화"){
				        	daynum = 2;
				            }
				        else if(day=="수"){
				        	daynum = 3;
				            }
				        else if(day=="목"){
				        	daynum = 4;
				            }
				        else if(day=="금"){
				        	daynum = 5;
				            }
				        //alert(daynum);
				        
				        // 시간
				        var time = tt.l_time;
				        // 1-3 ,4-6, 7-9 
				        var sptime = time.split("-");
				        var start = sptime[0];
				        //alert(start);
				        var end = sptime[1];
				        //alert(end);
				        
				        // 수업 이름
				        var name = tt.l_name;
				        
				        // 강의실
				        var room = tt.l_room;

				        // 교수00
				        var teacher = tt.l_teacher;

				        // 학과별 색상 변경
				        var dept = tt.l_dept;
				        var color = "";
				        if(dept=="경영학과"){
				        	color = "#EECECE";
				            }
				        else if(dept=="교양학과"){
				        	color = "#EFF7BE";
				            }
				        else if(dept=="국어국문학과"){
				        	color = "#BEDDF7";
				            }
				        else if(dept=="수학과"){
				        	color = "#BEF7EA";
				            }
				        else if(dept=="산업디자인학과"){
				        	color = "#F4DBAF";
				            }
				        else if(dept=="컴퓨터공학과"){
				        	color = "#E0A8FF";
				            }
				        // 시간표에 해당 내용을 올리는 For문
				        for(i=start;i<=end;i++){
				        	$("#t"+i+" > #d"+daynum)
				        	.css("background",color)
				        	.html(name+"<br>"+room+"<br>"+teacher);
				        }
				});				
				
				$("#lessonList tr").remove();
				$(data.lessonList).each(function(index, l){
					if(l.m_id == m_id){
						$("#lessonList").append(
							"<tr>"
								+"<td>"+l.l_num+"</td>"
			                    +"<td>"+l.l_dept+"</td>"
			                    +"<td>"+l.l_ismust+"</td>"
			                    +"<td>"+l.l_name+"</td>"
			                    +"<td>"+l.l_grade+"</td>"
			                    +"<td>"+l.l_term+"</td>"
			                    +"<td id='day"+l.l_num+"'>"+l.l_day+"</td>"
			                    +"<td id='time"+l.l_num+"'>"+l.l_time+"</td>"
			                    +"<td>"+l.l_teacher+"</td>"
			                    +"<td>"+l.l_room+"</td>"
			                    +"<td>"+l.l_credit+"</td>"
			                    +"<td><input type='button' onclick=deletelesson('"+l.l_num+"')  class='btn btn-danger' value='수강 취소'></td>"
		                    +"</tr>");
					}
					else{
						$("#lessonList").append(
							"<tr>"
								+"<td>"+l.l_num+"</td>"
			                    +"<td>"+l.l_dept+"</td>"
			                    +"<td>"+l.l_ismust+"</td>"
			                    +"<td>"+l.l_name+"</td>"
			                    +"<td>"+l.l_grade+"</td>"
			                    +"<td>"+l.l_term+"</td>"
			                    +"<td id='day"+l.l_num+"'>"+l.l_day+"</td>"
			                    +"<td id='time"+l.l_num+"'>"+l.l_time+"</td>"
			                    +"<td>"+l.l_teacher+"</td>"
			                    +"<td>"+l.l_room+"</td>"
			                    +"<td>"+l.l_credit+"</td>"
			                    +"<td><input type='button' onclick=enroll('"+l.l_num+"')  class='btn btn-primary' value='수강 등록'></td>"
			               +"</tr>");
					}
				});
				
				$("#lessonPage li").remove();
				
				var pagingTag = "";
				
				/*	ajax화 시키기
					1> 일단 어차피 페이징에 서 바뀌는 건 수업 리스트 밖에 없다.
					2> 여기다가 다 몰아넣는건 일단 버전1 향후 페이징도 ajax시키는건 버전2
					3> 현재 함수기능은 초기화 기능이다. 모든 걸 다할 필요가 없다.
					
					최종 목적 : 파라미터를  url에 보이면 안된다! 모든 처리를 ajax로! 
				*/
				if(true==data.prev){
					pagingTag += "<li><a href='/myPage/timeTable2"+data.prevUrl+"&tt_grade="+tt_grade+"&tt_term="+tt_term+"'>&laquo;</a></li>"
				}
				for(var i = data.startPage; i <= data.endPage; i++){
					if(data.criPage == i){
						pagingTag += "<li class=active>"
								  + "<a href='/myPage/timeTable2?page="+i+"&tt_grade="+tt_grade+"&tt_term="+tt_term+"'>" 
								  + i + "</a></li>";
					}
					else{
						pagingTag += "<li>"
							      + "<a href='/myPage/timeTable2?page="+i+"&tt_grade="+tt_grade+"&tt_term="+tt_term+"'>" 
							   	  + i + "</a></li>";						
					}
				}
				
				if(true==data.next && data.endPage>0){
					pagingTag += "<li><a href='/myPage/timeTable2"+data.nextUrl+"&tt_grade="+tt_grade+"&tt_term="+tt_term+"'>&raquo;</a></li>"
				}
				
				$("#lessonPage").append(pagingTag);
				
			});
}


function resetTime(){
	 // 시간표에 해당 내용을 올리는 For문
   for(i=1;i<=9;i++){
   	for(j=1;j<=5;j++){
	        $("#t"+i+" > #d"+j)
	        .css("background","#FFFFFF")
	        .html("");
   	}
   }
}


//등록
function enroll(lesson_num) {
	
	var l_num = lesson_num;
	var l_day = $("#day"+lesson_num).text();
	var l_time = $("#time"+lesson_num).text();
	var m_id = "${member.m_id}";
	var tt_grade = "${tt_grade}";
	var tt_term = "${tt_term}";
	var keyword = "${keyword}";
	var keyfield = "${keyfield}";
	
	$.ajax({
		type	:	'post',
		url		:	'/myPage/timeTable/enroll',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : 'text',
		data : JSON.stringify({
			//keyword	 :	keyword,
			//keyfield :  keyfield,
			m_id	 : 	m_id,
			l_num	 :	l_num,
			l_day	 :	l_day,
			l_time	 :	l_time,
			tt_grade :	tt_grade,
			tt_term	 :	tt_term
		}),
		success : function(result)
		{			
			if(result == 'enroll'){
				alert("수업이 시간표에 등록되었습니다.");
				refershLesson();
				//reset.submit();
			}
			else if(result == 'duplicate'){
				alert("시간표에 시간이 중복 됩니다.");
			}
		}
	});
}
//삭제
function deletelesson(lesson_num) {
	var l_num = lesson_num;
	var l_day = $("#day"+lesson_num).text();
	var l_time = $("#time"+lesson_num).text();
	var m_id = "${member.m_id}";
	var tt_grade = "${tt_grade}";
	var tt_term = "${tt_term}";
	var keyword = "${keyword}";
	var keyfield = "${keyfield}";
	
	$.ajax({
		type	:	'post',
		url		:	'/myPage/timeTable/delete',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : 'text',
		data : JSON.stringify({
			//keyword	 :	keyword,
			//keyfield :  keyfield,
			m_id	 : 	m_id,
			l_num	 :	l_num,
			l_day	 :	l_day,
			l_time	 :	l_time,
			tt_grade :	tt_grade,
			tt_term	 :	tt_term
		}),
		success : function(result)
		{			
			if(result == 'delete'){
				alert("수업이 시간표에 삭제되었습니다.");
				refershLesson();
				//reset.submit();
			}
			else if(result == 'evaluated'){
				alert("이미 평가가 완료된 수업입니다.");
			}
		}
	});
}
</script>
    