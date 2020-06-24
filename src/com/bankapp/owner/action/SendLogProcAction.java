package com.bankapp.owner.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankapp.owner.dao.AccountDao;
import com.bankapp.owner.dto.SendLogDto;
import com.bankapp.owner.model.Account;
import com.bankapp.owner.model.Mempool;
import com.bankapp.owner.util.Script;
import com.google.gson.Gson;



public class SendLogProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 유효성 검사
//		HttpSession session = request.getSession();
//		if
//		(	
//				request.getParameter("pwd").equals("")||
//				request.getParameter("pwd")==null	
//				) {
//			Script.back("비밀번호를 입력해주세요.", response);
//			return;
//		}
		
		//파라메터 받기
//		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");

		
		// db연결
		AccountDao accountDao = AccountDao.getinstance();
//		Account result = accountDao.pwdConfirm(pwd,phone);
		
		//페이지 이동//
//		if (result==null) {
//			Script.back("비밀번호가 틀립니다.", response);
//		}else {
			List<SendLogDto> sendLogDtos = accountDao.sendLog(phone);

			request.setAttribute("sendLogDtos", sendLogDtos);
			
			Gson gson = new Gson();
			String sendLogDtosJson = gson.toJson(sendLogDtos);

			
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println(sendLogDtosJson);

//			Script.href("/owner/account?cmd=home#sendLog", response);
		 
			
		}
	}
//}
