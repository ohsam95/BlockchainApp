package com.bankapp.owner.test;

import java.util.Timer;
import java.util.TimerTask;

public class timerTest2 {
	
	public static void slslslsl() {
		System.out.println("굳");
		//  해쉬 찾기
		
		// hash, timestamp, nonce 집어 넣고
		
		// Buffred 달아서 서버에 던져!!
	}

	public static void main(String[] args) {
		 TimerTask task = new TimerTask() {
	            @Override
	            public void run() {
	              //여기에 태스크를 정의
	            	slslslsl();
	            }
	        };
	        Timer timer = new Timer();
	        timer.scheduleAtFixedRate(task, 0, 1000); // 실행하는거


	
}
}
