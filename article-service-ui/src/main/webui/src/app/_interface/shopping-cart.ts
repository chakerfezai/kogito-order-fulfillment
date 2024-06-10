import {Product} from "./product";
import {OrderItem} from "./order-item";

export interface ShoppingCart {
  items: OrderItem[];
  totalAmount: number;
}
