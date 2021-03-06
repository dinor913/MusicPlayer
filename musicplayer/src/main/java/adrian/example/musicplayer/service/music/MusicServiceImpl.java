package adrian.example.musicplayer.service.music;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import adrian.example.musicplayer.dao.music.MusicPlayerDao;
import adrian.example.musicplayer.model.Music.Song;

@Service("musicServiceImpl")
public class MusicServiceImpl implements MusicService {

	@Autowired
	MusicPlayerDao musicPlayerDao;

	@Override
	@Transactional(readOnly = true)
	public List<Song> getSongByGenre(String genre_name) {
		return this.musicPlayerDao.getSongByGenre(genre_name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Song> getSongByAlbum(String album_name) {
		return this.musicPlayerDao.getSongByAlbum(album_name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Song> getSongByArtist(String artist_name) {
		return this.musicPlayerDao.getSongByArtist(artist_name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Song getSongById(int id) {
		return this.musicPlayerDao.getSongById(id);
	}
	

}
