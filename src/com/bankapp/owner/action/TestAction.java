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

public class TestAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountDao accountDao = AccountDao.getinstance();
		String data = accountDao.sendBlockData();
		//소켓통신으로 data를 노드로 보내야함
		// 그 후 노드는 블록을 만들고 넌스를 올리며 해쉬값을 찾음
		//해쉬값을 찾으면 블록체인에 넣고 이전해쉬를 활용해 새로운 블록을 만듬
		//또 해쉬를 찾는 작업 -> 찾으면 블록체인에 넣고 또 그 해쉬를 가지고 블록 생성
		System.out.println(data);
		
		
//		블록을 만드는 db를 삭제
//		accountDao.deleteBlockMempool();
		
		request.setAttribute("data", data);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(data);
	}
}
