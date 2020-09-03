package application;

import application.lib.Injector;
import application.model.Product;
import application.service.ProductService;

public class Application {
    private static Injector injector = Injector.getInstance("application");

    public static void main(String[] args) {
        ProductService service = (ProductService) injector.getInstance(ProductService.class);
        Product cat = new Product("Cat", 300);
        Product dog = new Product("Dog", 150);
        Product julia = new Product("Julia", 1);
        service.create(cat);
        service.create(dog);
        service.create(julia);

        System.out.println("Creation products:");
        for (Product product : service.getAll()) {
            System.out.println(product);
        }

        Product newCat = new Product("Cat", 270);
        newCat.setId(cat.getId());
        service.update(newCat);
        System.out.println("Updating products:");
        for (Product product : service.getAll()) {
            System.out.println(product);
        }

        System.out.println("Deleting products:");
        service.delete(julia.getId());
        for (Product product : service.getAll()) {
            System.out.println(product);
        }
    }
}
