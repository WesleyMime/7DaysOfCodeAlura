package seven.days.of.code.imdb;

import com.google.gson.Gson;

import seven.days.of.code.Content;

public final class Movie implements Content {

	private String title;
	private Integer year;
	private String image;
	private String imDbRating;
	
	public Movie() {}
	
	public Movie(String title, Integer year, String image, String imDbRating) {
		this.title = title;
		this.year = year;
		this.image = image;
		this.imDbRating = imDbRating;
	}

	public String title() {
		return title;
	}

	public Integer year() {
		return year;
	}

	public String image() {
		return image;
	}

	public String rating() {
		return imDbRating;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public void setImDbRating(String imDbRating) {
		this.imDbRating = imDbRating;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public String type() {
		return "Movie";
	}

	@Override
	public int compareTo(Content content) {
		return this.title.compareTo(content.title());
	}

}
