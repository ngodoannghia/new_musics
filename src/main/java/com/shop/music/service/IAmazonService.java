package com.shop.music.service;

import java.io.File;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Component
public interface IAmazonService {
	boolean createBuket(String name);
	boolean uploadFileToS3(String folder, String filename, File file);
	boolean uploadFileToS3Private(String filename, File file);
	boolean deleteFileS3(String filename);
	boolean copyFileS3(String folder, String filename, String tofolder, String tofile);
	URL getResourceURL(String folder, String file);
	URL getResourceURL(String file);
	S3ObjectInputStream getMusic(String file, Long start, Long end);
	S3ObjectInputStream getMusic(String file);
	boolean fileExist(String file);
	Long fileSize(String file);
	boolean changeRoles(String file);
}
