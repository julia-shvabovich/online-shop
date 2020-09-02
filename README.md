Create your own repository
Create models: Product, User, ShoppingCart, Order. Use UML diagram (models) for this, see below.
Create DAO and service layer for Product model. You can see all project structure at the UML diagram (project structure).
Add CRUD operations into ProductDao.
Use Storage class as a persistence layer.
Do not forget to use your own annotation Dao.
Use some static variable that will incrementally generate you an id for each model
Return Optional when you want return null in DAO. For example public Optional<User> get(Long id);
Add class Application with main method where you are invoking all your methods from service
Add new injector to your project.
ProductDao
- Product create(Product product);
- Optional<Product> get(Long id);
- List<Product> getAll();
- Product update(Product product);
- boolean delete(Long id);
ProductService
- Product create(Product product);
- Product get(Long id);
- List<Product> getAll();
- Product update(Product product);
- boolean delete(Long id);
How my models should be related one with each other?
User does not have any relations to ShoppingCart, Order or Product
Product will not have any relations to ShoppingCart, Order or User
ShoppingCart will have a relation with List<Product> products and User user
 class ShoppingCart {
    private Long userId;
    private List<Product> products;
 }
Order will have relation with List<Product> products and User user
 class Order {
    private Long userId;
    private List<Product> products;
 }
How your application should look like at the end (one of the possible implementations)
UML diagram (models) link

UML diagram (project structure) link