package com.example.jsfdemo.web;

import java.io.Serializable;
import java.sql.SQLException;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jsfdemo.domain.File;

@SessionScoped
@Named("fileBean")
public class FileFormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ListDataModel<File> files = new ListDataModel<File>();

	@Inject
	private DB db;
	public ListDataModel<File> getall() throws SQLException
    {
		files.setWrappedData(db.selAll());
    	return files;	    	
    }
    public ListDataModel<File> gethash() throws SQLException
    {
    	files.setWrappedData(db.selHash());
		return files;	    	
    }
    public ListDataModel<File> getnoHash() throws SQLException
    {
    	files.setWrappedData(db.selNoHash());
    	return files;	    	
    }
}
