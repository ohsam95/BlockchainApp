<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<!DOCTYPE HTML>

<html>
	<head>
		<title>계좌이체 앱</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/owner/assets/css/main.css" />
<!-- <noscript><link rel="stylesheet" href="/owner/assets/css/noscript.css" /></noscript> -->
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
						<div class="logo">
							<span class="icon fa-gem"></span>
						</div>
						<div class="content">
							<div class="inner">
								<h1>블록체인</h1>
								<c:choose>
									<c:when test="${empty sessionScope.principal}">
								<p>휴대폰 번호로 계좌를 만들고 이체를 할 수 있는 블록체인 기반의 서비스입니다.<br/> 
									신규 고객은 <a href="#contact">여기를 눌러 계좌를 신설해주세요</a> <br/> 
									기존 고객들은 계좌에 <a href="#contact">로그인</a>하여주십시오.</p>	
									</c:when>
									<c:otherwise>
									<p> 안녕하세요 ${sessionScope.principal.name}님! <br/>
									고객님의 잔액은 ${sessionScope.principal.amount}원입니다.<br/>
									로그아웃을 원하시면 <a href="/owner/account?cmd=logout">여기를</a> 누르세요
									</c:otherwise>
								</c:choose>
								
									<!-- 세션이 있으면 잔액 -->
							</div>
						</div>
						<nav>
							<ul>
							<c:choose>
								<c:when test="${empty sessionScope.principal}">
								<li><a href="#join">계좌신설</a></li>
								<li><a href="#login">로그인</a></li>
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

						<!-- 계좌개설 -->
							<article id="join">
								<h2 class="major">계좌신설</h2>
								<section>
									<h3 class="major">정보를 입력해주세요</h3>
									<form method="post" action="/owner/account?cmd=joinProc">
										<div class="fields">
											<div class="field half">
												<label for="demo-name">Name</label>
												<input type="text" name="name" id="name" value="" placeholder="이름을 입력해주세요." />
											</div>
											<div class="field half">
												<label for="demo-email">pwd</label>
												<input type="password" name="pwd" id="pwd" value="" placeholder="비밀번호를 입력해주세요." />
											</div>											
											<div class="field half">
												<label for="demo-email">phone</label>
												<input type="text" name="phone" id="phone" value="" placeholder="휴대폰 번호를 입력해주세요." />
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
												<label for="demo-name">Name</label>
												<input type="text" name="name" id="name" value="" placeholder="이름을 입력해주세요." />
											</div>
											<div class="field half">
												<label for="demo-name">pwd</label>
												<input type="password" name="pwd" id="pwd" value="" placeholder="비밀번호를 입력해주세요." />
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
								<input type="text" name="amount" id="amount" value="" placeholder="충전하실 금액을 입력하세요" /><br/>
								<input type="hidden" name="phone" id="phone" value="${sessionScope.principal.phone}"/>
								</div>
								<p>현재 잔액 : ${sessionScope.principal.amount}원 </p>
								<input type="submit" value="충전" class="primary" />
							</form>
							</section>	
							</article>

						<!-- 이체 -->
							<article id="send">
								<h2 class="major">Contact</h2>
								<form method="post" action="#">
									<div class="fields">
										<div class="field half">
											<label for="name">Name</label>
											<input type="text" name="name" id="name" />
										</div>
										<div class="field half">
											<label for="email">Email</label>
											<input type="text" name="email" id="email" />
										</div>
										<div class="field">
											<label for="message">Message</label>
											<textarea name="message" id="message" rows="4"></textarea>
										</div>
									</div>
									<ul class="actions">
										<li><input type="submit" value="Send Message" class="primary" /></li>
										<li><input type="reset" value="Reset" /></li>
									</ul>
								</form>
							</article>

						

					</div>

<%@include file="/include/footer.jsp" %>
