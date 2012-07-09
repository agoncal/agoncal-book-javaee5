package org.agoncal.book.javaee5.petstore;

import org.agoncal.book.javaee5.petstore.entity.Address;
import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.agoncal.book.javaee5.petstore.entity.catalog.Item;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;
import org.agoncal.book.javaee5.petstore.entity.order.CreditCard;
import org.agoncal.book.javaee5.petstore.entity.order.Order;
import org.agoncal.book.javaee5.petstore.entity.order.OrderLine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 */
public final class TestHelper {

    //==================================
    //=             Utility            =
    //==================================

    public static Long getRandom() {
        return (long) (Math.random() * 100000);
    }

    //==================================
    //=            CreditCard          =
    //==================================

    public static CreditCard getMockCreditCardValues() {
        Long random = getRandom();
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardExpDate("" + random);
        creditCard.setCreditCardNumber("numb" + random);
        creditCard.setCreditCardType("American Express");
        return creditCard;
    }

    public static CreditCard getNewCreditCard() {
        Long random = getRandom();
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardExpDate("" + random);
        creditCard.setCreditCardNumber("numb" + random);
        creditCard.setCreditCardType("American Express");
        return creditCard;
    }

    //==================================
    //=             Address            =
    //==================================

    public static Address getMockAddressValues() {
        return getMockAddressValues(new Address(), getRandom());
    }

    public static Address getMockAddressValues(final Address address, final Long random) {
        address.setCity("city" + random);
        address.setCountry("cnty" + random);
        address.setState("state" + random);
        address.setStreet1("street1" + random);
        address.setStreet2("street2" + random);
        address.setZipcode("zip" + random);
        return address;
    }

    public static Address getNewAddress() {
        Long random = getRandom();
        Address address = new Address();
//        address.setId(random);
        address.setCity("city" + random);
        address.setCountry("cnty" + random);
        address.setState("state" + random);
        address.setStreet1("street1" + random);
        address.setStreet2("street2" + random);
        address.setZipcode("zip" + random);
        return address;
    }

    public static void checkMockAddressValues(final Address address, final Long random) {
        assertNotNull("id", address.getId());
        assertEquals("city", "city" + random, address.getCity());
        assertEquals("country", "cnty" + random, address.getCountry());
        assertEquals("state", "state" + random, address.getState());
        assertEquals("street1", "street1" + random, address.getStreet1());
        assertEquals("street2", "street2" + random, address.getStreet2());
        assertEquals("zipcode", "zip" + random, address.getZipcode());
    }

    //==================================
    //=            Customer            =
    //==================================

    public static Customer getMockCustomerValues(final Customer customer, final Long random) {
        customer.setLogin("l" + random);
        customer.setPassword("p" + random);
        customer.setFirstname("firstname" + random);
        customer.setLastname("lastname" + random);
        customer.setTelephone("phone" + random);
        customer.setEmail("email" + random);
        Calendar birth = new GregorianCalendar();
        birth.set(1971, 5, 29, 0, 0, 0);
        customer.setDateOfBirth(birth.getTime());
        return customer;
    }

    public static Customer getNewCustomer() {
        Long random = getRandom();
        Customer customer = new Customer();
//        customer.setId(random);
        customer.setLogin("l" + random);
        customer.setPassword("p" + random);
        customer.setFirstname("firstname" + random);
        customer.setLastname("lastname" + random);
        customer.setTelephone("phone" + random);
        customer.setEmail("email" + random);
        Calendar birth = new GregorianCalendar();
        birth.set(1971, 5, 29, 0, 0, 0);
        customer.setDateOfBirth(birth.getTime());
        customer.setHomeAddress(getNewAddress());
        return customer;
    }

    public static void checkMockCustomerValues(final Customer customer, final Long random) {
        assertNotNull("id", customer.getId());
        assertEquals("login", "l" + random, customer.getLogin());
        assertEquals("password", "p" + random, customer.getPassword());
        assertEquals("firstname", "firstname" + random, customer.getFirstname());
        assertEquals("lastname", "lastname" + random, customer.getLastname());
        assertEquals("telephone", "phone" + random, customer.getTelephone());
        assertEquals("email", "email" + random, customer.getEmail());
        assertNotNull("date of birth", customer.getDateOfBirth());
        assertNotNull("age", customer.getAge());
    }

    public static Customer createCustomer() {
        return createCustomer(getRandom());
    }

    public static Customer createCustomer(Long random) {
        Customer customer = new Customer();
        Address address = new Address();
        customer = getMockCustomerValues(customer, random);
        address = getMockAddressValues(address, random);
//TODO        customer = CustomerDelegate.createCustomer(customer, address);
        return customer;
    }

    public static Customer updateCustomer(Customer customer, Long random) {
        customer = getMockCustomerValues(customer, random);
//TODO        customer = CustomerDelegate.updateCustomer(customer, customer.getHomeAddress());
        return customer;
    }

    public static int findAllCustomers() {
        List result = null; //TODO = CustomerDelegate.findCustomers();
        if (result == null)
            return 0;
        else
            return result.size();
    }

    //==================================
    //=            Category            =
    //==================================

    public static Category getMockCategoryValues(final Category category, final Long random) {
        category.setName("name" + random);
        category.setDescription("description" + random);
        return category;
    }

    public static Category getNewCategory() {
        Long random = getRandom();
        Category category = new Category();
//        category.setId(random);
        category.setName("name" + random);
        category.setDescription("description" + random);
        return category;
    }

    public static void checkMockCategoryValues(final Category category, final Long random) {
        assertNotNull("id", category.getId());
        assertEquals("name", "name" + random, category.getName());
        assertEquals("description", "description" + random, category.getDescription());
    }

    public static Category createCategory() {
        return createCategory(getRandom());
    }

    public static Category createCategory(Long random) {
        Category category = new Category();
        category = getMockCategoryValues(category, random);
//TODO        category = CatalogDelegate.createCategory(category);
        return category;
    }

    public static Category updateCategory(Category category, Long random) {
        category = getMockCategoryValues(category, random);
//TODO        category = CatalogDelegate.updateCategory(category);
        return category;
    }

    public static int findAllCategories() {
        List result = null; //TODO CatalogDelegate.findCategories();
        if (result == null)
            return 0;
        else
            return result.size();
    }

    //==================================
    //=            Product            =
    //==================================

    public static Product getMockProductValues(final Product product, final Long random) {
        product.setName("name" + random);
        product.setDescription("description" + random);
        return product;
    }

    public static Product getNewProduct() {
        Long random = getRandom();
        Product product = new Product();
//        product.setId(random);
        product.setName("name" + random);
        product.setDescription("description" + random);
        product.setCategory(getNewCategory());
        return product;
    }

    public static void checkMockProductValues(final Product product, final Long random) {
        assertNotNull("id", product.getId());
        assertEquals("name", "name" + random, product.getName());
        assertEquals("description", "description" + random, product.getDescription());
    }

    public static Product createProduct() {
        return createProduct(getRandom());
    }

    public static Product createProduct(Long random) {
        Product product = new Product();
        product = getMockProductValues(product, random);
//TODO        product = CatalogDelegate.createProduct(product, createCategory(random));
        return product;
    }

    public static Product updateProduct(Product product, Long random) {
        product = getMockProductValues(product, random);
//TODO        product = CatalogDelegate.updateProduct(product, product.getCategory());
        return product;
    }

    public static int findAllProducts() {
        List result = null; //TODO CatalogDelegate.findProducts();
        if (result == null)
            return 0;
        else
            return result.size();
    }

    //==================================
    //=               Item             =
    //==================================

    public static Item getMockItemValues(final Item item, final Long random) {
        item.setName("name" + random);
        item.setUnitCost(random.floatValue());
        return item;
    }

    public static Item getNewItem() {
        Long random = getRandom();
        Item item = new Item();
//        item.setId(random);
        item.setName("name" + random);
        item.setImagePath("image/" + random + "/image" + random);
        item.setUnitCost(random.floatValue());
        item.setProduct(getNewProduct());
        return item;
    }

    public static void checkMockItemValues(final Item item, final Long random) {
        assertNotNull("id", item.getId());
        assertEquals("name", "name" + random, item.getName());
        assertEquals("unitCost", new Float(random.floatValue()), item.getUnitCost());
    }

    public static Item createItem() {
        return createItem(getRandom());
    }

    public static Item createItem(Long random) {
        Item item = new Item();
        item = getMockItemValues(item, random);
//TODO        item = CatalogDelegate.createItem(item, createProduct(random));
        return item;
    }

    public static Item updateItem(Item item, Long random) {
        item = getMockItemValues(item, random);
//TODO        item = CatalogDelegate.updateItem(item, item.getProduct());
        return item;
    }

    public static int findAllItems() {
        List result = null; //TODO CatalogDelegate.findItems();
        if (result == null)
            return 0;
        else
            return result.size();
    }

    //==================================
    //=             Order              =
    //==================================

    public static Order getMockOrderValues() {
        return getMockOrderValues(new Order(), getRandom());
    }

    public static Order getMockOrderValues(final Order order, final Long random) {
        order.setCreditCardExpiryDate("01/08");
        order.setCreditCardNumber("ccnum" + random);
        order.setCreditCardType("American Express");
        // Un bon de commande doit avoir une adresse de livraison
        Address deliveryAddress = new Address();
        deliveryAddress = getMockAddressValues(deliveryAddress, random);
        order.setDeliveryAddress(deliveryAddress);
        return order;
    }

    public static Order getNewOrder() {
        Order order = new Order();
        Long random = getRandom();
        order.setCreditCard(getNewCreditCard());
        order.setDeliveryAddress(getNewAddress());
        order.setCustomer(getNewCustomer());
        List<OrderLine> orderLines = new ArrayList<OrderLine>();
        orderLines.add(getNewOrderLine());
        orderLines.add(getNewOrderLine());
        orderLines.add(getNewOrderLine());
        order.setOrderLines(orderLines);
        return order;
    }

    public static void checkMockOrderValues(final Order order, final Long random) {
        assertNotNull("id", order.getId());
        assertNotNull("order date", order.getOrderDate());
        assertEquals("credit card expiry date", "01/08", order.getCreditCardExpiryDate());
        assertEquals("credit card number", "ccnum" + random, order.getCreditCardNumber());
        assertEquals("credit card type", "American Express", order.getCreditCardType());
        checkMockAddressValues(order.getDeliveryAddress(), random);
    }

    //==================================
    //=          Order Line            =
    //==================================

    public static OrderLine getMockOrderLineValues(final OrderLine orderLine, final Long random) {
        orderLine.setQuantity(random.intValue());
        return orderLine;
    }

    public static OrderLine getNewOrderLine() {
        Long random = getRandom();
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(random.intValue());
        orderLine.setItem(getNewItem());
        return orderLine;
    }

    public static void checkMockOrderLineValues(final OrderLine orderLine, final Long random) {
        assertNotNull("id", orderLine.getId());
        assertEquals("quantity", new Integer(random.intValue()), orderLine.getQuantity());
    }
}