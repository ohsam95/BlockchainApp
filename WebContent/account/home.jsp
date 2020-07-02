<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>

<html>
<head>
<title>블록체인 지갑 APP</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/owner/assets/css/main.css" />
<!-- <noscript><link rel="stylesheet" href="/owner/assets/css/noscript.css" /></noscript> -->
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- 헤더 -->
		<header id="header">
			<div class="logo">
				<span class="icon fa-gem"></span>
			</div>
			<div class="content">
				<div class="inner">
					<h1>블록체인 지갑</h1>
					<c:choose>
						<c:when test="${empty sessionScope.principal}">
							<p>
								휴대폰 번호로 계좌를 만들고 이체를 할 수 있는 블록체인 기반의 서비스입니다.<br /> 블록체인으로 거래데이터를 암호화하여 안전한 금융 생활을 지원합니다. <br />
								<br /> 신규 고객은 <a href="#join">여기를</a> 눌러 계좌를 신설해주세요 <br /> 기존 고객들은 계좌에 <a href="#login">로그인</a>하여주십시오.
							</p>
						</c:when>
						<c:otherwise>
							<p>
								안녕하세요 ${sessionScope.principal.name}님! <br /> 고객님의 잔액은 ${sessionScope.principal.amount}원입니다.<br /><br /> 로그아웃을 원하시면 <a href="/owner/account?cmd=logout">여기를</a> 누르세요.<br /> 거래내역을 보려면 <a
									href="#sendLog">여기를</a> 누르세요.
							</p>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
			<nav>
				<ul>
					<!-- 관리자 페이지, 로그인 전 페이지, 로그인 후 페이지 분류 -->
					<c:choose>
						<c:when test="${empty sessionScope.principal}">
							<li><a href="#join">계좌신설</a></li>
							<li><a href="#login">로그인</a></li>
						</c:when>
						<c:when test="${sessionScope.principal.phone eq '01068484033'}">
							<li><a href="#charge">충전하기</a></li>
							<li><a href="#send">이체하기</a></li>
							<li><a href="#block">블록</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="#charge">충전하기</a></li>
							<li><a href="#send">이체하기</a></li>
						</c:otherwise>
					</c:choose>



				</ul>
			</nav>
		</header>

		<!-- Main -->
		<div id="main">

			<!-- 거래내역 -->
			<article id="sendLog">
				<h2 class="major">모든 거래내역 보기</h2>
				<section>
					<div class="fields">
						<div class="field half">
							<input type="hidden" name="phone" id="phone" value="${sessionScope.principal.phone}" />
						</div>
					</div>
					<button onclick="logClick(${sessionScope.principal.phone})">거래내역 조회</button>
					<br /> <br />
					<table>
						<thead>
							<tr>
								<th>받는 사람</th>
								<th>금액</th>
								<th>보내는 사람</th>
								<th>시간</th>
							</tr>
						</thead>
						<tbody id="table">

						</tbody>
					</table>
				</section>
			</article>

			<article id="block">
				<h2 class="major">블록체인 - 관리자 전용</h2>
				<br/>
				<section>
					<h3 class="major">버튼을 누르면 블록체인이 생성됩니다.</h3>
					<form>
						<input onclick="StartBlock()" value="BlockChain Start" type="button">
					</form>
				</section>
			</article>



			<!-- 계좌개설 -->
			<article id="join">
				<h2 class="major">계좌신설</h2>
				<section>
					<h3 class="major">정보를 입력해주세요</h3>
					<form method="post" action="/owner/account?cmd=joinProc">
						<div class="fields">
							<div class="field half">
								<label for="demo-name">Name</label> <input type="text" name="name" id="name" value="" placeholder="이름을 입력해주세요." />
							</div>
							<div class="field half">
								<label for="demo-email">pwd</label> <input type="password" name="pwd" id="pwd" value="" placeholder="비밀번호를 입력해주세요." />
							</div>
							<div class="field half">
								<label for="demo-email">phone</label> <input type="text" name="phone" id="phone" value="" placeholder="휴대폰 번호를 입력해주세요." />
							</div>

						</div>
						<input type="submit" value="계좌 개설 완료" class="primary" />

					</form>
				</section>
			</article>

			<!-- 로그인 -->
			<article id="login">
				<h2 class="major">로그인</h2>

				<section>
					<form method="post" action="/owner/account?cmd=loginProc">
						<div class="fields">
							<div class="field half">
								<label for="demo-name">Name</label> <input type="text" name="name" id="name" value="" placeholder="이름을 입력해주세요." />
							</div>
							<div class="field half">
								<label for="demo-name">pwd</label> <input type="password" name="pwd" id="pwd" value="" placeholder="비밀번호를 입력해주세요." />
							</div>
						</div>
						<input type="submit" value="로그인" class="primary" />

					</form>
				</section>
			</article>

			<!-- 충전 -->
			<article id="charge">
				<h2 class="major">충전하기</h2>
				<section>
					<form method="post" action="/owner/account?cmd=chargeProc">
						<div class="field half">
							<input type="text" name="amount" id="amount" value="" placeholder="충전하실 금액을 입력하세요" /><br /> <input type="hidden" name="phone" id="phone" value="${sessionScope.principal.phone}" />
						</div>
						<p>현재 잔액 : ${sessionScope.principal.amount}원</p>
						<input type="submit" value="충전" class="primary" />
					</form>
				</section>
			</article>

			<!-- 이체 -->
			<article id="send">
				<h2 class="major">이체하기</h2>
				<form method="post" action="/owner/account?cmd=sendProc">
					<div class="fields">
						<div class="field half">
							<label for="receiver">입금할 휴대폰 번호</label> <input type="text" name="receiver" id="receiver" />
						</div>
						<div class="field half">
							<label for="sendAmount">이체 금액</label> <input type="text" name="sendAmount" id="sendAmount" />
						</div>
						<div class="field half">
							<label for="pwd">내 비밀번호</label> <input type="password" name="pwd" id="pwd" /> <input type="hidden" name="phone" id="phone" value="${sessionScope.principal.phone}" />
						</div>
					</div>
					<input type="submit" value="이체" class="primary" />
				</form>
			</article>



		</div>

		<script>

//거래내역 보기 위한 ajax
function logClick(phone){
 		var phone = $("#phone").val();
	$.ajax({
		type:"post",
		url:"/owner/account?cmd=sendLogProc&phone="+phone,
		dataType:"json"		
	}).done(function(result){
		$("#table").empty();
		for (var sendLogDto of result) {
			var string =
				"<tr>\r\n" + 
				"			        <td>"+sendLogDto.receiver+"</td>\r\n" + 
				"			        <td>"+sendLogDto.sendAmount+"</td>\r\n" +  
				"			        <td>"+sendLogDto.sender+"</td>\r\n" +  
				"			        <td>"+sendLogDto.createDate+"</td>\r\n" +  
				"			      </tr>";
				
				$("#table").append(string);
		}
	}).fail(function(error) {
		alert("에러입니다.")
	});
}
var blockchain = new Array();

//노드와 웹소켓 연결
var node1Socket = new WebSocket("ws://localhost:8001/node1/websocket");
var node2Socket = new WebSocket("ws://localhost:8001/node2/websocket");
var node3Socket = new WebSocket("ws://localhost:8001/node3/websocket");

node1Socket.onmessage = function(e){
	onMessage(e);
};								
node2Socket.onmessage = function(e){
	onMessage(e);
};
node3Socket.onmessage = function(e){
	onMessage(e);
};

var prvHash = 0;
var count = 0;
var failCount = 0;
//노드에서 메시지가 오면 실행
function onMessage(e){
	var nodeCount = JSON.parse(e.data).count;
	
	if (nodeCount == count){
	 	count = count+1;
		//블록체인에 값 넣기
	 	blockchain.push(e.data);
		//이전해쉬에 해쉬 넣기
	 	prvHash = JSON.parse(e.data).hash;
		// 블록을 데이터베이스에 저장하기위해 ajax 실행
		var nodePreviousHash = JSON.parse(e.data).previousHash;
		var nodeData = JSON.parse(e.data).data;
		var nodeTimestamp = JSON.parse(e.data).timestamp;
		var nodeNonce = JSON.parse(e.data).nonce;
		var nodeNumber = JSON.parse(e.data).nodeNum;
			ResultBlock(prvHash,nodePreviousHash,nodeData,nodeTimestamp,nodeNonce,nodeNumber);
	 	
	}else{
		failCount++;
		//노드 경쟁을 위해 첫 채굴한 노드만 들어오게 만드는 알고리즘
		if(failCount == 2){
			count = 0;
			failCount = 0;
		}
	}	
	 	console.log(blockchain);
}

function takeJson(){
	$.ajax({
		type:"post",
		url:"/owner/account?cmd=start",
		dataType:"json"		
	}).done(function(result){
		//노드로 보낼 데이터 - 난이도 설정 할 수 있음!! (지금은 해쉬가 앞에서 5글자가 0인걸 찾는 난이도)
		// 테스트를 위해 난이도 2 설정
			var dataJson =
			{
				"prvHash" : prvHash,
				"dataJson" : result,
				"difficulty" : 2
			}
			//제이슨화
			var dataJsonDto = JSON.stringify(dataJson);
			// 노드로 send
			node1Socket.send(dataJsonDto);
			node2Socket.send(dataJsonDto);
			node3Socket.send(dataJsonDto);

	}).fail(function(error) {
		alert("블록체인 종료")
	});
}
 

function StartBlock() {
	takeJson();
	//노드에게 데이터 전송 반복하기 - 시간 설정 가능 (600000은 10분)
	setInterval(takeJson,10000);
}

//결과를 받는 ajax
function ResultBlock(prvHash,nodePreviousHash,nodeData,nodeTimestamp,nodeNonce,nodeNumber){
	$.ajax({
		type:"post",
		url:"/owner/account?cmd=result",
			data: {
				prvHash,
				nodePreviousHash,
				nodeData,
				nodeTimestamp,
				nodeNonce,
				nodeNumber
			},
		dataType:"text"		
	}).done(function(result){
		console.log("채굴 경쟁 끝")
	}).fail(function(error) {
		console.log("오류")
	});
	
}

</script>

		<!-- Footer -->
		<footer id="footer">
			<p class="copyright">
				&copy; 제작자 : 이동현.
			</p>
		</footer>

	</div>

	<!-- BG -->
	<div id="bg"></div>

	<!-- Scripts -->
	<script src="/owner/assets/js/jquery.min.js"></script>
	<script src="/owner/assets/js/browser.min.js"></script>
	<script src="/owner/assets/js/breakpoints.min.js"></script>
	<script src="/owner/assets/js/util.js"></script>
	<script src="/owner/assets/js/main.js"></script>

</body>
</html>