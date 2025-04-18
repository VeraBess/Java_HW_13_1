package ru.netology.domain.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    @Test
    public void removeProductByIdFromRepo() { //Успешное удаление товара из репозитория
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "книга", 1_000);
        Product product2 = new Product(2, "игра", 2_000);
        Product product3 = new Product(3, "шкаф", 30_000);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        repository.remove(1);

        Product[] expected = {product2, product3};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeNotFoundFromRepoGenereteEsception() { //генерация исключения при попытке удалить несуществующего элемента
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "книга", 1_000);
        Product product2 = new Product(2, "игра", 2_000);
        Product product3 = new Product(3, "шкаф", 30_000);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> repository.remove(100));
    }

    @Test
    public void addProductToRepo() { //Успешное добавление товара в репозиторий
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "книга", 1_000);
        Product product2 = new Product(2, "игра", 2_000);
        Product product3 = new Product(3, "шкаф", 30_000);
        Product product4 = new Product(3, "шкаф", 30_000);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        repository.add(product4);

        Product[] expected = {product1, product2, product3, product4};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addDublicateProductToRepoGenereteEsception() { //генерация исключения при попытке добавить существующий элемент
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "книга", 1_000);
        Product product2 = new Product(2, "игра", 2_000);
        Product product3 = new Product(3, "шкаф", 30_000);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> repository.add(product1));
    }
}
