package me.farah.songrapp.model.dto;

public class SongDTO {
    private String title;
    private int length;
    private String album;

    public SongDTO(String title, int length, String album) {
        this.title = title;
        this.length = length;
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}