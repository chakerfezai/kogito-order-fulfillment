import {computed, Injectable, signal} from '@angular/core';
import {Product} from "../_interface/product";
import {ShoppingCart} from "../_interface/shopping-cart";
import {OrderItem} from "../_interface/order-item";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems = signal<OrderItem[]>([]);

  totalAmount = computed(() =>
    this.cartItems().reduce((total, item) =>
      total + (item.product.price * Number(item.quantity)), 0)
  );

  private calculateTotalAmount(items: OrderItem[]): number {
    return items.reduce((total, item) => total + item.totalPrice * item.quantity, 0);
  }

  addItem(item: Product) {

    this.cartItems.update((currentCart) => {

      const existingItem = currentCart.find(
        (i) => i.product.productId === item.productId
      );

      if (existingItem) {
        // Increment quantity if item already exists
        existingItem.quantity += 1;
        existingItem.totalPrice += item.price;
      } else {
        currentCart?.push({
          product: item,
          quantity: 1,
          totalPrice: item.price
        });
      }
      return currentCart;
    });
  }

  removeItem(productId: String) {
    this.cartItems.update((currentCart) => {
      const item = currentCart.find((i) => i.product.productId === productId);
      if (item) {
        currentCart = currentCart.filter(
          (i) => i?.product?.productId !== productId
        );
      }
      return currentCart;
    });
  }
}
