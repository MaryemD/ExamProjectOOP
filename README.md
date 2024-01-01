# E-Commerce Console App
## Overview
This repository contains the code for a Java-based e-commerce console application.

## Main Features
#### User Authentication
- Create Account / Sign Up:
  The user can sign up as a customer and get access to all the customer features. The user will be asked to provide their username, email and a password
- Log in:
    The user can log in to an existing account by providing the right username and password.

#### Customer Features
A customer can:
- have access to all existing products (have them displayed and add them to cart).
- add items from existing products to cart.
- remove and update items in cart.
- view cart items.
- search for products by using keywords.
- filter through products by brand, type and price range.
- complete an order (if balance is sufficient).
- leave a review on a product.

#### Admin Features
An admin can:
- Create a product.
- Update and delete existing products.
- Display all products.


## Further Details
- An abstract class **Product** was created, containing all the main attributes of a product, which was then extended to two classes: **Computer** and **Phone**, with additional attributes for each.
- A **User** class, extended to **Admin** and **Customer**, each with the features previously mentioned.
- A **Card** class, containing all the basic attributes of a credit card and linked to a customer. (For the sake of simplicity, a card is initiated with a balance of 3500, which can always be changed).
- A **ShoppingCart** class, which is a list of **CartItem** objects, composed of products and desired quantity.
- An **Order** class, for when a customer decides to complete his order (linked to a method in the Customer class).
- A **PaymentProcessor** class, stimulating a simple payment process in which the validity of the cardNumber is verified along with the sufficiency of the card's balance.
- A **Review** class, also linked to a method in the customer class.
- A **Users** class, which is a list of all the users. (needed for the authentification).

## Usage
When running the Main class, you will be first asked to provide a UserName and a Password, in order to log in as an admin. Please use the Username "admin" and the password "adminpass".
You will then get access to all the actions an admin can perform, which you can choose from. You can perform all of the admins actions until you decide to log out.
Once you log out, you can choose between creating an account and logging in. In case you don't want to create an account, you can log in by using the username "customer" and the password "customerpass". 
If you chose to create an account, you'll be asked to log in to that account. Please make sure not to make any spelling mistakes.
After logging in, you'll be asked to enter your credit card number, and then you can choose which actions to perform, and you can stop once you decide to log out.
The program will stop running once the customer logs out.
