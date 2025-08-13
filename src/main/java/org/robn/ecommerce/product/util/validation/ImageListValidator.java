package org.robn.ecommerce.product.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.product.config.ProductImageConfig;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
public class ImageListValidator implements ConstraintValidator<ImageList, List<MultipartFile>> {

    private final ProductImageConfig productImageConfig;

    @Override
    public boolean isValid(final List<MultipartFile> files, final ConstraintValidatorContext context) {

        final int minCount = productImageConfig.getMinCount();
        final int maxCount = productImageConfig.getMaxCount();
        final long maxFileSize = productImageConfig.getMaxSize().toBytes();
        final long maxFileSizeInMB = productImageConfig.getMaxSize().toMegabytes();
        final List<String> allowedTypes = productImageConfig.getAllowedTypes();

        if (files == null || files.size() < minCount) {
            addConstraintViolation(context, String.format("At least %d images are required.", minCount));
            return false;
        }

        if (files.size() > maxCount) {
            addConstraintViolation(context, String.format("Number of images cannot exceed %d.", maxCount));
            return false;
        }

        for (int i = 0; i < files.size(); i++) {

            final MultipartFile file = files.get(i);

            if (file == null || file.isEmpty()) {
                addConstraintViolation(context, String.format("Image %d cannot be empty.", i + 1));
                return false;
            }

            if (file.getSize() > maxFileSize) {
                addConstraintViolation(context, String.format("Image %d exceeds the maximum file size of %s MB.", i + 1, maxFileSizeInMB));
                return false;
            }

            final String contentType = file.getContentType();
            if (contentType == null || !allowedTypes.contains(contentType)) {
                addConstraintViolation(context, String.format("Image %d has an unsupported content type: %s. Allowed types are: %s",
                        i + 1, contentType, String.join(", ", allowedTypes)));
                return false;
            }

        }

        return true;

    }

    private void addConstraintViolation(final ConstraintValidatorContext context, final String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
