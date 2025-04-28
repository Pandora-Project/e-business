package controllers

import (
	"echo-gorm-project/database"
	"echo-gorm-project/models"
	"net/http"

	"github.com/labstack/echo/v4"
)

// RegisterCategoryRoutes rejestruje endpointy dla kategorii
func RegisterCategoryRoutes(e *echo.Echo) {
	e.POST("/categories", CreateCategory)
	e.GET("/categories", GetCategories)
	e.GET("/categories/:id", GetCategory)
	e.PUT("/categories/:id", UpdateCategory)
	e.DELETE("/categories/:id", DeleteCategory)
}

// CreateCategory tworzy nową kategorię
func CreateCategory(c echo.Context) error {
	category := new(models.Category)
	if err := c.Bind(category); err != nil {
		return c.JSON(http.StatusBadRequest, map[string]string{"error": "Invalid input"})
	}
	database.DB.Create(category)
	return c.JSON(http.StatusCreated, category)
}

// GetCategories pobiera wszystkie kategorie
func GetCategories(c echo.Context) error {
	var categories []models.Category
	database.DB.Preload("Products").Find(&categories)
	return c.JSON(http.StatusOK, categories)
}

// GetCategory pobiera szczegóły jednej kategorii
func GetCategory(c echo.Context) error {
	id := c.Param("id")
	var category models.Category
	if err := database.DB.Preload("Products").First(&category, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, map[string]string{"error": "Category not found"})
	}
	return c.JSON(http.StatusOK, category)
}

// UpdateCategory aktualizuje kategorię
func UpdateCategory(c echo.Context) error {
	id := c.Param("id")
	var category models.Category
	if err := database.DB.First(&category, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, map[string]string{"error": "Category not found"})
	}

	if err := c.Bind(&category); err != nil {
		return c.JSON(http.StatusBadRequest, map[string]string{"error": "Invalid input"})
	}

	database.DB.Save(&category)
	return c.JSON(http.StatusOK, category)
}

// DeleteCategory usuwa kategorię
func DeleteCategory(c echo.Context) error {
	id := c.Param("id")
	var category models.Category
	if err := database.DB.First(&category, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, map[string]string{"error": "Category not found"})
	}
	database.DB.Delete(&category)
	return c.JSON(http.StatusOK, map[string]string{"message": "Category deleted"})
}
