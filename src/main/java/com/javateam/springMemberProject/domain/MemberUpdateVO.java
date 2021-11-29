package com.javateam.springMemberProject.domain;

public class MemberUpdateVO extends MemberVO {
	
	private String newPwd;
	private String newEmail;
	private String newPhone;
	
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getNewEmail() {
		return newEmail;
	}
	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}
	public String getNewPhone() {
		return newPhone;
	}
	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberUpdateVO [newPwd=").append(newPwd).append(", newEmail=").append(newEmail)
				.append(", newPhone=").append(newPhone).append(", name=").append(getName())
				.append(", userid=").append(getUserid()).append(", pwd=").append(getPwd())
				.append(", email=").append(getEmail()).append(", phone=").append(getPhone())
				.append(", admin=").append(getAdmin()).append("]");
		return builder.toString();
	}

}