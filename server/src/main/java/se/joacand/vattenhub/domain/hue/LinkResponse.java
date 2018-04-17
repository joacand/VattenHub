package se.joacand.vattenhub.domain.hue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkResponse {

	private LinkError error;

	private LinkSuccess success;

	public LinkError getError() {
		return error;
	}

	public void setError(LinkError error) {
		this.error = error;
	}

	public LinkSuccess getSuccess() {
		return success;
	}

	public void setSuccess(LinkSuccess success) {
		this.success = success;
	}
}