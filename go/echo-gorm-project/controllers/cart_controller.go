package controllers

import (
	"echo-gorm-project/database"
	"echo-gorm-project/models"
	"net/http"

	"github.com/labstack/echo/v4"
)

// RegisterCartRoutes rejestruje endpointy dla koszyka
func RegisterCartRoutes(e *echo.Echo) {
	e.POST("/carts", AddToCart)
	e.GET("/carts", GetCartItems)
}

// AddToCart dodaje produkt do koszyka
func AddToCart(c echo.Context) error {
	cartItem := new(models.Cart)
	if err := c.Bind(cartItem); err != nil {
		return c.JSON(http.StatusBadRequest, map[string]string{"error": "Invalid input"})
	}

	// Sprawdzenie, czy produkt istnieje
	var product models.Product
	if err := database.DB.First(&product, cartItem.ProductID).Error; err != nil {
		return c.JSON(http.StatusNotFound, map[string]string{"error": "Product not found"})
	}

	// Dodanie do koszyka
	cartItem.Product = product
	database.DB.Create(cartItem)
	return c.JSON(http.StatusCreated, cartItem)
}

// GetCartItems pobiera wszystkie produkty w koszyku
func GetCartItems(c echo.Context) error {
	var cartItems []models.Cart
	database.DB.Preload("Product").Find(&cartItems)
	return c.JSON(http.StatusOK, cartItems)
}
