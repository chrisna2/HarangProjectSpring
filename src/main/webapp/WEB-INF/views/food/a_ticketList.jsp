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
           
           <!-- sparkline이 갑자기 안되서 이렇게 기록으로 남겨둠 잊지말자 역순으로 하기 위해서 이런 방식도 기억 해줘야 한다.-->
           <div class="box box-solid">
            <div class="box-header">
              <h3 class="box-title text-blue">판매량 차트</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
              </div>
            </div>
            <div class="box-body text-center">
              <div class="sparkline" data-type="line" data-spot-Radius="3" data-highlight-Spot-Color="#f39c12" data-highlight-Line-Color="#222" data-min-Spot-Color="#f56954" data-max-Spot-Color="#00a65a" data-spot-Color="#39CCCC" data-offset="90" data-width="100%" data-height="100px" data-line-Width="2" data-line-Color="#39CCCC" data-fill-Color="rgba(57, 204, 204, 0.08)">
                 <c:set var="length" value="${fn:length(fmlist)-1}"/>
                 <c:forEach var="fc" begin="0" end="${length}">
                 	<c:set var="decr" value="${length-fc}"/>
                 	${fmlist[decr].fm_sellcount},
                 	<c:if test="${decr == 0}">
                 		${fmlist[decr].fm_sellcount}
                 	</c:if>
				 </c:forEach>
              </div>
            </div>
          </div>
           
            <!-- 리스트 사용시  -->
             <div class="box">
                <div class="box-header">
                  <h3 class="box-title">식권 판매량 조회</h3>
                   <div class="box-tools">
                    <form action="/food/Aticket" method="post">
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
                        <c:forEach items="${fmlist}" var="fm" varStatus="i">
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
                 <!-- 페이징, 새로운 방법을 적용 : 2017/07/19 -->
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
                 <!-- 페이징, 새로운 방법을 적용 : 2017/07/19 -->
                </div>
              </div><!-- /.box -->
              </div><!-- /.col -->
           </div><!-- /.row -->
        </section><!-- /. 작업 공간 끝! -->
<!------------------------------------------------------------------------------------------------------------------->        
      </div><!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->
<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------> 
<%@ include file="../include/footer.jsp" %>
<!-- jQuery Knob -->
<script src="../resources/plugins/knob/jquery.knob.js"></script>
<!-- Sparkline -->
<script src="../resources/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- ChartJS 1.0.1 -->
<script src="../resources/plugins/chartjs/Chart.min.js"></script>
<script>
$(function () {
    /* jQueryKnob */

    $(".knob").knob({
      /*change : function (value) {
       //console.log("change : " + value);
       },
       release : function (value) {
       console.log("release : " + value);
       },
       cancel : function () {
       console.log("cancel : " + this.value);
       },*/
      draw: function () {

        // "tron" case
        if (this.$.data('skin') == 'tron') {

          var a = this.angle(this.cv)  // Angle
              , sa = this.startAngle          // Previous start angle
              , sat = this.startAngle         // Start angle
              , ea                            // Previous end angle
              , eat = sat + a                 // End angle
              , r = true;

          this.g.lineWidth = this.lineWidth;

          this.o.cursor
          && (sat = eat - 0.3)
          && (eat = eat + 0.3);

          if (this.o.displayPrevious) {
            ea = this.startAngle + this.angle(this.value);
            this.o.cursor
            && (sa = ea - 0.3)
            && (ea = ea + 0.3);
            this.g.beginPath();
            this.g.strokeStyle = this.previousColor;
            this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false);
            this.g.stroke();
          }

          this.g.beginPath();
          this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
          this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false);
          this.g.stroke();

          this.g.lineWidth = 2;
          this.g.beginPath();
          this.g.strokeStyle = this.o.fgColor;
          this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
          this.g.stroke();

          return false;
        }
      }
    });
    /* END JQUERY KNOB */
//INITIALIZE SPARKLINE CHARTS
$(".sparkline").each(function () {
  var $this = $(this);
  $this.sparkline('html', $this.data());
});

/* SPARKLINE DOCUMENTATION EXAMPLES http://omnipotent.net/jquery.sparkline/#s-about */
drawDocSparklines();
drawMouseSpeedDemo();

});
function drawDocSparklines() {

// Bar + line composite charts
$('#compositebar').sparkline('html', {type: 'bar', barColor: '#aaf'});
$('#compositebar').sparkline([4, 1, 5, 7, 9, 9, 8, 7, 6, 6, 4, 7, 8, 4, 3, 2, 2, 5, 6, 7],
    {composite: true, fillColor: false, lineColor: 'red'});


// Line charts taking their values from the tag
$('.sparkline-1').sparkline();

// Larger line charts for the docs
$('.largeline').sparkline('html',
    {type: 'line', height: '2.5em', width: '4em'});

// Customized line chart
$('#linecustom').sparkline('html',
    {
      height: '1.5em', width: '8em', lineColor: '#f00', fillColor: '#ffa',
      minSpotColor: false, maxSpotColor: false, spotColor: '#77f', spotRadius: 3
    });

// Bar charts using inline values
$('.sparkbar').sparkline('html', {type: 'bar'});

$('.barformat').sparkline([1, 3, 5, 3, 8], {
  type: 'bar',
  tooltipFormat: '{{value:levels}} - {{value}}',
  tooltipValueLookups: {
    levels: $.range_map({':2': 'Low', '3:6': 'Medium', '7:': 'High'})
  }
});

// Tri-state charts using inline values
$('.sparktristate').sparkline('html', {type: 'tristate'});
$('.sparktristatecols').sparkline('html',
    {type: 'tristate', colorMap: {'-2': '#fa7', '2': '#44f'}});

// Composite line charts, the second using values supplied via javascript
$('#compositeline').sparkline('html', {fillColor: false, changeRangeMin: 0, chartRangeMax: 10});
$('#compositeline').sparkline([4, 1, 5, 7, 9, 9, 8, 7, 6, 6, 4, 7, 8, 4, 3, 2, 2, 5, 6, 7],
    {composite: true, fillColor: false, lineColor: 'red', changeRangeMin: 0, chartRangeMax: 10});

// Line charts with normal range marker
$('#normalline').sparkline('html',
    {fillColor: false, normalRangeMin: -1, normalRangeMax: 8});
$('#normalExample').sparkline('html',
    {fillColor: false, normalRangeMin: 80, normalRangeMax: 95, normalRangeColor: '#4f4'});

// Discrete charts
$('.discrete1').sparkline('html',
    {type: 'discrete', lineColor: 'blue', xwidth: 18});
$('#discrete2').sparkline('html',
    {type: 'discrete', lineColor: 'blue', thresholdColor: 'red', thresholdValue: 4});

// Bullet charts
$('.sparkbullet').sparkline('html', {type: 'bullet'});

// Pie charts
$('.sparkpie').sparkline('html', {type: 'pie', height: '1.0em'});

// Box plots
$('.sparkboxplot').sparkline('html', {type: 'box'});
$('.sparkboxplotraw').sparkline([1, 3, 5, 8, 10, 15, 18],
    {type: 'box', raw: true, showOutliers: true, target: 6});

// Box plot with specific field order
$('.boxfieldorder').sparkline('html', {
  type: 'box',
  tooltipFormatFieldlist: ['med', 'lq', 'uq'],
  tooltipFormatFieldlistKey: 'field'
});

// click event demo sparkline
$('.clickdemo').sparkline();
$('.clickdemo').bind('sparklineClick', function (ev) {
  var sparkline = ev.sparklines[0],
      region = sparkline.getCurrentRegionFields();
  value = region.y;
  alert("Clicked on x=" + region.x + " y=" + region.y);
});

// mouseover event demo sparkline
$('.mouseoverdemo').sparkline();
$('.mouseoverdemo').bind('sparklineRegionChange', function (ev) {
  var sparkline = ev.sparklines[0],
      region = sparkline.getCurrentRegionFields();
  value = region.y;
  $('.mouseoverregion').text("x=" + region.x + " y=" + region.y);
}).bind('mouseleave', function () {
  $('.mouseoverregion').text('');
});
}

/**
** Draw the little mouse speed animated graph
** This just attaches a handler to the mousemove event to see
** (roughly) how far the mouse has moved
** and then updates the display a couple of times a second via
** setTimeout()
**/
function drawMouseSpeedDemo() {
var mrefreshinterval = 500; // update display every 500ms
var lastmousex = -1;
var lastmousey = -1;
var lastmousetime;
var mousetravel = 0;
var mpoints = [];
var mpoints_max = 30;
$('html').mousemove(function (e) {
  var mousex = e.pageX;
  var mousey = e.pageY;
  if (lastmousex > -1) {
    mousetravel += Math.max(Math.abs(mousex - lastmousex), Math.abs(mousey - lastmousey));
  }
  lastmousex = mousex;
  lastmousey = mousey;
});
var mdraw = function () {
  var md = new Date();
  var timenow = md.getTime();
  if (lastmousetime && lastmousetime != timenow) {
    var pps = Math.round(mousetravel / (timenow - lastmousetime) * 1000);
    mpoints.push(pps);
    if (mpoints.length > mpoints_max)
      mpoints.splice(0, 1);
    mousetravel = 0;
    $('#mousespeed').sparkline(mpoints, {width: mpoints.length * 2, tooltipSuffix: ' pixels per second'});
  }
  lastmousetime = timenow;
  setTimeout(mdraw, mrefreshinterval);
};
// We could use setInterval instead, but I prefer to do it this way
setTimeout(mdraw, mrefreshinterval);
}

</script>

