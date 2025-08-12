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
import java.io.InputStream;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CloudinaryImageAdapter implements ImageStoragePort {

    private final Cloudinary cloudinary;

    @Override
    public UploadedImage upload(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Map<String, Object> options = ObjectUtils.asMap(
                    "overwrite", true,
                    "resource_type", "image"
            );

            Map result = cloudinary.uploader().upload(inputStream, options);

            return UploadedImage.builder()
                    .publicId(result.get("public_id").toString())
                    .url(result.get("secure_url").toString())
                    .format(result.get("format").toString())
                    .width(getAsInt(result, "width"))
                    .height(getAsInt(result, "height"))
                    .sizeBytes(getAsLong(result, "bytes"))
                    .build();
        } catch (IOException e) {
            throw new ImageUploadException("Failed to upload image.");
        }
    }

    @Override
    public void delete(String publicId, Boolean invalidateCdn) {
        try {
            Map options = Boolean.TRUE.equals(invalidateCdn) ?
                    ObjectUtils.asMap("invalidate", true) :
                    ObjectUtils.emptyMap();

            Map<?, ?> result = cloudinary.uploader().destroy(publicId, options);
            Object status = result.get("result");

            if(!"ok".equals(status) && !"not_found".equals(status)) {
                throw new ImageDeleteException("Failed to delete image: " + publicId);
            }
        } catch (IOException e) {
            throw new ImageDeleteException("Error during Cloudinary image deletion.");
        }
    }

    private Integer getAsInt(Map<String, Object> map, String key) {
        Object value = map.get(key);

        return value != null ? Integer.parseInt(value.toString()) : null;
    }

    private Long getAsLong(Map<String, Object> map, String key) {
        Object value = map.get(key);

        return value != null ? Long.parseLong(value.toString()) : null;
    }

}
