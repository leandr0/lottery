/**
 * 
 */
package com.lrgoncalves.megasena;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

/**
 * @author leandro_2
 *
 */
public class MongoDBDriver {

	private DB db;
	
	private DBCollection collection;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {


	}

	public MongoDBDriver connect(){

		try{

			//MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			MongoClient mongoClient = new MongoClient( "54.207.247.174" , 8080 );
			db = mongoClient.getDB( "test" );
			
			return this;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public MongoDBDriver createCollection(){
		
		collection = db.getCollection("results");
		return this;
	}
	
	public void insert(String json){
		DBObject dbObject = (DBObject)JSON.parse(json);
		collection.insert(dbObject);
	}
	
	public void insert(DBObject json){
		collection.insert(json);
	}
}
