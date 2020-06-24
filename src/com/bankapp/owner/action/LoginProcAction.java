package com.bankapp.owner.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankapp.owner.dao.AccountDao;
import com.bankapp.owner.model.Account;
import com.bankapp.owner.util.Script;



public class LoginProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 유효성 검사
		if
		(
				request.getParameter("name").equals("")||
				request.getParameter("name")==null||	
				request.getParameter("pwd").equals("")||
				request.getParameter("pwd")==null		
				) {
			Script.back("누락된 항목이 있습니다.", response);
			return;
		}
		
		//파라메터 받기
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");

		
		//오브젝트 변환
		
		// db연결
		AccountDao accountDao = AccountDao.getinstance();
		Account account = accountDao.login(name, pwd);
		
		//페이지 이동
		if (account != null) {
			HttpSession session = request.getSession();
			session.setAttribute("principal", account);
			
			if(request.getParameter("remember")!=null) {
				Cookie cookie = new Cookie("remember",account.getName());
				response.addCookie(cookie);
			}else {
				Cookie cookie =new Cookie("remember", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			
			Script.href("로그인이 성공했습니다.", "/owner/account?cmd=home", response);
		}else {
			Script.back("로그인이 실패했습니다.", response);
		}
	}
}
