package org.robn.ecommerce.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.common.model.UploadedImage;
import org.robn.ecommerce.common.port.ImageStoragePort;
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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductImageServiceImpl implements ProductImageService {

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
            throw new ProductImageNotFoundException(productId);
        }

        return productImages;
    }

    /**
     * Uploads an image for a product and saves the image details.
     * This method first checks if the product exists, then uploads the image using the image storage port,
     * and finally saves the image details in the product image save port.
     *
     * @param productId the ID of the product to which the image will be uploaded
     * @param file      the image file to be uploaded
     * @param altText   the alternative text for the image
     * @throws RelatedProductNotFoundException if the product with the specified ID does not exist
     */
    @Override
    @Transactional
    public void uploadImage(final Long productId, final MultipartFile file, final String altText) {
        ensureProductExists(productId);
        final UploadedImage uploadedImage = imageStoragePort.upload(file);
        final ProductImage productImage = ProductImage.builder()
                .productId(productId)
                .publicId(uploadedImage.getPublicId())
                .imageUrl(uploadedImage.getUrl())
                .format(uploadedImage.getFormat())
                .width(uploadedImage.getWidth())
                .height(uploadedImage.getHeight())
                .sizeBytes(uploadedImage.getSizeBytes())
                .altText(altText)
                .build();
        productImageSavePort.save(productImage);
    }

    private void ensureProductExists(final Long productId) {
        if (Boolean.FALSE.equals(productLookupPort.existsById(productId))) {
            throw new RelatedProductNotFoundException(productId);
        }
    }

}
