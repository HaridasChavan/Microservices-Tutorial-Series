package com.lcwd.user.service.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

	@Id
	@Column(name = "ID")
	private String userId;

	@Column(name = "Name", length = 15)
	private String name;

	public User(List<Rating> ratings) {
		super();
		this.ratings = ratings;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	@Column(name = "Email")
	private String email;

	@Column(name = "About")
	private String about;

	@Transient
	private List<Rating> ratings = new ArrayList<>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

//	public User(String userId, String name, String email, String about) {
//		super();
//		this.userId = userId;
//		this.name = name;
//		this.email = email;
//		this.about = about;
//	}
	private User(Builder builder) {
		this.userId = builder.userId;
		this.name = builder.name;
		this.email = builder.email;
		this.about = builder.about;
		this.ratings = builder.ratings;
	}

	// Builder class
	public static class Builder {
		private String userId;
		private String name;
		private String email;
		private String about;
		private List<Rating> ratings = new ArrayList<>();

		public Builder userId(String userId) {
			this.userId = userId;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder about(String about) {
			this.about = about;
			return this;
		}

		public Builder ratings(List<Rating> ratings) {
			this.ratings = ratings;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}

	// Additional methods, constructors, and overrides as needed

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", about=" + about + ", ratings="
				+ ratings + "]";
	}

	public User() {
		super();
	}

	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
}
