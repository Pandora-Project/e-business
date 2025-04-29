package main

import (
	"encoding/json"
	"log"
	"net/http"

	"github.com/gorilla/mux"
	"github.com/rs/cors"
)

type Product struct {
	ID    int    `json:"id"`
	Name  string `json:"name"`
	Price float64 `json:"price"`
}

type Payment struct {
	CartItems []Product `json:"cartItems"`
	Total     float64   `json:"total"`
}

var products = []Product{
	{ID: 1, Name: "Laptop", Price: 1200.50},
	{ID: 2, Name: "Headphones", Price: 150.00},
	{ID: 3, Name: "Keyboard", Price: 75.99},
}

func getProductsHandler(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(products)
}

func processPaymentHandler(w http.ResponseWriter, r *http.Request) {
	var payment Payment
	if err := json.NewDecoder(r.Body).Decode(&payment); err != nil {
		http.Error(w, "Invalid request payload", http.StatusBadRequest)
		return
	}
	log.Printf("Received payment: %+v\n", payment)
	w.WriteHeader(http.StatusOK)
	w.Write([]byte("Payment processed successfully"))
}

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/api/products", getProductsHandler).Methods("GET")
	r.HandleFunc("/api/payments", processPaymentHandler).Methods("POST")

	c := cors.New(cors.Options{
		AllowedOrigins: []string{"*"}, 
		AllowedMethods: []string{"GET", "POST", "OPTIONS"},
		AllowedHeaders: []string{"Content-Type"},
	})

	log.Println("Server is running on port 8080")
	http.ListenAndServe(":8080", c.Handler(r))
}
