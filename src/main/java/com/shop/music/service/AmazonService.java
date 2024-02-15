package com.shop.music.service;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class AmazonService implements IAmazonService {
	@Autowired
    private AmazonS3 s3client;
	
    @Value("${amazon.bucket}")
    private String bucket;
	
	@Override
	public boolean createBuket(String name) {
		// TODO Auto-generated method stub
        if (this.s3client.doesBucketExist(name)){
            return false;
        }
        this.s3client.createBucket(name);
		return true;
	}

	@Override
	public boolean uploadFileToS3(String folder, String filename, File file) {
		// TODO Auto-generated method stub
		try {
			s3client.putObject(new PutObjectRequest(bucket, folder+"/" + filename, file)
	                .withCannedAcl(CannedAccessControlList.PublicRead));
			return true;
		}
		catch(Exception ex){
			System.out.println(ex.toString());
			return false;
		}
	}

	@Override
	public boolean uploadFileToS3Private(String filename, File file) {
		// TODO Auto-generated method stub
		try {
			s3client.putObject(new PutObjectRequest(bucket, filename, file)
	                .withCannedAcl(CannedAccessControlList.PublicRead));
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}

	@Override
	public boolean deleteFileS3(String filename) {
		// TODO Auto-generated method stub
		try {
			s3client.deleteObject(bucket, filename);
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}

	@Override
	public boolean copyFileS3(String folder, String filename, String tofolder, String tofile) {
		// TODO Auto-generated method stub
		try {
			s3client.copyObject(bucket,filename,folder+"/" + filename, tofolder+"/"+tofile);
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}

	@Override
	public URL getResourceURL(String folder, String file) {
		// TODO Auto-generated method stub
		return s3client.getUrl(bucket,folder + "/"+ file);
	}

	@Override
	public URL getResourceURL(String file) {
		// TODO Auto-generated method stub
		return s3client.getUrl(bucket, file);
	}

	@Override
	public S3ObjectInputStream getMusic(String file, Long start, Long end) {
		// TODO Auto-generated method stub
		GetObjectRequest obj = new GetObjectRequest(bucket,file).withRange(start,end);
        return s3client.getObject(obj).getObjectContent();
	}

	@Override
	public S3ObjectInputStream getMusic(String file) {
		// TODO Auto-generated method stub
        GetObjectRequest obj = new GetObjectRequest(bucket,file);
        return s3client.getObject(obj).getObjectContent();
	}

	@Override
	public boolean fileExist(String file) {
		// TODO Auto-generated method stub
		return  s3client.doesObjectExist(bucket,file);
	}

	@Override
	public Long fileSize(String file) {
		// TODO Auto-generated method stub
		return  s3client.getObjectMetadata(bucket, file).getContentLength();
	}

	@Override
	public boolean changeRoles(String file) {
		// TODO Auto-generated method stub
		try {
			s3client.setObjectAcl(bucket, file,CannedAccessControlList.PublicRead);
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}

}
