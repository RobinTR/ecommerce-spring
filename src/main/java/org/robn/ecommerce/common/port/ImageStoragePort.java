package org.robn.ecommerce.common.port;

import org.robn.ecommerce.common.model.UploadedImage;
import org.springframework.web.multipart.MultipartFile;

public interface ImageStoragePort {

    UploadedImage upload(MultipartFile file);

    void delete(String publicId, Boolean invalidateCdn);

}
