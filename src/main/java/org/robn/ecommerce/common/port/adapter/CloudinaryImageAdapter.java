package org.robn.ecommerce.common.port.adapter;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.common.exception.ImageDeleteException;
import org.robn.ecommerce.common.exception.ImageUploadException;
import org.robn.ecommerce.common.model.UploadedImage;
import org.robn.ecommerce.common.port.ImageStoragePort;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CloudinaryImageAdapter implements ImageStoragePort {

    private final Cloudinary cloudinary;

    @Override
    public UploadedImage upload(MultipartFile file) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> options = ObjectUtils.asMap(
                    "overwrite", true,
                    "resource_type", "image"
            );

            @SuppressWarnings("unchecked")
            Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), options);

            String publicId = getStringValue(result, "public_id");
            String secureUrl = getStringValue(result, "secure_url");

            if (publicId == null || secureUrl == null) {
                throw ImageUploadException.of("Missing critical fields from Cloudinary response.");
            }

            return UploadedImage.builder()
                    .publicId(publicId)
                    .url(secureUrl)
                    .format(getStringValue(result, "format"))
                    .width(getAsInt(result, "width"))
                    .height(getAsInt(result, "height"))
                    .sizeBytes(getAsLong(result, "bytes"))
                    .build();
        } catch (IOException e) {
            throw ImageUploadException.of("Failed to upload image: " + e.getMessage());
        }
    }

    @Override
    public void delete(String publicId, Boolean invalidateCdn) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> options = Boolean.TRUE.equals(invalidateCdn) ?
                    ObjectUtils.asMap("invalidate", true) :
                    ObjectUtils.emptyMap();

            @SuppressWarnings("unchecked")
            Map<String, Object> result = cloudinary.uploader().destroy(publicId, options);
            Object status = result.get("result");

            if (!"ok".equals(status) && !"not_found".equals(status)) {
                throw ImageDeleteException.of("Failed to delete image: " + publicId);
            }
        } catch (IOException e) {
            throw ImageDeleteException.of("Error during Cloudinary image deletion.");
        }
    }

    private String getStringValue(Map<String, Object> map, String key) {
        Object value = map.get(key);

        return value != null ? value.toString() : null;
    }

    private Integer getAsInt(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value == null) return null;

        try {
            if (value instanceof Integer integer) {
                return integer;
            }
            return Integer.valueOf(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Long getAsLong(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value == null) return null;

        try {
            if (value instanceof Long longValue) {
                return longValue;
            }
            return Long.valueOf(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
