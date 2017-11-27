package com.fintech.orion.dto.request.api;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/*
 * MRZRequest
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-11-09T10:12:11.427Z")
public class MRZRequest {
	@JsonProperty("subType")
	private String subType = null;

	@JsonProperty("givenNames")
	private String givenNames = null;

	@JsonProperty("surNames")
	private String surNames = null;

	@JsonProperty("birthYear")
	private String birthYear = null;

	@JsonProperty("birthMonth")
	private String birthMonth = null;

	@JsonProperty("birthDate")
	private String birthDate = null;

	@JsonProperty("sex")
	private String sex = null;

	@JsonProperty("issuer")
	private String issuer = null;

	@JsonProperty("expireYear")
	private String expireYear = null;

	@JsonProperty("expireMonth")
	private String expireMonth = null;

	@JsonProperty("expireDate")
	private String expireDate = null;

	@JsonProperty("passportNum")
	private String passportNum = null;

	@JsonProperty("personalNum")
	private String personalNum = null;

	@JsonProperty("nationality")
	private String nationality = null;

	public MRZRequest subType(String subType) {
		this.subType = subType;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public MRZRequest givenNames(String givenNames) {
		this.givenNames = givenNames;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getGivenNames() {
		return givenNames;
	}

	public void setGivenNames(String givenNames) {
		this.givenNames = givenNames;
	}

	public MRZRequest surNames(String surNames) {
		this.surNames = surNames;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getSurNames() {
		return surNames;
	}

	public void setSurNames(String surNames) {
		this.surNames = surNames;
	}

	public MRZRequest birthYear(String birthYear) {
		this.birthYear = birthYear;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public MRZRequest birthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public MRZRequest birthDate(String birthDate) {
		this.birthDate = birthDate;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public MRZRequest sex(String sex) {
		this.sex = sex;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public MRZRequest issuer(String issuer) {
		this.issuer = issuer;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public MRZRequest expireYear(String expireYear) {
		this.expireYear = expireYear;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getExpireYear() {
		return expireYear;
	}

	public void setExpireYear(String expireYear) {
		this.expireYear = expireYear;
	}

	public MRZRequest expireMonth(String expireMonth) {
		this.expireMonth = expireMonth;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getExpireMonth() {
		return expireMonth;
	}

	public void setExpireMonth(String expireMonth) {
		this.expireMonth = expireMonth;
	}

	public MRZRequest expireDate(String expireDate) {
		this.expireDate = expireDate;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public MRZRequest passportNum(String passportNum) {
		this.passportNum = passportNum;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getPassportNum() {
		return passportNum;
	}

	public void setPassportNum(String passportNum) {
		this.passportNum = passportNum;
	}

	public MRZRequest personalNum(String personalNum) {
		this.personalNum = personalNum;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getPersonalNum() {
		return personalNum;
	}

	public void setPersonalNum(String personalNum) {
		this.personalNum = personalNum;
	}

	public MRZRequest nationality(String nationality) {
		this.nationality = nationality;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MRZRequest mrzRequest = (MRZRequest) o;
		return Objects.equals(this.subType, mrzRequest.subType)
				&& Objects.equals(this.givenNames, mrzRequest.givenNames)
				&& Objects.equals(this.surNames, mrzRequest.surNames)
				&& Objects.equals(this.birthYear, mrzRequest.birthYear)
				&& Objects.equals(this.birthMonth, mrzRequest.birthMonth)
				&& Objects.equals(this.birthDate, mrzRequest.birthDate) && Objects.equals(this.sex, mrzRequest.sex)
				&& Objects.equals(this.issuer, mrzRequest.issuer)
				&& Objects.equals(this.expireYear, mrzRequest.expireYear)
				&& Objects.equals(this.expireMonth, mrzRequest.expireMonth)
				&& Objects.equals(this.expireDate, mrzRequest.expireDate)
				&& Objects.equals(this.passportNum, mrzRequest.passportNum)
				&& Objects.equals(this.personalNum, mrzRequest.personalNum)
				&& Objects.equals(this.nationality, mrzRequest.nationality);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subType, givenNames, surNames, birthYear, birthMonth, birthDate, sex, issuer, expireYear,
				expireMonth, expireDate, passportNum, personalNum, nationality);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MRZRequest {\n");

		sb.append("    subType: ").append(toIndentedString(subType)).append("\n");
		sb.append("    givenNames: ").append(toIndentedString(givenNames)).append("\n");
		sb.append("    surNames: ").append(toIndentedString(surNames)).append("\n");
		sb.append("    birthYear: ").append(toIndentedString(birthYear)).append("\n");
		sb.append("    birthMonth: ").append(toIndentedString(birthMonth)).append("\n");
		sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
		sb.append("    sex: ").append(toIndentedString(sex)).append("\n");
		sb.append("    issuer: ").append(toIndentedString(issuer)).append("\n");
		sb.append("    expireYear: ").append(toIndentedString(expireYear)).append("\n");
		sb.append("    expireMonth: ").append(toIndentedString(expireMonth)).append("\n");
		sb.append("    expireDate: ").append(toIndentedString(expireDate)).append("\n");
		sb.append("    passportNum: ").append(toIndentedString(passportNum)).append("\n");
		sb.append("    personalNum: ").append(toIndentedString(personalNum)).append("\n");
		sb.append("    nationality: ").append(toIndentedString(nationality)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
