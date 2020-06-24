package com.bankapp.owner.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankapp.owner.dao.AccountDao;
import com.bankapp.owner.model.Account;
import com.bankapp.owner.util.Script;



public class ChargeProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		// 유효성 검사
		if
		(	
				request.getParameter("amount").equals("")||
				request.getParameter("amount")==null	
				) {
			Script.back("누락된 항목이 있습니다.", response);
			return;
		}
		
		//파라메터 받기
		int amount = Integer.parseInt(request.getParameter("amount"));
		String phone = request.getParameter("phone");
		
		//오브젝트 변환

		// db연결
		AccountDao accountDao = AccountDao.getinstance();
		int result = accountDao.charge(amount, phone);

		//페이지 이동
		if (result == 1) {
			Account principal = accountDao.find(phone);
			session.setAttribute("principal", principal);
			
			
			
			Script.href("충전되었습니다.", "/owner/account?cmd=home", response);
		}else {
			Script.back("충전이 실패했습니다.", response);
		}
	}
}
