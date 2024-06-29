package com.chatBot.indraChat.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBody {
	@JsonProperty("contents")
	private List<Content> contents;

	public RequestBody(List<Content> contents) {
		this.contents = contents;
	}

	public RequestBody() {
		// TODO Auto-generated constructor stub
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	public static class Content {
		@JsonProperty("parts")
		private List<Part> parts;

		public Content(List<Part> parts) {
			this.parts = parts;
		}

		public List<Part> getParts() {
			return parts;
		}

		public void setParts(List<Part> parts) {
			this.parts = parts;
		}
	}

	public static class Part {
		@JsonProperty("text")
		private String text;

		public Part(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}
}