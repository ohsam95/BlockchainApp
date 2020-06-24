package com.bankapp.owner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MempoolJson {

	private String receiver;
	private int sendAmount ;
	private String phone;
	
}
