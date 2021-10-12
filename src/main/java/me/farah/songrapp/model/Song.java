package me.farah.songrapp.model;

import javax.persistence.*;

@Entity
public class Song {
/**
 * A Song has a title,
 * a length (in seconds),
 * a trackNumber,
 * and the album on which that song appears.
 */
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", nullable = false)
private Long id;
private int trackNumber;
    private String title;
    private double length;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    public Song(String title, double length, Album album) {
        this.title = title;
        this.length = length;
        this.album = album;
    }

    public Song() {

    }

    public void setId(Long id) {
        this.id = id;
    }
    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
