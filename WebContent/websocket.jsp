<%@page import="com.bankapp.owner.dao.AccountDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Web Socket Example</title></head>
<body>
<form>
<!-- 송신 메시지를 작성하는 텍스트 박스 -->

<input onclick="getJson()" value="블록의 data 보내기" type="button">
</form>
<br />
<!-- 콘솔 메시지의 역할을 하는 로그 텍스트 에리어.(수신 메시지도 표시한다.) -->
<textarea id="messageTextArea" rows="10" cols="50"></textarea>
<script type="text/javascript">


var webSocket = new WebSocket("ws://localhost:8001/node1/websocket");
// 콘솔 텍스트 에리어 오브젝트
var messageTextArea = document.getElementById("messageTextArea");
// WebSocket 서버와 접속이 되면 호출되는 함수
webSocket.onopen = function(message) {
// 콘솔 텍스트에 메시지를 출력한다.
messageTextArea.value += ${data};
console.log(${data});
};

// WebSocket 서버로 부터 메시지가 오면 호출되는 함수
webSocket.onmessage = function(message) {
	
// 	var messageJson = JSON.parse(message.data);
// 	alert(messageJson[0].id);
// 	alert(messageJson[0].receiver);
// 	alert(messageJson[0].sendAmount);
// 	alert(messageJson[0].sender);
// 	alert(messageJson[0].hash);
// 	alert(messageJson[0].createDate);
	
};
function getJson() {

	var dataJson = JSON.stringify(${data});
	webSocket.send(dataJson);
	
}
</script>
</body>
</html>