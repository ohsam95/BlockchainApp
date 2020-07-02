package com.bankapp.owner.model;


import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckSend {
	
	private String receiver;
}
