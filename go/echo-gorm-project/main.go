package main

import (
	"echo-gorm-project/controllers"
	"echo-gorm-project/database"
	"echo-gorm-project/models"

	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
)

func main() {
	// Inicjalizacja bazy danych
	db := database.InitDB()
	db.AutoMigrate(&models.Product{}, &models.Cart{}, &models.Category{})

	// Inicjalizacja Echo
	e := echo.New()
	e.Use(middleware.Logger())
	e.Use(middleware.Recover())

	// Endpointy
	controllers.RegisterProductRoutes(e)
	controllers.RegisterCartRoutes(e)
	controllers.RegisterCategoryRoutes(e)

	// Start serwera
	e.Logger.Fatal(e.Start(":8080"))
}
