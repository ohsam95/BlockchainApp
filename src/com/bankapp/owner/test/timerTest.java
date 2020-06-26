package com.bankapp.owner.test;

import java.util.Timer;
import java.util.TimerTask;

public class timerTest {
	
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
	                int result = 0;
	                // 특정 로직
	                for(int i=0;i<10000;i++) {
	                    for(int j=0;j<10000;j++) {
	                        result = i+j;
	                    }
	                }
	                //이부분에 send()같은 것이 들어갈 것
	                System.out.println(System.currentTimeMillis());

	            }
	        };
	        Timer timer = new Timer();
	        timer.scheduleAtFixedRate(task, 0, 2000); // 실행하는거


	
}
}
