1. Create your own repository
2. Create models: Product, User, ShoppingCart, Order. Use UML diagram (models) for this, see below.
3. Create DAO and application.service layer for Product application.model. You can see all project structure at the UML diagram (project structure).
4. Add CRUD operations into ProductDao.
5. Use Storage class as a persistence layer.
6. Do not forget to use your own annotation Dao.
7. Use some static variable that will incrementally generate you an id for each application.model
8. Return Optional when you want return null in DAO. For example public Optional<User> get(Long id);
9. Add class application.Application with main method where you are invoking all your methods from application.service
10. Add new injector to your project.

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
User does not have any relations to ShoppingCart, Order or Product.
Product will not have any relations to ShoppingCart, Order or User.
ShoppingCart will have a relation with List<Product> products and User user.
Order will have relation with List<Product> products and User user.