package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import com.google.gson.Gson;
import exceptions.ExceptionFileNotFound;
import view.MainFx;

public class Serialisation {

	/**
	 * Save questions in a Json
	 * @param file
	 * File in which one saves
	 * @param obj
	 * The object saved
	 */
	public static void saveFile(String file, Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		try ( FileWriter fileWriter = new FileWriter(file+".json")) {
			fileWriter.write(json);
			fileWriter.close();;
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * Load the questions of a json
	 * @param c
	 * The class of the object
	 * @param file
	 * The file to load
	 * @return the object loaded
	 */
	public static Object loadFile(Class c, String file){
		Gson gson = new Gson();
		Object obj = null;
		try ( BufferedReader br = new BufferedReader(new FileReader(file+".json"))) {
			obj=gson.fromJson(br,c);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * Reset the file of the applciation
	 * @param file
	 * Backup of the file
	 * @param file2
	 * The current file
	 * @param c
	 * Class of the object
	 */
	public static void reset(String file, String file2, Class c) {
		Object obj = loadFile(c, file);		
		saveFile(file2, obj);
	}
	
	/**
	 * Serialize a object
	 * @param file
	 * File in which one saves
	 * @param o
	 * The object saved
	 */
	public static void serializeObject(String file, Object o) {
		try (ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(o);
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Deserialise a game
	 * @param file
	 * The file to load
	 * @return The object loaded
	 * @throws ExceptionFileNotFound
	 * If not file found
	 */
	public static Object deserializeObject(String file) throws ExceptionFileNotFound{
		Object obj = null;
		try (ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(file))) {
			obj = (Object)ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			MainFx.getStackGamePane().getAlertInformation("File not found.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(obj == null) {
			throw new ExceptionFileNotFound();
		}
		return obj;
	}
}
