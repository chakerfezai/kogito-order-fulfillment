import {OrderItem} from "./order-item";

export interface ShoppingCart {
  items: OrderItem[];
  totalAmount: number;
}
