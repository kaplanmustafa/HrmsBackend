package com.mustafakaplan.hrms.core.utilities.services.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mustafakaplan.hrms.core.utilities.services.abstracts.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageManager implements ImageService {

    @Value("${cloudinary.cloudName}")
    private String cloudName;

    @Value("${cloudinary.apiKey}")
    private String apiKey;

    @Value("${cloudinary.apiSecret}")
    private String apiSecret;

    @Override
    public String uploadImage(String imagePath, String identityNumber) {

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));

        Map params = ObjectUtils.asMap(
                "public_id", "hrms/employee/" + identityNumber,
                "overwrite", true,
                "notification_url", "https://mysite.com/notify_endpoint",
                "resource_type", "image"
        );

        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(new File(imagePath), params);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uploadResult.get("url").toString();
    }
}
