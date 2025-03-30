package controllers

import javax.inject._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}
import scala.collection.mutable.ListBuffer

@Singleton
class ProductsController @Inject()(val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext) extends BaseController {

  private val products = ListBuffer[String]("Laptop", "Smartphone", "Headphones")

  def list() = Action {
    Ok(products.mkString(", "))
  }

  def get(id: Int) = Action {
    if (id >= 0 && id < products.length) {
      Ok(s"Product: ${products(id)}")
    } else {
      NotFound(s"No product found with ID $id")
    }
  }

  def create() = Action.async(parse.text: BodyParser[String]) { request =>
    val productName = request.body.trim
    if (productName.nonEmpty) {
      createProduct(productName).map { lastMessage =>
        Created(s"Product added: $productName. Message: $lastMessage")
      }
    } else {
      Future.successful(BadRequest("Product name cannot be empty"))
    }
  }

  private def createProduct(productName: String): Future[String] = Future {
    // Simulate database insertion delay
    Thread.sleep(100) // Simulating asynchronous behavior
    products += productName
    "Operation successful"
  }

  def update(id: Int) = Action.async(parse.text: BodyParser[String]) { request =>
    val newName = request.body.trim
    if (id >= 0 && id < products.length) {
      if (newName.nonEmpty) {
        updateProduct(id, newName).map { lastMessage =>
          Ok(s"Product with ID $id updated to: $newName. Message: $lastMessage")
        }
      } else {
        Future.successful(BadRequest("Updated product name cannot be empty"))
      }
    } else {
      Future.successful(NotFound(s"No product found with ID $id"))
    }
  }

  private def updateProduct(id: Int, newName: String): Future[String] = Future {
    // Simulate database update delay
    Thread.sleep(100) // Simulating asynchronous behavior
    products(id) = newName
    "Update operation successful"
  }

  def delete(id: Int) = Action.async {
    if (id >= 0 && id < products.length) {
      deleteProduct(id).map { lastMessage =>
        Ok(s"Product removed. Message: $lastMessage")
      }
    } else {
      Future.successful(NotFound(s"No product found with ID $id"))
    }
  }

  private def deleteProduct(id: Int): Future[String] = Future {
    // Simulate database deletion delay
    Thread.sleep(100) // Simulating asynchronous behavior
    val removedProduct = products.remove(id)
    s"Successfully removed: $removedProduct"
  }
}
