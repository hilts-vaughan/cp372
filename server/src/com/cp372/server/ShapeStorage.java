package com.cp372.server;


public class ShapeStorage {
	   private static ShapeStorage instance = null;
	   protected ShapeStorage() {
	      // Exists only to defeat instantiation.
	   }
	   public static ShapeStorage getInstance() {
	      if(instance == null) {
	         instance = new ShapeStorage();
	      }
	      return instance;
	   }
	}