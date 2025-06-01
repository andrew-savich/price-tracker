package com.andrewsavich.price_checker.api;

import com.andrewsavich.price_checker.domain.PriceHistory;
import com.andrewsavich.price_checker.domain.TrackedProduct;
import com.andrewsavich.price_checker.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1//products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<TrackedProduct> addProduct(
            @Valid @RequestBody AddProductRequest request,
            @AuthenticationPrincipal OAuth2User user
    ) {
        String email = user.getAttribute("email");
        TrackedProduct product = productService.addProductToTrack(request.getUrl(), email);
        return ResponseEntity.ok(product);
    }

    // GET /products — List all tracked products for current user
    @GetMapping
    public ResponseEntity<List<TrackedProduct>> getTrackedProducts(
            @AuthenticationPrincipal OAuth2User user
    ) {
        String email = user.getAttribute("email");
        List<TrackedProduct> products = productService.getTrackedProducts(email);
        return ResponseEntity.ok(products);
    }

    // GET /products/{id}/history — Get price history for a product
    @GetMapping("/{id}/history")
    public ResponseEntity<List<PriceHistory>> getProductHistory(
            @PathVariable UUID id,
            @AuthenticationPrincipal OAuth2User user
    ) {
        String email = user.getAttribute("email");
        List<PriceHistory> history = productService.getPriceHistory(id, email);
        return ResponseEntity.ok(history);
    }

    // DTO
    public record AddProductRequest(String url) {}

}
