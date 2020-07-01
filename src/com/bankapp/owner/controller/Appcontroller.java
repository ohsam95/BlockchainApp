package com.bankapp.owner.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankapp.owner.action.Action;
import com.bankapp.owner.action.ChargeProcAction;
import com.bankapp.owner.action.HomeAction;
import com.bankapp.owner.action.JoinProcAction;
import com.bankapp.owner.action.LoginProcAction;
import com.bankapp.owner.action.LogoutAction;
import com.bankapp.owner.action.ResultAction;
import com.bankapp.owner.action.SendLogProcAction;
import com.bankapp.owner.action.SendProcAction;
import com.bankapp.owner.action.StartAction;



@WebServlet("/account")
public class Appcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Appcontroller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}
	protected void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		Action action =router(cmd);
		action.execute(request, response);
	}
	private Action router(String cmd) {
		if (cmd.equals("joinProc")) {
			return new JoinProcAction();
		}else if(cmd.equals("home")) {
			return new HomeAction();
		}else if(cmd.equals("loginProc")) {
			return new LoginProcAction();
		}else if(cmd.equals("logout")) {
			return new LogoutAction();
		}else if(cmd.equals("chargeProc")) {
			return new ChargeProcAction();
		}else if(cmd.equals("sendProc")) {
		return new SendProcAction();
		}else if(cmd.equals("sendLogProc")) {
			return new SendLogProcAction();
		}else if(cmd.equals("start")) {
			return new StartAction();
		}else if(cmd.equals("result")) {
			return new ResultAction();
		}
		return null;
	}
}
