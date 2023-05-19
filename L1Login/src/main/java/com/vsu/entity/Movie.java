package com.vsu.entity;

import java.util.Objects;

public class Movie {
    private Long id;
    private Integer releaseYear;
    private String title;
    private Long idProfile;

    public Movie(Integer releaseYear, String title, Long idProfile) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.idProfile = idProfile;
    }

    public Movie(Long id, Integer releaseYear, String title, Long idProfile) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.idProfile = idProfile;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Long idProfile) {
        this.idProfile = idProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(getId(), movie.getId()) && Objects.equals(getReleaseYear(), movie.getReleaseYear()) && Objects.equals(getTitle(), movie.getTitle()) && Objects.equals(getIdProfile(), movie.getIdProfile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getReleaseYear(), getTitle(), getIdProfile());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", releaseYear=" + releaseYear +
                ", title='" + title + '\'' +
                ", idProfile=" + idProfile +
                '}';
    }
}
