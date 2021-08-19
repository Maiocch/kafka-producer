package com.example.demo.dto;

public class TestDTO {
	
	private String message;

	public TestDTO() {
	}

	public TestDTO(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TestDTO [message=" + message + "]";
	}
	
	

}
