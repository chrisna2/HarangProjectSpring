<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../include/header.jsp"%>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->
<!-- 페이지 헤드 라인 : 제목 -->
<head>
<title>내 예약 내역</title>
<style>
.pagination {
	display: inline-block;
	padding-left: 0;
	margin: 0px 0;
	border-radius: 4px
}
</style>
<script type="text/javascript">
	window.onload = function() {
		var result = "${result}";

		if (result == null) {

		} else if (result == "true") {
			alert("예약이 취소되었습니다.");
		}
		$("#timeline label").remove();
		for(var i = 0 ; i < 13 ; i++){
			$("#timeline").append(
				 "<label class='btn btn-warning' id='l"+i+"' disabled='disabled' style='width:7.6%'>"
				+"<input type='checkbox'  id='b"+i+"' value='"+(i+8)+"'>"
				+(i+8)
				+"</label>"
			);
		}
	}

	function dateFormCheck() {
		/*날짜 비교 */
		var _reserdate = checkform.resertime.value.split("-");
		//alert(_reserdate);
		var year = _reserdate[0];
		var month = _reserdate[1];
		var day = _reserdate[2];
		var reserdate = new Date();
		reserdate.setFullYear(year, month - 1, day);
		var today = new Date();

		if (today.getTime() > reserdate.getTime()) {
			alert("이미 예약일을 지난 시설예약은 환불이 불가합니다.");
			return false;
		} else {
			return true;
		}
	}
</script>

</head>
<!-- 메인 페이지 구역 , 즉 작업 구역 -->
<div class="content-wrapper">
	<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
	<section class="content-header">
		<h1>시설 예약 관리</h1>
		<ol class="breadcrumb">
			<!-- 페이지 기록 : 메인에서 부터 현재 페이지 까지의 경로 나열 -->
			<li><a href="#"><i class="fa fa-dashboard"></i> 메인</a></li>
			<li class="active">시설 예약 내역</li>
		</ol>
	</section>
	<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
	<section class="content">
		<!-- 운동장 테이블 위치 -->
		<!-- 세로 길이 수정 -->
		<div class="row">
			<!-- 너비 사이즈 수정  : col-->
			<div class="col-md-12">
				<!-- 리스트 사용시  -->
				<div class="box box-warning">
					<div class="box-header">
						<h3 class="box-title">운동장 예약내역</h3>
						<div class="box-tools pull-right">
 							<div class="input-group">
                                <form action="/facil/FacilMain" name="pgsearch" method="post">
                                	<button class="btn btn-sm btn-default pull-right">
                                    	<i class="fa fa-search"></i>
                                    </button>
                                    <input type="text" name="keyword"
                                        class="form-control input-sm pull-right" style="width: 150px;"
                                        placeholder="Search" /> 
                                    <select class="form-control input-sm pull-right" name='keyfield' style="width: 150px;">
                                       	<option value="pg_type" ${keyfield eq 'pg_type' ? 'selected' : null }>시설명</option>
										<option value="pg_name" ${keyfield eq 'pg_name' ? 'selected' : null }>호수</option>
										<option value="pgm_date" ${keyfield eq 'pgm_date' ? 'selected' : null }>예약 날짜</option>
                                    </select>
                                </form>
                            </div>			
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>예약 번호</th>
									<th>시설 종류</th>
									<th>시설명</th>
									<th>호수</th>
									<th>예약날짜</th>
									<th>예약 포인트</th>
									<th>예약취소</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.pgmlist}" var="p" varStatus="i">
									<tr class="text-blue">
										<td>${p.pgm_num}</td>
										<td>운동장</td>
										<td>${p.pg_type}</td>
										<td>${p.pg_name}</td>
										<td>${p.pgm_date}</td>
										<td>${p.payoutpoint}</td>
										<td><input type="button" class="btn btn-warning"
											value="취소"
											onclick="pgSelectDelete('${p.pgm_num}','${p.pg_type}','${p.pg_name}','${p.pgm_date}','${p.payoutpoint}')" />
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<!-- 페이징 -->
						<div class="row" align="center">
							<ul class="pagination pagination-sm no-margin">
								<li><a href="#">&laquo;</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">&raquo;</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /.box -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->

		<!-- 스터디룸 테이블 위치. -->
		<!-- 세로 길이 수정 -->
		<div class="row">
			<!-- 너비 사이즈 수정  : col-->
			<div class="col-md-12">
				<!-- 리스트 사용시  -->
				<div class="box box-warning">
					<div class="box-header">
						<h3 class="box-title">스터디룸 예약내역</h3>
						<div class="box-tools pull-right">
 							<div class="input-group">
                                <form action="/facil/FacilMain" name="srsearch" method="post">
                                	<button class="btn btn-sm btn-default pull-right">
                                    	<i class="fa fa-search"></i>
                                    </button>
                                    <input type="text" name="keyword"
                                        class="form-control input-sm pull-right" style="width: 150px;"
                                        placeholder="Search" /> 
                                    <select class="form-control input-sm pull-right" name='keyfield' style="width: 150px;">
										<option value="sr_type" ${keyfield eq 'sr_type' ? 'selected' : null }>시설명</option>
										<option value="sr_name"	${keyfield eq 'sr_name' ? 'selected' : null }>호수</option>
										<option value="srm_date" ${keyfield eq 'srm_date' ? 'selected' : null }>예약 날짜</option>
                                    </select>
                                </form>
                            </div>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>예약 번호</th>
									<th>시설 종류</th>
									<th>시설명</th>
									<th>호수</th>
									<th>예약날짜</th>
									<th>예약 포인트</th>
									<th>선택</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.srmlist}" var="s" varStatus="i">
									<tr class="text-blue">
										<td>${s.srm_num}</td>
										<td>스터디룸</td>
										<td>${s.sr_type}</td>
										<td>${s.sr_name}</td>
										<td>${s.srm_date}</td>
										<td>${s.payoutpoint}</td>
										<td><input type="button" class="btn btn-warning"
											value="취소"
											onclick="srSelectDelete('${s.srm_num}','${s.sr_type}','${s.sr_name}','${s.srm_date}','${s.payoutpoint}')" />
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<!-- 페이징 -->
						<div class="row" align="center">
							<ul class="pagination pagination-sm no-margin">
								<li><a href="#">&laquo;</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">&raquo;</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /.box -->
			</div>
			<!-- /.col -->
		</div>
		<!-- -------취소 시설 선택후 표기되는 정보------- -->
		
		<!-- 입력 및 수정 자세한정보 확인 -->
		<div class="modal fade in" id="cancel" data-backdrop="static">
		    <div class="modal-dialog" style="width:55%">
		        <div class="modal-content">
		            <div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                  		<span aria-hidden="true">×</span>
	                  	</button>			            	
		                <h5 class="modal-title">예약정보</h5>
		            </div>
		            <form role="form" method="post" name="checkform" action="/facil/FacilMainDel" onsubmit="return dateFormCheck()" enctype="multipart/form-data">
						<div class="modal-body">
							<div class="row">
								<!-- text input -->
								<div class="form-group col-md-12">
									<label>예약번호</label> <input type="text" class="form-control"
										id="resernum" name="resernum" readonly="readonly">
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-12">
									<label>시설종류</label> <input type="text" id="mfaciltype"
										name="mfaciltype" class="form-control" readonly="readonly">
								</div>
								<div class="form-group col-md-12">
									<label>시설명</label> <input type="text" id="faciltype"
										name="faciltype" class="form-control" readonly="readonly">
								</div>
								<div class="form-group col-md-12">
									<label>호수</label> <input type="text" id="facilname"
										name="facilname" class="form-control" readonly="readonly">
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-12">
									<label>예약시간</label> 
									<input type="text" id="resertime" name="resertime" class="form-control" readonly="readonly">
								</div>
								<div class="form-group col-md-12" data-toggle="buttons" id="timeline">
								</div>	
								<div class="form-group col-md-12">
									<label>환불포인트</label> <input type="text" id="backpoint"
										name="backpoint" class="form-control" readonly="readonly">
								</div>
							</div>
							<!-- /.box-body -->
						</div>		    
			            <div class="modal-footer">
							<input type="submit" class="btn btn-primary pull-left" value="예약취소">
							<input type="button" class="btn btn-default" value="다시 선택" onclick="shutdown()">
			            </div>
		            </form>	   
		        </div>
		    </div>
		</div>
		
	</section>
	<!-- /. 작업 공간 끝! -->
	<!------------------------------------------------------------------------------------------------------------------->
</div>
<!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->

<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">
	function srSelectDelete(srm_num, sr_type, sr_name, srm_date, payoutpoint) {

		$("#cancel").modal('toggle');

		$("#mfaciltype").attr("value", "스터디룸");
		$("#resernum").attr("value", srm_num);
		$("#faciltype").attr("value", sr_type);
		$("#facilname").attr("value", sr_name);
		$("#resertime").attr("value", srm_date);
		$("#backpoint").attr("value", payoutpoint);
		
	}

	function pgSelectDelete(pgm_num, pg_type, pg_name, pgm_date, payoutpoint) {

		$("#cancel").modal('toggle');

		$("#mfaciltype").attr("value", "운동장");
		$("#resernum").attr("value", pgm_num);
		$("#faciltype").attr("value", pg_type);
		$("#facilname").attr("value", pg_name);
		$("#resertime").attr("value", pgm_date);
		$("#backpoint").attr("value", payoutpoint);
	}
	
	
	function timelineChk(timecode){
		
	}
	
	
	function shutdown() {
		$("#cancel").modal('toggle');
	}
</script>