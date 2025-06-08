FROM golang:1.22-alpine3.19 AS build
WORKDIR /app
COPY go.mod go.sum ./
RUN go mod download
COPY . .
RUN go build -o server

FROM alpine:3.19
WORKDIR /app
RUN apk update && apk upgrade
COPY --from=build /app/server ./
EXPOSE 8080
CMD ["./server"]
