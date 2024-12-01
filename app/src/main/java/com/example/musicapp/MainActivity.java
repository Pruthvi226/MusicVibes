package com.example.musicapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton, pauseButton, stopButton, nextButton, prevButton;
    private TextView songTitle;
    private ArrayList<Integer> songList;
    private int currentSongIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);
        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        songTitle = findViewById(R.id.songTitle);

        // Add songs to the playlist
        songList = new ArrayList<>();
        songList.add(R.raw.song10);
        songList.add(R.raw.song11);
        songList.add(R.raw.song9);
        songList.add(R.raw.song1);
        songList.add(R.raw.song3);// Replace with actual file names
        songList.add(R.raw.song2);
        songList.add(R.raw.song5);
        songList.add(R.raw.song6);
        songList.add(R.raw.song7);
        songList.add(R.raw.song8);


        // Initialize MediaPlayer with the first song
        mediaPlayer = MediaPlayer.create(this, songList.get(currentSongIndex));
        songTitle.setText("Song 1"); // Replace with corresponding titles

        playButton.setOnClickListener(v -> playSong());
        pauseButton.setOnClickListener(v -> pauseSong());
        stopButton.setOnClickListener(v -> stopSong());
        nextButton.setOnClickListener(v -> playNextSong());
        prevButton.setOnClickListener(v -> playPreviousSong());
    }

    private void playSong() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    private void pauseSong() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void stopSong() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, songList.get(currentSongIndex));
        }
    }

    private void playNextSong() {
        stopSong();
        currentSongIndex = (currentSongIndex + 1) % songList.size();
        mediaPlayer = MediaPlayer.create(this, songList.get(currentSongIndex));
        songTitle.setText("Song " + (currentSongIndex + 1)); // Replace with dynamic titles
        playSong();
    }

    private void playPreviousSong() {
        stopSong();
        currentSongIndex = (currentSongIndex - 1 + songList.size()) % songList.size();
        mediaPlayer = MediaPlayer.create(this, songList.get(currentSongIndex));
        songTitle.setText("Song " + (currentSongIndex + 1)); // Replace with dynamic titles
        playSong();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}