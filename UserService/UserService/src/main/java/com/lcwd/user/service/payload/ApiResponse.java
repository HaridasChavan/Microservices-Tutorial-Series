package com.lcwd.user.service.payload;

import org.springframework.http.HttpStatus;


public class ApiResponse {
	private String message;
	private boolean success;
	private HttpStatus status;

	// Private constructor to force the use of the builder
	private ApiResponse(Builder builder) {
		this.message = builder.message;
		this.success = builder.success;
		this.status = builder.status;
	}

	public static class Builder {
		private String message;
		private boolean success;
		private HttpStatus status;

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder success(boolean success) {
			this.success = success;
			return this;
		}

		public Builder status(HttpStatus status) {
			this.status = status;
			return this;
		}

		public ApiResponse build() {
			return new ApiResponse(this);
		}
	}

	// Getters and setters if needed

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}

	public HttpStatus getStatus() {
		return status;
	}

	// Additional methods or overrides as needed
}
