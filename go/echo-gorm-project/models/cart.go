package models

import "gorm.io/gorm"

type Cart struct {
	gorm.Model
	ProductID uint
	Quantity  int
	Product   Product `gorm:"foreignKey:ProductID"`
}
