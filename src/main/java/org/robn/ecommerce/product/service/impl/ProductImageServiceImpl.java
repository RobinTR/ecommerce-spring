package org.robn.ecommerce.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.common.model.UploadedImage;
import org.robn.ecommerce.common.port.ImageStoragePort;
import org.robn.ecommerce.product.config.ProductImageConfig;
import org.robn.ecommerce.product.exception.ProductImageLimitExceededException;
import org.robn.ecommerce.product.exception.ProductImageNotFoundException;
import org.robn.ecommerce.product.exception.RelatedProductNotFoundException;
import org.robn.ecommerce.product.model.ProductImage;
import org.robn.ecommerce.product.port.ProductImageReadPort;
import org.robn.ecommerce.product.port.ProductImageSavePort;
import org.robn.ecommerce.product.port.ProductLookupPort;
import org.robn.ecommerce.product.service.ProductImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageConfig productImageConfig;
    private final ProductImageReadPort productImageReadPort;
    private final ProductImageSavePort productImageSavePort;
    private final ImageStoragePort imageStoragePort;
    private final ProductLookupPort productLookupPort;

    /**
     * Retrieves all product images.
     *
     * @return a list of all ProductImage objects
     */
    @Override
    public List<ProductImage> findAll() {
        return productImageReadPort.findAll();
    }

    /**
     * Retrieves all product images associated with a specific product ID.
     *
     * @param productId the ID of the product for which images are to be retrieved
     * @return a list of ProductImage objects associated with the specified product ID
     */
    @Override
    public List<ProductImage> findAllByProductId(final Long productId) {

        final List<ProductImage> productImages = productImageReadPort.findAllByProductId(productId);

        if (productImages.isEmpty()) {
            throw ProductImageNotFoundException.of(productId);
        }

        return productImages;

    }

    /**
     * Uploads multiple images for a product and saves the image details.
     * This method checks if the product exists and if the image size limit is not exceeded before uploading the images.
     *
     * @param productId the ID of the product to which the images will be uploaded
     * @param files     a list of image files to be uploaded
     * @param altTexts  a list of alternative texts for the images, can be null or shorter than files
     * @throws RelatedProductNotFoundException    if the product with the specified ID does not exist
     * @throws ProductImageLimitExceededException if the product already has the maximum number of images allowed
     */
    @Override
    @Transactional
    public void uploadImages(final Long productId, final List<MultipartFile> files, final List<String> altTexts) {

        ensureProductExists(productId);
        checkForImageSizeLimit(productId, files.size());

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String altText = (altTexts != null && i < altTexts.size()) ? altTexts.get(i) : null;
            final UploadedImage uploadedImage = imageStoragePort.upload(file);
            final ProductImage productImage = buildProductImage(productId, uploadedImage, altText);
            productImageSavePort.save(productImage);
        }

    }

    private void ensureProductExists(final Long productId) {

        if (Boolean.FALSE.equals(productLookupPort.existsById(productId))) {
            throw RelatedProductNotFoundException.of(productId);
        }

    }

    private void checkForImageSizeLimit(final Long productId, final Integer fileCount) {

        final Integer imageCount = Optional.ofNullable(productImageReadPort.countByProductId(productId)).orElse(0);
        final int totalCount = imageCount + fileCount;
        final int maxImageCount = productImageConfig.getMaxCount();

        if (totalCount > maxImageCount) {
            throw ProductImageLimitExceededException.of(
                    String.format("Cannot upload %d images. Product %d already has %d images. Maximum allowed is %d.",
                            fileCount, productId, imageCount, maxImageCount));
        }

    }

    private ProductImage buildProductImage(Long productId, UploadedImage uploadedImage, String altText) {

        return ProductImage.builder()
                .productId(productId)
                .publicId(uploadedImage.getPublicId())
                .imageUrl(uploadedImage.getUrl())
                .format(uploadedImage.getFormat())
                .width(uploadedImage.getWidth())
                .height(uploadedImage.getHeight())
                .sizeBytes(uploadedImage.getSizeBytes())
                .altText(altText)
                .build();
    }

}
