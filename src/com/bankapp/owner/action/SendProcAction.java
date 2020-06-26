package com.bankapp.owner.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankapp.owner.dao.AccountDao;
import com.bankapp.owner.dto.MempoolJson;
import com.bankapp.owner.model.Account;
import com.bankapp.owner.util.SHA256;
import com.bankapp.owner.util.Script;
import com.google.gson.Gson;



public class SendProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 유효성 검사
		HttpSession session = request.getSession();
		if
		(
				request.getParameter("receiver").equals("")||
				request.getParameter("receiver")==null||	
				request.getParameter("sendAmount").equals("")||
				request.getParameter("sendAmount")==null||		
				request.getParameter("pwd").equals("")||
				request.getParameter("pwd")==null	
				) {
			Script.back("누락된 항목이 있습니다.", response);
			return;
		}
		
		//파라메터 받기
		String receiver = request.getParameter("receiver");
		int sendAmount = Integer.parseInt(request.getParameter("sendAmount"));
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");

		// 여기까지 옴
		//해쉬화하기
		
			MempoolJson mempoolJson = MempoolJson.builder()
					.receiver(receiver)
					.sendAmount(sendAmount)
					.phone(phone)
					.build();
		System.out.println(mempoolJson.getPhone());
		
		Gson gson = new Gson();
		String sendInfo = gson.toJson(mempoolJson);
		response.setCharacterEncoding("UTF-8");
		String hash = SHA256.encodeSha256(sendInfo);
		System.out.println(hash);
		System.out.println(sendInfo);
		
		// db연결
		AccountDao accountDao = AccountDao.getinstance();
		Account result = accountDao.pwdConfirm(pwd,phone);
		
		//페이지 이동//
		if (result==null) {
			Script.back("비밀번호가 틀립니다.", response);
		}else {
			accountDao.recevie(sendAmount, receiver);
			accountDao.send(sendAmount, phone);
			
			accountDao.insertMempool(receiver, sendAmount, phone,hash);
			accountDao.insertBlockMempool(receiver, sendAmount, phone, hash);
			
			Account principal = accountDao.find(phone);
			session.setAttribute("principal", principal);
			Script.href("이체가 성공했습니다.", "/owner/account?cmd=home", response);			
		}
	}
}
