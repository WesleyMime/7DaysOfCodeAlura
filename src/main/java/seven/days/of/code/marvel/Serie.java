package seven.days.of.code.marvel;

import com.google.gson.Gson;

import seven.days.of.code.Content;

public final class Serie implements Content {

	private String title;
	private Integer startYear;
	private Thumbnail thumbnail;
	private String rating;
	
	public Serie() {}
	
	public Serie(String title, Integer year, Thumbnail thumbnail, String rating) {
		this.title = title;
		this.startYear = year;
		this.thumbnail = thumbnail;
		this.rating = rating;
	}

	public String title() {
		return title;
	}

	public Integer year() {
		return startYear;
	}

	public String image() {
		return thumbnail.getPath() + "/portrait_incredible." + thumbnail.getExtension();
	}

	public String rating() {
		return rating;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public String type() {
		return "Serie";
	}

	@Override
	public int compareTo(Content content) {
		return this.title.compareTo(content.title());
	}

}
