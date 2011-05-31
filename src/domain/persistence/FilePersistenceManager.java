package domain.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import domain.Game;

public class FilePersistenceManager implements PersistenceManager {

	@Override
	public Game loadGame(String filename) {

		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream o = new ObjectInputStream(file);
			Game game = (Game) o.readObject();
			o.close();
			return game;
		} catch (IOException e) {
			System.err.println(e);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
		return null;
	}

	@Override
	public Boolean saveGame(Game game, String filename) {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream o = new ObjectOutputStream(file);
			o.writeObject(game);
			o.close();
		} catch (IOException e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
}
