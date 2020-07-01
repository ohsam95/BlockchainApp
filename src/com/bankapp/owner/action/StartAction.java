package com.bankapp.owner.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankapp.owner.dao.AccountDao;

public class StartAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountDao accountDao = AccountDao.getinstance();
		String data = accountDao.sendBlockData();

		System.out.println(data);
		
		request.setAttribute("data", data);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(data);

		
		
//		블록에 보낼 10분간의 데이터를 만든 db를 삭제
		accountDao.deleteBlockMempool();
	}
}
