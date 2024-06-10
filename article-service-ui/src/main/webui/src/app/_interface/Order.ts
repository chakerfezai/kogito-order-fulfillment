import {OrderItem} from "./order-item";
import {Customer} from "./customer";

export interface Order {
  orderId?: number
  status: OrderStatus;
  customer: Customer;
  items: OrderItem[];
  orderDate: Date;
}

export enum OrderStatus {
  PENDING,
  PROCESSING,
  SHIPPED,
  DELIVERED,
  CANCELLED
}
