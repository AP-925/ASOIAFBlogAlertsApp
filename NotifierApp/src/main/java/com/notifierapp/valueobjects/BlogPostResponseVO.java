package com.notifierapp.valueobjects;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class BlogPostResponseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isNewBlogPostToday;
	private String recentBlogDate;

	public boolean isNewBlogPostToday() {
		return isNewBlogPostToday;
	}

	public void setNewBlogPostToday(boolean isNewBlogPostToday) {
		this.isNewBlogPostToday = isNewBlogPostToday;
	}

	public String getRecentBlogDate() {
		return recentBlogDate;
	}

	public void setRecentBlogDate(String recentBlogDate) {
		this.recentBlogDate = recentBlogDate;
	}

	@Override
	public String toString() {
		return "BlogPostResponseVO [isNewBlogPostToday=" + isNewBlogPostToday + ", recentBlogDate=" + recentBlogDate
				+ "]";
	}

}
