package com.fintech.orion.dto.mrz;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fintech.orion.dto.response.api.VerificationRequestResponse;

import io.swagger.annotations.ApiModelProperty;

/*
 * ScriptResult
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-11-09T10:12:11.427Z")
public class ScriptResult {

	@JsonProperty("mrz")
	private MRZ mrz = null;

	@JsonProperty("error")
	private String error = null;

	public ScriptResult mrz(MRZ mrz) {
		this.mrz = mrz;
		return this;
	}

	@ApiModelProperty(value = "")
	public MRZ getMrz() {
		return mrz;
	}

	public void setMrz(MRZ mrz) {
		this.mrz = mrz;
	}

	public ScriptResult error(String error) {
		this.error = error;
		return this;
	}

	@ApiModelProperty(value = "")
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ScriptResult scriptResult = (ScriptResult) o;
		return Objects.equals(this.mrz, scriptResult.getMrz()) && Objects.equals(this.error, scriptResult.getError());
	}

	@Override
	public int hashCode() {
		return Objects.hash(mrz, error);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ScriptResult {\n");

		sb.append("    mrz: ").append(toIndentedString(mrz)).append("\n");
		sb.append("    error: ").append(toIndentedString(error)).append("\n");
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
