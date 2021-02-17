package com.mudasir.poetry.ui.models;

import java.util.HashMap;
import java.util.Map;

public class Images {

    private String ImageUrl;
    private String Imagekey;
    private String ImageType;

    public Images() {
    }

    public Images(String imageUrl, String imagekey, String imageType) {
        ImageUrl = imageUrl;
        Imagekey = imagekey;
        ImageType = imageType;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getImagekey() {
        return Imagekey;
    }

    public void setImagekey(String imagekey) {
        Imagekey = imagekey;
    }

    public String getImageType() {
        return ImageType;
    }

    public void setImageType(String imageType) {
        ImageType = imageType;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("key", this.Imagekey);
        result.put("url", this.ImageUrl);
        result.put("type", this.ImageType);
        return result;
    }

}
